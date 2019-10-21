/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller.Usuario;

import clinicafx.MainApp;
import clinicafx.Model.Usuario.model_agenda;
import clinicafx.Model.Usuario.model_usuario;
import clinicafx.Model.model_sexo;
import static clinicafx.Util.MsgErro.MessagemErroFormulario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author pedro
 */
public class UsuariosController implements Initializable {

    private MainApp mainapp;
    private model_usuario modificao_usuario;

    /**
     *
     * @param mainapp
     */
    public void setMainApp(MainApp mainapp) {
        this.mainapp = mainapp;
        PessoaTable.setItems(this.mainapp.getDadosData().getUsuariosData());
        SexoBox.setItems(this.mainapp.getDadosData().getSexosData());
    }
    @FXML
    private TableView<model_usuario> PessoaTable;

    @FXML
    private TableColumn<model_usuario, Integer> IDColumn;

    @FXML
    private TableColumn<model_usuario, String> UsuarioColumn;

    @FXML
    private TableColumn<model_usuario, String> NomeColumn;

    @FXML
    private MenuItem btnDetalhes;

    @FXML
    private Button btNovo;

    @FXML
    private Button btEditar;

    @FXML
    private Button btDeletar;

    @FXML
    private TextField PrimeiroNomeField;

    @FXML
    private TextField SobrenomeField;

    @FXML
    private TextField usuarioField;

    @FXML
    private PasswordField senhaField;

    @FXML
    private DatePicker dpData;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnSalvar;

    @FXML
    private TextField EmailField;

    @FXML
    private ComboBox<model_sexo> SexoBox;

    @FXML
    void OnCancelar(ActionEvent event) {
        LimparCampo();
        On_Off_Button(true);
    }

    @FXML
    void OnSalvar(ActionEvent event) {
        if (modificao_usuario == null) {
            modificao_usuario = new model_usuario(mainapp);
        }
        if (isInputValid()) {
            modificao_usuario.setDataNascimento(java.sql.Date.valueOf(dpData.getValue()));
            modificao_usuario.setEmail(EmailField.getText());
            modificao_usuario.setUsuario(usuarioField.getText());
            modificao_usuario.setNome(PrimeiroNomeField.getText());
            modificao_usuario.setSenha(senhaField.getText());
            modificao_usuario.setSexo(SexoBox.getValue().getID_Sexo());
            modificao_usuario.setSobrenome(SobrenomeField.getText());
            modificao_usuario.save();
            LimparCampo();
            mainapp.getDadosData().getUsuariosData();
            On_Off_Button(true);

        }
    }

    @FXML
    void DeletarPessoa(ActionEvent event) {
        model_usuario selected = PessoaTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.delete();
            mainapp.getDadosData().getAgendaData();
        } else {

        }
    }

    @FXML
    void EditarPessoa(ActionEvent event) {
        LimparCampo();
        On_Off_Button(false);
        modificao_usuario = PessoaTable.getSelectionModel().getSelectedItem();
        PrimeiroNomeField.setText(modificao_usuario.getNome());
        SobrenomeField.setText(modificao_usuario.getSobrenome());
        usuarioField.setText(modificao_usuario.getUsuario());
        senhaField.setText(modificao_usuario.getSenha());
        dpData.setValue(modificao_usuario.getDataNascimento().toLocalDate());
        EmailField.setText(modificao_usuario.getEmail());
        SexoBox.setValue(model_sexo.find(modificao_usuario.getSexo(), mainapp));
    }

    @FXML
    void NovaPessoa(ActionEvent event) {
        LimparCampo();
        On_Off_Button(false);
    }

    @FXML
    void OnDetalhes(ActionEvent event) {
        LimparCampo();
        On_Off_Button(true);
        modificao_usuario = PessoaTable.getSelectionModel().getSelectedItem();
        PrimeiroNomeField.setText(modificao_usuario.getNome());
        SobrenomeField.setText(modificao_usuario.getSobrenome());
        usuarioField.setText(modificao_usuario.getUsuario());
        senhaField.setText(modificao_usuario.getSenha());
        dpData.setValue(modificao_usuario.getDataNascimento().toLocalDate());
        EmailField.setText(modificao_usuario.getEmail());
        SexoBox.setValue(model_sexo.find(modificao_usuario.getSexo(), mainapp));

    }

    private void On_Off_Button(boolean es) {
        PrimeiroNomeField.setDisable(es);
        SobrenomeField.setDisable(es);
        usuarioField.setDisable(es);
        senhaField.setDisable(es);
        dpData.setDisable(es);
        SexoBox.setDisable(es);
        btnSalvar.setDisable(es);
        btnCancelar.setDisable(es);
    }

    private void LimparCampo() {
        PrimeiroNomeField.setText("");
        EmailField.setText("");
        SobrenomeField.setText("");
        usuarioField.setText("");
        senhaField.setText("");
        dpData.setValue(null);
        SexoBox.setValue(null);
        modificao_usuario = null;

    }

    private boolean isInputValid() {
        System.out.println("isInputValid");
        String errorMessage = "";

        if (PrimeiroNomeField.getText() == null || PrimeiroNomeField.getText().length() == 0) {
            errorMessage += "Usuario inválido!\n";
            PrimeiroNomeField.setStyle("-fx-border-color:red");
        } else {
            PrimeiroNomeField.setStyle("");
        }
        if (SobrenomeField.getText() == null || SobrenomeField.getText().length() == 0) {
            errorMessage += "Cliente inválido!\n";
            SobrenomeField.setStyle("-fx-border-color:red");
        } else {
            SobrenomeField.setStyle("");
        }
        if (usuarioField.getText() == null || usuarioField.getText().length() == 0) {
            errorMessage += "Clinica inválido!\n";
            usuarioField.setStyle("-fx-border-color:red");
        } else {
            usuarioField.setStyle("");
        }
        if (senhaField.getText() == null || senhaField.getText().length() == 0) {
            errorMessage += "Observação inválido!\n";
            senhaField.setStyle("-fx-border-color:red");
        } else {
            senhaField.setStyle("");
        }

        if (dpData.getValue() == null || dpData.getValue().toString().length() == 0) {
            errorMessage += "Hora inválido!\n";
            dpData.setStyle("-fx-border-color:red");
        } else {
            dpData.setStyle("");
        }
        if (SexoBox.getValue() == null || SexoBox.getValue().toString().length() == 0) {
            errorMessage += "Data inválido!\n";
            SexoBox.setStyle("-fx-border-color:red");
        } else {
            SexoBox.setStyle("");
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            MessagemErroFormulario(errorMessage);
//            System.out.println(errorMessage);
//           
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        On_Off_Button(true);

        btnDetalhes.disableProperty().bind(PessoaTable.getSelectionModel().selectedItemProperty().isNull());
        btEditar.disableProperty().bind(PessoaTable.getSelectionModel().selectedItemProperty().isNull());
        btDeletar.disableProperty().bind(PessoaTable.getSelectionModel().selectedItemProperty().isNull());

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID_usuario"));
        UsuarioColumn.setCellValueFactory(new PropertyValueFactory<>("Usuario"));
        NomeColumn.setCellValueFactory(new PropertyValueFactory<>("Nome"));
    }

}
