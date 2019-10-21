/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller.Cadastros;

import clinicafx.MainApp;
import clinicafx.Model.model_sexo;
import clinicafx.Util.MsgErro;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho
 * <pedro.marinho238@gmail.com & https://github.com/TECFlyingCommunity>
 */
public class CadastroSexoController implements Initializable {

    private MainApp mainApp;
    private model_sexo modifição_sexo;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private TextField NomeSexoText;

    @FXML
    private Button bntSalva;

    @FXML
    private Button BtnCancelar;

    @FXML
    void OnCancelar(ActionEvent event) {

        dialogStage.close();
    }

    @FXML
    void OnSalvar(ActionEvent event) {
        modifição_sexo = new model_sexo(mainApp);

        if (isInputValid()) {
            modifição_sexo.setSexo(NomeSexoText.getText());
            modifição_sexo.save();
            mainApp.getDadosData().getSexosData();
            dialogStage.close();

        }
    }

    private boolean isInputValid() {
        System.out.println("isInputValid");
        String errorMessage = "";

        if (NomeSexoText.getText() == null || NomeSexoText.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
            NomeSexoText.setStyle("-fx-border-color:red");
        } else {
            NomeSexoText.setStyle("");
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            MsgErro.MessagemErroFormulario(errorMessage);
//            System.out.println(errorMessage);
//           
            return false;
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
