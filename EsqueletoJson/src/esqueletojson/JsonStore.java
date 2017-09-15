/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esqueletojson;

import Logica.ListaCir;
import Logica.ListaDo;
import java.io.File;
import java.io.FileReader;
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
public final class JsonStore {

    private final ListaDo<String> listaCarpeta;
     private ListaCir<String> listaJson;
    private Documento json;

    private static JsonStore conteo = null;
    private final Metadata metadataPrincipal;

    public JsonStore() throws IOException, ParseException {
        listaCarpeta = new ListaDo();
        metadataPrincipal = new Metadata();
        metadataPrincipal.MetadaPrimaria();
        CargarPrimaria();
        

    }

    /**
     * singleton
     *
     * @return
     * @throws java.io.IOException
     * @throws org.json.simple.parser.ParseException
     */
    public static JsonStore GetInstance() throws IOException, ParseException {
        if (conteo == null) {
            conteo = new JsonStore();
        }
        return conteo;
    }

    public String CargarPrimaria() throws  ParseException, IOException {
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
        Iterator<String> iterator = courseArray.iterator();

        while (iterator.hasNext()) {
             listaCarpeta.Insertar(iterator.next());
        }
        listaCarpeta.Imprimir();
        
        } catch (IOException e) {
            }
        return null;
    }

    /**
     * nuevoNodo Consiste en un metodo que crea carpetas en DATA
     * Cada carpeta es un nodo en una lista doblementenlazada
     *
     * @param carpeta : nombre de la carpeta
     */
    public void nuevoNodo(String carpeta) {
        File directorio = new File("data/" + carpeta);
        try {
            if (directorio.exists()) {
                //TODO verificar si no han borrado el archivo metadata
                Metadata metadata = new Metadata(carpeta);
                metadata.CargoInfo();

                if (listaCarpeta.Existe(carpeta)) {
                    System.out.print(listaCarpeta.Buscar(carpeta));
                } else {
                    listaCarpeta.Insertar(carpeta);
                    listaCarpeta.Imprimir();
                }

            } else {
                directorio.mkdir();
                listaCarpeta.Insertar(carpeta);
                listaCarpeta.Imprimir();
                Metadata metadata = new Metadata(carpeta);

            }

        } catch (Exception e) {

        }

    }

    /**
     * Esta funcion busca que la carpeta con ese nombre exista en la carpeta
     * data
     *
     * @param carpeta
     */
    public void Buscar(String carpeta) {
        File directorio = new File("data/" + carpeta);
        System.out.print(listaCarpeta.Buscar(carpeta));
        listaCarpeta.Buscar(carpeta);
        if (directorio.exists()) {
            Metadata metadata = new Metadata(carpeta);
            metadata.CargoInfo();
        } else {
            System.out.println("no existe");
        }
    }

    public void Eliminar(String carpeta) {
        File directorio = new File("data/" + carpeta);
        listaCarpeta.Eliminar(carpeta);
        if (directorio.exists()) {
            try {
                delete(directorio);
            } catch (Exception e) {

            }
        } else {
            System.out.println("no existe");
        }
    }

    private static void delete(File file) throws IOException {

        for (File childFile : file.listFiles()) {

            if (childFile.isDirectory()) {
                delete(childFile);
            } else if (!childFile.delete()) {
                throw new IOException();
            }
        }

        if (!file.delete()) {
            throw new IOException();
        }
    }

}
