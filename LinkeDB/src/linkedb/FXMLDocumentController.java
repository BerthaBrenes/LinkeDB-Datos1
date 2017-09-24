/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedb;

import esqueletojson.JsonStore;
//import java.awt.Image;
import java.awt.TextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author berta
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML  private TextField tfCarpeta = new TextField();
    @FXML  public TextField tf_datos = new TextField();
    @FXML  public TextField tfNombre = new TextField();
    @FXML  public TreeView<String> tv_visual;
    @FXML  public Button bt_Documento;
    @FXML  private ListView listView;
    @FXML  private Label labelNomCarpeta;
    @FXML  private Label labelNomDocumento;
    @FXML  private Label labelNom;
    @FXML  private Label labelValor;
    @FXML  private Label labelTipo;
    @FXML  private Label labelLlave;
    //Image icon = new Image(getClass().getResourceAsStream("img/foldericon,.png")) {};
   

   
    @FXML void VentanaAtributos(ActionEvent event) throws IOException{
        try{
        FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("Atributos.fxml"));
        Parent root1 = (Parent) fxmloader.load();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Atributos");
        stage.setScene(new Scene(root1));
        stage.show();
    }catch(Exception e){
            System.err.println("no se abre");
    }}
    /**
     * Metodo que abre los documentos existentes en un tableview
     *
     * @param event 
     */
    public void DocumentoBt( ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("/home/berta/ownCloud/NetBeansProjects/Proyecto1Datos1/EsqueletoJson/data/"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Documentos Json", "*.json"));
        File selectFile = fc.showOpenDialog(null);
        if (selectFile != null){
          listView.getItems().add(selectFile.getName());
        }else{
            System.out.println("no sirve como todo lo de est progra");
        }
    }
   /**
    * crear una nueva carpeta
    * @param evento 
    */
    @FXML public void NuevoTitem(ActionEvent evento){
        
        System.out.println(tfCarpeta.getText());
        //this.nomCarpeta = nom;
        //TreeItem<String> item = tv_visual.getSelectionModel().getSelectedItems();
       // item.getChildren().add(new TreeItem<String>(tf_carpeta.getText()));
        JsonStore.Inicializar();
        JsonStore.nuevoNodo(tfCarpeta.getText());
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         TreeItem<String> root = new TreeItem<String>("Data");
  
  TreeItem<String> nodeA = new TreeItem<String>("nodeA");
  TreeItem<String> nodeB = new TreeItem<String>("nodeB");
  TreeItem<String> nodeC = new TreeItem<String>("nodeC");
  
  TreeItem<String> nodeA1 = new TreeItem<String>("nodeA1");
  TreeItem<String> nodeA2 = new TreeItem<String>("nodeB2 ");
  TreeItem<String> nodeA3 = new TreeItem<String>("nodeC3");
  nodeA.getChildren().addAll(nodeA1,nodeA2,nodeA3);
  
  root.getChildren().addAll(nodeA,nodeB,nodeC);
  
  tv_visual.setRoot(root);
    }
    public void mouseClick(MouseEvent mouseEvent){
        TreeItem<String> item = tv_visual.getSelectionModel().getSelectedItem();
        System.out.print(item.getValue());
    }

}
