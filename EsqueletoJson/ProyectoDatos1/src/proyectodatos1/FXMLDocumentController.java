/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectodatos1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

/**
 *
 * @author berta
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField tfNombre = new TextField();
    @FXML
    private TreeView tvDatos = new TreeView();
    TreeItem<String> root = new TreeItem<String>("Data");
  
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        String  valor = tfNombre.getText();
        System.out.println("You clicked me!");
        label.setText(valor);
        TreeItem<String> item = (TreeItem<String>) tvDatos.getSelectionModel().getSelectedItem();
        item.getChildren().add(new TreeItem<String>(tfNombre.getText()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    
  TreeItem<String> nodeA = new TreeItem<String>("nodeA");
  TreeItem<String> nodeB = new TreeItem<String>("nodeB");
  TreeItem<String> nodeC = new TreeItem<String>("nodeC");
  
  TreeItem<String> nodeA1 = new TreeItem<String>("nodeA1");
  TreeItem<String> nodeA2 = new TreeItem<String>("nodeB2 ");
  TreeItem<String> nodeA3 = new TreeItem<String>("nodeC3");
  nodeA.getChildren().addAll(nodeA1,nodeA2,nodeA3);
  tvDatos.setEditable(true);
  tvDatos.setCellFactory(new Callback<TreeView<String>,TreeCell<String>>(){
      
      @Override
      public TreeCell<String> call(TreeView<String> p){
          return new TextFieldTreeCellImpl();
      }
  });
  root.getChildren().addAll(nodeA,nodeB,nodeC);
  tvDatos.setRoot(root);
   }  
   private final class TextFieldTreeCellImpl extends TreeCell<String> {
 
        private TextField textField;
        private ContextMenu addMenu = new ContextMenu();
 
        public TextFieldTreeCellImpl() {
            MenuItem addMenuItem = new MenuItem("Agregar Documento");
            addMenu.getItems().add(addMenuItem);
            addMenuItem.setOnAction(new EventHandler() {
                public void handle(Event t) {
                    TreeItem newEmployee = 
                        new TreeItem<String>("Documento1");
                            getTreeItem().getChildren().add(newEmployee);
                }
            });
        }
 /**
        @Override
        public void startEdit() {
            super.startEdit();
 
            if (textField == null) {
                //createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }*/
 
        @Override
        public void cancelEdit() {
            super.cancelEdit();
 
            setText((String) getItem());
            setGraphic(getTreeItem().getGraphic());
        }
 
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
 
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(getTreeItem().getGraphic());
                    if (
                        !getTreeItem().isLeaf()&&getTreeItem().getParent()!= null
                    ){
                        setContextMenu(addMenu);
                    }
                }
            }
        }
        /**
        private void createTextField() {
            textField = new TextField(getString());
            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {
 
                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(textField.getText());
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });  
            
        }*/
 
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
   public static class Store{

        public String getCarpeta() {
            return carpeta.get();
        }

        public String getDocumento() {
            return documento.get();
        }
        public void setCarpeta(String fName) {
            carpeta.set(fName);
        }
        public void setDocumento(String dName){
            documento.setValue(dName);
        }
       private final SimpleStringProperty carpeta;
       private final SimpleStringProperty documento;
       
       private Store(String carpetas, String documentos){
           this.carpeta = new SimpleStringProperty(carpetas);
           this.documento = new SimpleStringProperty(documentos);
       }
       
   }
}
