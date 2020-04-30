package TOS.Conecta3.Controller;

import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestorDB {

	private static GestorDB mGestorDB;
	
	private Connection con;
	private Statement statement;
	
	private GestorDB() {
		try {
			con = DriverManager.getConnection("jdbc:sqlite:conecta4.db");
			statement = con.createStatement();
			statement.execute("CREATE TABLE IF NOT EXISTS Ranking (nombre VARCHAR (50),fecha DATETIME PRIMARY KEY, puntuacion BIGINT, dificultad VARCHAR (50));");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static GestorDB getGestorDB() {
		if(mGestorDB == null)
			mGestorDB = new GestorDB();
		return mGestorDB;
	}
	
	public void anadirRegistro(String nombre, long puntuacion, int dificultad) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fecha = sdf.format(new Date());
		
		String dif = dificultad==1?"TONTO":"LISTO";
		
		try {
			statement.executeUpdate("INSERT INTO Ranking VALUES('"+nombre+"','"+fecha+"',"+puntuacion+",'"+dif+"')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public String[][] getDatos(int modoBot){
		
		try {
			ArrayList<String[]> listaDatos = new ArrayList<String[]>();
			ResultSet rs = statement.executeQuery("SELECT * FROM Ranking WHERE dificultad = '"+(modoBot==0?"TONTO":"LISTO")+"' order by puntuacion");
			while(rs.next()) {
				String[] datos = new String[3];
				datos[0] = rs.getString(1);
				datos[1] = String.valueOf(rs.getLong(3));
				datos[2] = rs.getString(4);
				
				listaDatos.add(datos);
				
			}
			
			return listaDatos.toArray(new String[listaDatos.size()][3]);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
