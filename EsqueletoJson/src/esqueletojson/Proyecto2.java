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
    
    public static int valorP;
    public static void setP(int dato){
        valorP = dato;
    }
    
    public static int getP(){
        return valorP;
    }
    
    public static void main(String[] args) throws ParseException, IOException {
        Atributo atr = new Atributo("cuarto"."jds.json","mio","tengo sue;o","entero","primaria","43");
        Metadata hn = new Metadata("cuarto");
        hn.CargoInfo();
        atr.ImprimirAtributo();
        
        
        /**
       JsonStore hn = new JsonStore();
        ListaCir<Integer> lista = new ListaCir<Integer>();
        int opcion = 0;
        do {
            
            try{
                opcion = Integer.parseInt(JOptionPane.showInputDialog(null,"1. Desea crear una carpeta nueva\n 2.Buscar un elemento\n 3.Buscar y eliminar dato\n 4. salir", "Menu de Opciones",3));
                switch(opcion){
                    case 1:   
                    String el = JOptionPane.showInputDialog(null,"agregar un json",3);
                    hn.nuevoNodo(el);
                    break;
                    case 2:
                        String elo = JOptionPane.showInputDialog(null,"buscar",3);
                        hn.Buscar(elo);
                        break;
                    case 3:
                        String eli = JOptionPane.showInputDialog(null,"Ingresar elemento a buscar","Eliminar Dato",3);
                        hn.Eliminar(eli);
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
