/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esqueletojson;
import Logica.Lista;
import Logica.ListaCir;
import Logica.ListaDo;
import java.util.Scanner;
import javax.swing.JOptionPane;


/**
 *
 * @author berta
 */
public class Proyecto2 {

    /**
     */
    
    public static int valorP;
    public static void setP(int dato){
        valorP = dato;
    }
    
    public static int getP(){
        return valorP;
    }
    
    public static void main(String[] args) {
        /**
        System.out.println("vamos a crear una carpeta");
        String documento = JOptionPane.showInputDialog("Cual es el nombre del documento que desea crear");
        System.out.println("Documento: "+documento+"\n");
        JsonStore carpeta = JsonStore.GetInstance();
        carpeta.nuevoNodo(documento);
        Metadata metadata = new Metadata(documento);
        String Json1 = JOptionPane.showInputDialog("agregar un json");
        metadata.AgregarJson(Json1);
        String Json2 = JOptionPane.showInputDialog("agregar otro json");
        metadata.AgregarJson(Json2);
        
        JsonStore carpeta = JsonStore.GetInstance();
        String documento = "segundo";
        Metadata metadata = new Metadata(documento);
        metadata.EliminarJson("json1");*/
        
        ListaCir<Integer> lista = new ListaCir<Integer>();
        int opcion =0, el,eli;
        do {
            try{
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null,"1. Agregar un Elemento al Inicio de la lista\n 2.Mostrar los Datos de la lista\n 3.Buscar y eliminar dato\n 4. salir", "Menu de Opciones",3));
                switch(opcion){
                    case 1:   
                    el = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresar el Elemento:","Insertando al inicio",3));
                    lista.Insertar(el);
                    break;
                    case 2:
                        lista.Imprimir();
                        break;
                    case 3:
                        eli = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresar elemento a buscar","Eliminar Dato",3));
                        lista.Existe(eli);
                        break;
                    case 4 :
                        break;
                }
            }catch (Exception e){   
            }
            
        } while(opcion != 4);
        /**
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
        
        
   
     */   
    }
    
}
