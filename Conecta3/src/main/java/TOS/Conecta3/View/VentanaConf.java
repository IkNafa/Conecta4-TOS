package TOS.Conecta3.View;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import TOS.Conecta3.Controller.Tablero;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.Color;

public class VentanaConf extends JDialog {
	
	private static final int COLUMNAS = 9;
	private static final int FILAS = 6;
	private static final char COLOR_J1 = 'r';
	private static final char COLOR_J2 = 'v';
	private int modoBot;
	private int turno;
	
	private JPanel panelJugadores;
	private JPanel panel;
	private JLabel lblJ_1;
	private JLabel lblJ_2;
	private JLabel lblTurno_1;
	private JLabel lblTurno_2;
	private JLabel[][] lblCasillas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConf dialog = new VentanaConf(0);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public VentanaConf(int pModoBot) {
		
		modoBot = pModoBot;
		
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
			lblTurno_1 = new JLabel("turno_1");
			lblTurno_1.setBounds(getWidth()/5-12, 10, 25, 25);
			
			Image image = new ImageIcon(getClass().getResource("/turno.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
			lblTurno_1.setIcon(new ImageIcon(image));
		}
		return lblTurno_1;
	}
	private JLabel getLblTurno_2() {
		if (lblTurno_2 == null) {
			lblTurno_2 = new JLabel("turno_2");
			lblTurno_2.setBounds(getWidth()*4/5-12, 10, 25, 25);
			
			Image image = new ImageIcon(getClass().getResource("/turno.png")).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
			lblTurno_2.setIcon(new ImageIcon(image));
		}
		return lblTurno_2;
	}
	
	private void crearFlechas(JPanel pPanel) {
		
		for(int i = 0; i<COLUMNAS;i++) {
			final JLabel lblFlecha = new JLabel();
			int x = (getWidth() - 75*COLUMNAS)/2 + 75*i;
			lblFlecha.setBounds(x, 0, 50, 50);
			
			final Image image = new ImageIcon(getClass().getResource("/flecha.png")).getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT);
			lblFlecha.setIcon(new ImageIcon(image));
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
				
				pPanel.add(lblCasillas[fila][i]);
			}
		}

	}
	
}
