/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller.Usuario;

import clinicafx.MainApp;
import clinicafx.Model.Cliente.model_cliente;
import clinicafx.Model.Usuario.model_usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho
 * <pedro.marinho238@gmail.com & https://github.com/TECFlyingCommunity>
 */
public class SelectUsuarioController implements Initializable {

    private MainApp mainApp;
    private model_usuario usuario;
    @FXML
    private Button btnPesquisar;

    @FXML
    private TextField PesquisarField;

    @FXML
    private Button btnSelecionar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TableView<model_usuario> ClientesView;

    @FXML
    private TableColumn<model_usuario, String> IDColumn;

    @FXML
    private TableColumn<model_usuario, String> NomeColumn;

    @FXML
    void OnCancelar(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void OnPesquisar(ActionEvent event) {
        if (!PesquisarField.getText().equals("")) {
            ClientesView.setItems(findItems());
        } else {
            ClientesView.setItems(mainApp.getDadosData().getUsuariosData());
        }
    }

    @FXML
    void OnSelecionar(ActionEvent event) {
        usuario = ClientesView.getSelectionModel().getSelectedItem();
        dialogStage.close();
    }

    public void setMainApp(MainApp mainApp) {
        System.out.println("setMainApp");
        this.mainApp = mainApp;
        this.ClientesView.setItems(this.mainApp.getDadosData().getUsuariosData());

    }

    public model_usuario getUsuario() {
        return usuario;
    }

    
    private ObservableList<model_usuario> findItems() {
        ObservableList<model_usuario> itensEncontrados = FXCollections.observableArrayList();
        Integer ID;
        try {
            ID = Integer.parseInt(PesquisarField.getText());

        } catch (NumberFormatException a) {
            ID = null;

        }
        for (model_usuario itens : mainApp.getDadosData().getUsuariosData()) {

            //itens.getID().contains(Integer.valueOf( PesquisaField.getText())
            if (ID != null) {
                if (itens.getID_usuario() == ID) {
                    itensEncontrados.add(itens);

                }
            } else {
                if (itens.getNome().contains(PesquisarField.getText()) || itens.getSobrenome().equalsIgnoreCase(PesquisarField.getText()) || itens.getUsuario().contains(PesquisarField.getText())|| itens.getEmail().contains(PesquisarField.getText())) {
                    itensEncontrados.add(itens);

                }
            }
        }
        return itensEncontrados;
    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID_usuario"));
        NomeColumn.setCellValueFactory(new PropertyValueFactory<>("Usuario"));
    }
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}
