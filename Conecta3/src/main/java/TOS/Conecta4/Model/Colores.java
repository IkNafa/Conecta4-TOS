package TOS.Conecta4.Model;

public enum Colores {
	VERDE('v'), ROJO('r');
	
	char color;
	private Colores(char c) {
		color = c;
	}
	
	public char getColor() {
		return color;
	}
}
