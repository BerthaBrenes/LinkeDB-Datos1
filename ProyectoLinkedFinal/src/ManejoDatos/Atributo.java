/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManejoDatos;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author berta
 */
public final class Atributo implements Comparable<Atributo> {

    private String nombre;
    private String valor;
    private String tipo;
    private String llave;
    private String requerido;
    private String carpeta;
    private String json;

    public Atributo(String carpet,String nombrejson, String nombr, String valo, String tip, String llav, String requerid) {
        setLlave(llav);
        setNombre(nombr);
        setRequerido(requerid);
        setTipo(tip);
        setValor(valo);
        this.carpeta = carpet;
        
        System.out.println(carpet +" "+ nombr);
        //GuardarAtributo();
        //InsertarAtributo();

    }
    public Atributo(String carpeta1,String json1){
        this.json =  json1;
        this.carpeta = carpeta1;
    }
    

    public JSONObject Atributo(String nombr, String valo, String tip, String llav, String requerid) {
        String nuevoObj;
        JSONObject obj = new JSONObject();
        obj.put("nombre",nombr);
        obj.put("valor", valo);
        obj.put("tipo",tip);
        obj.put("llave",llav);
        obj.put("requerido",requerid);
        //nuevoObj = obj.toString();
        return obj;
    }
    
/**
   
    public void InsertarAtributo() {
        String path = "data/"+carpeta+"/metadata.json";
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        try {
            fr = new FileReader(path);
        } catch (Exception e) {
            try {
                File f = new File(path);
                f.createNewFile();
            } catch (IOException ex) {
                System.out.print("no se abrio");
                Logger.getLogger(Atributo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            Object obj = parser.parse(fr);
            JSONObject atributObjeto = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray)atributObjeto.get("DocumentosJson") ;
            JSONArray atributosArray = (JSONArray) jsonArray.get(jsonArray.indexOf(json));
            JSONObject atributosObjeto = new JSONObject();
            atributosObjeto.put("Nombre", getNombre());
            atributosObjeto.put("Tipo", getTipo());
            atributosObjeto.put("Tipo Especial", getLlave());
            atributosObjeto.put("Requerimiento", isRequerido());
            atributosObjeto.put("Valor", getValor());
            atributosArray.add(atributosObjeto);
            //atributObjeto.put("Atributos", atributosArray);
            
            try (FileWriter file = new FileWriter(path)) {
                file.write(obj.toString());
                file.flush();
                System.out.print(atributObjeto);
            } catch (IOException e) {
            }
        } catch (ParseException ex) {
        } catch (IOException ex) {
            Logger.getLogger(Atributo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    */
    public void CargarListas(){
        
    }

    public String getNombre() {
        return nombre;
    }

    public String getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    public String getLlave() {
        return llave;
    }

    public String isRequerido() {
        return requerido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public void setRequerido(String requerido) {
        this.requerido = requerido;
    }

    @Override
    public int compareTo(Atributo o) {
        return this.nombre.compareTo(o.nombre);
    }

}
