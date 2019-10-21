/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import clinicafx.MainApp;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class MenuBottomController implements Initializable {

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

    private Stage primeira_cena;
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        System.out.println("setMainApp");
        this.mainApp = mainApp;
        this.primeira_cena = this.mainApp.getTelas().getPrimeriaCena();

    }

}
