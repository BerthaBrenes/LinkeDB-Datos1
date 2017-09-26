/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectolinkedfinal;

import ManejoDatos.DocFabrica;
import ManejoDatos.Json;
import ManejoDatos.Metadata;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author berta
 */

public class Prueba2Controller implements Initializable {
    @FXML private TextField tfNomAtributo = new TextField();
    @FXML private TextField tfValorAtributo = new TextField();
    @FXML private ComboBox cbllave;
    @FXML private ComboBox cbrequerido;
    @FXML private ComboBox cbTipo;
    ObservableList<String> listllave = FXCollections.observableArrayList("flotante","primaria");
    ObservableList<String> listtipo = FXCollections.observableArrayList("entero","flotante","fecha-hora","cadena");
    ObservableList<String> listrequerido = FXCollections.observableArrayList("False","True");
    public String Documentojson;
    private Json atributos;
    public String Nomcarpeta;

    //public void setCarpeta(String nombre){
        //System.out.println(nombre);
        //jsonCarpeta = nombre;
    //}
    @FXML
    private void NombrePRueba(ActionEvent event) {
        System.out.print(this.Documentojson);
        //this.objetos.AgregarJson(this.jsonAtributo);

    }
    
    @FXML
    private void AtributosEnlista(ActionEvent event){
      // System.out.println(this.Nomcarpeta + "Documento: "+this.Documentojson );
       Metadata temporales = new Metadata(this.Nomcarpeta);
       //temporales.Agregar();
        temporales.CrearTemporales();
       
       
       String nombre =  tfNomAtributo.getText();
       String tipo = cbTipo.getValue().toString();
       String llave = cbllave.getValue().toString();
       String valor = tfValorAtributo.getText();
       String requerido = cbrequerido.getValue().toString();
       if(!nombre.trim().isEmpty() && !tipo.isEmpty() && !llave.isEmpty() && !requerido.isEmpty()){
           this.atributos = DocFabrica.getInstance().get(this.Nomcarpeta, this.Documentojson);
          // this.atributos = new Json(this.Nomcarpeta, this.Documentojson);
           this.atributos.AgregarAtributos(nombre, valor, tipo, llave, requerido);
           
       }else{
           
       }
    }
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbTipo.setItems(listtipo);
        cbllave.setItems(listllave);
        cbrequerido.setItems(listrequerido);
        tfValorAtributo.setText("Default");
        
        
    }    
    
}
