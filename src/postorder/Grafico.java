package postorder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class Grafico extends JFrame  implements ActionListener {

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
	JTextField txtcadena;
	JButton btnGenerar;
	private JLabel lblGenerarGrafo;

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
		
		JLabel lblIngresa = new JLabel("Ingresa la lista de numeros");
		lblIngresa.setBounds(50, 53, 155, 14);
		panel.add(lblIngresa);
		
		txtcadena = new JTextField();
		txtcadena.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtcadena.setBounds(50, 78, 677, 38);
		panel.add(txtcadena);
		txtcadena.setColumns(10);
		
		btnGenerar = new JButton();
		btnGenerar.setContentAreaFilled(false);
		btnGenerar.setBorder(null);
		btnGenerar.setOpaque(false);
		btnGenerar.setIcon(new ImageIcon(Grafico.class.getResource("/postorder/generar.png")));
		btnGenerar.setBounds(890, 23, 153, 100);
		btnGenerar.addActionListener(this);
		panel.add(btnGenerar);
		
		lblGenerarGrafo = new JLabel("Generar Grafo");
		lblGenerarGrafo.setHorizontalAlignment(JLabel.CENTER);
		lblGenerarGrafo.setBounds(890, 137, 153, 14);
		panel.add(lblGenerarGrafo);
		this.setVisible(true);
		
	}
	
	private void Analizar(String cadena){
		
		LinkedList<String> lista_cadena = new LinkedList<String>();
		
		boolean fin = false;
		int indice = 0, estado = 0;
		String lexema="";
		char token;
		try{
		while(fin != true){
			token = cadena.charAt(indice);
			switch (estado){
			case 0:
				if(token == ',' || token == '{'){
					indice++;
				}else if(Character.isDigit(token)){
					
					char tmp = cadena.charAt(indice+1);
					if(Character.isDigit(tmp)){
						lexema += Character.toString(token);
						indice++;
					}else{
						indice++;
						lexema += Character.toString(token);
						lista_cadena.add(lexema);
						lexema="";
					}
					
					
				}else if(Character.isLetter(token)){
					indice++;
					lista_cadena.add(Character.toString(token));
				}else if( essimbolo(token) ){
					indice++;
					lista_cadena.add(Character.toString(token));
				}else if(token == '}'){
					fin = true;
				}
				break;
			}
		}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "bug");
		}
		
		for(int i=0; i<lista_cadena.size(); i++){
			System.out.println("posicion "+i+"-"+lista_cadena.get(i));
		}
	}
	
	private boolean essimbolo(char token){
		boolean aprovacion = false;
		char[] simbolos = {'+','-','/','*'};
		for(int i=0; i<simbolos.length;i++){
			char actual = simbolos[i];
			if(actual == token){
				aprovacion = true;
			}
		}
		return aprovacion;
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == btnGenerar){
			String linea = txtcadena.getText().toString();
			Analizar(linea);
		}
	}

}
