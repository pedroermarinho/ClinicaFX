package clinicafx.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import clinicafx.MainApp;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class MenuDiretoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {//dados a ser inicializado em conjunto com a interface
        System.out.println("Interface de cadastrdos inicializada ");
        //tablePessoa.refresh();
        btnPerfil.setDisable(true);
        btnAlertas.setDisable(true);

    }

    protected BorderPane PalcoPrincipal;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        System.out.println("setMainApp");
        this.mainApp = mainApp;
        this.PalcoPrincipal = this.mainApp.getTelas().getPalcoPrincipal();

    }

    @FXML
    private JFXButton btnInicio;

    @FXML
    private JFXButton btnAgenda;

    @FXML
    private JFXButton btnUsuarios;

    @FXML
    private JFXButton btnCadastros;

    @FXML
    private JFXButton btnPerfil;

    @FXML
    private JFXButton btnAlertas;

    @FXML
    private JFXButton btnConfiguracao;

    @FXML
    private JFXButton btnFecharMenu;

    @FXML
    void OnAgenda(ActionEvent event) {
        this.mainApp.getTelas().Agenda();
    }

    @FXML
    void OnAlertas(ActionEvent event) {
        this.mainApp.getTelas().Alertas();
    }

    @FXML
    void OnCadastros(ActionEvent event) {
        this.mainApp.getTelas().Cadastros();
    }

    @FXML
    void OnUsuarios(ActionEvent event) {
        this.mainApp.getTelas().usuarios();
    }

    @FXML
    void OnConfiguracao(ActionEvent event) {
        this.mainApp.getTelas().Menu_Configuracao();
    }

    @FXML
    void OnFecharMenu(ActionEvent event) {
        this.PalcoPrincipal.setLeft(null);

    }

    @FXML
    void OnInicio(ActionEvent event) {
        this.mainApp.getTelas().CentralTexto();
    }

    @FXML
    void OnPerfil(ActionEvent event) {
        this.mainApp.getTelas().Perfil();
    }

    @FXML
    void OnSobre(ActionEvent event) {
        this.mainApp.getTelas().sobre();
    }

}
