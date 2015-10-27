package postorder;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Archivo {
	String ruta_maquina, ruta_;
	
	public void CrearGRAPHVIZ(String Contenido,LinkedList<String> nodos){
		
		ruta_maquina = Agregar.ruta.replace("\\","\\"+"\\" );
		ruta_ = ruta_maquina.replace("dot.exe", "");
		String nodo_ = "";
		for(int i=0; i<nodos.size(); i++){
			nodo_ += nodos.get(i).toString();
		}
		
		try {
			String textoGraphviz = "digraph G{ node [shape = record, height=.1];" + nodo_ + Contenido + "}";
			
			File Archivo_Graphviz = new File( ruta_, "nuevo.gv");
			FileWriter escritor = new FileWriter(Archivo_Graphviz);
			BufferedWriter bw = new BufferedWriter(escritor);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.write(textoGraphviz);
			
			pw.close();
			bw.close();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e,"Advertencia", JOptionPane.WARNING_MESSAGE);
			
		}
		
		try{
			String rutaDot = ruta_maquina;
			String archivoGraphviz = ruta_ +  "nuevo.gv";
			String archivoImagen = ruta_ + "grafo1.png";
			String tParam = "-Tpng";
		    String tOParam = "-o";
		    
		    String[] cmd = new String[5];
		      cmd[0] = rutaDot;
		      cmd[1] = tParam;
		      cmd[2] = archivoGraphviz;
		      cmd[3] = tOParam;
		      cmd[4] = archivoImagen;
		      
		      Runtime ruti = Runtime.getRuntime();
		      
		      ruti.exec(cmd).waitFor();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e,"Advertencia", JOptionPane.WARNING_MESSAGE);
		}
		
		ImageIcon icon = new ImageIcon(ruta_ + "grafo1.png"); 
		Icon icono = new ImageIcon(icon.getImage().getScaledInstance(Grafico.lblGrafo.getWidth(), Grafico.lblGrafo.getHeight(), Image.SCALE_DEFAULT));
		Grafico.lblGrafo.setIcon(icono);
	}

}
