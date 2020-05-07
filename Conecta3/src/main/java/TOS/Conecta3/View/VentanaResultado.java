package TOS.Conecta3.View;


import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import TOS.Conecta3.Controller.GestorDB;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;

public class VentanaResultado extends JDialog {
	
	private static final long serialVersionUID = -5943743542183628642L;
	private String msg;
	private boolean guardar;
	private long puntuacion;
	private int dificultad;
	
	private JPanel panel;
	private JLabel lblLogo;
	private JLabel lblMsg;
	private JButton btnGuardar;
	private JButton btnSalir;

	public VentanaResultado(JFrame pFrame, String pMsg, boolean pGuardar, long pPuntuacion, int pDificultad) {
		super(pFrame,ModalityType.APPLICATION_MODAL);
		
		msg = pMsg;
		guardar = pGuardar;
		puntuacion = pPuntuacion;
		dificultad = pDificultad;
		
		initialize();
	}
	private void initialize() {
		setBounds(100, 100, 500, 300);
		getContentPane().add(getPanel(), BorderLayout.CENTER);
		setTitle("Resultado");
		setVisible(true);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(null);
			panel.add(getLblLogo());
			panel.add(getLblMsg());
			panel.add(getBtnGuardar());
			panel.add(getBtnSalir());
		}
		return panel;
	}
	private JLabel getLblLogo() {
		if (lblLogo == null) {
			lblLogo = new JLabel();
			lblLogo.setBounds(200, 10, 100, 100);
			Image image = null;
			if(guardar && msg.contains("GANADO"))
				image = new ImageIcon(getClass().getResource("/win.gif")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
			else
				image = new ImageIcon(getClass().getResource("/logo.png")).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
			lblLogo.setIcon(new ImageIcon(image));
		}
		return lblLogo;
	}
	private JLabel getLblMsg() {
		if (lblMsg == null) {
			lblMsg = new JLabel(msg);
			lblMsg.setFont(new Font("DialogInput", Font.BOLD, 18));
			lblMsg.setHorizontalAlignment((int) CENTER_ALIGNMENT);
			lblMsg.setBounds(getWidth()/2-125, getHeight()/2-7, 250, 20);
		}
		return lblMsg;
	}
	private JButton getBtnGuardar() {
		if (btnGuardar == null) {
			btnGuardar = new JButton("GUARDAR");
			btnGuardar.setEnabled(guardar);
			btnGuardar.setBounds(getWidth()/4-59, 206, 118, 25);
			if(guardar) {
				btnGuardar.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						String nombre;
						do {
							nombre = JOptionPane.showInputDialog(VentanaResultado.this, "Introduce tu nombre:");
						}while(nombre == null || nombre.isEmpty());
						
						GestorDB.getGestorDB().anadirRegistro(nombre, puntuacion, dificultad);
						dispose();
						
						
					}
				});
			}
		}
		return btnGuardar;
	}
	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("SALIR");
			btnSalir.setBounds(getWidth()*3/4-59, 206, 118, 25);
			btnSalir.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					
				}
			});
		}
		return btnSalir;
	}
}
