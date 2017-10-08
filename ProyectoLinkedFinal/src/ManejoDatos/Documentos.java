/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManejoDatos;

import logica.ListaCir;
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
    private final ListaCir<String> listaJson;
    private final Json json;

    public Documentos(String Carpeta) {
        this.carpeta = Carpeta;
        json = DocFabrica.getInstance().get(Carpeta);
        listaJson = new ListaCir();
        CargarLista();
        
    }

    public void AgregarJson(String nombrejson) {
        
        File directorio = new File("data/" + carpeta + "/" + nombrejson + ".json");
        JSONArray listatributos = new JSONArray();
        JSONObject obj = new JSONObject();
        if (directorio.exists()) {
            System.out.println("existo");
        }else{
            System.out.println(" no existo");
           //listaJson.Insertar(nombrejson);
           
           obj.put("Atributos", listatributos);
            obj.put("nombre", nombrejson);
            try {
                FileWriter file = new FileWriter("data/" + carpeta + "/" + nombrejson + ".json");
                file.write(obj.toString());
                file.flush();

            } catch (IOException e) {
            }
            GuardarMetada(nombrejson);
        }
        //this.json.AgregarJson(nombrejson);
        
    }
    public String BuscarOrigen(String nombre){
        if(listaJson.Existe(nombre)){
            return carpeta;
        }else{
            return null;
        }
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
               
                listaJson.Insertar(iterator.next());
            }
            //listaJson.Imprimir();

        } catch (IOException | ParseException ex) {
            Logger.getLogger(Documentos.class.getName()).log(Level.SEVERE, null, ex);
}
    }
/**
 * Guarda el nuevo documento en la metadata principal
 * @param nombrejson 
 */
    public void GuardarMetada(String nombrejson) {
        Metadata metadataDocumento = new Metadata(this.carpeta);
        metadataDocumento.GuardarMetada(nombrejson);
    }

    public int getLargolista() {
        return listaJson.Largo();
    }
    public ListaCir<String> getLista(){
        return listaJson;
    }
}
