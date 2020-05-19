package TOS.Conecta3.Controller;

import java.util.ArrayList;

import TOS.Conecta3.Model.Columna;

public class Tablero {
	private static Tablero mTablero;
	private Columna[] columnas;
	private int filas;
	
	
	private Tablero() {
		
	}

	public static Tablero getTablero() {
		if (mTablero == null)
			mTablero = new Tablero();

		return mTablero;
	}
	
	public void setDimensiones(int pFilas, int pColumnas) {
		columnas = new Columna[pColumnas];
		filas = pFilas;
		for(int i = 0; i<columnas.length; i++) {
			columnas[i] = new Columna(pFilas);
		}
	}
	
	public boolean anadirElemento(char pChar, int pColumna) {
		if(pColumna > 0 && pColumna <= columnas.length) {
			return columnas[pColumna-1].push(pChar);
		}
		return false;
	}
	
	public String toString() {
		String cadena = new String();
		
		for(int i = 0; i<filas; i++) {
			for(int j=0; j<columnas.length;j++) {
				if(columnas[j].get(i) != ' ')
					cadena += columnas[j].get(i) + " ";
				else
					cadena += "- ";
			}
			cadena += "\n";
		}
		return cadena;
	}
	
	public char getGanador() {
		for(int i = 0; i<filas; i++) {
			for(int j=0; j<columnas.length;j++) {
				
				if(columnas[j].get(i) != ' ') {
					if(buscar_dig_izq(i, j, columnas[j].get(i)) ||
					   buscar_dig_der(i,j,columnas[j].get(i)) ||
					   buscar_izq(i, j, columnas[j].get(i)) ||
					   buscar_abajo(i, j, columnas[j].get(i))) {
						
						return columnas[j].get(i);
					}
				}
			}
			
		}
		return ' ';
	}
	
	private boolean buscar_izq(int pFila, int pColumna, char pChar) {
		if(columnas.length-1 - pColumna < 3) {
			return false;
		}
		
		for(int i=1; i<=3; i++) {
			if(columnas[pColumna+i].get(pFila) != pChar) {
				return false;
			}
		}
		
		System.out.println("CONECTA 4 - IZQUIERDA");
		return true;
	}
	
	private boolean buscar_abajo(int pFila, int pColumna, char pChar) {
		if(filas-1 - pFila < 3) {
			return false;
		}
		
		for(int i=1; i<=3; i++) {
			if(columnas[pColumna].get(pFila+i) != pChar) {
				return false;
			}
		}
		System.out.println("CONECTA 4 - ABAJO");
		return true;
	}
	
	private boolean buscar_dig_izq(int pFila, int pColumna, char pChar) {
		if(filas-1 - pFila < 3 || columnas.length - 1 - pColumna < 3) {
			return false;
		}
		
		for(int i=1; i<=3; i++) {
			if(columnas[pColumna+i].get(pFila+i) != pChar) {
				return false;
			}
		}
		System.out.println("CONECTA 4 - DIAGONAL_IZQ");
		return true;
	}
	
	private boolean buscar_dig_der(int pFila, int pColumna, char pChar) {
		if(filas-1 - pFila < 3 || pColumna < 3) {
			return false;
		}
		
		for(int i=1; i<=3;i++) {
			if(columnas[pColumna-i].get(pFila+i) != pChar)
				return false;
		}
		
		System.out.println("CONECTA 4 - DIAGONAL_DER");
		return true;
	}
	
	public Columna getColumna(int pColumna) {
		return columnas[pColumna];
	}

	public char[][] getFichas() {
		char[][] fichas = new char[filas][columnas.length];
		
		for(int i = 0; i<filas; i++) {
			for(int j=0; j<columnas.length;j++) {
				fichas[i][j] = columnas[j].get(i);
			}
		}
		return fichas;
	}
	
	public Integer[] getColumnasLlenas() {
		ArrayList<Integer> columnasLlenas = new ArrayList<Integer>();
		
		for(int i = 0; i<columnas.length;i++) {
			if(columnas[i].full())
				columnasLlenas.add(i);
		}
		
		return columnasLlenas.toArray(new Integer[columnasLlenas.size()]);
	}
	
	public int getNumColumnas() {
		return columnas.length;
	}
	
	public boolean estaLleno() {
		for(int i = 0; i<columnas.length; i++) {
			if(!columnas[i].full())
				return false;
		}
		return true;
	}
	
	public int getMejorJugada(char pChar) {
		
		int mejorResul = -1;
		int mejorIndice = -1;
		
		for(int i = 0; i<columnas.length;i++) {
			if(!columnas[i].full()) {
				Columna[] nColumnas = getCopia();
				nColumnas[i].push(pChar);
				
				int resul = eval(nColumnas, pChar);
				if(resul > mejorResul) {
					mejorResul = resul;
					mejorIndice = i+1;
				}
			}

		}
		
		return mejorIndice;
	}
	
	private int eval(Columna[] tablero, char pChar) {
		
		int mejorRadio = -1;
		
		for(int fila = 0; fila<filas; fila++) {
			for(int col = 0; col<tablero.length; col++) {
				
				int radio = 0;
				
				boolean abajo = fila+radio<filas && tablero[col].get(fila+radio) == pChar;
				boolean dcha = col+radio<tablero.length && tablero[col+radio].get(fila) == pChar;
				boolean dig_dcha = fila+radio<filas && col+radio<tablero.length && tablero[col+radio].get(fila+radio) == pChar;
				boolean dig_izq = fila+radio<filas && col-radio>=0 && tablero[col-radio].get(fila+radio) == pChar;
				
				while(abajo || dcha || dig_dcha || dig_izq) {
					radio++;
					abajo = fila+radio<filas && tablero[col].get(fila+radio) == pChar;
					dcha = col+radio<tablero.length && tablero[col+radio].get(fila) == pChar;
					dig_dcha = fila+radio<filas && col+radio<tablero.length && tablero[col+radio].get(fila+radio) == pChar;
					dig_izq = fila+radio<filas && col-radio>=0 && tablero[col-radio].get(fila+radio) == pChar;
				}
				
				mejorRadio = Math.max(mejorRadio, radio-1);
				
			}
		}
		
		return mejorRadio;
	}
	
	private Columna[] getCopia() {
		Columna[] nColumnas = new Columna[columnas.length];
		for(int i = 0; i<nColumnas.length; i++) {
			nColumnas[i] = columnas[i].clone();
		}
		
		return nColumnas;
	}
	
	
	
	
}
