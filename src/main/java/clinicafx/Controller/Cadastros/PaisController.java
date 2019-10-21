/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller.Cadastros;

import clinicafx.MainApp;
import clinicafx.Model.Cliente.model_cliente;
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
public class PaisController implements Initializable {

    private MainApp mainApp;

    private model_pais modifição_pais;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        registrosView.setItems(this.mainApp.getDadosData().getPaisData());
    }
    @FXML
    private TableView<model_pais> registrosView;

    @FXML
    private TableColumn<model_pais, Integer> IDColumn;

    @FXML
    private TableColumn<model_pais, String> PaisColumn;

    @FXML
    private MenuItem bntDetalhes;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnNovo;

    @FXML
    private TextField NomePaisText;

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
            registrosView.setItems(mainApp.getDadosData().getPaisData());
        }
    }

    private ObservableList<model_pais> findItems() {
        ObservableList<model_pais> itensEncontrados = FXCollections.observableArrayList();
        Integer ID;
        try {
            ID = Integer.parseInt(PesquisarField.getText());

        } catch (NumberFormatException a) {
            ID = null;

        }
        for (model_pais itens : mainApp.getDadosData().getPaisData()) {

            //itens.getID().contains(Integer.valueOf( PesquisaField.getText())
            if (ID != null) {
                if (itens.getID_Pais() == ID) {
                    itensEncontrados.add(itens);

                }
            } else {
                if (itens.getPais().contains(PesquisarField.getText())) {
                    itensEncontrados.add(itens);

                }
            }
        }
        return itensEncontrados;
    }

    @FXML
    void OnCancelar(ActionEvent event) {
NomePaisText.setDisable(true);
        bntSalva.setDisable(true);
        BtnCancelar.setDisable(true);
        NomePaisText.setText("");
        modifição_pais = null;
        
    }

    @FXML
    void OnDeletar(ActionEvent event) {
        System.out.println("DeletaPessoa");
        model_pais selected = registrosView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.delete();
            mainApp.getDadosData().getPaisData();
        } else {

        }
    }

    @FXML
    void OnDetalhes(ActionEvent event) {

        NomePaisText.setDisable(true);
        bntSalva.setDisable(true);
        BtnCancelar.setDisable(true);
        modifição_pais = registrosView.getSelectionModel().getSelectedItem();
        NomePaisText.setText(modifição_pais.getPais());

    }

    @FXML
    void OnEditar(ActionEvent event) {
        NomePaisText.setDisable(false);
        bntSalva.setDisable(false);
        BtnCancelar.setDisable(false);
        modifição_pais = registrosView.getSelectionModel().getSelectedItem();
        NomePaisText.setText(modifição_pais.getPais());

    }

    @FXML
    void OnNovo(ActionEvent event) {
        modifição_pais = null;

        NomePaisText.setDisable(false);
        bntSalva.setDisable(false);
        BtnCancelar.setDisable(false);

        NomePaisText.setText("");

    }

    @FXML
    void OnSalvar(ActionEvent event) {

        if (modifição_pais == null) {
            modifição_pais = new model_pais(mainApp);
        }
        if (isInputValid()) {
            modifição_pais.setPais(NomePaisText.getText());
            NomePaisText.setText("");
            modifição_pais.save();
            modifição_pais = null;
            mainApp.getDadosData().getPaisData();
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

        if (NomePaisText.getText() == null || NomePaisText.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
            NomePaisText.setStyle("-fx-border-color:red");
        } else {
            NomePaisText.setStyle("");
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

        bntDetalhes.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());
        btnEditar.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());
        btnDeletar.disableProperty().bind(registrosView.getSelectionModel().selectedItemProperty().isNull());

        NomePaisText.setDisable(true);
        bntSalva.setDisable(true);
        BtnCancelar.setDisable(true);

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID_Pais"));
        PaisColumn.setCellValueFactory(new PropertyValueFactory<>("Pais"));

    }

}
