/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller.Usuario;

import clinicafx.MainApp;
import clinicafx.Model.Usuario.model_usuario;
import clinicafx.Util.Filtro;
import static clinicafx.Util.Filtro.Senha_Usuario;
import clinicafx.Util.Senha;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class UsuarioController implements Initializable {

    @FXML
    private TextField usuarioText;

    @FXML
    private PasswordField senhaPassword;

    @FXML
    private Button btOk;

    @FXML
    private Circle avatarCircle;

    private Stage dialogStage;

    private MainApp mainapp;

    private Image img;

    private ImagePattern pattern;

    private model_usuario usuario = null;

    @FXML
    void ok(ActionEvent event) {

        usuario = Senha_Usuario(usuarioText.getText(), senhaPassword.getText(), mainapp);
        if (usuario != null) {
            dialogStage.close();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            img = new Image("http://fxexperience.com/wp-content/uploads/2012/03/FxExperienceToolsIcon-256.png");
            pattern = new ImagePattern(img);
            avatarCircle.setFill(pattern);
        } catch (Exception e) {

        }
    }

    public model_usuario usuario() {
        return usuario;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(MainApp mainapp) {
        this.mainapp = mainapp;
        if (this.mainapp.getUsuario() != null) {
            usuarioText.setText(this.mainapp.getUsuario().getUsuario());
        }
    }

}
