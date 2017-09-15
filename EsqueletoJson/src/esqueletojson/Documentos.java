/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esqueletojson;

import Logica.ListaCir;
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
public class Documentos {

    private final String carpeta;
    private ListaCir<String> listaJson;
    private final Json json;

    public Documentos(String Carpeta) {
        this.carpeta = Carpeta;
        this.json = new Json(carpeta);
    }

    public void AgregarJson(String nombrejson) {
        //listaJson.Insertar(nombrejson);
        this.json.AgregarJson(nombrejson);
        GuardarMetada(nombrejson);

    }

    public void BuscarJson(String nombrejson) {
        System.out.println(listaJson.Buscar(nombrejson));
        this.json.BuscarJson(nombrejson);
    }

    public void EliminarJson(String nombrejson) {
        listaJson.Eliminar(nombrejson);
        this.json.EliminarJson(nombrejson);
    }

    public void CargarLista() {
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        try {
            fr = new FileReader("data/" + carpeta + "/metadata.json");
        } catch (Exception e) {
            try {
                File f = new File("data/" + carpeta + "/metadata.json");
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Documentos.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        try {
            Object obj = parser.parse(fr);
            JSONObject jsonObjeto = (JSONObject) obj;
            JSONArray courseArray = (JSONArray) jsonObjeto.get("DocumentosJson");
            Iterator<String> iterator = courseArray.iterator();

            while (iterator.hasNext() ) {
                System.out.println("Json existentes: " + iterator.next());
                listaJson.Insertar(iterator.next());
            }
            //listaJson.Imprimir();

        } catch (IOException | ParseException ex) {
            Logger.getLogger(Documentos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void GuardarMetada(String nombrejson) {
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        try {
            fr = new FileReader("data/" + carpeta + "/" + "metadata.json");
        } catch (Exception e) {
            try {
                File f = new File("data/" + carpeta + "/" + "metadata.json");
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Documentos.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        try {
            Object obj = parser.parse(fr);
            JSONObject jsonObjeto = (JSONObject) obj;
            JSONObject atributosObjeto = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObjeto.get("DocumentosJson");
            jsonArray.add(nombrejson);
            //JSONArray atributosArray = (JSONArray) jsonArray.get(jsonArray.indexOf(nombrejson));

            try (FileWriter file = new FileWriter("data/" + carpeta + "/" + "metadata.json")) {
                file.write(obj.toString());
                file.flush();
            } catch (IOException e) {
            }
        } catch (ParseException ex) {
        } catch (IOException ex) {
            Logger.getLogger(Documentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
