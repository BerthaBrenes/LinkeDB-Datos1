/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectolinkedfinal;

import ManejoDatos.DocFabrica;
import ManejoDatos.Documentos;
import ManejoDatos.Json;
import ManejoDatos.JsonStore;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import logica.ListaCir;
import logica.ListaDo;

/**
 *
 * @author berta
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField tfStore = new TextField();
    @FXML
    private TextField tfDocuemento = new TextField();
    @FXML
    private TreeView tvDatos = new TreeView();
    @FXML
    private ChoiceBox<String> cbLlave = new ChoiceBox<>(FXCollections.observableArrayList("Primaria", "Foranea"));
    @FXML
    private ChoiceBox<String> cbTipo = new ChoiceBox<>(FXCollections.observableArrayList("entero", "String", "Fecha", "flotante"));
    @FXML
    private Alert alert = new Alert(Alert.AlertType.WARNING);
    @FXML
    TextInputDialog atributos = new TextInputDialog();
    private final String[] arrayData = {"entero", "date", "flotante"};
    private List<String> dialogData;

    TreeItem<String> root = new TreeItem<String>("Data");
    private Prueba2Controller ventada2;
    JsonStore store = new JsonStore();
    String jsonAtributo;
    Json objetos;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        String carpeta = tfStore.getText();
        System.out.println(carpeta);
        if (!carpeta.trim().isEmpty()) {
            store.nuevoNodo(carpeta);
            TreeItem<String> item = (TreeItem<String>) tvDatos.getSelectionModel().getSelectedItem();
            item.getChildren().add(new TreeItem<>(carpeta));
            System.out.println(item.isLeaf());
        } else {
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Necesita ingresar un nombre valido para crear una carpeta y ademas seleccionar data");
            System.err.print("Necesita ingresar un nombre valido");
            alert.showAndWait();
        }

    }

    @FXML
    private void CrearDocumento(ActionEvent event) {
        String documenti = tfDocuemento.getText();
        System.out.println(documenti);
        TreeItem<String> item = (TreeItem<String>) tvDatos.getSelectionModel().getSelectedItem();
        if (!documenti.trim().isEmpty() && item.getParent().getValue() == "Data") {

            item.getChildren().add(new TreeItem<>(documenti));
            //System.out.println(item.toString());
            System.out.println(item.getValue());
            System.out.println(item.getParent());
            Documentos documento1 = new Documentos(item.getValue());
            documento1.AgregarJson(documenti);
        } else {
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Necesita ingresar un nombre valido para crear un documento y seleccionar la carpeta donde se va a almacenar el dato");
            System.err.print("Necesita ingresar un nombre valido");
            alert.showAndWait();
        }

    }

    @FXML
    private void AnadirAtributo(ActionEvent event) {
        TreeItem<String> item = (TreeItem<String>) tvDatos.getSelectionModel().getSelectedItem();
        if (item.isLeaf() && item.getParent().getValue() != "Data") {
            System.out.println(item.getValue());
            //this.ventada2.setCarpeta(item.getValue());
            //this.objetos = new Json(item.getValue());
            try {
                FXMLLoader fxmloader = new FXMLLoader(getClass().getResource("prueba2.fxml"));

                Parent root1 = (Parent) fxmloader.load();
                Stage stage = new Stage();
                stage.initStyle(StageStyle.DECORATED);
                stage.setTitle("Atributos");
                stage.setScene(new Scene(root1));
                stage.show();
                Prueba2Controller controller = fxmloader.getController();
                controller.Documentojson = item.getValue();
                controller.Nomcarpeta = item.getParent().getValue();
                System.out.println(item.getParent().getValue());
            } catch (Exception e) {
                System.err.println("no se abre");
            }
        }else{
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Necesita seleccionar el documento donde se va a almacenar los atributos");
            System.err.print("Necesita seleccionar el documento");
            alert.showAndWait();
        }
    }
    
    @FXML
    private void Commit(ActionEvent event) {
        TreeItem<String> item = (TreeItem<String>) tvDatos.getSelectionModel().getSelectedItem();
        if (item.isLeaf() && item.getParent().getValue() != "Data"){
          this.objetos = DocFabrica.getInstance().get(item.getParent().getValue(), item.getValue());
          
           int iterador = objetos.getLista().Largo();
          for (int i =0; i < iterador;i++ ){
              System.out.println("agregando:"+i);
              objetos.CommitAtributo(objetos.getLista().Iterador(i).get("nombre").toString(),objetos.getLista().Iterador(i).get("valor").toString());
              objetos.GuardarAtributoMetadata(objetos.getLista().Iterador(i));
          }
          
        }else{
             alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Necesita seleccionar el documento donde se va a almacenar los atributos");
            System.err.print("Necesita seleccionar el documento");
            alert.showAndWait();
        }
    }

 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int iteradorC = store.getLargolista();
        ListaDo<String> carpetasTree = store.getLista();
        for (int i = 0; i < iteradorC; i++) {
            TreeItem<String> nombre = new TreeItem<>(carpetasTree.Iterador(i));
            Documentos documentos = new Documentos(carpetasTree.Iterador(i));
           int iteradorD = documentos.getLargolista();
            System.out.print(iteradorD);
            ListaCir<String> documentosTree = documentos.getLista();
            for (int j = 0; j < iteradorD; j++) {
                TreeItem<String> documento = new TreeItem<>(documentosTree.Iterador(j));
                nombre.getChildren().add(documento);
            }
            root.getChildren().add(nombre);
        }
        tvDatos.setEditable(true);
        tvDatos.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {

            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return new TextFieldTreeCellImpl();
            }
        });
        root.setExpanded(true);
        tvDatos.setRoot(root);
    }

    private final class TextFieldTreeCellImpl extends TreeCell<String> {

        private TextField textField;
        private ContextMenu addMenu = new ContextMenu();

        public TextFieldTreeCellImpl() {
            MenuItem agregar = new MenuItem("Agregar Documento");
            MenuItem eliminar = new MenuItem("Eliminar");
            addMenu.getItems().addAll(agregar, eliminar);
            agregar.setOnAction(new EventHandler() {
                public void handle(Event t) {
                    TreeItem newEmployee
                            = new TreeItem<>("Documento1");
                    getTreeItem().getChildren().add(newEmployee);
                }
            });
            eliminar.setOnAction(new EventHandler() {
                public void handle(Event t) {
                    System.out.print(getStringF());
                }
            });
        }

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
            } else if (isEditing()) {
                if (textField != null) {
                    textField.setText(getStringF());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getStringF());
                setGraphic(getTreeItem().getGraphic());
                if (!getTreeItem().isLeaf() && getTreeItem().getParent() != null) {
                    setContextMenu(addMenu);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getStringF());
            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode()
                            == KeyCode.ENTER) {
                        commitEdit(textField.getText());
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });

        }

        private String getStringF() {
            return getItem() == null ? "" : getItem().toString();
        }
    }

}
