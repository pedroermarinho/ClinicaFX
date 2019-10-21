package clinicafx.Controller;

import clinicafx.MainApp;
import clinicafx.Util.MsgErro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class CadastroController {

    private MainApp mainApp;
    private boolean on_off;
    @FXML
    private BorderPane Border;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    private Button btnClinica;
    @FXML
    private Button btnEnderecoClinica;

    @FXML
    private BorderPane TelaCadastrosBorderPane;

    @FXML
    private VBox menuVBox;

    @FXML
    private Button btnBairro;

    @FXML
    private Button btnCidade;

    @FXML
    private Button btnCliente;

    @FXML
    private Button btnCorpoCliente;

    @FXML
    private Button btnEndereco;

    @FXML
    private Button btnEstado;

    @FXML
    private Button btnFicha;

    @FXML
    private Button btnPais;

    @FXML
    private Button btnRua;

    @FXML
    private Button btnSexo;

    @FXML
    void OnBairro(ActionEvent event) {
        try {
            TelaCadastrosBorderPane.setCenter(mainApp.getTelas().CadastroBairro());
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "OnBairro");
        }

    }

    @FXML
    void OnCidade(ActionEvent event) {
        try {
            TelaCadastrosBorderPane.setCenter(mainApp.getTelas().CadastroCidade());
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "OnCidade");
        }
    }

    @FXML
    void OnCliente(ActionEvent event) {
        try {
            TelaCadastrosBorderPane.setCenter(mainApp.getTelas().CadastroCliente());
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "OnCliente");
        }
    }

    @FXML
    void OnClinica(ActionEvent event) {
        try {
            TelaCadastrosBorderPane.setCenter(mainApp.getTelas().CadastroClinica());
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "OnClinica");
        }
    }

    @FXML
    void OnCorpoCliente(ActionEvent event) {
        try {
            TelaCadastrosBorderPane.setCenter(mainApp.getTelas().CadastroCorpoCliente());
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "OnCorpoCliente");
        }
    }

    @FXML
    void OnEndereco(ActionEvent event) {
        try {
            TelaCadastrosBorderPane.setCenter(mainApp.getTelas().CadastroEnderecoCliente());
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "OnEndereco");
        }
    }

    @FXML
    void OnEnderecoClinica(ActionEvent event) {
        try {
            TelaCadastrosBorderPane.setCenter(mainApp.getTelas().CadastroEnderecoClinica());
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "OnEnderecoClinica");
        }
    }

    @FXML
    void OnEstado(ActionEvent event) {
        try {
            TelaCadastrosBorderPane.setCenter(mainApp.getTelas().CadastroEstado());
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "OnEstado");
        }
    }

    @FXML
    void OnFicha(ActionEvent event) {
        try {
            TelaCadastrosBorderPane.setCenter(mainApp.getTelas().CadastroFicha());
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "OnFicha");
        }
    }

    @FXML
    void OnPais(ActionEvent event) {
        try {
            TelaCadastrosBorderPane.setCenter(mainApp.getTelas().CadastroPais());
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "OnPais");
        }
    }

    @FXML
    void OnRua(ActionEvent event) {
        try {
            TelaCadastrosBorderPane.setCenter(mainApp.getTelas().CadastroRua());
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "OnRua");
        }
    }

    @FXML
    void OnSexo(ActionEvent event) {
        try {
            TelaCadastrosBorderPane.setCenter(mainApp.getTelas().CadastroSexo());
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "OnSexo");
        }
    }

    @FXML
    void On_OFF_ON(ActionEvent event) {
        if (on_off) {
            Border.setPrefWidth(200);
            Border.setCenter(menuVBox);
            on_off = false;

        } else {
            Border.setPrefWidth(5);
            Border.setCenter(null);
            on_off = true;
        }
    }

}
