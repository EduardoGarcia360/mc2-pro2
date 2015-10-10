package postorder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Grafico extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grafico frame = new Grafico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public JPanel panel = new JPanel(){
		public void paintComponent(Graphics g){
			g.drawImage(imagen, 0, 0,getWidth(), getHeight(), null);
		}
	};
	
	Image imagen, icono;

	/**
	 * Create the frame.
	 */
	public Grafico() {
		this.setSize(1280, 720);
		this.setTitle("Matematica para Computacion 2 - Proyecto 2");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		imagen = new ImageIcon(this.getClass().getResource("fondo3.jpg")).getImage();
		this.getContentPane().add(panel);
		icono = new ImageIcon(this.getClass().getResource("icono.png")).getImage();
		this.setIconImage(icono);
		
		this.getContentPane().add(panel);
		panel.setLayout(null);
		this.setVisible(true);
		
	}

}
