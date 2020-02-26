package TOS.Conecta3.Model;

import java.util.ArrayList;

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
	
	
	
	
}
