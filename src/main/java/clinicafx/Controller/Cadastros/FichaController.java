/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller.Cadastros;

import clinicafx.MainApp;
import clinicafx.Model.Cliente.model_cliente;
import clinicafx.Model.Cliente.model_ficha;
import clinicafx.Util.Filtro;
import clinicafx.Util.MsgErro;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class FichaController implements Initializable {

    private model_ficha modifição_ficha;
    private model_cliente cliente;
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        registrosClienteView.setItems(this.mainApp.getDadosData().getClientesData());
    }

    @FXML
    private TableView<model_cliente> registrosClienteView;

    @FXML
    private TableColumn<model_cliente, Integer> IDClienteColumn;

    @FXML
    private TableColumn<model_cliente, String> ClienteNomeColumn;

    @FXML
    private Tooltip DetalhesClienteTooltip;

    @FXML
    private TableView<model_ficha> registrosFichaView;

    @FXML
    private TableColumn<model_ficha, Integer> IDFichaColumn;

    @FXML
    private TableColumn<model_ficha, Date> DataFichaColumn;

    @FXML
    private MenuItem bntDetalhes;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnNovo;

    @FXML
    private Label ClienteLabel;

    @FXML
    private DatePicker DataPicker;

    @FXML
    private TextArea ObservacaoText;

    @FXML
    private Button bntSalva;

    @FXML
    private Button BtnCancelar;
    @FXML
    private TextField PesquisarField;

    @FXML
    void OnPesquisar(ActionEvent event) {
        if (!PesquisarField.getText().equals("")) {
            registrosClienteView.setItems(findItems());
        } else {
            registrosClienteView.setItems(mainApp.getDadosData().getClientesData());
        }
    }

    private ObservableList<model_cliente> findItems() {
        ObservableList<model_cliente> itensEncontrados = FXCollections.observableArrayList();
        Integer ID;
        try {
            ID = Integer.parseInt(PesquisarField.getText());

        } catch (NumberFormatException a) {
            ID = null;

        }
        for (model_cliente itens : mainApp.getDadosData().getClientesData()) {

            //itens.getID().contains(Integer.valueOf( PesquisaField.getText())
            if (ID != null) {
                if (itens.getID_cliente() == ID) {
                    itensEncontrados.add(itens);

                }
            } else {
                if (itens.getCPF().contains(PesquisarField.getText()) || itens.getCartao_SUS().equalsIgnoreCase(PesquisarField.getText()) || itens.getNome().contains(PesquisarField.getText())) {
                    itensEncontrados.add(itens);

                }
            }
        }
        return itensEncontrados;
    }

    @FXML
    void OnCancelar(ActionEvent event) {
        LimparCampo();
        On_Off_Button(true);

    }

    @FXML
    void OnDeletar(ActionEvent event) {
        System.out.println("DeletaPessoa");
        model_ficha selected = registrosFichaView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.delete();
            mainApp.getDadosData().getFichaData();
        } else {

        }
    }

    @FXML
    void OnDetalhes(ActionEvent event) {
        On_Off_Button(true);
        modifição_ficha = registrosFichaView.getSelectionModel().getSelectedItem();
        cliente = model_cliente.find(modifição_ficha.getID_cliente(), mainApp);
        ClienteLabel.setText(cliente.getNome());
        ObservacaoText.setText(modifição_ficha.getObservacao());
        DataPicker.setValue(LocalDate.parse(modifição_ficha.getData_ficha()));
    }

    @FXML
    void OnEditar(ActionEvent event) {
        On_Off_Button(false);

        modifição_ficha = registrosFichaView.getSelectionModel().getSelectedItem();
        cliente = model_cliente.find(modifição_ficha.getID_cliente(), mainApp);
        ClienteLabel.setText(cliente.getNome());
        ObservacaoText.setText(modifição_ficha.getObservacao());
        DataPicker.setValue(LocalDate.parse(modifição_ficha.getData_ficha()));
    }

    @FXML
    void OnNovo(ActionEvent event) {

        On_Off_Button(false);

        LimparCampo();

        cliente = registrosClienteView.getSelectionModel().getSelectedItem();
        ClienteLabel.setText(cliente.getNome());
    }

    @FXML
    void OnSalvar(ActionEvent event) {
        if (modifição_ficha == null) {
            modifição_ficha = new model_ficha(mainApp);
        }
        if (isInputValid()) {
            modifição_ficha.setData_ficha(String.valueOf(java.sql.Date.valueOf(DataPicker.getValue())));
            modifição_ficha.setObservacao(ObservacaoText.getText());
            modifição_ficha.setID_cliente(cliente.getID_cliente());
            modifição_ficha.save();
            LimparCampo();
            mainApp.getDadosData().getFichaData();
            registrosCorpos(registrosClienteView.getSelectionModel().getSelectedItem());
            On_Off_Button(true);
        }

    }

    private void LimparCampo() {
        ClienteLabel.setText("");
        ObservacaoText.setText("");
        DataPicker.setValue(null);
        modifição_ficha = null;
    }

    private void On_Off_Button(boolean es) {
        ClienteLabel.setDisable(es);
        ObservacaoText.setDisable(es);

        DataPicker.setDisable(es);
        bntSalva.setDisable(es);
        BtnCancelar.setDisable(es);
    }

    private boolean isInputValid() {
        System.out.println("isInputValid");
        String errorMessage = "";

        if (DataPicker.getValue() == null || DataPicker.getValue().toString().length() == 0) {
            errorMessage += "Data inválido!\n";
            DataPicker.setStyle("-fx-border-color:red");
        } else {
            DataPicker.setStyle("");
        }
        if (ObservacaoText.getText() == null || ObservacaoText.getText().length() == 0) {
            errorMessage += "Data inválido!\n";
            ObservacaoText.setStyle("-fx-border-color:red");
        } else {
            ObservacaoText.setStyle("");
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            MsgErro.MessagemErroFormulario(errorMessage);
//            System.out.println(errorMessage);
//           
            return false;
        }
    }

    private void registrosCorpos(model_cliente obj) {
        if (obj != null) {
            registrosFichaView.setItems(Filtro.Cliente_para_Ficha(obj.getID_cliente(), mainApp));
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
        On_Off_Button(true);

        registrosClienteView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> registrosCorpos(newValue));

        bntDetalhes.disableProperty().bind(registrosFichaView.getSelectionModel().selectedItemProperty().isNull());
        btnEditar.disableProperty().bind(registrosFichaView.getSelectionModel().selectedItemProperty().isNull());
        btnDeletar.disableProperty().bind(registrosFichaView.getSelectionModel().selectedItemProperty().isNull());

        IDFichaColumn.setCellValueFactory(new PropertyValueFactory<>("ID_Ficha"));
        DataFichaColumn.setCellValueFactory(new PropertyValueFactory<>("data_ficha"));

        IDClienteColumn.setCellValueFactory(new PropertyValueFactory<>("ID_cliente"));
        ClienteNomeColumn.setCellValueFactory(new PropertyValueFactory<>("Nome"));

    }

}
