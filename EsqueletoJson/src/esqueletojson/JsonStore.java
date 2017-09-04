/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esqueletojson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import org.json.simple.JSONObject;

/**
 *
 * @author berta
 */
public class JsonStore {

    private Lista<String> listaCarpeta;
    private Lista<Metadata> listaMetadata;

    private static JsonStore conteo = null;

    public JsonStore() {
        listaCarpeta = new Lista();
        listaMetadata = new Lista();

    }

    /**
     * singleton
     *
     * @return
     */
    public static JsonStore GetInstance() {
        if (conteo == null) {
            conteo = new JsonStore();
        }
        return conteo;
    }

    /**
     * nuevoNodo Consiste en un metodo que crea carpetas en la carpeta data cada
     * carpeta es un nodo en una lista enlazada
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
                listaMetadata.Add(metadata);

                if (listaCarpeta.Existe(carpeta)) {
                    System.out.print(listaCarpeta.Buscar(carpeta));
                } else {
                    listaCarpeta.Add(carpeta);
                    listaCarpeta.Print();
                }

            } else {
                directorio.mkdir();
                listaCarpeta.Add(carpeta);
                listaCarpeta.Print();
                Metadata metadata = new Metadata(carpeta);
                metadata.GuardarJson();
                listaMetadata.Add(metadata);

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
        if (directorio.exists()) {
            Metadata metadata = new Metadata(carpeta);
            metadata.CargoInfo();
        } else {
            System.out.println("no existe");
        }
    }

    public void Eliminar(String carpeta) {
        File directorio = new File("data/" + carpeta);
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
