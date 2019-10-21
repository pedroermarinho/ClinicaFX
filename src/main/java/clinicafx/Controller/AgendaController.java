/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller;

import clinicafx.MainApp;
import clinicafx.Model.Cliente.model_cliente;
import clinicafx.Model.Clinicas.model_clinica;
import clinicafx.Model.Usuario.model_agenda;
import clinicafx.Model.Usuario.model_usuario;
import static clinicafx.Util.MsgErro.MessagemErroFormulario;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class AgendaController implements Initializable {

    private MainApp mainApp;
    private model_usuario usuario = null;
    private model_cliente cliente = null;
    private model_clinica clinica = null;
    private model_agenda modificao_agenda;

    @FXML
    private Button btnUsuario;

    @FXML
    private Button btnCliente;

    @FXML
    private Button btnClinica;

    @FXML
    private JFXTimePicker HoraPicker;

    @FXML
    private JFXDatePicker DataPicker;

    @FXML
    private TextArea ObservacaoText;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnSalvar;

    @FXML
    private TableView<model_agenda> AgendaView;

    @FXML
    private TableColumn<model_agenda, Integer> IDAgendaColumn;

    @FXML
    private TableColumn<model_agenda, Integer> IDClienteColumn;

    @FXML
    private TableColumn<model_agenda, Time> HoraColumn;

    @FXML
    private TableColumn<model_agenda, String> DateColumn;

//    @FXML
//    private TableColumn<model_agenda, Date> DataColumn;
    @FXML
    private MenuItem bntDetalhes;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnNovo;

    @FXML
    void OnCliente(ActionEvent event) {
        cliente = mainApp.getTelas().Clientes();
        if (cliente != null) {
            btnCliente.textProperty().bind(cliente.NomeProperty());
        }
    }

    @FXML
    void OnClinica(ActionEvent event) {
        clinica = mainApp.getTelas().Clinicas();
        if (clinica != null) {
            btnClinica.textProperty().bind(clinica.nomeProperty());
        }
    }

    @FXML
    void OnDeletar(ActionEvent event) {
        model_agenda selected = AgendaView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.delete();
            mainApp.getDadosData().getAgendaData();
        } else {

        }
    }

    @FXML
    void OnDetalhes(ActionEvent event) {
        LimparCampo();
        On_Off_Button(true);
        modificao_agenda = AgendaView.getSelectionModel().getSelectedItem();
        usuario = model_usuario.find(modificao_agenda.getID_usuario(), mainApp);
        cliente = model_cliente.find(modificao_agenda.getID_cliente(), mainApp);
        clinica = model_clinica.find(modificao_agenda.getID_clinica(), mainApp);

        btnUsuario.textProperty().bind(usuario.UsuarioProperty());
        btnCliente.textProperty().bind(cliente.NomeProperty());
        btnClinica.textProperty().bind(clinica.nomeProperty());
        HoraPicker.setValue(modificao_agenda.getHorario().toLocalTime());
        DataPicker.setValue(modificao_agenda.getData().toLocalDate());
        ObservacaoText.setText(modificao_agenda.getObservacao());
    }

    @FXML
    void OnEditar(ActionEvent event) {
        LimparCampo();
        On_Off_Button(false);
        modificao_agenda = AgendaView.getSelectionModel().getSelectedItem();
        usuario = model_usuario.find(modificao_agenda.getID_usuario(), mainApp);
        cliente = model_cliente.find(modificao_agenda.getID_cliente(), mainApp);
        clinica = model_clinica.find(modificao_agenda.getID_clinica(), mainApp);

        btnUsuario.textProperty().bind(usuario.UsuarioProperty());
        btnCliente.textProperty().bind(cliente.NomeProperty());
        btnClinica.textProperty().bind(clinica.nomeProperty());
        HoraPicker.setValue(modificao_agenda.getHorario().toLocalTime());
        DataPicker.setValue(modificao_agenda.getData().toLocalDate());
        ObservacaoText.setText(modificao_agenda.getObservacao());
    }

    @FXML
    void OnNovo(ActionEvent event) {
        On_Off_Button(false);

        LimparCampo();

    }

    @FXML
    void OnCancelar(ActionEvent event) {
        On_Off_Button(true);

        LimparCampo();
    }

    @FXML
    void OnSalvar(ActionEvent event) {
        if (modificao_agenda == null) {
            modificao_agenda = new model_agenda(mainApp);
        }
        if (isInputValid()) {

            modificao_agenda.setID_usuario(usuario.getID_usuario());
            modificao_agenda.setID_cliente(cliente.getID_cliente());
            modificao_agenda.setID_clinica(clinica.getID_clinica());
            modificao_agenda.setData(java.sql.Date.valueOf(DataPicker.getValue()));
            modificao_agenda.setHorario(java.sql.Time.valueOf(HoraPicker.getValue()));
            modificao_agenda.setObservacao(ObservacaoText.getText());
            modificao_agenda.save();
            LimparCampo();
            mainApp.getDadosData().getAgendaData();
            On_Off_Button(true);
        }
    }

    @FXML
    void OnUsuario(ActionEvent event) {
        usuario = mainApp.getTelas().SelectUsuario();
        if (usuario != null) {
            btnUsuario.textProperty().bind(usuario.NomeProperty());
        }
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        AgendaView.setItems(this.mainApp.getDadosData().getAgendaData());
    }

    private void On_Off_Button(boolean es) {
        btnUsuario.setDisable(es);
        btnCliente.setDisable(es);
        btnClinica.setDisable(es);
        HoraPicker.setDisable(es);
        DataPicker.setDisable(es);
        ObservacaoText.setDisable(es);
        btnSalvar.setDisable(es);
        btnCancelar.setDisable(es);
    }

    private void LimparCampo() {
        btnUsuario.textProperty().bind(new SimpleStringProperty(""));
        btnCliente.textProperty().bind(new SimpleStringProperty(""));
        btnClinica.textProperty().bind(new SimpleStringProperty(""));
        HoraPicker.setValue(null);
        DataPicker.setValue(null);
        ObservacaoText.setText("");
        modificao_agenda = null;
        usuario = null;
        cliente = null;
        clinica = null;
    }

    private boolean isInputValid() {
        System.out.println("isInputValid");
        String errorMessage = "";

        if (btnUsuario.getText() == null || btnUsuario.getText().length() == 0 || usuario == null) {
            errorMessage += "Usuario inválido!\n";
            btnUsuario.setStyle("-fx-border-color:red");
        } else {
            btnUsuario.setStyle("");
        }
        if (btnCliente.getText() == null || btnCliente.getText().length() == 0 || cliente == null) {
            errorMessage += "Cliente inválido!\n";
            btnCliente.setStyle("-fx-border-color:red");
        } else {
            btnCliente.setStyle("");
        }
        if (btnClinica.getText() == null || btnClinica.getText().length() == 0 || clinica == null) {
            errorMessage += "Clinica inválido!\n";
            btnClinica.setStyle("-fx-border-color:red");
        } else {
            btnClinica.setStyle("");
        }
        if (ObservacaoText.getText() == null || ObservacaoText.getText().length() == 0) {
            errorMessage += "Observação inválido!\n";
            ObservacaoText.setStyle("-fx-border-color:red");
        } else {
            ObservacaoText.setStyle("");
        }

        if (HoraPicker.getValue() == null || HoraPicker.getValue().toString().length() == 0) {
            errorMessage += "Hora inválido!\n";
            HoraPicker.setStyle("-fx-border-color:red");
        } else {
            HoraPicker.setStyle("");
        }
        if (DataPicker.getValue() == null || DataPicker.getValue().toString().length() == 0) {
            errorMessage += "Data inválido!\n";
            DataPicker.setStyle("-fx-border-color:red");
        } else {
            DataPicker.setStyle("");
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

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
        On_Off_Button(true);
        bntDetalhes.disableProperty().bind(AgendaView.getSelectionModel().selectedItemProperty().isNull());
        btnEditar.disableProperty().bind(AgendaView.getSelectionModel().selectedItemProperty().isNull());
        btnDeletar.disableProperty().bind(AgendaView.getSelectionModel().selectedItemProperty().isNull());

        IDAgendaColumn.setCellValueFactory(new PropertyValueFactory<>("ID_agenda"));
        IDClienteColumn.setCellValueFactory(new PropertyValueFactory<>("ID_cliente"));
//        DataColumn.setCellValueFactory(new PropertyValueFactory<>("data_agenda"));
        HoraColumn.setCellValueFactory(new PropertyValueFactory<>("horario"));

        DateColumn.setCellValueFactory(new PropertyValueFactory<>("Data"));
    }

}
