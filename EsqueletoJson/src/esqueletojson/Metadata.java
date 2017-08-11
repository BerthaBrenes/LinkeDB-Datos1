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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author berta
 */
public class Metadata {
    private String documento;
    private String nombre;
    private String tipo;
    private int foranea;
    private int primaria;
    private int requerido;
    private String valor;
    JSONObject obj = new JSONObject();
    
    public Metadata(String Doc,String nombre,String tipo, int foranea, int Primaria, int Requerido, String valor){
        SetDocumento(Doc);
        SetNombre(nombre);
        SetTipo(tipo);
        SetForanea(foranea);
        SetPrimary(Primaria);
        SeRequerido(Requerido);
        SetValor(valor);
        FileReader fr=null;
        try{
            fr= new FileReader("metadata.json");
        }catch(Exception e){
          File f = new File("metadata.json");  
          try{
            f.createNewFile();
            fr= new FileReader("metadata.json");
          }catch(Exception ex){
              System.err.print("El archivo metadata no existe y no puede ser creado.");
          }
        }
        IngresarDatos();
        Cuerpo json = new Cuerpo(GetDocumento(),GetNombre(),GeTipo(),GetValor(),GetForanea(),GetRequerido());
        
    }
    public void SetDocumento(String dato){
        documento = dato;
    }
    public String GetDocumento(){
        return documento;
    }
    public void SetNombre(String dato){
        nombre = dato;
    }
    public String GetNombre(){
        return nombre;
    }
    public void SetTipo(String dato){
        tipo = dato;
    }
    public String GeTipo(){
        return tipo;
    }
    public void SetForanea(int dato){
        foranea = dato;
    }
    public int GetForanea(){
        return foranea;
    }
    public void SetPrimary(int dato){
        primaria = dato;
    }
    public int GetPrimary(){
        return primaria;
    }
    public void SetValor(String dato){
        valor = dato;
    }
    public String GetValor(){
        return valor;
    }
    public void SeRequerido(int dato){
        requerido = dato;
    }
    public int GetRequerido(){
        return requerido;
    }
    public void IngresarDatos(){
       obj.put("Archivo", GetDocumento()); 
       JSONArray list = new JSONArray();
       JSONObject atributos = new JSONObject();
       atributos.put("\nName", GetNombre());
       atributos.put("\nType",GeTipo());
       atributos.put("\nForanea", GetForanea());
       atributos.put("\nPrimary",GetPrimary());
       atributos.put("\nMandatory", GetRequerido());
       atributos.put("\nDefecto",GetValor());
       list.add(0,atributos);
       obj.put("\natributos", list);
       try(FileWriter file = new FileWriter("metadata.json"))
        {
           file.write(obj.toString());
           file.flush();
        }
        catch(IOException e){
            e.printStackTrace();
            
        }
       
    }
    
}
