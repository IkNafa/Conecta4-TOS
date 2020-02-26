package TOS.Conecta4.Model;

public class Columna {

	private char[] elementos;
	
	public Columna(int pFilas) {
		elementos = new char[pFilas];
		for(int i = 0; i<elementos.length;i++) {
			elementos[i] = ' ';
		}
	}
	
	private int elementosEnColumna() {
		int num = 0;
		for (int i = 0; i < elementos.length; i++) {
			if(elementos[i] != ' ') {
				num++;
			}
		}
		return num;
	}
	
	public boolean push(char pC) {
		int i = elementos.length - elementosEnColumna() - 1;
		if(i>=0) {
			elementos[i] = pC;
			return true;
		}
		
		return false;
	}
	
	public char get(int i) {
		return elementos[i];
	}
	
	public boolean full() {
		return elementosEnColumna() == elementos.length;
	}
	
	@Override
	public String toString() {
		String cadena = new String();
		for(int i = 0; i<elementos.length;i++) {
			cadena += "|" + elementos[i] + "| \n";
		}
		
		return cadena;
	}
	
}
