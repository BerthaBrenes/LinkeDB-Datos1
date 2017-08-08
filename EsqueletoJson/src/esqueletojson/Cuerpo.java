/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esqueletojson;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 *
 * @author berta
 */
public class Cuerpo {
    private String nombreValor;
    private String TipoValor;
    private boolean LlaveValor;
    private boolean RequerimientoValor;
    private String DefectoValor;
    private String NombreDocumento;
    
    public Cuerpo(String documento,String name, String tipo, String defecto, boolean llave, boolean requerido){
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
        try(FileWriter file = new FileWriter(NombreDocumento+".json"))
        {
           file.write(obj.toString());
           file.flush();
        }
        catch(IOException e){
            e.printStackTrace();
            
        }
        
        System.out.println(obj);
    
    }
   
}
