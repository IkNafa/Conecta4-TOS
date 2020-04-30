package TOS.Conecta3.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import TOS.Conecta3.Controller.Tablero;

public class VentanaTablero extends JFrame {

	
	private static final long serialVersionUID = -7605383656197154375L;
	private static final int COLUMNAS = 9;
	private static final int FILAS = 6;
	private static final char COLOR_J1 = 'r';
	private static final char COLOR_J2 = 'v';
	private int modoBot;
	private int turno;
	private long tiempoInicial;
	
	private JPanel panelJugadores;
	private JPanel panel;
	private JLabel lblJ_1;
	private JLabel lblJ_2;
	private JLabel lblTurno_1;
	private JLabel lblTurno_2;
	private JLabel[][] lblCasillas;


	/**
	 * Create the frame.
	 */
	public VentanaTablero(int pModoBot) {
		modoBot = pModoBot;
		tiempoInicial = System.currentTimeMillis();
		Tablero.getTablero().setDimensiones(FILAS, COLUMNAS);
		initialize();
	}
	private void initialize() {
		setBounds(100, 100, 1280, 720);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1280};
		gridBagLayout.rowHeights = new int[]{getHeight()*20/100, getHeight()*80/100};
		gridBagLayout.columnWeights = new double[]{0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		GridBagConstraints gbc_panelJugadores = new GridBagConstraints();
		gbc_panelJugadores.fill = GridBagConstraints.BOTH;
		gbc_panelJugadores.insets = new Insets(0, 0, 5, 0);
		gbc_panelJugadores.gridx = 0;
		gbc_panelJugadores.gridy = 0;
		getContentPane().add(getPanelJugadores(), gbc_panelJugadores);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		getContentPane().add(getPanel(), gbc_panel);
		
		setTitle("Conecta 3 - La secuela");
		setResizable(false);
		setVisible(true);
	}

	private JPanel getPanelJugadores() {
		if (panelJugadores == null) {
			panelJugadores = new JPanel();
			panelJugadores.setLayout(null);
			panelJugadores.add(getLblJ_1());
			panelJugadores.add(getLblJ_2());
			panelJugadores.add(getLblTurno_1());
			panelJugadores.add(getLblTurno_2());
		}
		return panelJugadores;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			crearFlechas(panel);
			crearTablero(panel);
		}
		return panel;
	}
	private JLabel getLblJ_1() {
		if (lblJ_1 == null) {
			lblJ_1 = new JLabel("JUGADOR 1");
			lblJ_1.setForeground(Color.BLUE);
			lblJ_1.setFont(new Font("DialogInput", Font.BOLD, 20));
			lblJ_1.setBounds(getWidth()/5-75, 40, 150, 25);
			
			Image image = new ImageIcon(getClass().getResource("/account.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
			lblJ_1.setIcon(new ImageIcon(image));
		}
		return lblJ_1;
	}
	private JLabel getLblJ_2() {
		if (lblJ_2 == null) {
			lblJ_2 = new JLabel(modoBot==0?"JUGADOR 2":"BOT_TOS");
			lblJ_2.setForeground(Color.GREEN);
			lblJ_2.setFont(new Font("DialogInput", Font.BOLD, 20));
			lblJ_2.setBounds(getWidth()*4/5 - 75, 40, 150, 25);
			
			Image image = new ImageIcon(getClass().getResource(modoBot==0?"/account.png":"/bot.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
			lblJ_2.setIcon(new ImageIcon(image));
		}
		return lblJ_2;
	}
	private JLabel getLblTurno_1() {
		if (lblTurno_1 == null) {
			lblTurno_1 = new JLabel();
			lblTurno_1.setBounds(getWidth()/5-12, 10, 25, 25);
			
			Image image = new ImageIcon(getClass().getResource("/turno.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
			lblTurno_1.setIcon(new ImageIcon(image));
		}
		return lblTurno_1;
	}
	private JLabel getLblTurno_2() {
		if (lblTurno_2 == null) {
			lblTurno_2 = new JLabel();
			lblTurno_2.setBounds(getWidth()*4/5-12, 10, 25, 25);
		}
		return lblTurno_2;
	}
	
	private void crearFlechas(JPanel pPanel) {
		
		for(int i = 0; i<COLUMNAS;i++) {
			final JLabel lblFlecha = new JLabel();
			int x = (getWidth() - 75*COLUMNAS)/2 + 75*i;
			lblFlecha.setBounds(x, 0, 75, 50);
			lblFlecha.setHorizontalAlignment((int) CENTER_ALIGNMENT);
			final Image image = new ImageIcon(getClass().getResource("/flecha.png")).getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT);
			final int num = i;
			lblFlecha.addMouseListener(new MouseAdapter() {
				
				public void mouseExited(MouseEvent arg0) {
					lblFlecha.setIcon(null);
				}
				
				public void mouseEntered(MouseEvent arg0) {
					lblFlecha.setIcon(new ImageIcon(image));
				}
				
				public void mouseClicked(MouseEvent arg0) {
					
					Tablero.getTablero().anadirElemento(turno==0?COLOR_J1:COLOR_J2, num+1);
					actualizarTablero();
					cambiarTurno();
					
					if(!partidaFinalizada() && modoBot != 0) {
						lanzarBot();
					}
					
				}
			});
			
			pPanel.add(lblFlecha);
		}
		
	}
	
	private void crearTablero(JPanel pPanel) {
		lblCasillas = new JLabel[FILAS][COLUMNAS];
		
		for(int fila = 0; fila<FILAS; fila++) {
			for(int i = 0; i<COLUMNAS;i++) {
				lblCasillas[fila][i] = new JLabel();
				int x = (getWidth() - 75*COLUMNAS)/2 + 75*i;
				lblCasillas[fila][i].setBounds(x, 50 + 75*(fila), 75, 75);
				lblCasillas[fila][i].setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
				lblCasillas[fila][i].setHorizontalAlignment((int) CENTER_ALIGNMENT);
				
				pPanel.add(lblCasillas[fila][i]);
			}
		}

	}
	
	private void actualizarTablero() {
		char[][] fichas = Tablero.getTablero().getFichas();
		
		for(int i = 0; i<fichas.length; i++) {
			for(int j = 0; j<fichas[i].length; j++) {
				if(fichas[i][j] != ' ') {
					Image image = new ImageIcon(getClass().getResource("/ficha-"+fichas[i][j]+".png")).getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
					lblCasillas[i][j].setIcon(new ImageIcon(image));
				}
			}
		}
	}
	
	private boolean partidaFinalizada() {
		char c;
		if((c = Tablero.getTablero().getGanador()) != ' ' || Tablero.getTablero().estaLleno()) {
			
			long puntuacion = System.currentTimeMillis() - tiempoInicial;
			String msg = new String();
			boolean guardar=false;
			if(c == ' ') {
				msg = "¡HA HABIDO UN EMPATE!";
			}else {
				if(c == COLOR_J1) {
					if(modoBot == 0) {
						msg = "¡HA GANADO JUGADOR 1!";
					}else {
						msg = "¡HAS GANADO!";
						guardar = true;
					}
				}else {
					if(modoBot == 0) {
						msg = "¡HA GANADO JUGADOR 2!";
					}else {
						msg = "¡HAS PERDIDO!";
					}
				}
				
				new VentanaResultado(VentanaTablero.this, msg, guardar,puntuacion,modoBot);
				new MenuPrincipal();
				dispose();
			}
			
			
			
			return true;
		}
		
		return false;
	}
	
	private void lanzarBot() {
		
		Random r = new Random();
		
		while(!Tablero.getTablero().anadirElemento(COLOR_J2, r.nextInt(COLUMNAS)+1));
		
		actualizarTablero();
		cambiarTurno();
		partidaFinalizada();
	}
	
	private void cambiarTurno() {
		turno = turno == 0?1:0;
		
		Image image = new ImageIcon(getClass().getResource("/turno.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
		if(turno == 0) {
			lblTurno_1.setIcon(new ImageIcon(image));
			lblTurno_2.setIcon(null);
		}else {
			lblTurno_1.setIcon(null);
			lblTurno_2.setIcon(new ImageIcon(image));
		}

	}
}
