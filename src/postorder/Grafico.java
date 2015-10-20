package postorder;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Stack;

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

	Archivo archivo = new Archivo();
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
	LinkedList<String> lista_nodos = new LinkedList<String>();
	LinkedList<String> lista_relacion = new LinkedList<String>();

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
		int indice = 0;
		String lexema="";
		char token;
		try{
		while(fin != true){
			token = cadena.charAt(indice);
				if(token == ',' || token == '{'){
					//SI ES "," O "{" SOLO SEGUIMOS AVANZANDO
					indice++;
				}else if(Character.isDigit(token)){
					/**
					 * SI ES NUMERO PUEDE TERNER UNO O MAS DIGITOS.
					 * SI EL SIGUIENTE ES DIGITO ENTONCES, EN EL QUE ESTAMOS LO ALMACENAMOS EN "LEXEMA" Y AVANZAMOS EL INDICE.
					 * SI NO LO AGREGAMOS A LA LISTA.
					 */
					
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
					
				}else if( essimbolo(token) ){
					/**
					 * SI EL SIMBOLO ES "-" PUEDE SER UN SIGNO O EL INICIO DE UN NUMERO NEGATIVO.
					 * ENTONCES VERIFICAMOS SI EL SIGUIENTE ES UN DIGITO O NO.
					 */
					
					char tmp = cadena.charAt(indice+1);
					if(token == '-' && Character.isDigit(tmp)){
						lexema = Character.toString(token);
						indice++;
					}else{
						indice++;
						lista_cadena.add(Character.toString(token));
					}
					
				}else if(token == '}'){
					fin = true;
				}
		}
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "bug");
		}
		
		for(int i=0; i<lista_cadena.size(); i++){
			String nodo = "node"+i+" [label=\""+lista_cadena.get(i)+"\"];";
			lista_nodos.add(nodo);
			String rela = lista_cadena.get(i) + "%" + i;
			lista_relacion.add(rela);
			//System.out.println(nodo +"\n" + rela);
		}
		Relaciones(lista_relacion, lista_nodos);
		
	}
	
	private void Relaciones(LinkedList<String> relacion, LinkedList<String> nodos){
		
		Stack<String> Postfija = new Stack<String>();
		String rela_derecha = "", rela_izquierda = "", relacion_grafo="";
		
		for(int i=0; i<relacion.size(); i++){
			
			String actual[] = relacion.get(i).split("%"); //SEPARAMOS DATO%POSICION
			String actual_ = relacion.get(i); //TENEMOS DATO%POSICION
			
			if( esnumero(actual[0]) ){ //VERIFICAMOS SI ES UN NUMERO
				
				Postfija.push( actual_ ); //LO METEMOS A LA PILA.
				
			}else{ //SI NO ES UN SIMBOLO
				
				String raiz = actual[1];
				String derecha[] = Postfija.pop().split("%");
				String izquierda[] = Postfija.pop().split("%");
				rela_izquierda = "\"node" + raiz + "\":f1 -> \"node" + izquierda[1] + "\":f1;" + "\n";
				rela_derecha = "\"node" + raiz + "\":f1 -> \"node" + derecha[1] + "\":f1;" + "\n";
				relacion_grafo += rela_izquierda + rela_derecha;
				Postfija.push(actual_);
				
			}
			
		}//FIN FOR
		System.out.println(relacion_grafo);
		archivo.CrearGRAPHVIZ(relacion_grafo, nodos);
	}
	
	private boolean esnumero(String dato){
        try{
            Integer.parseInt(dato);
        }catch(NumberFormatException nfe){
            return false;
        }
        return true;
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
