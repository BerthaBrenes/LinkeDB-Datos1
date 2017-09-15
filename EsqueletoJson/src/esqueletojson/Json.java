/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esqueletojson;

import Logica.Lista;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author berta
 */
public class Json {


    private Atributo atributos;
    private final String carpeta;
    private Lista<Atributo> listaAtributos;
    

    public Json(String Carpet) {
        this.carpeta = Carpet;
        atributos = new Atributo(carpeta);
        
        
    }

    /**
     * Agregar Json agrega json en la carpeta donde se encuentre la metadata y
     * ademas de eso lo almacena en la metadata
     *
     * @param json
     */
    public void AgregarJson(String json) {
        
        JSONObject obj = new JSONObject();
        obj.put("nombre", json);
        try {
            FileWriter file = new FileWriter("data/" + carpeta + "/" + json + ".json");
            file.write(obj.toString());
            file.flush();
            //Nodo<String> gato = listaCarpeta.Buscar(carpeta);
        } catch (IOException e) {
        }

    }
    /**
     * Agrega los atributos al json que faltan 
     * @param nameC
     * @param document
     * @param nombr
     * @param valo
     * @param tip
     * @param llav
     * @param requerid
     */
    public void AgregarAtributos( String nameC,String document,String nombr, String valo, Tipo tip, Llave llav, boolean requerid) {
        atributos = new Atributo(nameC,document, nombr,valo,tip,llav,requerid);
        listaAtributos.Insertar(atributos);
    }
    /**
     * Guardas los atributos que estan las listas y los escribe en el json
     */
    public void Commit() {
      atributos.InsertarAtributo();
    }
    
    /**
    *Busca los json existentes en la carpeta
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
     * @param json 
     */
    public void EliminarJson(String json) {
        File directorio = new File("data/" + carpeta + "/" + json + ".json");
        if (directorio.exists()) {
            try {
                directorio.deleteOnExit();
            } catch (Exception e) {

            }
        } else {
            System.out.println("no existe");
        }
    }

}
