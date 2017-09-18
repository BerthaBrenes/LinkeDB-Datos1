/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esqueletojson;

import Logica.Lista;
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
    private final String carpeta;
    private final Lista<Atributo> listaAtributos;
   // private  String nombrejson;
    
    public Json(String Carpet) {
        this.carpeta = Carpet;
        atributos = new Atributo(carpeta);
        listaAtributos = new Lista();
    }
    
    

    /**
     * Agregar Json agrega json en la carpeta donde se encuentre la metadata y
     * ademas de eso lo almacena en la metadata
     *
     * @param json
     */
    public void AgregarJson(String json) {
       
        File directorio = new File("data/" + carpeta + "/" + json + ".json");
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
    public void AgregarAtributos(String nameC,String nombrejson, String nombr, String valo, Tipo tip, Llave llav, boolean requerid) {
        if (listaAtributos.Existe(atributos)) {

        } else {
            System.out.println("entre aqui");
            atributos = new Atributo(nameC,nombrejson, nombr, valo, tip, llav, requerid);
            listaAtributos.Insertar(atributos);
            
            listaAtributos.Imprimir();
        }
    }

    public void CargarLista(String nombrejson) {
        String path = "data/" + carpeta + "/"+ nombrejson+".json";
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

                listaAtributos.Insertar(iterator.next());
            }
            //listaJson.Imprimir();

        } catch (IOException | ParseException ex) {
            Logger.getLogger(Documentos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Guardas los atributos que estan las listas y los escribe en el json
     */
    public void Commit() {
        atributos.InsertarAtributo();
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
        String path = "data/" + carpeta + "/metadata.json";
        File directorio = new File("data/" + carpeta + "/" + json + ".json");
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        if (directorio.exists()) {
            try {
                directorio.deleteOnExit();
            } catch (Exception e) {

            }
        } else {
            System.out.println("no existe");
        }
        try {
            fr = new FileReader(path);
        } catch (Exception e) {
            try {
                File f = new File(path);
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Metadata.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        try {
            Object obj = parser.parse(fr);
            JSONObject jsonObjeto = (JSONObject) obj;

            JSONArray jsonArray = (JSONArray) jsonObjeto.get("DocumentosJson");
            jsonArray.remove(json);
            jsonObjeto.put("DocumentosJson", jsonArray);
            try (FileWriter file = new FileWriter(path)) {
                file.write(obj.toString());
                file.flush();
            } catch (IOException e) {
            }
        } catch (ParseException ex) {
        } catch (IOException ex) {
            Logger.getLogger(Metadata.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
