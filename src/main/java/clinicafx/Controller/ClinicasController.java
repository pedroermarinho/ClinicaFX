/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller;

import clinicafx.MainApp;
import clinicafx.Model.Cliente.model_cliente;
import clinicafx.Model.Clinicas.model_clinica;
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
public class ClinicasController implements Initializable {

    private MainApp mainApp;
    private model_clinica clinica;
    @FXML
    private Button btnPesquisar;

    @FXML
    private TextField PesquisarField;

    @FXML
    private Button btnSelecionar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TableView<model_clinica> ClinicasView;

    @FXML
    private TableColumn<model_clinica, String> IDColumn;

    @FXML
    private TableColumn<model_clinica, String> NomeColumn;

    @FXML
    void OnCancelar(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    void OnPesquisar(ActionEvent event) {
         if (!PesquisarField.getText().equals("")) {
            ClinicasView.setItems(findItems());
        } else {
            ClinicasView.setItems(mainApp.getDadosData().getClinicaData());
        }
    }

    @FXML
    void OnSelecionar(ActionEvent event) {
        clinica = ClinicasView.getSelectionModel().getSelectedItem();
        dialogStage.close();
    }

    public void setMainApp(MainApp mainApp) {
        System.out.println("setMainApp");
        this.mainApp = mainApp;
        this.ClinicasView.setItems(this.mainApp.getDadosData().getClinicaData());

    }

    public model_clinica getclinica() {
        return clinica;
    }
        private ObservableList<model_clinica> findItems() {
        ObservableList<model_clinica> itensEncontrados = FXCollections.observableArrayList();
        Integer ID;
        try {
            ID = Integer.parseInt(PesquisarField.getText());

        } catch (NumberFormatException a) {
            ID = null;

        }
        for (model_clinica itens : mainApp.getDadosData().getClinicaData()) {

            //itens.getID().contains(Integer.valueOf( PesquisaField.getText())
            if (ID != null) {
                if (itens.getID_clinica() == ID) {
                    itensEncontrados.add(itens);

                }
            } else {
                if (itens.getNome().contains(PesquisarField.getText()) ) {
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
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID_clinica"));
        NomeColumn.setCellValueFactory(new PropertyValueFactory<>("Nome"));
    }
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}
