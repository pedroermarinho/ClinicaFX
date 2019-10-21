/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller.Cadastros;

import clinicafx.MainApp;
import clinicafx.Model.Cliente.model_cliente;
import clinicafx.Model.Cliente.model_corpo_cliente;
import static clinicafx.Util.Filtro.Cliente_para_Corpo;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class Corpo_ClienteController implements Initializable {

    private MainApp mainApp;
    private model_corpo_cliente modifição_corpo_cliente;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        registrosClienteView.setItems(mainApp.getDadosData().getClientesData());
        ClienteBox.setItems(this.mainApp.getDadosData().getClientesData());
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
    private TableView<model_corpo_cliente> registrosCorpoView;

    @FXML
    private TableColumn<model_corpo_cliente, Integer> IDCorpoColumn;

    @FXML
    private TableColumn<model_corpo_cliente, Date> DataCorpoColumn;

    @FXML
    private MenuItem bntDetalhes;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnNovo;

    @FXML
    private ComboBox<model_cliente> ClienteBox;

    @FXML
    private DatePicker DataPicker;

    @FXML
    private TextField PesoText;

    @FXML
    private TextField AlturaText;

    @FXML
    private TextField QuadrilText;

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
        model_corpo_cliente selected = registrosCorpoView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.delete();
            mainApp.getDadosData().getCorpoClienteData();
        } else {

        }
    }

    @FXML
    void OnDetalhes(ActionEvent event) {
        On_Off_Button(true);
        modifição_corpo_cliente = registrosCorpoView.getSelectionModel().getSelectedItem();
        AlturaText.setText(String.valueOf(modifição_corpo_cliente.getAltura()));
        PesoText.setText(String.valueOf(modifição_corpo_cliente.getPeso()));
        QuadrilText.setText(String.valueOf(modifição_corpo_cliente.getQuadril()));
        ClienteBox.setValue(model_cliente.find(modifição_corpo_cliente.getID_cliente(), mainApp));
        DataPicker.setValue(LocalDate.parse(modifição_corpo_cliente.getData_Corpo_Cliente()));

    }

    @FXML
    void OnEditar(ActionEvent event) {
        On_Off_Button(false);
        modifição_corpo_cliente = registrosCorpoView.getSelectionModel().getSelectedItem();
        AlturaText.setText(String.valueOf(modifição_corpo_cliente.getAltura()));
        PesoText.setText(String.valueOf(modifição_corpo_cliente.getPeso()));
        QuadrilText.setText(String.valueOf(modifição_corpo_cliente.getQuadril()));
        ClienteBox.setValue(model_cliente.find(modifição_corpo_cliente.getID_cliente(), mainApp));
        DataPicker.setValue(LocalDate.parse(modifição_corpo_cliente.getData_Corpo_Cliente()));
    }

    @FXML
    void OnNovo(ActionEvent event) {

        On_Off_Button(false);

        LimparCampo();
    }

    @FXML
    void OnSalvar(ActionEvent event) {
        if (modifição_corpo_cliente == null) {
            modifição_corpo_cliente = new model_corpo_cliente(mainApp);
        }
        if (isInputValid()) {
            modifição_corpo_cliente.setAltura(Float.valueOf(AlturaText.getText()));
            modifição_corpo_cliente.setPeso(Float.valueOf(PesoText.getText()));
            modifição_corpo_cliente.setQuadril(Float.valueOf(QuadrilText.getText()));
            modifição_corpo_cliente.setData_Corpo_Cliente(String.valueOf(java.sql.Date.valueOf(DataPicker.getValue())));
            modifição_corpo_cliente.setID_cliente(ClienteBox.getValue().getID_cliente());
            modifição_corpo_cliente.save();
            LimparCampo();
            mainApp.getDadosData().getCorpoClienteData();
            registrosCorpos(registrosClienteView.getSelectionModel().getSelectedItem());
            On_Off_Button(true);
        }

    }

    private void LimparCampo() {
        AlturaText.setText("");
        PesoText.setText("");
        QuadrilText.setText("");
        ClienteBox.setValue(null);
        DataPicker.setValue(null);
        modifição_corpo_cliente = null;
    }

    private void On_Off_Button(boolean es) {

        AlturaText.setDisable(es);
        PesoText.setDisable(es);
        QuadrilText.setDisable(es);
        ClienteBox.setDisable(es);
        DataPicker.setDisable(es);
        bntSalva.setDisable(es);
        BtnCancelar.setDisable(es);
    }

    private boolean isInputValid() {
        System.out.println("isInputValid");
        String errorMessage = "";

        if (AlturaText.getText() == null || AlturaText.getText().length() == 0) {
            errorMessage += "Altura inválido!\n";
            AlturaText.setStyle("-fx-border-color:red");
        } else {
            try {
                Float.valueOf(AlturaText.getText());
                AlturaText.setStyle("");
            } catch (NumberFormatException e) {
                errorMessage += "Altura inválido!\n";
                AlturaText.setStyle("-fx-border-color:red");
            }
        }
        if (PesoText.getText() == null || PesoText.getText().length() == 0) {
            errorMessage += "Peso inválido!\n";
            PesoText.setStyle("-fx-border-color:red");
        } else {
            try {
                Float.valueOf(PesoText.getText());
                PesoText.setStyle("");
            } catch (NumberFormatException e) {
                errorMessage += "Peso inválido!\n";
                PesoText.setStyle("-fx-border-color:red");
            }
        }
        if (QuadrilText.getText() == null || QuadrilText.getText().length() == 0) {
            errorMessage += "Quadril inválido!\n";
            QuadrilText.setStyle("-fx-border-color:red");
        } else {
            try {
                Float.valueOf(QuadrilText.getText());
                QuadrilText.setStyle("");
            } catch (NumberFormatException e) {
                errorMessage += "Quadril inválido!\n";
                QuadrilText.setStyle("-fx-border-color:red");
            }
        }
        if (ClienteBox.getValue() == null || ClienteBox.getValue().toString().length() == 0) {
            errorMessage += "Cliente inválido!\n";
            ClienteBox.setStyle("-fx-border-color:red");
        }

        if (DataPicker.getValue() == null || DataPicker.getValue().toString().length() == 0) {
            errorMessage += "Data inválido!\n";
            DataPicker.setStyle("-fx-border-color:red");
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
            registrosCorpoView.setItems(Cliente_para_Corpo(obj.getID_cliente(), mainApp));
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

        bntDetalhes.disableProperty().bind(registrosCorpoView.getSelectionModel().selectedItemProperty().isNull());
        btnEditar.disableProperty().bind(registrosCorpoView.getSelectionModel().selectedItemProperty().isNull());
        btnDeletar.disableProperty().bind(registrosCorpoView.getSelectionModel().selectedItemProperty().isNull());

        IDCorpoColumn.setCellValueFactory(new PropertyValueFactory<>("ID_Corpo_Cliente"));
        DataCorpoColumn.setCellValueFactory(new PropertyValueFactory<>("Data_Corpo_Cliente"));

        IDClienteColumn.setCellValueFactory(new PropertyValueFactory<>("ID_cliente"));
        ClienteNomeColumn.setCellValueFactory(new PropertyValueFactory<>("Nome"));

    }

}
