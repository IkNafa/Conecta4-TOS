package TOS.Conecta3.View;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class VentanaConf extends JDialog {
	
	private MenuPrincipal menuPrincipal;
	private int modoBot;
	
	private JPanel panel;
	private JLabel lblFoto;
	private JLabel lblModoBot;
	private JRadioButton rdbtnModoTonto;
	private JRadioButton rdbtnModoListo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnSalir;


	/**
	 * Create the dialog.
	 */
	public VentanaConf(JFrame pJFrame, int pModoBot) {
		super(pJFrame, ModalityType.APPLICATION_MODAL);
		
		modoBot = pModoBot;
		
		if(pJFrame instanceof MenuPrincipal)
			menuPrincipal = (MenuPrincipal) pJFrame;
		
		
		initialize();
	}
	private void initialize() {
		setBounds(100, 100, 250, 350);
		getContentPane().add(getPanel(), BorderLayout.CENTER);
		setVisible(true);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getLblFoto());
			panel.add(getLblModoBot());
			panel.add(getRdbtnModoTonto());
			panel.add(getRdbtnModoListo());
			panel.add(getBtnSalir());
		}
		return panel;
	}
	private JLabel getLblFoto() {
		if (lblFoto == null) {
			lblFoto = new JLabel("foto");
			lblFoto.setBounds(getWidth()/2-38, 25, 75, 75);
			
			Image image = new ImageIcon(getClass().getResource("/bot.png")).getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT);
			lblFoto.setIcon(new ImageIcon(image));
		}
		return lblFoto;
	}
	private JLabel getLblModoBot() {
		if (lblModoBot == null) {
			lblModoBot = new JLabel("MODO BOT:");
			lblModoBot.setFont(new Font("Monospaced", Font.BOLD, 15));
			lblModoBot.setBounds(getWidth()/3-50, 125, 100, 15);
		}
		return lblModoBot;
	}
	private JRadioButton getRdbtnModoTonto() {
		if (rdbtnModoTonto == null) {
			rdbtnModoTonto = new JRadioButton("Modo TONTO");
			rdbtnModoTonto.setSelected(modoBot==0);
			buttonGroup.add(rdbtnModoTonto);
			rdbtnModoTonto.setBounds(getWidth()/2-75, 145, 150, 25);
			rdbtnModoTonto.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					if(menuPrincipal != null && rdbtnModoTonto.isSelected()) {
						menuPrincipal.setModoBot(0);
					}
					
				}
			});
		}
		return rdbtnModoTonto;
	}
	private JRadioButton getRdbtnModoListo() {
		if (rdbtnModoListo == null) {
			rdbtnModoListo = new JRadioButton("Modo LISTO");
			rdbtnModoListo.setSelected(modoBot!=0);
			buttonGroup.add(rdbtnModoListo);
			rdbtnModoListo.setBounds(getWidth()/2-75, 170, 150, 25);
			rdbtnModoListo.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					if(menuPrincipal != null && rdbtnModoListo.isSelected()) {
						menuPrincipal.setModoBot(1);
					}
					
				}
			});
		}
		return rdbtnModoListo;
	}
	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("SALIR");
			btnSalir.setBounds(getWidth()/2-60, 260, 120, 25);
			btnSalir.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					
				}
			});
		}
		return btnSalir;
	}
}
