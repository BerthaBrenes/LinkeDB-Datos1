/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esqueletojson;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 *
 * @author berta
 */

public class Cuerpo {
    /**
     * 
     */
    private String nombreValor;
    private String TipoValor;
    private int  LlaveValor;
    private int  RequerimientoValor;
    private String DefectoValor;
    private String NombreDocumento;
    /**
     * 
     * @param documento
     * @param name
     * @param tipo
     * @param defecto
     * @param llave
     * @param requerido 
     */
    public Cuerpo(String documento,String name, String tipo, String defecto, int llave, int requerido){
        nombreValor = name;
        TipoValor = tipo;
        LlaveValor = llave;
        RequerimientoValor = requerido;
        DefectoValor = defecto;
        NombreDocumento = documento;
        
        JSONObject obj = new JSONObject();
         
         if (TipoValor == "Array"){
             JSONArray list = new JSONArray();
         }
         obj.put(nombreValor,DefectoValor);
         FileReader fr=null;
        try{
            fr= new FileReader(NombreDocumento+".json");
        }
        catch(Exception e){
          File f = new File("myJason.txt");  
          try{
            f.createNewFile();
            fr= new FileReader(NombreDocumento+".json");
          }catch(Exception ex){
              System.err.print("El archivo myHason no existe y no puede ser creado.");
          }
        }
        
        
        System.out.println(obj);
    
    }
   
}
