/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller.Cadastros;

import clinicafx.MainApp;
import clinicafx.Model.Cliente.model_cliente;
import clinicafx.Model.model_bairros;
import clinicafx.Model.model_cidades;
import clinicafx.Model.model_estado;
import clinicafx.Model.model_pais;
import clinicafx.Model.model_ruas;
import clinicafx.Util.Filtro;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class RuaController implements Initializable {

    private MainApp mainApp;
    private model_ruas modificao_rua;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        registrosView.setItems(mainApp.getDadosData().getRuasData());
        PaisBox.setItems(this.mainApp.getDadosData().getPaisData());
    }

    @FXML
    private TableView<model_ruas> registrosView;

    @FXML
    private TableColumn<model_ruas, Integer> IDColumn;

    @FXML
    private TableColumn<model_ruas, String> RuaColumn;

    @FXML
    private MenuItem bntDetalhes;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnNovo;

    @FXML
    private ComboBox<model_pais> PaisBox;

    @FXML
    private ComboBox<model_estado> EstadoBox;

    @FXML
    private ComboBox<model_cidades> CidadeBox;

    @FXML
    private ComboBox<model_bairros> BairroBox;

    @FXML
    private TextField NomeRuaText;

    @FXML
    private Button bntSalva;

    @FXML
    private Button BtnCancelar;
    @FXML
    private TextField PesquisarField;

    @FXML
    void OnPesquisar(ActionEvent event) {
        if (!PesquisarField.getText().equals("")) {
            registrosView.setItems(findItems());
        } else {
            registrosView.setItems(mainApp.getDadosData().getRuasData());
        }
    }

    private ObservableList<model_ruas> findItems() {
        ObservableList<model_ruas> itensEncontrados = FXCollections.observableArrayList();
        Integer ID;
        try {
            ID = Integer.parseInt(PesquisarField.getText());

        } catch (NumberFormatException a) {
            ID = null;

        }
        for (model_ruas itens : mainApp.getDadosData().getRuasData()) {

            //itens.getID().contains(Integer.valueOf( PesquisaField.getText())
            if (ID != null) {
                if (itens.getID_rua() == ID) {
                    itensEncontrados.add(itens);

                }
            } else {
                if (itens.getRua().contains(PesquisarField.getText())) {
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
        model_ruas selected = registrosView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.delete();
            mainApp.getDadosData().getRuasData();
        } else {

        }
    }

    @FXML
    void OnDetalhes(ActionEvent event) {
        On_Off_Button(true);
        modificao_rua = registrosView.getSelectionModel().getSelectedItem();
        NomeRuaText.setText(modificao_rua.getRua());
        BairroBox.setValue(model_bairros.find(modificao_rua.getID_Bairro(), mainApp));
        CidadeBox.setValue(model_cidades.find(model_bairros.find(modificao_rua.getID_Bairro(), mainApp).getID_Cidade(), mainApp));
        EstadoBox.setValue(model_estado.find(model_cidades.find(model_bairros.find(modificao_rua.getID_Bairro(), mainApp).getID_Cidade(), mainApp).getID_Estado(), mainApp));
        PaisBox.setValue(model_pais.find(model_estado.find(model_cidades.find(model_bairros.find(modificao_rua.getID_Bairro(), mainApp).getID_Cidade(), mainApp).getID_Estado(), mainApp).getID_Pais(), mainApp));

    }

    @FXML
    void OnEditar(ActionEvent event) {
        On_Off_Button(false);
        modificao_rua = registrosView.getSelectionModel().getSelectedItem();
        NomeRuaText.setText(modificao_rua.getRua());
        BairroBox.setValue(model_bairros.find(modificao_rua.getID_Bairro(), mainApp));
        CidadeBox.setValue(model_cidades.find(model_bairros.find(modificao_rua.getID_Bairro(), mainApp).getID_Cidade(), mainApp));
        EstadoBox.setValue(model_estado.find(model_cidades.find(model_bairros.find(modificao_rua.getID_Bairro(), mainApp).getID_Cidade(), mainApp).getID_Estado(), mainApp));
        PaisBox.setValue(model_pais.find(model_estado.find(model_cidades.find(model_bairros.find(modificao_rua.getID_Bairro(), mainApp).getID_Cidade(), mainApp).getID_Estado(), mainApp).getID_Pais(), mainApp));

    }

    @FXML
    void OnNovo(ActionEvent event) {

        On_Off_Button(false);

        LimparCampo();
    }

    @FXML
    void OnSalvar(ActionEvent event) {
        if (modificao_rua == null) {
            modificao_rua = new model_ruas(mainApp);
        }
        if (isInputValid()) {
            modificao_rua.setRua(NomeRuaText.getText());
            modificao_rua.setID_Bairro(BairroBox.getValue().getID_Bairro());
            modificao_rua.save();
            LimparCampo();
            mainApp.getDadosData().getRuasData();
            On_Off_Button(true);
        }

    }

    private void LimparCampo() {
        NomeRuaText.setText("");
        PaisBox.setValue(null);
        EstadoBox.setValue(null);
        CidadeBox.setValue(null);
        BairroBox.setValue(null);
        modificao_rua = null;
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
            BairroBox.setStyle("-fx-border-color:red");
        } else {
            BairroBox.setItems(Filtro.Cidade_para_Bairro(CidadeBox.getValue().getID_Cidade(), mainApp));
            BairroBox.setStyle("");
        }
    }

    private void On_Off_Button(boolean es) {
        NomeRuaText.setDisable(es);
        PaisBox.setDisable(es);
        EstadoBox.setDisable(es);
        CidadeBox.setDisable(es);
        BairroBox.setDisable(es);
        bntSalva.setDisable(es);
        BtnCancelar.setDisable(es);
    }

    private boolean isInputValid() {
        System.out.println("isInputValid");
        String errorMessage = "";

        if (NomeRuaText.getText() == null || NomeRuaText.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
            NomeRuaText.setStyle("-fx-border-color:red");
        } else {
            NomeRuaText.setStyle("");
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
        btnDeletar.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID_rua"));
        RuaColumn.setCellValueFactory(new PropertyValueFactory<>("Rua"));

    }

}
