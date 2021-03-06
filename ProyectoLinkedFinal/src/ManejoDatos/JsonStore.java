/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManejoDatos;

import logica.ListaDo;
import logica.NodoCir;
import java.io.File;
import java.io.FileReader;
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
public final class JsonStore {

    private static ListaDo<String> listaCarpeta;
   // private static Json json;
    private static Documentos documento;
    private static Metadata metadataPrincipal;

    public JsonStore(){
        listaCarpeta = new ListaDo();
        metadataPrincipal = new Metadata();
        metadataPrincipal.MetadaPrimaria();
        CargarPrimaria();

    }

    public  String CargarPrimaria() {
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        try {
            fr = new FileReader("data/metadata.json");
        } catch (Exception e) {
            try {
                File f = new File("data/metadata.json");
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(JsonStore.class.getName()).log(Level.SEVERE, null, ex);
            }

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
        } catch (ParseException ex) {
            Logger.getLogger(JsonStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public  int getLargolista() {
        return listaCarpeta.Largo();
    }
    public ListaDo<String> getLista(){
        return listaCarpeta;
    }
    

    /**
     * nuevoNodo Consiste en un metodo que crea carpetas en DATA Cada carpeta es
     * un nodo en una lista doblementenlazada
     *
     * @param carpeta : nombre de la carpeta
     */
    public void nuevoNodo(String carpeta) {
        File directorio = new File("data/" + carpeta);
        Metadata metadata = new Metadata(carpeta);
        try {
            if (directorio.exists()) {
                //TODO verificar si no han borrado el archivo metadata
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
                metadata.InsertarPrimaria(carpeta);
                metadata.CrearSecundarias();

            }

        } catch (Exception e) {

        }

    }

    public  NodoCir<String> Retornar(String carpeta) {
        if (listaCarpeta.Existe(carpeta)) {
            return listaCarpeta.Buscar(carpeta);
        }
        return null;
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
        //listaCarpeta.Buscar(carpeta);
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
                metadataPrincipal.EliminarPrimaria(carpeta);
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
