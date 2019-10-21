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
import javafx.scene.control.ChoiceBox;
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
public class BairroController implements Initializable {

    private MainApp mainApp;
    private model_bairros modifição_bairro;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        registrosView.setItems(this.mainApp.getDadosData().getBairrosData());
        PaisBox.setItems(this.mainApp.getDadosData().getPaisData());

    }

    @FXML
    private TableView<model_bairros> registrosView;

    @FXML
    private TableColumn<model_cidades, Integer> IDColumn;

    @FXML
    private TableColumn<model_cidades, String> BairroColumn;

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
    private TextField NomeBairroText;

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
            registrosView.setItems(mainApp.getDadosData().getBairrosData());
        }
    }

    private ObservableList<model_bairros> findItems() {
        ObservableList<model_bairros> itensEncontrados = FXCollections.observableArrayList();
        Integer ID;
        try {
            ID = Integer.parseInt(PesquisarField.getText());

        } catch (NumberFormatException a) {
            ID = null;

        }
        for (model_bairros itens : mainApp.getDadosData().getBairrosData()) {

            //itens.getID().contains(Integer.valueOf( PesquisaField.getText())
            if (ID != null) {
                if (itens.getID_Bairro() == ID) {
                    itensEncontrados.add(itens);

                }
            } else {
                if (itens.getBairro().contains(PesquisarField.getText())) {
                    itensEncontrados.add(itens);

                }
            }
        }
        return itensEncontrados;
    }

    @FXML
    void OnCancelar(ActionEvent event) {
        On_Off_Button(true);
        NomeBairroText.setText("");
        PaisBox.setValue(null);
        EstadoBox.setValue(null);
        CidadeBox.setValue(null);
        modifição_bairro = null;
    }

    @FXML
    void OnDeletar(ActionEvent event) {
        System.out.println("DeletaPessoa");
        model_bairros selected = registrosView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.delete();
            mainApp.getDadosData().getBairrosData();
        } else {

        }
    }

    @FXML
    void OnDetalhes(ActionEvent event) {
        On_Off_Button(true);
        modifição_bairro = registrosView.getSelectionModel().getSelectedItem();
        NomeBairroText.setText(modifição_bairro.getBairro());
        CidadeBox.setValue(model_cidades.find(modifição_bairro.getID_Cidade(), mainApp));
        EstadoBox.setValue(model_estado.find(model_cidades.find(modifição_bairro.getID_Cidade(), mainApp).getID_Estado(), mainApp));
        PaisBox.setValue(model_pais.find(model_estado.find(model_cidades.find(modifição_bairro.getID_Cidade(), mainApp).getID_Estado(), mainApp).getID_Pais(), mainApp));

    }

    @FXML
    void OnEditar(ActionEvent event) {
        On_Off_Button(false);
        modifição_bairro = registrosView.getSelectionModel().getSelectedItem();
        NomeBairroText.setText(modifição_bairro.getBairro());
        CidadeBox.setValue(model_cidades.find(modifição_bairro.getID_Cidade(), mainApp));
        EstadoBox.setValue(model_estado.find(model_cidades.find(modifição_bairro.getID_Cidade(), mainApp).getID_Estado(), mainApp));
        PaisBox.setValue(model_pais.find(model_estado.find(model_cidades.find(modifição_bairro.getID_Cidade(), mainApp).getID_Estado(), mainApp).getID_Pais(), mainApp));

    }

    @FXML
    void OnNovo(ActionEvent event) {
        On_Off_Button(false);
        modifição_bairro = null;
        NomeBairroText.setText("");
        PaisBox.setValue(null);
        EstadoBox.setValue(null);
        CidadeBox.setValue(null);
    }

    @FXML
    void OnSalvar(ActionEvent event) {

        if (modifição_bairro == null) {
            modifição_bairro = new model_bairros(mainApp);
        }
        if (isInputValid()) {
            modifição_bairro.setBairro(NomeBairroText.getText());
            modifição_bairro.setID_Cidade(CidadeBox.getValue().getID_Cidade());
            NomeBairroText.setText("");
            PaisBox.setValue(null);
            EstadoBox.setValue(null);
            CidadeBox.setValue(null);
            modifição_bairro.save();
            modifição_bairro = null;
            mainApp.getDadosData().getBairrosData();
            On_Off_Button(true);
        }

    }

    private void On_Off_Button(boolean es) {
        PaisBox.setDisable(es);
        EstadoBox.setDisable(es);
        CidadeBox.setDisable(es);
        NomeBairroText.setDisable(es);
        bntSalva.setDisable(es);
        BtnCancelar.setDisable(es);
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

    private boolean isInputValid() {
        System.out.println("isInputValid");
        String errorMessage = "";

        if (NomeBairroText.getText() == null || NomeBairroText.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
            NomeBairroText.setStyle("-fx-border-color:red");
        } else {
            NomeBairroText.setStyle("");
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

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID_Bairro"));
        BairroColumn.setCellValueFactory(new PropertyValueFactory<>("Bairro"));

    }

}
