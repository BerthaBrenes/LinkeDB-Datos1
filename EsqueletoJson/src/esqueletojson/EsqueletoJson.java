/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esqueletojson;

/**
 *
 * @author berta
 */
public class EsqueletoJson implements Comparable<EsqueletoJson> {
    //TODO ENUM para estos atributos
    public String nombre;
    public String tipo;
    public String llave;
    public boolean requerido;
    public String valor;
    
    public EsqueletoJson(){
        
    }
   
    
    @Override
    public int compareTo(EsqueletoJson o) {
       return 2;
    }
    
}
