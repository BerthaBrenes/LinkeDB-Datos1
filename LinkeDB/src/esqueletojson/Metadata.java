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
public class Metadata implements Comparable<Metadata> {

    private String carpeta;
    private Json json;

    /**
     * Constructor de Metadata donde lo que voy a hacer es crear una lista de
     * Json
     *
     * @param carpeta :nombre de la carpeta
     */
    public Metadata(String carpeta) {
        this.carpeta = carpeta;
        json = new Json(carpeta);
       

    }

    public Metadata() {

    }
/**
 * Crea la metadata primaria, almacena todas las carpetas existentes
 */
    public void MetadaPrimaria() {
        File directorio = new File("data/" + "metadata.json");
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        if (directorio.exists()) {
            try {
                fr = new FileReader("data/metadata.json");
            } catch (Exception e) {
                System.err.print("El archivo no se pudo abrir");
            }
        } else {
            JSONObject obj = new JSONObject();
            JSONArray list = new JSONArray();

            obj.put("Carpetas", list);

            try (FileWriter file = new FileWriter("data/metadata.json")) {
                file.write(obj.toString());
                file.flush();
            } catch (IOException e) {
            }

            System.out.println(obj);
        }
    }
/**
 * inserta una nueva store a la metadata
 * @param nombre 
 */
    public void InsertarPrimaria(String nombre)  {
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        try {
            fr = new FileReader("data/metadata.json");
        } catch (Exception e) {
            try {
                File f = new File("data/metadata.json");
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Metadata.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        try {
            Object obj = parser.parse(fr);
            JSONObject jsonObjeto = (JSONObject) obj;

            JSONArray courseArray = (JSONArray) jsonObjeto.get("Carpetas");
            courseArray.add(nombre);
            jsonObjeto.put("Carpetas", courseArray);
            try (FileWriter file = new FileWriter("data/metadata.json")) {
                file.write(obj.toString());
                file.flush();
            } catch (IOException e) {
            }
        } catch (ParseException ex) {
        } catch (IOException ex) {
            Logger.getLogger(Metadata.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
/**
 * Elimina un store de la metadata
 * @param nombre 
 */
    public void EliminarPrimaria(String nombre) {
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        try {
            fr = new FileReader("data/metadata.json");
        } catch (Exception e) {
            try {
                File f = new File("data/metadata.json");
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Metadata.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        try {
            Object obj = parser.parse(fr);
            JSONObject jsonObjeto = (JSONObject) obj;

            JSONArray courseArray = (JSONArray) jsonObjeto.get("Carpetas");
            courseArray.remove(nombre);
            jsonObjeto.put("Carpetas", courseArray);
            try (FileWriter file = new FileWriter("data/metadata.json")) {
                file.write(obj.toString());
                file.flush();
            } catch (IOException e) {
            }
        } catch (ParseException ex) {
        } catch (IOException ex) {
            Logger.getLogger(Metadata.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Crear las metadatas secundarias por store
     */
    public void CrearSecundarias(){
        String path = "data/" + carpeta + "/metadata.json";
        File directorio = new File(path);
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        if (directorio.exists()) {
            try {
                fr = new FileReader(path);
            } catch (Exception e) {
                System.err.print("El archivo no se pudo abrir");
            }
        } else {
            JSONObject obj = new JSONObject();
            JSONArray listDocumentos = new JSONArray();
            obj.put("nombre", carpeta);
            obj.put("DocumentosJson", listDocumentos);
            
            try (FileWriter file = new FileWriter(path)) {
                file.write(obj.toString());
                file.flush();
            } catch (IOException e) {
            }

            System.out.println(obj);
        }
        
    } 
    
    /**
     * Este metodo carga toda la informacion que existe en la carpeta usando la
     * metadata
     *
     */
    public String CargoInfo() {
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        try {
            fr = new FileReader("data/" + carpeta + "/metadata.json");
        } catch (Exception e) {
            CrearSecundarias();
            System.err.print("El archivo no se pudo abrir");
        }
        try {
            Object obj = parser.parse(fr);
            JSONObject jsonObjeto = (JSONObject) obj;
            String name = (String) jsonObjeto.get("nombre");
            System.out.println("el nombre es: " + name);
            JSONArray courseArray = (JSONArray) jsonObjeto.get("DocumentosJson");
            Iterator<String> iterator = courseArray.iterator();
            while (iterator.hasNext()) {
                System.out.println("Documentos existentes: " + iterator.next());
            }
            return (String) obj;
        } catch (ParseException | IOException ex) {
        }
       return null; 
    }

   

   
   

    @Override
    public int compareTo(Metadata o) {
        return this.carpeta.compareTo(o.carpeta);
    }

    @Override
    public String toString() {
        return this.carpeta;
    }

}