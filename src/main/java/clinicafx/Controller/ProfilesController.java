/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import clinicafx.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class ProfilesController implements Initializable {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        System.out.println("setMainApp");
        this.mainApp = mainApp;

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {//dados a ser inicializado em conjunto com a interface
        System.out.println("Interface de cadastrdos inicializada ");
        //tablePessoa.refresh();

    }

}
