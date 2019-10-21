/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller;

import clinicafx.MainApp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class ExamesController implements Initializable {

    private MainApp mainApp;

    private boolean on_off;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    private BorderPane Border;

    @FXML
    private VBox menuVBox;

    @FXML
    private Button btnEAS;

    @FXML
    private Button btnFezes;

    @FXML
    private Button btnHelmito;

    @FXML
    private Button btnHomograma;

    @FXML
    private Button btnProtozoarios;

    @FXML
    private Button btnSedimentoscopia;

    @FXML
    private Button btnOff_On;

    @FXML
    void OnEAS(ActionEvent event) {
        mainApp.getTelas().Eas();
    }

    @FXML
    void OnFezes(ActionEvent event) {
        mainApp.getTelas().Fezes();
    }

    @FXML
    void OnHelmito(ActionEvent event) {
        mainApp.getTelas().Helmito();
    }

    @FXML
    void OnHomograma(ActionEvent event) {
        mainApp.getTelas().Homograma();
    }

    @FXML
    void OnOff_On(ActionEvent event) {
        if (on_off) {
            Border.setPrefWidth(195);
            Border.setCenter(menuVBox);
            on_off = false;

        } else {
            Border.setPrefWidth(5);
            Border.setCenter(null);
            on_off = true;
        }
    }

    @FXML
    void OnProtozoarios(ActionEvent event) {
        mainApp.getTelas().Protozoarios();
    }

    @FXML
    void OnSedimentoscopia(ActionEvent event) {
        mainApp.getTelas().Sedimentoscopia();
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
    }

}
