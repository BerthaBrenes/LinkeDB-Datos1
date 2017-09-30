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
    TextInputDialog dialog = new TextInputDialog();
    @FXML
    TextInputDialog atributos = new TextInputDialog();
    private final String[] arrayData = {"entero", "date", "flotante"};
    private List<String> dialogData;

    TreeItem<String> root = new TreeItem<String>("Data");
    private Prueba2Controller ventada2;
    JsonStore store = new JsonStore();
    String jsonAtributo;
    Json objetos;
/**
 * a raiz de un evento crea las carpetas
 * @param event 
 */
    @FXML
    private void CrearCarpeta(ActionEvent event) {
        String carpeta = tfStore.getText();
        System.out.println(carpeta);
        TreeItem<String> item = (TreeItem<String>) tvDatos.getSelectionModel().getSelectedItem();
        if (!tvDatos.getSelectionModel().isEmpty() && !carpeta.trim().isEmpty() && item.getParent() == null) {
            store.nuevoNodo(carpeta);
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
/**
 * A raiz de un evento crea las documentos de las carpetas
 * @param event 
 */
    @FXML
    private void CrearDocumento(ActionEvent event) {
        String documenti = tfDocuemento.getText();
        System.out.println(documenti);
        TreeItem<String> item = (TreeItem<String>) tvDatos.getSelectionModel().getSelectedItem();
        if (!tvDatos.getSelectionModel().isEmpty()) {
            if (item.getParent() != null) {
                if (!documenti.trim().isEmpty() && !tvDatos.getSelectionModel().isEmpty() && item.getParent().getValue() == "Data") {
                    item.getChildren().add(new TreeItem<>(documenti));
                    System.out.println(item.getValue());
                    System.out.println(item.getParent());
                    Documentos documento1 = new Documentos(item.getValue());
                    documento1.AgregarJson(documenti);
                } else {
                    alert.setTitle("Aviso");
                    alert.setHeaderText(null);
                    alert.setContentText("Necesita ingresar un nombre valido para crear un documento y seleccionar una carpeta");
                    System.err.print("Necesita ingresar un nombre valido");
                    alert.showAndWait();
                }
            } else {
                alert.setTitle("Aviso");
                alert.setHeaderText(null);
                alert.setContentText("Necesita seleccionar una carpeta diferente de Data");
                System.err.print("Necesita ingresar un nombre valido");
                alert.showAndWait();
            }
        } else {
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Necesita seleccionar una carpeta");
            System.err.print("Necesita ingresar un nombre valido");
            alert.showAndWait();
        }

    }
/**
 * A raiz de un evento llama a la segunda ventana donde voy detallando la informacion de los nuevos atributos
 * @param event 
 */
    @FXML
    private void AnadirAtributo(ActionEvent event) {
        TreeItem<String> item = (TreeItem<String>) tvDatos.getSelectionModel().getSelectedItem();
        if (!tvDatos.getSelectionModel().isEmpty()) {
            if (item.getParent() != null) {
                if (item.isLeaf() && item.getParent().getValue() != "Data") {
                    System.out.println(item.getValue());
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
                } else {
                    alert.setTitle("Aviso");
                    alert.setHeaderText(null);
                    alert.setContentText("Necesita seleccionar el documento donde se va a almacenar los atributos");
                    System.err.print("Necesita seleccionar el documento");
                    alert.showAndWait();
                }
            } else {
                alert.setTitle("Aviso");
                alert.setHeaderText(null);
                alert.setContentText("El documento no debe ser la Data");
                System.err.print("Necesita seleccionar el documento");
                alert.showAndWait();
            }
        } else {
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Necesita seleccionar el documento donde se va a almacenar los atributos");
            System.err.print("Necesita seleccionar el documento");
            alert.showAndWait();
        }
    }
/**
 * A raiz de un evento, Almacena los atributos de las listas en la metadata y en el JSon
 * @param event 
 */
    @FXML
    private void Commit(ActionEvent event) {
        TreeItem<String> item = (TreeItem<String>) tvDatos.getSelectionModel().getSelectedItem();
        if (!tvDatos.getSelectionModel().isEmpty()) {

            if (item.isLeaf() && item.getParent().getValue() != "Data") {
                this.objetos = DocFabrica.getInstance().get(item.getParent().getValue(), item.getValue());

                int iterador = objetos.getLista().Largo();
                for (int i = 0; i < iterador; i++) {
                    System.out.println("agregando:" + i);
                    objetos.CommitAtributo(objetos.getLista().Iterador(i).get("nombre").toString(), objetos.getLista().Iterador(i).get("valor").toString());
                    objetos.GuardarAtributoMetadata(objetos.getLista().Iterador(i));
                }

            } else {
                alert.setTitle("Aviso");
                alert.setHeaderText(null);
                alert.setContentText("Necesita seleccionar el documento donde se va a almacenar los atributos");
                System.err.print("Necesita seleccionar el documento");
                alert.showAndWait();
            }
        } else {
            alert.setTitle("Aviso");
            alert.setHeaderText(null);
            alert.setContentText("Necesita seleccionar el documento");
            System.err.print("Necesita seleccionar el documento");
            alert.showAndWait();
        }
    }
/**
 * Inicia todo el fxml de la pagina principal
 * Ademas inicializa con la informacion de la metadata los TreeView
 * @param url
 * @param rb 
 */
    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
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
        private ContextMenu MenuCar = new ContextMenu();
        private ContextMenu MenuDocs = new ContextMenu();

        /**
         * es la implementacion de los menuContent
         * retorna menus
         */
        public TextFieldTreeCellImpl() {
            MenuItem agregarC = new MenuItem("Agregar Documento");
            MenuItem eliminarC = new MenuItem("Eliminar");
            MenuItem eliminarD = new MenuItem("Eliminar");
            MenuCar.getItems().addAll(agregarC, eliminarC);
            MenuDocs.getItems().add(eliminarD);
            /**
             * Agrega un documento a una carpeta
             */
            agregarC.setOnAction(new EventHandler() {
                public void handle(Event t) {
                    dialog.setTitle("Nombre documento");
                    dialog.setHeaderText(null);
                    dialog.setContentText("Npmbre del documento:");
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        System.out.println("Your name: " + result.get());
                    }

                    TreeItem nuevoDocumento = new TreeItem<>(result.get());
                    getTreeItem().getChildren().add(nuevoDocumento);
                    Documentos documento1 = new Documentos(getStringF());
                    documento1.AgregarJson(result.get());
                    //store.nuevoNodo(result.get());

                }
            });
            /**
             * Elimina una carpeta con todo el contenido dentro
             */
            eliminarC.setOnAction(new EventHandler() {
                public void handle(Event t) {
                    store.Eliminar(getStringF());
                    TreeItem<String> item = (TreeItem<String>) tvDatos.getSelectionModel().getSelectedItem();
                    item.getParent().getChildren().remove(item);
                    updateItem(getStringF(), true);
                    tvDatos.refresh();

                }
            });
            /**
             * Funcion que elimina el documento seleccionado
             */
            eliminarD.setOnAction(new EventHandler() {
                public void handle(Event t) {
                    System.out.println("docu: " + getStringF()+" algo:" + getTreeItem().getParent().getValue() );
                    Documentos documentos = new Documentos(getTreeItem().getParent().getValue());
                    documentos.EliminarJson(getStringF());
                    TreeItem<String> item = (TreeItem<String>) tvDatos.getSelectionModel().getSelectedItem();
                    item.getParent().getChildren().remove(item);
                    updateItem(getStringF(), true);
                    tvDatos.refresh();

                }
            });
        }

        @Override
        /**
         * cancela el menu content
         */
        public void cancelEdit() {
            super.cancelEdit();

            setText((String) getItem());
            setGraphic(getTreeItem().getGraphic());
        }

        @Override
        /**
         * Crea los menus para cada hijo y ademas refresca el treeView
         */
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
                    setContextMenu(MenuCar);
                } else if (getTreeItem().getParent() != null && getTreeItem().isLeaf()) {
                    if (getTreeItem().getParent().getValue() == "Data") {
                        setContextMenu(MenuCar);
                    }else{
                        setContextMenu(MenuDocs);
                        
                    }

                } else {
                    // setContextMenu(agregarMenu);
                }

            }
        }


        private String getStringF() {
            return getItem() == null ? "" : getItem().toString();
        }
    }

}
