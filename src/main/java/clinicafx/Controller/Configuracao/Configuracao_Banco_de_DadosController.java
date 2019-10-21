/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller.Configuracao;

import clinicafx.MainApp;
import clinicafx.Model.Configuracao_Local.model_banco_de_dados;
import clinicafx.Model.Usuario.model_agenda;
import static clinicafx.Util.MsgErro.MessagemErroFormulario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class Configuracao_Banco_de_DadosController implements Initializable {

    private MainApp mainApp;
    private model_banco_de_dados modificao_banco_dados;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        BancosView.setItems(mainApp.getDadosData().getBancos_de_DadosData());
    }
    @FXML
    private ListView<model_banco_de_dados> BancosView;

    @FXML
    private MenuItem bntDetalhes;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnNovo;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField HostField;

    @FXML
    private TextField UserField;

    @FXML
    private TextField PasswordField;

    @FXML
    private TextField DataBaseField;

    @FXML
    private TextField PrefixField;

    @FXML
    private TextField PortField;

    @FXML
    void OnCancelar(ActionEvent event) {
        On_Off_Button(true);

        LimparCampo();
    }

    @FXML
    void OnDeletar(ActionEvent event) {
        model_banco_de_dados selected = BancosView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.delete();
            mainApp.getDadosData().getBancos_de_DadosData();
        } else {

        }
    }

    @FXML
    void OnSalvar(ActionEvent event) {
        if (modificao_banco_dados == null) {
            modificao_banco_dados = new model_banco_de_dados();
        }
        if (isInputValid()) {
            modificao_banco_dados = new model_banco_de_dados();
            modificao_banco_dados.setDataBase(DataBaseField.getText());
            modificao_banco_dados.setHost(HostField.getText());
            modificao_banco_dados.setPassword(PasswordField.getText());
            modificao_banco_dados.setPorts(Integer.valueOf(PortField.getText()));
            modificao_banco_dados.setPrefix(PrefixField.getText());
            modificao_banco_dados.setUser(UserField.getText());
            modificao_banco_dados.save();
            LimparCampo();
            On_Off_Button(true);
            mainApp.getDadosData().getBancos_de_DadosData();

        }

    }

    @FXML
    void OnDetalhes(ActionEvent event) {
        On_Off_Button(true);
        DataBaseField.setText(modificao_banco_dados.getDataBase());
        HostField.setText(modificao_banco_dados.getHost());
        PasswordField.setText(modificao_banco_dados.getPassword());
        PortField.setText(String.valueOf(modificao_banco_dados.getPorts()));
        PrefixField.setText(modificao_banco_dados.getPrefix());
        UserField.setText(modificao_banco_dados.getUser());
    }

    @FXML
    void OnEditar(ActionEvent event) {
        On_Off_Button(false);
        DataBaseField.setText(modificao_banco_dados.getDataBase());
        HostField.setText(modificao_banco_dados.getHost());
        PasswordField.setText(modificao_banco_dados.getPassword());
        PortField.setText(String.valueOf(modificao_banco_dados.getPorts()));
        PrefixField.setText(modificao_banco_dados.getPrefix());
        UserField.setText(modificao_banco_dados.getUser());
    }

    @FXML
    void OnNovo(ActionEvent event) {
        On_Off_Button(false);
        LimparCampo();
    }

    private boolean isInputValid() {
        System.out.println("isInputValid");
        String errorMessage = "";

        if (DataBaseField.getText() == null || DataBaseField.getText().length() == 0) {
            errorMessage += "DataBase inválido!\n";
            DataBaseField.setStyle("-fx-border-color:red");
        } else {
            DataBaseField.setStyle("");
        }
        if (HostField.getText() == null || HostField.getText().length() == 0) {
            errorMessage += "Host inválido!\n";
            HostField.setStyle("-fx-border-color:red");
        } else {
            HostField.setStyle("");
        }
        if (PasswordField.getText() == null) {
            PasswordField.setText("");
            PasswordField.setStyle("-fx-border-color:red");
        } else {
            PasswordField.setStyle("");
        }
        if (PrefixField.getText() == null || PrefixField.getText().length() == 0) {
            errorMessage += "Prefix inválido!\n";
            PrefixField.setStyle("-fx-border-color:red");
        } else {
            PrefixField.setStyle("");
        }
        if (UserField.getText() == null || UserField.getText().length() == 0) {
            errorMessage += "User inválido!\n";
            UserField.setStyle("-fx-border-color:red");
        } else {
            UserField.setStyle("");
        }
        if (PortField.getText() == null || PortField.getText().length() == 0) {
            errorMessage += "Port inválido!\n";
            PortField.setStyle("-fx-border-color:red");
        } else {
            try {

                Integer.valueOf(PortField.getText());
                PortField.setStyle("");
            } catch (NumberFormatException e) {
                errorMessage += "Port inválido!\n";
                PortField.setStyle("-fx-border-color:red");
            }

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
        HostField.setDisable(es);
        UserField.setDisable(es);
        PasswordField.setDisable(es);
        DataBaseField.setDisable(es);
        PrefixField.setDisable(es);
        PortField.setDisable(es);
        btnSalvar.setDisable(es);
        btnCancelar.setDisable(es);
    }

    private void LimparCampo() {
        HostField.setText("");
        UserField.setText("");
        PasswordField.setText("");
        DataBaseField.setText("");
        PrefixField.setText("");
        PortField.setText("");
        modificao_banco_dados = null;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        On_Off_Button(true);
        // TODO
        bntDetalhes.disableProperty().bind(BancosView.getSelectionModel().selectedItemProperty().isNull());
        btnEditar.disableProperty().bind(BancosView.getSelectionModel().selectedItemProperty().isNull());
        btnDeletar.disableProperty().bind(BancosView.getSelectionModel().selectedItemProperty().isNull());
    }

}
