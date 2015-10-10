package postorder;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JButton;

public class Principal extends JFrame implements ActionListener {
	
	Image imagen, icono;
	JButton btnGraphviz, btnAgregar;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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

	/**
	 * Create the frame.
	 */
	public Principal() {
		this.setSize(1280, 700);
		this.setTitle("Matematica para Computacion 2 - Proyecto 2");
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		imagen = new ImageIcon(this.getClass().getResource("fondo.jpg")).getImage();
		this.getContentPane().add(panel);
		panel.setLayout(null);
		icono = new ImageIcon(this.getClass().getResource("icono.png")).getImage();
		this.setIconImage(icono);
		
		btnGraphviz = new JButton();
		btnGraphviz.setBorder(null);
		btnGraphviz.setOpaque(false);
		btnGraphviz.setContentAreaFilled(false);
		btnGraphviz.setIcon(new ImageIcon(Principal.class.getResource("/postorder/btngraph.png")));
		btnGraphviz.setBounds(189, 134, 153, 100);
		btnGraphviz.addActionListener(this);
		panel.add(btnGraphviz);
		
		btnAgregar = new JButton();
		btnAgregar.setIcon(new ImageIcon(Principal.class.getResource("/postorder/btnagregar.png")));
		btnAgregar.setBorder(null);
		btnAgregar.setOpaque(false);
		btnAgregar.setContentAreaFilled(false);
		btnAgregar.setBounds(201, 323, 153, 100);
		btnAgregar.addActionListener(this);
		panel.add(btnAgregar);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnGraphviz){
			try{
				Desktop.getDesktop().browse(new URI("http://www.graphviz.org/Download.php"));
			}catch(Exception ex){
				JOptionPane.showMessageDialog(null, ex,"Advertencia", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		if(e.getSource() == btnAgregar){
			Agregar a = new Agregar();
			this.setVisible(false);
			a.setVisible(true);
		}
		
	}
}
