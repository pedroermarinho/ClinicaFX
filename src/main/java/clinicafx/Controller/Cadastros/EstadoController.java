/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller.Cadastros;

import clinicafx.MainApp;
import clinicafx.Model.Cliente.model_cliente;
import clinicafx.Model.model_estado;
import clinicafx.Model.model_pais;
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
public class EstadoController implements Initializable {

    private MainApp mainApp;
    private model_estado modifição_estado;

    /**
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        modifição_estado = null;
        this.mainApp = mainApp;
        registrosView.setItems(this.mainApp.getDadosData().getEstadoData());
        PaisBox.setItems(this.mainApp.getDadosData().getPaisData());
    }

    @FXML
    private TableView<model_estado> registrosView;

    @FXML
    private TableColumn<model_estado, Integer> IDColumn;

    @FXML
    private TableColumn<model_estado, String> EstadoColumn;

    @FXML
    private ComboBox<model_pais> PaisBox;

    @FXML
    private MenuItem bntDetalhes;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnNovo;

    @FXML
    private TextField NomeEstadoText;

    @FXML
    private TextField AbreviacaoText;

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
            registrosView.setItems(mainApp.getDadosData().getEstadoData());
        }
    }

    private ObservableList<model_estado> findItems() {
        ObservableList<model_estado> itensEncontrados = FXCollections.observableArrayList();
        Integer ID;
        try {
            ID = Integer.parseInt(PesquisarField.getText());

        } catch (NumberFormatException a) {
            ID = null;

        }
        for (model_estado itens : mainApp.getDadosData().getEstadoData()) {

            //itens.getID().contains(Integer.valueOf( PesquisaField.getText())
            if (ID != null) {
                if (itens.getID_Estado() == ID) {
                    itensEncontrados.add(itens);

                }
            } else {
                if (itens.getEstado().contains(PesquisarField.getText()) || itens.getAbreviacao().contains(PesquisarField.getText())) {
                    itensEncontrados.add(itens);

                }
            }
        }
        return itensEncontrados;
    }

    @FXML
    void OnCancelar(ActionEvent event) {
        NomeEstadoText.setText("");
        AbreviacaoText.setText("");
        PaisBox.setValue(null);
        modifição_estado = null;
        On_Off_Button(true);
    }

    @FXML
    void OnDeletar(ActionEvent event) {
        System.out.println("DeletaPessoa");
        model_estado selected = registrosView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.delete();
            mainApp.getDadosData().getEstadoData();
        } else {

        }

    }

    @FXML
    void OnDetalhes(ActionEvent event) {
        On_Off_Button(true);
        modifição_estado = registrosView.getSelectionModel().getSelectedItem();
        NomeEstadoText.setText(modifição_estado.getEstado());
        AbreviacaoText.setText(modifição_estado.getAbreviacao());
        PaisBox.setValue(model_pais.find(modifição_estado.getID_Pais(), mainApp));

    }

    @FXML
    void OnEditar(ActionEvent event) {
        On_Off_Button(false);
        modifição_estado = registrosView.getSelectionModel().getSelectedItem();
        NomeEstadoText.setText(modifição_estado.getEstado());
        AbreviacaoText.setText(modifição_estado.getAbreviacao());
        PaisBox.setValue(model_pais.find(modifição_estado.getID_Pais(), mainApp));

    }

    @FXML
    void OnNovo(ActionEvent event) {
        modifição_estado = null;

        On_Off_Button(false);

        NomeEstadoText.setText("");
        AbreviacaoText.setText("");
        PaisBox.setValue(null);

    }

    @FXML
    void OnSalvar(ActionEvent event) {
        if (modifição_estado == null) {
            modifição_estado = new model_estado(mainApp);
        }
        if (isInputValid()) {
            modifição_estado.setEstado(NomeEstadoText.getText());
            modifição_estado.setID_Pais(PaisBox.getValue().getID_Pais());
            modifição_estado.setAbreviacao(AbreviacaoText.getText());
            NomeEstadoText.setText("");
            AbreviacaoText.setText("");
            PaisBox.setValue(null);
            modifição_estado.save();
            modifição_estado = null;
            mainApp.getDadosData().getEstadoData();
            On_Off_Button(true);
        }

    }

    /**
     * Valida a entrada do usuário nos campos de texto.
     *
     * @return true se a entrada é válida
     */
    private boolean isInputValid() {
        System.out.println("isInputValid");
        String errorMessage = "";

        if (NomeEstadoText.getText() == null || NomeEstadoText.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
            NomeEstadoText.setStyle("-fx-border-color:red");
        } else {
            NomeEstadoText.setStyle("");
        }
        if (AbreviacaoText.getText() == null || AbreviacaoText.getText().length() == 0) {
            errorMessage += "Abreviaçãos inválido!\n";
            AbreviacaoText.setStyle("-fx-border-color:red");
        } else {
            AbreviacaoText.setStyle("");
        }

        if (PaisBox.getValue() == null || PaisBox.getValue().getID_Pais() == 0) {
            errorMessage += "País inválido!\n";
            PaisBox.setStyle("-fx-border-color:red");
        } else {
            PaisBox.setStyle("");
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

    private void On_Off_Button(boolean es) {
        NomeEstadoText.setDisable(es);
        AbreviacaoText.setDisable(es);
        PaisBox.setDisable(es);
        bntSalva.setDisable(es);
        BtnCancelar.setDisable(es);
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

        bntDetalhes.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());
        btnEditar.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());
        btnDeletar.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());

        On_Off_Button(true);
//        
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID_Estado"));
        EstadoColumn.setCellValueFactory(new PropertyValueFactory<>("Estado"));

    }

}
