/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esqueletojson;
import java.util.Scanner;


/**
 *
 * @author berta
 */
public class EsqueletoJson {

    /**
     * @param args the command line arguments
     */
    
            
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner entrada = new Scanner(System.in);
        System.out.println("ingrese por favor el nombre del documento");
        String NombreDocumento = entrada.nextLine();
        System.out.println("ingrese por favor el nombre");
        String nombreValor  = entrada.nextLine();
        System.out.println("ingrese por favor el tipo");
        String tipo  = entrada.nextLine();
        System.out.println("ingrese por favor el valor ");
        String DefectoValor = entrada.nextLine();
        System.out.println("ingrese 0 para llave primaria o 1 para foranea");
        boolean llave = entrada.hasNext();
        System.out.println("ingrese 0 si no requiere el dato o 1 si lo requiere");
        boolean RequerimientoValor = entrada.hasNext();
        
        
        
        
        if (DefectoValor == null){
          DefectoValor = "";
        }
        Cuerpo nuevoJason = new Cuerpo(NombreDocumento,nombreValor, tipo,DefectoValor, llave, RequerimientoValor);
        
    }
    
}
