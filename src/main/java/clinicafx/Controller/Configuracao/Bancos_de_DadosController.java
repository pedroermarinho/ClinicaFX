/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller.Configuracao;

import clinicafx.MainApp;
import clinicafx.Model.Configuracao_Local.model_banco_de_dados;
import clinicafx.Model.Usuario.model_usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
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
public class Bancos_de_DadosController implements Initializable {

    private MainApp mainApp;
    private model_banco_de_dados banco_de_dados;
    @FXML
    private Button btnPesquisar;

    @FXML
    private TextField PesquisarField;

    @FXML
    private Button btnSelecionar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ListView<model_banco_de_dados> BancosView;

    @FXML
    void OnCancelar(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void OnPesquisar(ActionEvent event) {
        if (!PesquisarField.getText().equals("")) {
            BancosView.setItems(findItems());
        } else {
            BancosView.setItems(mainApp.getDadosData().getBancos_de_DadosData());
        }

    }

    @FXML
    void OnADD(ActionEvent event) {
        mainApp.getTelas().CadastroBancoDeDados();
    }

    @FXML
    void OnSelecionar(ActionEvent event) {
        banco_de_dados = BancosView.getSelectionModel().getSelectedItem();
        dialogStage.close();
    }

    public void setMainApp(MainApp mainApp) {
        System.out.println("setMainApp");
        this.mainApp = mainApp;
        BancosView.setItems(mainApp.getDadosData().getBancos_de_DadosData());

    }

    public model_banco_de_dados getBanco_de_Dados() {
        return banco_de_dados;
    }

    private ObservableList<model_banco_de_dados> findItems() {
        ObservableList<model_banco_de_dados> itensEncontrados = FXCollections.observableArrayList();
        Integer ID;
        try {
            ID = Integer.parseInt(PesquisarField.getText());

        } catch (NumberFormatException a) {
            ID = null;

        }
        for (model_banco_de_dados itens : mainApp.getDadosData().getBancos_de_DadosData()) {

            //itens.getID().contains(Integer.valueOf( PesquisaField.getText())
            if (ID != null) {
                if (itens.getID_banco_de_dados() == ID) {
                    itensEncontrados.add(itens);

                }
            } else {
                if (itens.getHost().contains(PesquisarField.getText()) || itens.getUser().equalsIgnoreCase(PesquisarField.getText()) || itens.getDataBase().contains(PesquisarField.getText())) {
                    itensEncontrados.add(itens);

                }
            }
        }
        return itensEncontrados;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnSelecionar.disableProperty().bind(BancosView.getSelectionModel().selectedItemProperty().isNull());

    }
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}
