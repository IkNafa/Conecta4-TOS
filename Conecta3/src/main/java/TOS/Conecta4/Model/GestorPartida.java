package TOS.Conecta4.Model;

import java.util.Random;

public class GestorPartida {
	private static GestorPartida mGestorPartida;
	private Jugador jugador;
	private Bot bot;
	
	private GestorPartida() {
		jugador = new Jugador("Iker",'v');
		bot = new Bot("Bot-0.1", generarColorBot(jugador.getFicha()));
	}

	public static GestorPartida getGestorPartida() {
		if (mGestorPartida == null)
			mGestorPartida = new GestorPartida();

		return mGestorPartida;
	}
	
	public void lanzarFichaJugador(int pColumna) {
		jugador.lanzarFicha(pColumna);
	}
	
	public char generarColorBot(char colorJugador) {
		char[] colores = new char[Colores.values().length];
		for(int i = 0; i<colores.length;i++) {
			colores[i] = Colores.values()[i].getColor();
		}
		
		Random r = new Random();
		
		char colorBot = colores[r.nextInt(colores.length)];
		
		while(colorBot == colorJugador) {
			colorBot = colores[r.nextInt(colores.length)];
		}
		
		return colorBot;
	}
	
	public void lanzarFichaBot() {
		bot.lanzarFicha();
	}
	
	public String getGanador(char pColor) {
		if(jugador.getFicha() == pColor) {
			return jugador.getNombre();
		}
		return bot.getNombre();
	}
}
