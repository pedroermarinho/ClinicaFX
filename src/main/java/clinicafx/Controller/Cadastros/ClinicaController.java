/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller.Cadastros;

import clinicafx.MainApp;
import clinicafx.Model.Cliente.model_cliente;
import clinicafx.Model.Clinicas.model_clinica;
import static clinicafx.Util.MsgErro.MessagemErroFormulario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho
 * <pedro.marinho238@gmail.com & https://github.com/TECFlyingCommunity>
 */
public class ClinicaController implements Initializable {

    private MainApp mainApp;
    private model_clinica modifição_clinica;

    public void setMainApp(MainApp mainApp) {
        modifição_clinica = null;
        this.mainApp = mainApp;
        registrosView.setItems(this.mainApp.getDadosData().getClinicaData());
    }

    @FXML
    private TableView<model_clinica> registrosView;

    @FXML
    private TableColumn<model_clinica, Integer> IDColumn;

    @FXML
    private TableColumn<model_clinica, String> ClinicaColumn;

    @FXML
    private MenuItem bntDetalhes;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnNovo;

    @FXML
    private TextField NomeClinicaText;

    @FXML
    private Button bntSalva;

    @FXML
    private Button BtnCancelar;
    
    @FXML
    private TextField PesquisarField;

    @FXML
    void OnPesquisar(ActionEvent event) {
        if (!PesquisarField.getText().equals("")) {
            registrosView.setItems(findItems());
        } else {
            registrosView.setItems(mainApp.getDadosData().getClinicaData());
        }
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
                if (itens.getID_clinica()== ID) {
                    itensEncontrados.add(itens);

                }
            } else {
                if (itens.getNome().contains(PesquisarField.getText())) {
                    itensEncontrados.add(itens);

                }
            }
        }
        return itensEncontrados;
    }

    @FXML
    void OnCancelar(ActionEvent event) {
        NomeClinicaText.setText("");
        modifição_clinica = null;
        On_Off_Button(true);
    }

    @FXML
    void OnDeletar(ActionEvent event) {

        System.out.println("DeletaPessoa");
        model_clinica selected = registrosView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.delete();
            mainApp.getDadosData().getClinicaData();
        } else {

        }

    }

    @FXML
    void OnDetalhes(ActionEvent event) {
        On_Off_Button(true);
        modifição_clinica = registrosView.getSelectionModel().getSelectedItem();
        NomeClinicaText.setText(modifição_clinica.getNome());

    }

    @FXML
    void OnEditar(ActionEvent event) {
        On_Off_Button(false);
        modifição_clinica = registrosView.getSelectionModel().getSelectedItem();
        NomeClinicaText.setText(modifição_clinica.getNome());

    }

    @FXML
    void OnNovo(ActionEvent event) {
        modifição_clinica = null;

        On_Off_Button(false);

        NomeClinicaText.setText("");

    }

    @FXML
    void OnSalvar(ActionEvent event) {
        if (modifição_clinica == null) {
            modifição_clinica = new model_clinica(mainApp);
        }
        if (isInputValid()) {
            modifição_clinica.setNome(NomeClinicaText.getText());
            NomeClinicaText.setText("");
            modifição_clinica.save();
            modifição_clinica = null;
            mainApp.getDadosData().getClinicaData();
            On_Off_Button(true);
        }

    }

    /**
     * Valida a entrada do usuário nos campos de texto.
     *
     * @return true se a entrada é válida
     */
    private boolean isInputValid() {
        System.out.println("isInputValid");
        String errorMessage = "";

        if (NomeClinicaText.getText() == null || NomeClinicaText.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
            NomeClinicaText.setStyle("-fx-border-color:red");
        } else {
            NomeClinicaText.setStyle("");
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            MessagemErroFormulario(errorMessage);
//            System.out.println(errorMessage);
//           
            return false;
        }
    }

    private void On_Off_Button(boolean es) {
        NomeClinicaText.setDisable(es);
        bntSalva.setDisable(es);
        BtnCancelar.setDisable(es);
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        bntDetalhes.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());
        btnEditar.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());
        btnDeletar.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());

        On_Off_Button(true);

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID_clinica"));
        ClinicaColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

    }
}
