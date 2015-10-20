package postorder;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.PrintWriter;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Agregar extends JFrame implements ActionListener {

	Image imagen, icono;
	public static String ruta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agregar frame = new Agregar();
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
	public static JTextField txtruta;
	JButton btnExaminar, btnSiguiente;
	

	/**
	 * Create the frame.
	 */
	public Agregar() {
		this.setSize(1280,720);
		this.setTitle("Matematica para Computacion 2 - Proyecto 2");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		imagen = new ImageIcon(this.getClass().getResource("fondo2.jpg")).getImage();
		getContentPane().setLayout(null);
		panel.setBounds(0, 0, 1274, 692);
		this.getContentPane().add(panel);
		panel.setLayout(null);
		
		btnExaminar = new JButton("Examinar");
		btnExaminar.addActionListener(this);
		btnExaminar.setBounds(284, 525, 89, 23);
		panel.add(btnExaminar);
		
		txtruta = new JTextField();
		txtruta.setEditable(false);
		txtruta.setBounds(383, 526, 841, 20);
		panel.add(txtruta);
		txtruta.setColumns(10);
		
		btnSiguiente = new JButton();
		btnSiguiente.setContentAreaFilled(false);
		btnSiguiente.setBorder(null);
		btnSiguiente.setOpaque(false);
		btnSiguiente.setIcon(new ImageIcon(Agregar.class.getResource("/postorder/siguiente.png")));
		btnSiguiente.setBounds(669, 569, 153, 100);
		btnSiguiente.addActionListener(this);
		btnSiguiente.setEnabled(false);
		panel.add(btnSiguiente);
		
		JLabel lblSiguiente = new JLabel("Siguiente:");
		lblSiguiente.setBounds(583, 612, 76, 14);
		panel.add(lblSiguiente);
		icono = new ImageIcon(this.getClass().getResource("icono.png")).getImage();
		this.setIconImage(icono);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == btnExaminar){
			JFileChooser fc = new JFileChooser();
			fc.setDialogTitle("Busca el archivo \"\\dot.exe\"");
			 int s = fc.showSaveDialog(null);
			 try{
		            if (s == JFileChooser.APPROVE_OPTION){
		                File JArchivo = fc.getSelectedFile();
		                ruta = JArchivo.getAbsolutePath();
		                
		                if( !(ruta.endsWith("dot.exe")) ){
		                	
		                    JOptionPane.showMessageDialog(null, "Debes seleccionar el archivo \"dot.exe\"","Advertencia", JOptionPane.WARNING_MESSAGE);
		                    
		                }else{
		                	txtruta.setText(ruta);
		                	btnSiguiente.setEnabled(true);
		                }
		            }
		        }catch (Exception ex){
		            JOptionPane.showMessageDialog(null,"Si no seleccionas el archivo \"dot.exe\" no podras continuar", "Notificacion", JOptionPane.INFORMATION_MESSAGE);
		        }
		}
		
		if(e.getSource() == btnSiguiente){
			Grafico g = new Grafico();
			this.setVisible(false);
			g.setVisible(true);
		}
		
	}
}
