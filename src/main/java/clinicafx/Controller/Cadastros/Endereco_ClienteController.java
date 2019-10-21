/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller.Cadastros;

import clinicafx.MainApp;
import clinicafx.Model.model_bairros;
import clinicafx.Model.model_cidades;
import clinicafx.Model.Cliente.model_cliente;
import clinicafx.Model.Cliente.model_endereco_cliente;
import clinicafx.Model.model_estado;
import clinicafx.Model.model_pais;
import clinicafx.Model.model_ruas;
import clinicafx.Util.Filtro;
import static clinicafx.Util.Filtro.Cliente_para_Endereco;
import clinicafx.Util.MsgErro;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class Endereco_ClienteController implements Initializable {

    private MainApp mainApp;
    private model_endereco_cliente modifição_endereco;
    private model_cliente cliente;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        registrosView.setItems(this.mainApp.getDadosData().getClientesData());
        PaisBox.setItems(this.mainApp.getDadosData().getPaisData());
    }

    @FXML
    private ComboBox<model_pais> PaisBox;

    @FXML
    private ComboBox<model_estado> EstadoBox;

    @FXML
    private ComboBox<model_cidades> CidadeBox;

    @FXML
    private ComboBox<model_bairros> BairroBox;

    @FXML
    private ComboBox<model_ruas> RuaBox;

    @FXML
    private TextField NCasaText;

    @FXML
    private TextField TelefoneText;

    @FXML
    private TextField TelefoneFixoText;

    @FXML
    private TextArea ObservacaoText;

    @FXML
    private Button bntSalva;

    @FXML
    private Button BtnCancelar;

    @FXML
    private TextField PesquisaText;

    @FXML
    private Button btnPesquisar;

    @FXML
    private TableView<model_cliente> registrosView;

    @FXML
    private TableColumn<model_cliente, Integer> IDColumn;

    @FXML
    private TableColumn<model_cliente, String> ClienteColumn;

    @FXML
    private MenuItem bntDetalhes;

    @FXML
    private Button btnEditar;
    
    @FXML
    private TextField PesquisarField;

    @FXML
    void OnPesquisar(ActionEvent event) {
        if (!PesquisarField.getText().equals("")) {
            registrosView.setItems(findItems());
        } else {
            registrosView.setItems(mainApp.getDadosData().getClientesData());
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
    void OnDetalhes(ActionEvent event) {
        On_Off_Button(true);
        cliente = registrosView.getSelectionModel().getSelectedItem();
        modifição_endereco = Cliente_para_Endereco(cliente.getID_cliente(), mainApp);
        if (modifição_endereco != null) {

            NCasaText.setText(String.valueOf(modifição_endereco.getNumero_Casa()));
            TelefoneText.setText(modifição_endereco.getTelefone());
            TelefoneFixoText.setText(modifição_endereco.getTelefone_Fixo());
            ObservacaoText.setText(modifição_endereco.getTelefone_Fixo());

            RuaBox.setValue(model_ruas.find(modifição_endereco.getID_Rua(), mainApp));
            BairroBox.setValue(model_bairros.find(modifição_endereco.getID_Bairro(), mainApp));
            CidadeBox.setValue(model_cidades.find(model_bairros.find(modifição_endereco.getID_Bairro(), mainApp).getID_Cidade(), mainApp));
            EstadoBox.setValue(model_estado.find(model_cidades.find(model_bairros.find(modifição_endereco.getID_Bairro(), mainApp).getID_Cidade(), mainApp).getID_Estado(), mainApp));
            PaisBox.setValue(model_pais.find(model_estado.find(model_cidades.find(model_bairros.find(modifição_endereco.getID_Bairro(), mainApp).getID_Cidade(), mainApp).getID_Estado(), mainApp).getID_Pais(), mainApp));
        }
    }

    

    @FXML
    void OnEditar(ActionEvent event) {
        On_Off_Button(false);
        cliente = registrosView.getSelectionModel().getSelectedItem();

        modifição_endereco = Cliente_para_Endereco(cliente.getID_cliente(), mainApp);
        if (modifição_endereco != null) {
            NCasaText.setText(String.valueOf(modifição_endereco.getNumero_Casa()));
            TelefoneText.setText(modifição_endereco.getTelefone());
            TelefoneFixoText.setText(modifição_endereco.getTelefone_Fixo());
            ObservacaoText.setText(modifição_endereco.getComplemento());

            RuaBox.setValue(model_ruas.find(modifição_endereco.getID_Rua(), mainApp));
            BairroBox.setValue(model_bairros.find(modifição_endereco.getID_Bairro(), mainApp));
            CidadeBox.setValue(model_cidades.find(modifição_endereco.getID_cidade(), mainApp));
            EstadoBox.setValue(model_estado.find(model_cidades.find(modifição_endereco.getID_cidade(), mainApp).getID_Estado(), mainApp));
            PaisBox.setValue(model_pais.find(model_estado.find(model_cidades.find(modifição_endereco.getID_cidade(), mainApp).getID_Estado(), mainApp).getID_Pais(), mainApp));
        }
    }

    @FXML
    void OnSalvar(ActionEvent event) {
        if (modifição_endereco == null) {
            modifição_endereco = new model_endereco_cliente(mainApp);
        }
        if (isInputValid()) {

            modifição_endereco.setID_Rua(RuaBox.getValue().getID_rua());
            modifição_endereco.setID_Bairro(BairroBox.getValue().getID_Bairro());
            modifição_endereco.setID_cidade(CidadeBox.getValue().getID_Cidade());
            modifição_endereco.setNumero_Casa(Integer.valueOf(NCasaText.getText()));
            modifição_endereco.setTelefone(TelefoneText.getText());
            modifição_endereco.setTelefone_Fixo(TelefoneFixoText.getText());
            modifição_endereco.setComplemento(ObservacaoText.getText());
            modifição_endereco.setID_cliente(cliente.getID_cliente());

            modifição_endereco.save();
            LimparCampo();
            mainApp.getDadosData().getEnderecoClienteData();
            On_Off_Button(true);
        }

    }

    private void LimparCampo() {
        NCasaText.setText("");
        TelefoneText.setText("");
        TelefoneFixoText.setText("");
        ObservacaoText.setText("");

        PaisBox.setValue(null);
        EstadoBox.setValue(null);
        CidadeBox.setValue(null);
        BairroBox.setValue(null);
        RuaBox.setValue(null);

        cliente = null;
        modifição_endereco = null;
    }

    @FXML
    void setEstados(ActionEvent event) {
        if (PaisBox.getValue() == null || PaisBox.getValue().getID_Pais() == 0) {
            PaisBox.setStyle("-fx-border-color:red");
        } else {
            EstadoBox.setItems(Filtro.Pais_para_Estado(PaisBox.getValue().getID_Pais(), mainApp));
            PaisBox.setStyle("");
        }
    }

    @FXML
    void setCidades(ActionEvent event) {
        if (EstadoBox.getValue() == null || EstadoBox.getValue().getID_Estado() == 0) {
            EstadoBox.setStyle("-fx-border-color:red");
        } else {
            CidadeBox.setItems(Filtro.Estado_para_Cidade(EstadoBox.getValue().getID_Estado(), mainApp));
            EstadoBox.setStyle("");
        }
    }

    @FXML
    void setBairro(ActionEvent event) {
        if (CidadeBox.getValue() == null || CidadeBox.getValue().getID_Cidade() == 0) {
            CidadeBox.setStyle("-fx-border-color:red");
        } else {
            BairroBox.setItems(Filtro.Cidade_para_Bairro(CidadeBox.getValue().getID_Cidade(), mainApp));
            CidadeBox.setStyle("");
        }
    }

    @FXML
    void setRua(ActionEvent event) {
        if (BairroBox.getValue() == null || BairroBox.getValue().getID_Bairro() == 0) {
            BairroBox.setStyle("-fx-border-color:red");
        } else {
            RuaBox.setItems(Filtro.Bairro_para_Rua(BairroBox.getValue().getID_Cidade(), mainApp));
            BairroBox.setStyle("");
        }
    }

    private void On_Off_Button(boolean es) {
        modifição_endereco = null;
        NCasaText.setDisable(es);
        TelefoneText.setDisable(es);
        TelefoneFixoText.setDisable(es);
        ObservacaoText.setDisable(es);

        PaisBox.setDisable(es);
        EstadoBox.setDisable(es);
        CidadeBox.setDisable(es);
        BairroBox.setDisable(es);
        RuaBox.setDisable(es);

        bntSalva.setDisable(es);
        BtnCancelar.setDisable(es);
    }

    private boolean isInputValid() {
        System.out.println("isInputValid");
        String errorMessage = "";

        if (NCasaText.getText() == null || NCasaText.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
            NCasaText.setStyle("-fx-border-color:red");
        } else {
            NCasaText.setStyle("");
        }
        if (TelefoneText.getText() == null || TelefoneText.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
            TelefoneText.setStyle("-fx-border-color:red");
        } else {
            TelefoneText.setStyle("");
        }
        if (TelefoneFixoText.getText() == null || TelefoneFixoText.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
            TelefoneFixoText.setStyle("-fx-border-color:red");
        } else {
            TelefoneFixoText.setStyle("");
        }
        if (ObservacaoText.getText() == null || ObservacaoText.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
            ObservacaoText.setStyle("-fx-border-color:red");
        } else {
            ObservacaoText.setStyle("");
        }

        if (PaisBox.getValue() == null || PaisBox.getValue().getID_Pais() == 0) {
            errorMessage += "País inválido!\n";
            PaisBox.setStyle("-fx-border-color:red");
        } else {
            PaisBox.setStyle("");
        }
        if (EstadoBox.getValue() == null || EstadoBox.getValue().getID_Estado() == 0) {
            errorMessage += "Estado inválido!\n";
            EstadoBox.setStyle("-fx-border-color:red");
        } else {
            EstadoBox.setStyle("");
        }
        if (CidadeBox.getValue() == null || CidadeBox.getValue().getID_Cidade() == 0) {
            errorMessage += "Cidade inválido!\n";
            CidadeBox.setStyle("-fx-border-color:red");
        } else {
            CidadeBox.setStyle("");
        }

        if (BairroBox.getValue() == null || BairroBox.getValue().getID_Cidade() == 0) {
            errorMessage += "Bairro inválido!\n";
            BairroBox.setStyle("-fx-border-color:red");
        } else {
            BairroBox.setStyle("");
        }
        if (RuaBox.getValue() == null || RuaBox.getValue().getID_rua() == 0) {
            errorMessage += "Bairro inválido!\n";
            RuaBox.setStyle("-fx-border-color:red");
        } else {
            RuaBox.setStyle("");
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
        bntDetalhes.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());
        btnEditar.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID_cliente"));
        ClienteColumn.setCellValueFactory(new PropertyValueFactory<>("Nome"));

    }

}
