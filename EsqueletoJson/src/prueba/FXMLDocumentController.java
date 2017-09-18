/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import esqueletojson.JsonStore;
import esqueletojson.Metadata;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.text.View;

/**
 *
 * @author berta
 */
public class FXMLDocumentController implements Initializable {
    private String nomCarpeta = null;
    JsonStore store = new JsonStore();
    
    @FXML
    public TextField tfCarpeta;
    @FXML
    public TextField tfDatos;
    @FXML
    private TreeView<String> tv_visualizacion;
    
    
    @FXML 
    public void NuevoTitem(ActionEvent evento){
        String nom = tfCarpeta.getText();
        this.nomCarpeta = nom;
        
        TreeItem<Object> item = (TreeItem<Object>) tv_visualizacion.getSelectionModel().getSelectedItems();
        item.getChildren().add(new TreeItem<>(tfCarpeta.getText()));
        
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ObservableList<String> list = FXCollections.observableArrayList("Entero","flotante","String","DateTime");
        //fxtipo.setItems(list);
        TreeItem<String> rooti = new TreeItem<>("Data" );//new ImageView(icon)
        if(this.nomCarpeta != null){
         TreeItem<String> nodeA = new TreeItem<>(nomCarpeta);//, new ImageView(icon)
         rooti.getChildren().addAll(nodeA);
        
        }

        tv_visualizacion.setRoot(rooti);
       

    
    }
    

}
