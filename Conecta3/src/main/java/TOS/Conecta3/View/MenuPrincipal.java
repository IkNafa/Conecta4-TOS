package TOS.Conecta3.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;

public class MenuPrincipal extends JFrame {


	private static final long serialVersionUID = 1838309628406358163L;
	private int numJugadores;
	private int modoBot;
	
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblLogo;
	private JLabel lblImgunjugador;
	private JLabel lblImgdosjugadores;
	private JLabel lblUnjugador;
	private JLabel lblDosjugadores;
	private JButton btnJugar;
	private JButton btnConfiguracion;
	private JLabel lblModoTonto;
	private JButton btnRanking;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
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
	public MenuPrincipal() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.CENTER);
		
		setTitle("Conecta 3 - La secuela");
		setResizable(false);
		setVisible(true);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getLblLogo());
			panel.add(getLblImgunjugador());
			panel.add(getLblImgdosjugadores());
			panel.add(getLblUnjugador());
			panel.add(getLblDosjugadores());
			panel.add(getBtnJugar());
			panel.add(getBtnConfiguracion());
			panel.add(getLblModoTonto());
			panel.add(getBtnRanking());
		}
		return panel;
	}
	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel("logo");
			lblLogo.setBounds(getWidth()/2-83, 0, 165, 165);
			lblLogo.setHorizontalAlignment((int) CENTER_ALIGNMENT);
			Image image = new ImageIcon(getClass().getResource("/logo.png")).getImage().getScaledInstance(165, 165, Image.SCALE_DEFAULT);
			lblLogo.setIcon(new ImageIcon(image));
		}
		return lblLogo;
	}
	private JLabel getLblImgunjugador() {
		if (lblImgunjugador == null) {
			lblImgunjugador = new JLabel();
			lblImgunjugador.setBounds(getWidth()/4-75, 189, 150, 150);
			lblImgunjugador.setHorizontalAlignment((int) CENTER_ALIGNMENT);
			
			Image image = new ImageIcon(getClass().getResource("/un_jugador.png")).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
			lblImgunjugador.setIcon(new ImageIcon(image));
			
			lblImgunjugador.addMouseListener(new MouseAdapter() {
				
				public void mouseExited(MouseEvent arg0) {
					if(numJugadores == 1) {
						lblImgunjugador.setBorder(new MatteBorder(1,1,1,1, Color.BLACK));
					}else {
						lblImgunjugador.setBorder(null);
					}
					
				}
				
				public void mouseEntered(MouseEvent arg0) {
					lblImgunjugador.setBorder(new MatteBorder(3,3,3,3, Color.BLACK));
					
				}
				
				public void mouseClicked(MouseEvent arg0) {
					numJugadores = 1;
					lblImgdosjugadores.setBorder(null);
					
				}
			});
		}
		return lblImgunjugador;
	}
	private JLabel getLblImgdosjugadores() {
		if (lblImgdosjugadores == null) {
			lblImgdosjugadores = new JLabel("imgDosJugadors");
			lblImgdosjugadores.setBounds(getWidth()*3/4-75, 189, 150, 150);
			
			Image image = new ImageIcon(getClass().getResource("/dos_jugadores.png")).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
			lblImgdosjugadores.setIcon(new ImageIcon(image));
			
			lblImgdosjugadores.addMouseListener(new MouseAdapter() {
				
				public void mouseExited(MouseEvent arg0) {
					if(numJugadores == 2) {
						lblImgdosjugadores.setBorder(new MatteBorder(1,1,1,1, Color.BLACK));
					}else {
						lblImgdosjugadores.setBorder(null);
					}
					
				}
				
				public void mouseEntered(MouseEvent arg0) {
					lblImgdosjugadores.setBorder(new MatteBorder(3,3,3,3, Color.BLACK));
					
				}
				
				public void mouseClicked(MouseEvent arg0) {
					numJugadores = 2;
					lblImgunjugador.setBorder(null);
					
				}
			});
		}
		return lblImgdosjugadores;
	}
	private JLabel getLblUnjugador() {
		if (lblUnjugador == null) {
			lblUnjugador = new JLabel("<html><center>1<br/>JUGADOR</center></html>");
			lblUnjugador.setFont(new Font("DialogInput", Font.BOLD, 15));
			lblUnjugador.setHorizontalAlignment((int) CENTER_ALIGNMENT);
			lblUnjugador.setBounds(getWidth()/4-75, 192 + lblImgunjugador.getHeight(), 150, 40);
		}
		return lblUnjugador;
	}
	private JLabel getLblDosjugadores() {
		if (lblDosjugadores == null) {
			lblDosjugadores = new JLabel("<html><center>2<br/>JUGADORES</center></html>");
			lblDosjugadores.setFont(new Font("DialogInput", Font.BOLD, 15));
			lblDosjugadores.setHorizontalAlignment((int) CENTER_ALIGNMENT);
			lblDosjugadores.setBounds(getWidth()*3/4-75, 192 + lblImgdosjugadores.getHeight(), 150, 40);
		}
		return lblDosjugadores;
	}
	private JButton getBtnJugar() {
		if (btnJugar == null) {
			btnJugar = new JButton("JUGAR");
			btnJugar.setBounds(getWidth()/2-75, 233, 150, 25);
			btnJugar.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					if(numJugadores == 0) {
						JOptionPane.showMessageDialog(MenuPrincipal.this, "Elige un modo de juego");
					}else {
						new VentanaTablero(numJugadores==2?0:modoBot+1);
						dispose();
					}
					
				}
			});
		}
		return btnJugar;
	}
	private JButton getBtnConfiguracion() {
		if (btnConfiguracion == null) {
			btnConfiguracion = new JButton("CONFIGURACION");
			btnConfiguracion.setBounds(getWidth()/2-75, 270, 150, 25);
			btnConfiguracion.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					new VentanaConf(MenuPrincipal.this,modoBot);
					
				}
			});
		}
		return btnConfiguracion;
	}
	private JLabel getLblModoTonto() {
		if (lblModoTonto == null) {
			lblModoTonto = new JLabel("Modo: "+ (modoBot==0?"TONTO":"LISTO"));
			lblModoTonto.setFont(new Font("DialogInput", Font.BOLD, 14));
			lblModoTonto.setBounds(12, 406, 100, 15);
		}
		return lblModoTonto;
	}
	private JButton getBtnRanking() {
		if (btnRanking == null) {
			btnRanking = new JButton("RANKING");
			btnRanking.setBounds(581, 401, 117, 25);
			btnRanking.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					new VentanaRanking(MenuPrincipal.this);
					
				}
			});
		}
		return btnRanking;
	}
	
	public void setModoBot(int pModoBot) {
		modoBot = pModoBot;
		lblModoTonto.setText("Modo: "+ (modoBot==0?"TONTO":"LISTO"));
	}
}
