package TOS.Conecta3.Model;

public class Jugador {
	
	private String nombre;
	private char ficha;
	
	public Jugador(String pNombre, char pFicha) {
		nombre = pNombre;
		ficha = pFicha;
	}
	
	public void lanzarFicha(int pColumna) {
		Tablero.getTablero().anadirElemento(ficha, pColumna);
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public char getFicha() {
		return ficha;
	}
	
}
