package TOS.Conecta3.View;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import TOS.Conecta3.Controller.GestorDB;

public class VentanaRanking extends JDialog {
	private JScrollPane scrollPane;
	private JTable table;
	private int modoBot;
	private static final String[] NOMBRE_COLUMNAS = new String[] {"Nombre","Tiempo (ms)","Dificultad BOT"};


	/**
	 * Create the dialog.
	 */
	public VentanaRanking(JFrame jframe) {
		super(jframe,ModalityType.APPLICATION_MODAL);
		modoBot = 0;
		initialize();
	}
	private void initialize() {
		setBounds(100, 100, 450, 300);
		getContentPane().add(getScrollPane(), BorderLayout.CENTER);
		
		setTitle("Ranking");
		setVisible(true);
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable(GestorDB.getGestorDB().getDatos(modoBot),NOMBRE_COLUMNAS);
			table.getTableHeader().addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent arg0) {
					modoBot = modoBot==0?1:0;
					DefaultTableModel defaultTableModel = new DefaultTableModel(GestorDB.getGestorDB().getDatos(modoBot),NOMBRE_COLUMNAS);
					table.setModel(defaultTableModel);
					
				}
			});
		}
		return table;
	}
}
