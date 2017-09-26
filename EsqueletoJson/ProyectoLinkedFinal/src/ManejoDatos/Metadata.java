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
import java.util.Collection;
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
        json = DocFabrica.getInstance().get(carpeta);

    }

    public Metadata() {

    }

    /**
     * Crear la metadata principal en la carpeta data
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
     * Inserta el nombre de la nueva carpeta creada en la metadata principal
     *
     * @param nombre
     */
    public void InsertarPrimaria(String nombre) {
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
     * Eliminar el nombre de la carpeta de la metadata incial
     *
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
     * +
     * Crea las metadatas que estan en cada carpeta
     */
    public void CrearSecundarias() {
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
     * Guarda el nuevo documento creado en la metadata de la carpeta
     * correspondiente
     *
     * @param nombrejson
     */
    public void GuardarMetada(String nombrejson) {
        String path = "data/" + carpeta + "/" + "metadata.json";
        File directorio = new File(path);
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        try {
            fr = new FileReader(path);
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
            Iterator<String> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next());
            }

            jsonArray.add(nombrejson);
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

    /**
     * Crea una metadata temporal en la carpeta previamente seteado
     */
    public void CrearTemporales() {
        String path = "data/" + carpeta + "/metadataTemp.json";
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
            JSONArray listAtributos = new JSONArray();
            //System.out.println(CargoDocumentos());
            try {
                fr = new FileReader("data/" + carpeta + "/metadata.json");
            } catch (Exception e) {
                System.err.print("El archivo no se pudo abrir");
            }
            try {
                Object obj2 = parser.parse(fr);
                JSONObject jsonObjeto = (JSONObject) obj2;
                JSONArray courseArray = (JSONArray) jsonObjeto.get("DocumentosJson");
                Iterator<String> iterator = courseArray.iterator();

                while (iterator.hasNext()) {
                    obj.put(iterator.next(), listAtributos);
                }
                //(String) obj;
            } catch (ParseException | IOException ex) {
            }

            try (FileWriter file = new FileWriter(path)) {
                file.write(obj.toString());
                file.flush();
            } catch (IOException e) {
            }

            System.out.println(obj);
        }

    }
   
    /**
     * Verifica que temporal cuante con todas todos los nombres de las
     * docuementos
     */
    public void Actualizar() {
        Documentos prueba = new Documentos(carpeta);
        ListaCir<String> lista = prueba.getLista();
        String path = "data/" + carpeta + "/" + "metadataTemp.json";
        File directorio = new File(path);
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
            JSONObject obj1 = new JSONObject();
            JSONObject jsonObjeto = (JSONObject) obj;
            JSONArray listAtributos = new JSONArray();
            Collection name = jsonObjeto.keySet();
            Iterator<String> iterator = name.iterator();
            lista.Imprimir();
            int iterador1 = lista.Largo();
            int iterador2 = 0;
            while (iterador1 != 0) {
                System.out.println(iterador1);
                System.out.println(iterador2);
                if (iterator.hasNext()) {
                    if (lista.Iterador(iterador2) == iterator.next()) {
                        iterador1--;
                        iterador2++;
                    }
                } else {
                    obj1.put(lista.Iterador(iterador2), listAtributos);
                    iterador1--;
                    iterador2++;
                }
            }
            try (FileWriter file = new FileWriter(path)) {
                file.write(obj1.toString());
                file.flush();
            } catch (IOException e) {
            }
            //System.out.print(name);
        } catch (ParseException ex) {
        } catch (IOException ex) {
            Logger.getLogger(Documentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Guarda el nuevo valor asignado al documento que le tengo que agregar
     *
     * @param nombredocument
     * @param valor1 un objeto
     */
    public void AgregarTemporar(String nombredocument, JSONObject valor1) {
        System.out.println("Carpeta:" + this.carpeta);
        System.out.println("documento: " + nombredocument);
        String path = "data/" + this.carpeta + "/metadataTemp.json";
        //File directorio = new File(path);
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        try {
            fr = new FileReader(path);
        } catch (Exception e) {
            try {
                System.out.println("creando nuevo archivo");
                File f = new File(path);
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Documentos.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        try {
            Object obj = parser.parse(fr);
            JSONObject jsonObjeto = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObjeto.get(nombredocument);
            //Iterator<String> iterator = jsonArray.iterator();
            System.out.println(jsonObjeto.toJSONString());
            jsonArray.add(valor1);
            try (FileWriter file = new FileWriter(path)) {
                file.write(jsonObjeto.toJSONString());
                file.flush();
            } catch (IOException e) {
            }
        } catch (ParseException ex) {
        } catch (IOException ex) {
            Logger.getLogger(Documentos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void InsertarSecundaria(JSONObject atributos,String nombrejson){
        String path = "data/"+carpeta+"/metadata.json";
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        try {
            fr = new FileReader(path);
        } catch (Exception e) {
            try {
                File f = new File(path);
                f.createNewFile();
            } catch (IOException ex) {
                System.out.print("no se abrio");
                Logger.getLogger(Atributo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            Object obj = parser.parse(fr);
            JSONObject atributObjeto = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray)atributObjeto.get("DocumentosJson") ;
            JSONArray atributosArray = (JSONArray) jsonArray.get(jsonArray.indexOf(nombrejson));
            JSONObject atributosObjeto = new JSONObject();
           
            atributosArray.add(atributos);
            //atributObjeto.put("Atributos", atributosArray);
            
            try (FileWriter file = new FileWriter(path)) {
                file.write(obj.toString());
                file.flush();
                System.out.print(atributObjeto);
            } catch (IOException e) {
            }
        } catch (ParseException ex) {
        } catch (IOException ex) {
            Logger.getLogger(Atributo.class.getName()).log(Level.SEVERE, null, ex);
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

    @Override
    public int compareTo(Metadata o) {
        return this.carpeta.compareTo(o.carpeta);
    }

    @Override
    public String toString() {
        return this.carpeta;
    }

}
