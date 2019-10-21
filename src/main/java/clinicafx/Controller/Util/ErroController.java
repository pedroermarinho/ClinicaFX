/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller.Util;

import clinicafx.MainApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class ErroController implements Initializable {

    @FXML
    private Label lbErro;
    private String erro;
    private Stage dialogStage;
    private MainApp mainApp;

    public void setDialogStage(Stage dialogStage) {
        System.out.println("setDialogStage");
        this.dialogStage = dialogStage;
    }

    public void setMainApp(MainApp mainApp, Exception ex) {
        System.out.println("setMainApp");
        this.mainApp = mainApp;
        this.erro = erro;
        lbErro.setText(erro);
    }

    @FXML
    private void ok() {
        dialogStage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
