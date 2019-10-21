/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller.Util;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class SobreController implements Initializable {

    @FXML
    private Label texto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        texto.setText("\n\n\n\n\t Criado por: Pedro Marinho\n\t Eirunepe-AM\n\t Twitter: @Pedro_ERMarinho\n\t Gmail: pedro.marinho238@gmail.com");
    }

}
