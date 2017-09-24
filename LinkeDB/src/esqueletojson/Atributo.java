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
    private Tipo tipo;
    private Llave llave;
    private boolean requerido;
    private String carpeta;
    private String json;

    public Atributo(String carpet,String nombrejson, String nombr, String valo, Tipo tip, Llave llav, boolean requerid) {
        setLlave(llav);
        setNombre(nombr);
        setRequerido(requerid);
        setTipo(tip);
        setValor(valo);
        this.carpeta = carpet;
        
        System.out.println(carpet +" "+ nombr);
        GuardarAtributo();
        //InsertarAtributo();

    }

    public Atributo(String carpet) {
        this.carpeta = carpet;
       
    }
    /**
     * Imprime los datos existentes en los artributos
     */
    public void ImprimirAtributo() {
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        try {
            fr = new FileReader("data/" + carpeta +"/"+json+".json");
        } catch (Exception e) {
            System.err.print("El archivo no se pudo abrir");
        }
        try {
            Object obj = parser.parse(fr);
            JSONObject jsonObjeto = (JSONObject) obj;
            JSONArray courseArray = (JSONArray) jsonObjeto.get("Atributos");
            Iterator<String> iterator = courseArray.iterator();
            while (iterator.hasNext()) {
                
                System.out.println("Json existentes: " + iterator.next());
            }
            
        } catch (ParseException | IOException ex) {
        }
    }

    public void EliminarAtributo() {

    }
    /**
     * Guardar los atributos en disco
     */
    public void GuardarAtributo(){
         String path = "data/"+carpeta+"/"+json+".json";
         JSONParser parser = new JSONParser();
         FileReader fr = null;
         try{
             fr = new FileReader(path);
         }catch(Exception e){
            try{
                File f = new File(path);
                f.createNewFile();
            } catch (IOException ex) {
                 Logger.getLogger(Atributo.class.getName()).log(Level.SEVERE, null, ex);
             }
         }
         try{
            Object obj = parser.parse(fr);
            JSONObject jsonObjeto = (JSONObject) obj;
            JSONObject atributosObjeto = new JSONObject();
            JSONArray atributosArray = (JSONArray) jsonObjeto.get("Atributos");
            Iterator<String> iterator = atributosArray.iterator();
            while(iterator.hasNext()){
                iterator.next();
            }
            atributosObjeto.put("Nombre:", this.nombre);
            atributosObjeto.put("Tipo",this.tipo);
            atributosObjeto.put("Llave",this.llave);
            atributosObjeto.put("Valor",this.valor);
            atributosObjeto.put("Requerido",this.requerido);
            atributosArray.add(atributosObjeto);
            try (FileWriter file = new FileWriter(path)) {
                file.write(obj.toString());
                file.flush();
            } catch (IOException e) {
            }
        } catch (ParseException ex) {
        } catch (IOException ex) {
            Logger.getLogger(Documentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Inserta los atributos en la metadata
     */
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

    public String getNombre() {
        return nombre;
    }

    public String getValor() {
        return valor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Llave getLlave() {
        return llave;
    }

    public boolean isRequerido() {
        return requerido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setLlave(Llave llave) {
        this.llave = llave;
    }

    public void setRequerido(boolean requerido) {
        this.requerido = requerido;
    }

    @Override
    public int compareTo(Atributo o) {
        return this.nombre.compareTo(o.nombre);
    }

}
