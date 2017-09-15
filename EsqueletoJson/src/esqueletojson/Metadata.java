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
    private Lista<String> listaJson;

    /**
     * Constructor de Metadata donde lo que voy a hacer es crear una lista de
     * Json
     *
     * @param carpeta :nombre de la carpeta
     */
    public Metadata(String carpeta) {
        this.carpeta = carpeta;
        listaJson = new Lista();

    }

    public Metadata() {

    }

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

    public void InsertarPrimaria(String nombre) throws IOException {
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        try {
            fr = new FileReader("data/metadata.json");
        } catch (Exception e) {
            File f = new File("data/metadata.json");
            f.createNewFile();

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
        }

    }
    public void EliminarPrimaria(String nombre) throws IOException{
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        try {
            fr = new FileReader("data/metadata.json");
        } catch (Exception e) {
            File f = new File("data/metadata.json");
            f.createNewFile();

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
        }
    }

    /**
     * Este metodo carga toda la informacion que existe en la carpeta usando la
     * metadata
     *
     */
    public void CargoInfo() {
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        try {
            fr = new FileReader("data/" + carpeta + "/metadata.json");
        } catch (Exception e) {
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
                System.out.println("Json existentes: " + iterator.next());
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Se encarga de guardar cualquier json creado en la carpeta en un nuevo
     * json
     */
    public void GuardarJson(String json) {
        File directorio = new File("data/" + carpeta + "/" + json + ".json");
        if (directorio.exists()) {
            System.out.println("existe");

            JSONObject obj = new JSONObject();
            obj.put("nombre", carpeta);
            JSONArray listjson = new JSONArray();
            for (int i = 0; i < listaJson.Largo(); i++) {
                listjson.add(listaJson.Iterador(i));
            }
            obj.put("DocumentosJson", listjson);
            try {
                FileWriter file = new FileWriter("data/" + carpeta + "/metadata.json");
                file.write(obj.toString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    /**
     * Agregar Json agrega json en la carpeta donde se encuentre la metadata y
     * ademas de eso lo almacena en la metadata
     *
     * @param json
     */
    public void AgregarJson(String json) {
        listaJson.Insertar(json);
        JSONObject obj = new JSONObject();
        obj.put("nombre", json);
        try {
            FileWriter file = new FileWriter("data/" + carpeta + "/" + json + ".json");
            file.write(obj.toString());
            file.flush();
            //Nodo<String> gato = listaCarpeta.Buscar(carpeta);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void BuscarJson(String json) {
        File directorio = new File("data/" + carpeta + "/" + json + ".json");
        System.out.println(listaJson.Buscar(json));
        if (directorio.exists()) {
            System.out.println("existe");
        } else {
            System.out.println("no existe");
        }
    }

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

    @Override
    public int compareTo(Metadata o) {
        return this.carpeta.compareTo(o.carpeta);
    }

    @Override
    public String toString() {
        return this.carpeta;
    }

}
