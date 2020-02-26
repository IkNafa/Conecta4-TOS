package TOS.Conecta3.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import TOS.Conecta3.Model.GestorPartida;
import TOS.Conecta3.Model.Tablero;

public class VentanaTablero extends JFrame {

	private static final long serialVersionUID = -5489150745425531076L;
	private int filas, columnas;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblImagentablero;
	private JLabel[][] lblFichas;
	private JButton[] botones;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaTablero frame = new VentanaTablero(5,7);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaTablero(int pFilas, int pColumnas) {
		filas = pFilas;
		columnas = pColumnas;
		Tablero.getTablero().setDimensiones(pFilas, pColumnas);
		initComponents();
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.CENTER);
		setResizable(false);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getLblImagentablero());
			crearBotones();
			crearLblFichas();
		}
		return panel;
	}
	private JLabel getLblImagentablero() {
		if (lblImagentablero == null) {
			lblImagentablero = new JLabel("");
			lblImagentablero.setBounds(0, 0, 1280, 720);
			lblImagentablero.setIcon(new ImageIcon(getClass().getResource("/tablero.png")));
		}
		return lblImagentablero;
	}
	
	private void crearBotones() {
		botones = new JButton[columnas];
		for(int i = 0; i<columnas; i++) {
			JButton boton = new JButton("LANZAR");
			boton.setBounds(275 + 105*i, 15, 90, 25);
			final int n = i;
			boton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					anadirFicha(n+1);
					
				}
			});
			panel.add(boton);
			botones[i] = boton;
		}
	}
	
	private void crearLblFichas() {
		lblFichas = new JLabel[filas][columnas];
		for(int i = 0; i<filas; i++) {
			for(int j = 0; j<columnas; j++) {
				JLabel ficha = new JLabel();
				ficha.setBounds(291 + 105*j,109 + 93*i,68,71);
				panel.add(ficha);
				lblFichas[i][j] = ficha;
			}
		}
	}
	
	private void actualizarTablero() {
		char[][] fichas = Tablero.getTablero().getFichas();
		
		for(int i = 0; i<fichas.length; i++) {
			for(int j = 0; j<fichas[i].length; j++) {
				if(fichas[i][j] != ' ')
					lblFichas[i][j].setIcon(new ImageIcon(getClass().getResource("/ficha-" + fichas[i][j] + ".png")));
			}
		}
		
		Integer[] columnasLlenas = Tablero.getTablero().getColumnasLlenas();
		
		
		for(int i = 0; i<columnasLlenas.length;i++) {
			botones[columnasLlenas[i]].setEnabled(false);
		}
		
		char color = Tablero.getTablero().getGanador();
		if(Tablero.getTablero().estaLleno() || color != ' ') {
			finalizarPartida(color);
		}
	}
	
	private void anadirFicha(int pColumna) {
		
		GestorPartida.getGestorPartida().lanzarFichaJugador(pColumna);
		actualizarTablero();
		
		GestorPartida.getGestorPartida().lanzarFichaBot();
		actualizarTablero();
	}
	
	private void finalizarPartida(char pColor) {
		if(pColor == ' ') {
			JOptionPane.showMessageDialog(VentanaTablero.this, "¡¡NO HA GANADO NADIE!!");
		}else {
			JOptionPane.showMessageDialog(VentanaTablero.this, "¡¡CONECTA 4!!"
					+ "\n Enhorabuena: " + GestorPartida.getGestorPartida().getGanador(pColor));
		}
		this.dispose();	
	}
}
