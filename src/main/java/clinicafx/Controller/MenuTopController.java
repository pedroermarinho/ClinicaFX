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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class MenuTopController implements Initializable {

    private Stage primeira_cena;
    private MainApp mainApp;
    private boolean on_off;
    @FXML
    private AnchorPane anchorpane;

    @FXML
    private BorderPane menuBorder;

    @FXML
    private HBox menuHBox;

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnMLateral;

    @FXML
    private Button btnCadastros;

    @FXML
    private Button btnInformacoes;

    @FXML
    private Button btnAgenda;

    @FXML
    private Button btnExames;

    @FXML
    private Button btnDados;

    @FXML
    void OnCadastros(ActionEvent event) {
        mainApp.getTelas().Cadastros();
    }

    @FXML
    void OnDados(ActionEvent event) {
        mainApp.getTelas().Dados();
    }

    @FXML
    void OnExames(ActionEvent event) {
        mainApp.getTelas().Exames();
    }

    @FXML
    void OnInformacoes(ActionEvent event) {
        mainApp.getTelas().Informacao();
    }

    @FXML
    void OnInicio(ActionEvent event) {
        mainApp.getTelas().CentralTexto();
    }

    @FXML
    void OnMLateral(ActionEvent event) {
        mainApp.getTelas().MenuDireto();
    }

    @FXML
    void OnAgenda(ActionEvent event) {
        mainApp.getTelas().Agenda();
    }

    public void setMainApp(MainApp mainApp) {
        System.out.println("setMainApp");
        this.mainApp = mainApp;
        this.primeira_cena = this.mainApp.getTelas().getPrimeriaCena();

    }

    @FXML
    void on_off_menu(ActionEvent event) {
        if (on_off) {
            anchorpane.setPrefHeight(55);
            menuBorder.setCenter(menuHBox);
            on_off = false;

        } else {
            anchorpane.setPrefHeight(5);
            menuBorder.setCenter(null);
            on_off = true;
        }

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
        btnExames.setDisable(true);
        btnDados.setDisable(true);
    }

}
