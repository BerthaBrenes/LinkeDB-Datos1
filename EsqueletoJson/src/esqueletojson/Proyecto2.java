/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esqueletojson;
import Logica.Lista;
import Logica.ListaCir;
import Logica.ListaDo;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.json.simple.parser.ParseException;


/**
 *
 * @author berta
 */
public class Proyecto2 {

    /**
     */
    
    
   static Tipo entero;
   static Llave primaria;
   
    public static void main(String[] args) throws ParseException, IOException {

     String carpeta = "pablo";
     JsonStore basico = new JsonStore();
     basico.nuevoNodo(carpeta);
     basico.Eliminar(carpeta);
     Documentos doc = new Documentos(carpeta);
     ListaCir<String> lista = new ListaCir<String>();
     doc.CargarLista();
     doc.AgregarJson("json1");
     //atr.ImprimirAtributo();
   
  
        
       /** 
       JsonStore hn = new JsonStore();
        ListaCir<String> lista = new ListaCir<String>();
        int opcion = 0;
        do {
            
            try{
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null,"1. Desea crear una carpeta nueva\n 2.Buscar un elemento\n 3.Buscar y eliminar dato\n 4. salir", "Menu de Opciones",3));
                switch(opcion){
                    case 1:   
                    String el = JOptionPane.showInputDialog(null,"agregar elemento",3);
                    lista.Insertar(el);
                    break;
                    case 2:
                        String elo = JOptionPane.showInputDialog(null,"buscar",3);
                        lista.Buscar(elo);
                        break;
                    case 3:
                        String eli = JOptionPane.showInputDialog(null,"Ingresar elemento a buscar","Eliminar Dato",3);
                        lista.Imprimir();
                        break;
                    case 4 :
                        break;
                }
            }catch (HeadlessException | NumberFormatException e){   
            }
            
        } while(opcion != 4);
        */
        
    }
   
}
