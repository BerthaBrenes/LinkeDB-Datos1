/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedb;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 *
 * @author berta
 */
public class FXMLDocumentController implements Initializable {
    private String nomCarpeta = null;
    @FXML 
    public TextField tf_carpeta;
    @FXML 
    public TextField tf_datos;
    @FXML
    public TreeView<String> tv_visual;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        
    }
    @FXML 
    public void NuevoTitem(ActionEvent evento){
        //String nom = tf_carpeta.getText();
        //this.nomCarpeta = nom;
        
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         TreeItem<String> root = new TreeItem<String>("root");
  
  TreeItem<String> nodeA = new TreeItem<String>("nodeA");
  TreeItem<String> nodeB = new TreeItem<String>("nodeB");
  TreeItem<String> nodeC = new TreeItem<String>("nodeC");
  
  TreeItem<String> nodeA1 = new TreeItem<String>("nodeA1");
  TreeItem<String> nodeA2 = new TreeItem<String>("nodeB2");
  TreeItem<String> nodeA3 = new TreeItem<String>("nodeC3");
  nodeA.getChildren().addAll(nodeA1,nodeA2,nodeA3);
  
  root.getChildren().addAll(nodeA,nodeB,nodeC);
  
  tv_visual.setRoot(root);
    }

}
