/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManejoDatos;

import logica.Lista;
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
public class Json {

    private Atributo atributos;
    private String carpeta;
    private String nombreJson;
    private Lista<JSONObject> listaAtributos;
    // private  String nombrejson;

    public Json(String Carpet, String json) {
        this.carpeta = Carpet;
        this.nombreJson = json;
        atributos = new Atributo(this.carpeta, this.nombreJson);
        listaAtributos = new Lista();
    }

    public Json(String Carpet) {
        this.carpeta = Carpet;
        //this.nombreJson = json;
        atributos = new Atributo(this.carpeta, this.nombreJson);
        listaAtributos = new Lista();
    }

    /**
     * Agregar Json agrega json en la carpeta donde se encuentre la metadata y
     * ademas de eso lo almacena en la metadata
     *
     * @param json
     */
    public void AgregarJson(String json) {

        File directorio = new File("data/" + this.carpeta + "/" + json + ".json");
        JSONArray listatributos = new JSONArray();
        JSONObject obj = new JSONObject();
        if (directorio.exists()) {

        } else {
            obj.put("Atributos", listatributos);
            obj.put("nombre", json);
            try {
                FileWriter file = new FileWriter("data/" + carpeta + "/" + json + ".json");
                file.write(obj.toString());
                file.flush();

            } catch (IOException e) {
            }
        }
    }

    /**
     * Agrega los atributos al json que faltan
     *
     * @param nameC
     * @param nombrejson
     * @param nombr
     * @param valo
     * @param tip
     * @param llav
     * @param requerid
     */
    public void AgregarAtributos(String nombr, String valo, String tip, String llav, String requerid) {

        System.out.println("entre aqui");
        //atributos = new Atributo();
        JSONObject valor = atributos.Atributo(nombr, valo, tip, llav, requerid);
        listaAtributos.Insertar(valor);
        listaAtributos.Imprimir();
        //Metadata metadatatemp = new Metadata(carpeta);
        //metadatatemp.AgregarTemporar(nombreJson, valor);
       

    }
   

    public void CargarLista(String nombrejson) {
        String path = "data/" + carpeta + "/" + nombrejson + ".json";
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        try {
            fr = new FileReader(path);
        } catch (Exception e) {
            try {
                File f = new File(path);
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Documentos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            Object obj = parser.parse(fr);
            JSONObject jsonObjeto = (JSONObject) obj;
            JSONArray courseArray = (JSONArray) jsonObjeto.get("Atributos");
            Iterator<Atributo> iterator = courseArray.iterator();
            while (iterator.hasNext()) {

                //listaAtributos.Insertar(iterator.next());
            }
            //listaJson.Imprimir();

        } catch (IOException | ParseException ex) {
            Logger.getLogger(Documentos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Guardas los atributos que estan las listas y los escribe en el json
     */
    public void CommitAtributo(String nombre, String valor) {
        String path = "data/"+this.carpeta+"/"+this.nombreJson+".json";
        System.out.println("carpeta: "+this.carpeta);
        System.out.println("documento: "+this.nombreJson);
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
            atributosObjeto.put(nombre, valor);
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
    public void GuardarAtributoMetadata(JSONObject atributo){
        Metadata metadata = new Metadata(carpeta);
        metadata.InsertarSecundaria(atributo,nombreJson);
    }

    /**
     * Busca los json existentes en la carpeta
     *
     * @param json
     */
    public void BuscarJson(String json) {
        File directorio = new File("data/" + carpeta + "/" + json + ".json");
        if (directorio.exists()) {
            System.out.println("existe");
        } else {
            System.out.println("no existe");
        }
    }

    /**
     * Busca el elemento dado y lo elimina
     *
     * @param json
     */
    public void EliminarJson(String json) {
       Metadata metadata = new Metadata(carpeta);
       metadata.EliminarJson(json);
    }

    public void Prueba(String jsonAtributo) {
        System.out.print(jsonAtributo);
    }

    /**
     *
     * @return
     */
    public Lista<JSONObject> getLista(){
        return listaAtributos;
    }

}
