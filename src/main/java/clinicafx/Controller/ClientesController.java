/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller;

import clinicafx.MainApp;
import clinicafx.Model.Cliente.model_cliente;
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
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class ClientesController implements Initializable {

    private MainApp mainApp;
    private model_cliente cliente;
    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnSelecionar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TableView<model_cliente> ClientesView;

    @FXML
    private TableColumn<model_cliente, String> IDColumn;

    @FXML
    private TableColumn<model_cliente, String> NomeColumn;

    @FXML
    private TextField PesquisarField;

    @FXML
    void OnPesquisar(ActionEvent event) {
        if (!PesquisarField.getText().equals("")) {
            ClientesView.setItems(findItems());
        } else {
            ClientesView.setItems(mainApp.getDadosData().getClientesData());
        }
    }

    private ObservableList<model_cliente> findItems() {
        ObservableList<model_cliente> itensEncontrados = FXCollections.observableArrayList();
        Integer ID;
        try {
            ID = Integer.parseInt(PesquisarField.getText());

        } catch (NumberFormatException a) {
            ID = null;

        }
        for (model_cliente itens : mainApp.getDadosData().getClientesData()) {

            //itens.getID().contains(Integer.valueOf( PesquisaField.getText())
            if (ID != null) {
                if (itens.getID_cliente() == ID) {
                    itensEncontrados.add(itens);

                }
            } else {
                if (itens.getCPF().contains(PesquisarField.getText()) || itens.getCartao_SUS().equalsIgnoreCase(PesquisarField.getText()) || itens.getNome().contains(PesquisarField.getText())) {
                    itensEncontrados.add(itens);

                }
            }
        }
        return itensEncontrados;
    }

    @FXML
    void OnSelecionar(ActionEvent event) {
        cliente = ClientesView.getSelectionModel().getSelectedItem();
        dialogStage.close();
    }

    public void setMainApp(MainApp mainApp) {
        System.out.println("setMainApp");
        this.mainApp = mainApp;
        this.ClientesView.setItems(this.mainApp.getDadosData().getClientesData());

    }

    public model_cliente getclinte() {
        return cliente;
    }

    @FXML
    void OnCancelar(ActionEvent event) {
        dialogStage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID_cliente"));
        NomeColumn.setCellValueFactory(new PropertyValueFactory<>("Nome"));
    }
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}
