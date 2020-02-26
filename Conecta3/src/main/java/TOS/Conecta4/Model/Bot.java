package TOS.Conecta4.Model;

import java.util.Random;

public class Bot extends Jugador{
	
	public Bot(String pNombre, char pFicha) {
		super(pNombre,pFicha);
	}

	public void lanzarFicha() {
		Random r = new Random();
		int numColumnas = Tablero.getTablero().getNumColumnas();
		
		
		//Hasta que no sea True (es decir, hasta que lo haya lanzado) no para)
		while(!Tablero.getTablero().anadirElemento(getFicha(), r.nextInt(numColumnas+1))) {}
	}
	
	
}
