package TOS.Conecta4.Model;

import java.net.Socket;

public class GestorPartidaSocket {
	private static GestorPartidaSocket mGestorPartidaSocket;

	private GestorPartidaSocket() {
		
	}

	public static GestorPartidaSocket getGestorPartidaSocket() {
		if (mGestorPartidaSocket == null)
			mGestorPartidaSocket = new GestorPartidaSocket();

		return mGestorPartidaSocket;
	}
	
	public void setJugador(String nombre) {
		
	}
}
