/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManejoDatos;

import java.util.Map;

/**
 *
 * @author berta
 */
public class DocFabrica {
    private static DocFabrica instancia = null;
    public static DocFabrica getInstance(){
        if(instancia == null){
            instancia = new DocFabrica();
        }
        return instancia;
    }
    private Map<String,Json> instancias;
    public Json get(String nombreC){
        Json nuevo = instancias.get(nombreC);
        if (nuevo == null){
            nuevo = new Json(nombreC);
            instancias.put(nombreC, nuevo);
        }
        return nuevo;
    }
    public Json get(String nombreC, String nombreD){
        Json nuevo = instancias.get(nombreC+nombreD);
        if (nuevo == null){
            nuevo = new Json(nombreC,nombreD);
            instancias.put(nombreC+nombreD, nuevo); 
        }
        return nuevo;
    }
}
