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
public final class Atributo implements Comparable<Atributo> {

    private String nombre;
    private String valor;
    private Tipo tipo;
    private Llave llave;
    private boolean requerido;
    private String carpeta;
    private String json;

    public Atributo(String document, String nombr, String valo, Tipo tip, Llave llav, boolean requerid) {
        setLlave(llav);
        setNombre(nombr);
        setRequerido(requerid);
        setTipo(tip);
        setValor(valo);
        this.json = document;

    }

    public Atributo(String carpet) {
        this.carpeta = carpet;

    }

    public void ImprimirAtributo() {
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
            JSONArray courseArray = (JSONArray) jsonObjeto.get("Atributos");
            Iterator<String> iterator = courseArray.iterator();
            while (iterator.hasNext()) {
                
                System.out.println("Json existentes: " + iterator.next());
            }
            
        } catch (ParseException | IOException ex) {
        }
    }

    public void EliminarAtributo() {

    }

    public void InsertarAtributo() {
        JSONParser parser = new JSONParser();
        FileReader fr = null;
        try {
            fr = new FileReader("data/metadata.json");
        } catch (Exception e) {
            try {
                File f = new File("data/metadata.json");
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Atributo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        try {
            Object obj = parser.parse(fr);
            JSONObject atributObjeto = (JSONObject) obj;
            JSONArray atributosArray = (JSONArray) atributObjeto.get("Atributos");
            JSONObject atributosObjeto = (JSONObject) obj;
            atributosObjeto.put("Nombre", getNombre());
            atributosObjeto.put("Tipo", getTipo());
            atributosObjeto.put("Tipo Especial", getLlave());
            atributosObjeto.put("Requerimiento", isRequerido());
            atributosObjeto.put("Valor", getValor());
            atributosArray.add(atributosObjeto);
            atributObjeto.put("Atributos", atributosArray);
            try (FileWriter file = new FileWriter("data/" + carpeta + json + "metadata.json")) {
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

    public String getNombre() {
        return nombre;
    }

    public String getValor() {
        return valor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Llave getLlave() {
        return llave;
    }

    public boolean isRequerido() {
        return requerido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setLlave(Llave llave) {
        this.llave = llave;
    }

    public void setRequerido(boolean requerido) {
        this.requerido = requerido;
    }

    @Override
    public int compareTo(Atributo o) {
        return this.nombre.compareTo(o.nombre);
    }

}
