/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller.Configuracao;

import clinicafx.MainApp;
import clinicafx.Model.Configuracao_Local.model_banco_de_dados;
import clinicafx.Util.MsgErro;
import static clinicafx.Util.MsgErro.MessagemErroFormulario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho
 * <pedro.marinho238@gmail.com & https://github.com/TECFlyingCommunity>
 */
public class CadastroBancoDeDadosController implements Initializable {

    private MainApp mainApp;
    private model_banco_de_dados banco_de_dados = null;
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
    private Button bntCancelar;

    @FXML
    private Button btnSalvar;

    @FXML
    private ToggleButton bntOffline;

    @FXML
    private ToggleButton bntOnline;

    @FXML
    void OnCancelar(ActionEvent event) {
        dialogStage.close();
    }



    @FXML
    void OnOffline(ActionEvent event) {
        DisableBnt(true);
        boolean value=false;
        DataBaseField.setDisable(value);
        btnSalvar.setDisable(value);
        bntOffline.setSelected(true);
        bntOnline.setSelected(false);
        PrefixField.setText("jdbc:sqlite:");
        PrefixField.setDisable(true);

    }

    @FXML
    void OnOnline(ActionEvent event) {
        DisableBnt(false);
        bntOffline.setSelected(true);
        bntOnline.setSelected(false);
        PrefixField.setText("jdbc:mysql:");
        PrefixField.setDisable(true);
    }


    @FXML
    void OnSalvar(ActionEvent event) {
        if (isInputValid()) {
            banco_de_dados = new model_banco_de_dados();
            banco_de_dados.setDataBase(DataBaseField.getText());
            banco_de_dados.setHost(HostField.getText());
            banco_de_dados.setPassword(PasswordField.getText());
            banco_de_dados.setPorts(Integer.valueOf(PortField.getText()));
            banco_de_dados.setPrefix(PrefixField.getText());
            banco_de_dados.setUser(UserField.getText());
            banco_de_dados.save();
            mainApp.getDadosData().getBancos_de_DadosData();
            dialogStage.close();
        }
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

    private void DisableBnt(boolean value){
        DataBaseField.setDisable(value);
        HostField.setDisable(value);
        PasswordField.setDisable(value);
        PortField.setDisable(value);
        PrefixField.setDisable(value);
        UserField.setDisable(value);
        btnSalvar.setDisable(value);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DisableBnt(true);
    }

    public void setMainApp(MainApp mainApp) {
        System.out.println("setMainApp");
        this.mainApp = mainApp;

    }
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

}
