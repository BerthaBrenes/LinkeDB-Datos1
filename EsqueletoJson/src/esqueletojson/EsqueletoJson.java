/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esqueletojson;
import java.util.Scanner;
import javax.swing.JOptionPane;


/**
 *
 * @author berta
 */
public class EsqueletoJson {

    /**
     * @param args the command line arguments
     */
    
    public static int valorP;
    public static void setP(int dato){
        valorP = dato;
    }
    public static int getP(){
        return valorP;
    }
    
    public static void main(String[] args) {
        
        JOptionPane.showMessageDialog(null,"Bienvenidos a la bases de datos linkedb");
        // TODO code application logic here
        String documento = JOptionPane.showInputDialog("Cual es el nombre del documento que desea crear");
        System.out.println("Documento: "+documento+"\n");
        String nombre = JOptionPane.showInputDialog("Cual va a ser el nombre de su atributo");
        System.out.println("Nombre: "+nombre+"\n");
        String tipo = JOptionPane.showInputDialog("Que tipo va a ser este atributo");
        System.out.println("Tipo: "+tipo+"\n");
        
        int valorF = JOptionPane.showConfirmDialog (null, "Quiere usted que este atibuto sea una llave foranea","Consulta", JOptionPane.YES_NO_OPTION);
        
        if(valorF  == JOptionPane.YES_OPTION) {
        System.out.println(valorF+"\n");
        }
        else {
        
            int valorP = JOptionPane.showConfirmDialog (null, "Quiere usted que este atibuto sea una llave primaria","Consulta", JOptionPane.YES_NO_OPTION);
            if(valorP  == JOptionPane.YES_OPTION) {
                System.out.println(valorP+"\n");
            
            }
            else {
                System.out.println(valorP+"\n");
            }
            setP(valorP);
        
        }
        
        int requerimiento = JOptionPane.showConfirmDialog (null, "Este atributo va a ser requerido","Consulta", JOptionPane.YES_NO_OPTION);
            if(requerimiento  == JOptionPane.YES_OPTION) {
                System.out.println("Requerimiento: "+requerimiento+"\n");
            }
            else {
                System.out.println("Requerimiento: "+requerimiento+"\n");
            }
        
        String defecto = JOptionPane.showInputDialog("Quiere asignarle un valor por defecto");
        System.out.println("Valor por defecto:"+defecto+"\n");
        
  
    Metadata jsonPrincipal = new Metadata(documento,nombre,tipo,valorF,getP(),requerimiento,defecto);
        
        
   
        
    }
    
}
