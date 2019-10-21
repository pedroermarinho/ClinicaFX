/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller.Cadastros;

import clinicafx.MainApp;
import clinicafx.Model.Cliente.model_cliente;
import clinicafx.Model.model_sexo;
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
public class SexoController implements Initializable {

    private MainApp mainApp;
    private model_sexo modifição_sexo;

    public void setMainApp(MainApp mainApp) {
        modifição_sexo = null;
        this.mainApp = mainApp;
        registrosView.setItems(this.mainApp.getDadosData().getSexosData());
    }

    @FXML
    private TableView<model_sexo> registrosView;

    @FXML
    private TableColumn<model_sexo, Integer> IDColumn;

    @FXML
    private TableColumn<model_sexo, String> SexoColumn;

    @FXML
    private MenuItem bntDetalhes;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnDeletar;

    @FXML
    private Button btnNovo;

    @FXML
    private TextField NomeSexoText;

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
            registrosView.setItems(mainApp.getDadosData().getSexosData());
        }
    }

    private ObservableList<model_sexo> findItems() {
        ObservableList<model_sexo> itensEncontrados = FXCollections.observableArrayList();
        Integer ID;
        try {
            ID = Integer.parseInt(PesquisarField.getText());

        } catch (NumberFormatException a) {
            ID = null;

        }
        for (model_sexo itens : mainApp.getDadosData().getSexosData()) {

            //itens.getID().contains(Integer.valueOf( PesquisaField.getText())
            if (ID != null) {
                if (itens.getID_Sexo() == ID) {
                    itensEncontrados.add(itens);

                }
            } else {
                if (itens.getSexo().contains(PesquisarField.getText())) {
                    itensEncontrados.add(itens);

                }
            }
        }
        return itensEncontrados;
    }

    @FXML
    void OnCancelar(ActionEvent event) {
        NomeSexoText.setText("");
        modifição_sexo = null;
        On_Off_Button(true);
    }

    @FXML
    void OnDeletar(ActionEvent event) {

        System.out.println("DeletaPessoa");
        model_sexo selected = registrosView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.delete();
            mainApp.getDadosData().getSexosData();
        } else {

        }

    }

    @FXML
    void OnDetalhes(ActionEvent event) {
        On_Off_Button(true);
        modifição_sexo = registrosView.getSelectionModel().getSelectedItem();
        NomeSexoText.setText(modifição_sexo.getSexo());

    }

    @FXML
    void OnEditar(ActionEvent event) {
        On_Off_Button(false);
        modifição_sexo = registrosView.getSelectionModel().getSelectedItem();
        NomeSexoText.setText(modifição_sexo.getSexo());

    }

    @FXML
    void OnNovo(ActionEvent event) {
        modifição_sexo = null;

        On_Off_Button(false);

        NomeSexoText.setText("");

    }

    @FXML
    void OnSalvar(ActionEvent event) {
        if (modifição_sexo == null) {
            modifição_sexo = new model_sexo(mainApp);
        }
        if (isInputValid()) {
            modifição_sexo.setSexo(NomeSexoText.getText());
            NomeSexoText.setText("");
            modifição_sexo.save();
            modifição_sexo = null;
            mainApp.getDadosData().getSexosData();
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

        if (NomeSexoText.getText() == null || NomeSexoText.getText().length() == 0) {
            errorMessage += "Nome inválido!\n";
            NomeSexoText.setStyle("-fx-border-color:red");
        } else {
            NomeSexoText.setStyle("");
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
        NomeSexoText.setDisable(es);
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

        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID_Sexo"));
        SexoColumn.setCellValueFactory(new PropertyValueFactory<>("Sexo"));

    }

}
