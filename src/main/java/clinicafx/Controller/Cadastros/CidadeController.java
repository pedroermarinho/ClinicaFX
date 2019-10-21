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
public class CidadeController implements Initializable {

    private MainApp mainApp;
    private model_cidades modifição_cidade;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        registrosView.setItems(this.mainApp.getDadosData().getCidadesData());
        PaisBox.setItems(this.mainApp.getDadosData().getPaisData());

    }

    @FXML
    private TableView<model_cidades> registrosView;

    @FXML
    private TableColumn<model_cidades, Integer> IDColumn;

    @FXML
    private TableColumn<model_cidades, String> CidadeColumn;

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
    private TextField NomeCidadeText;

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
            registrosView.setItems(mainApp.getDadosData().getCidadesData());
        }
    }

    private ObservableList<model_cidades> findItems() {
        ObservableList<model_cidades> itensEncontrados = FXCollections.observableArrayList();
        Integer ID;
        try {
            ID = Integer.parseInt(PesquisarField.getText());

        } catch (NumberFormatException a) {
            ID = null;

        }
        for (model_cidades itens : mainApp.getDadosData().getCidadesData()) {

            //itens.getID().contains(Integer.valueOf( PesquisaField.getText())
            if (ID != null) {
                if (itens.getID_Cidade() == ID) {
                    itensEncontrados.add(itens);

                }
            } else {
                if (itens.getCidade().contains(PesquisarField.getText())) {
                    itensEncontrados.add(itens);

                }
            }
        }
        return itensEncontrados;
    }

    @FXML
    void OnCancelar(ActionEvent event) {
        NomeCidadeText.setText("");
        PaisBox.setValue(null);
        EstadoBox.setValue(null);
        modifição_cidade = null;
        On_Off_Button(true);

    }

    @FXML
    void setEstados() {
        if (PaisBox.getValue() == null || PaisBox.getValue().getID_Pais() == 0) {
            EstadoBox.setStyle("-fx-border-color:red");
        } else {
            EstadoBox.setItems(Filtro.Pais_para_Estado(PaisBox.getValue().getID_Pais(), mainApp));
            EstadoBox.setStyle("");
        }
    }

    @FXML
    void OnDeletar(ActionEvent event) {
        System.out.println("DeletaPessoa");
        model_cidades selected = registrosView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.delete();
            
            mainApp.getDadosData().getCidadesData();
        } else {

        }
    }

    @FXML
    void OnDetalhes(ActionEvent event) {
        On_Off_Button(true);
        modifição_cidade = registrosView.getSelectionModel().getSelectedItem();
        NomeCidadeText.setText(modifição_cidade.getCidade());
        EstadoBox.setValue(model_estado.find(modifição_cidade.getID_Estado(), mainApp));
        PaisBox.setValue(model_pais.find(model_estado.find(modifição_cidade.getID_Estado(), mainApp).getID_Pais(), mainApp));

    }

    @FXML
    void OnEditar(ActionEvent event) {
        On_Off_Button(false);
        modifição_cidade = registrosView.getSelectionModel().getSelectedItem();
        NomeCidadeText.setText(modifição_cidade.getCidade());
        EstadoBox.setValue(model_estado.find(modifição_cidade.getID_Estado(), mainApp));
        PaisBox.setValue(model_pais.find(model_estado.find(modifição_cidade.getID_Estado(), mainApp).getID_Pais(), mainApp));

    }

    @FXML
    void OnNovo(ActionEvent event) {
        modifição_cidade = null;

        On_Off_Button(false);

        NomeCidadeText.setText("");
        PaisBox.setValue(null);
        EstadoBox.setValue(null);
    }

    @FXML
    void OnSalvar(ActionEvent event) {
        if (modifição_cidade == null) {
            modifição_cidade = new model_cidades(mainApp);
        }
        if (isInputValid()) {
            modifição_cidade.setCidade(NomeCidadeText.getText());
            modifição_cidade.setID_Estado(EstadoBox.getValue().getID_Estado());
            NomeCidadeText.setText("");
            PaisBox.setValue(null);
            EstadoBox.setValue(null);
            modifição_cidade.save();
            modifição_cidade = null;
            mainApp.getDadosData().getCidadesData();
            On_Off_Button(true);
        }

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

    private void On_Off_Button(boolean es) {
        NomeCidadeText.setDisable(es);
        PaisBox.setDisable(es);
        EstadoBox.setDisable(es);
        bntSalva.setDisable(es);
        BtnCancelar.setDisable(es);
    }

    private boolean isInputValid() {
        System.out.println("isInputValid");
        String errorMessage = "";

        if (NomeCidadeText.getText() == null || NomeCidadeText.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
            NomeCidadeText.setStyle("-fx-border-color:red");
        } else {
            NomeCidadeText.setStyle("");
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

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID_Cidade"));
        CidadeColumn.setCellValueFactory(new PropertyValueFactory<>("Cidade"));

    }

}
