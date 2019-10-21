/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Controller;

import clinicafx.MainApp;
import clinicafx.Model.Cliente.model_cliente;
import clinicafx.Model.Cliente.model_endereco_cliente;
import clinicafx.Model.Cliente.model_ficha;
import clinicafx.Model.model_bairros;
import clinicafx.Model.model_cidades;
import clinicafx.Model.model_estado;
import clinicafx.Model.model_pais;
import clinicafx.Model.model_ruas;
import clinicafx.Model.model_sexo;
import clinicafx.Util.Filtro;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class InformacaoController implements Initializable {

    private MainApp mainApp;
    private model_endereco_cliente endereco_cliente;
    private model_pais pais;
    private model_estado estado;
    private model_cidades cidade;
    private model_bairros bairro;
    private model_ruas ruas;

    @FXML
    private TableView<model_cliente> RegistroClientesView;

    @FXML
    private TableColumn<model_cliente, String> IDClienteColumn;

    @FXML
    private TableColumn<model_cliente, String> NomeClienteColumn;

    @FXML
    private Label CPFLabel;

    @FXML
    private Label CartaoSUSLabel;

    @FXML
    private Label NascimentoLabel;

    @FXML
    private Label SexoLabel;

    @FXML
    private Label MaeLabel;

    @FXML
    private Label EmailLabel;

    @FXML
    private Label NomeLabel;

    @FXML
    private Label PaiLabel;

    @FXML
    private Label CidadeLabel;

    @FXML
    private Label BairroLabel;

    @FXML
    private Label EstadoLabel;

    @FXML
    private Label PaisLabel;

    @FXML
    private Label RuaLabel;

    @FXML
    private Label NCasaLabel;

    @FXML
    private Label TelefoneLabel;

    @FXML
    private Label TelefoneFixoLabel;

    @FXML
    private TableView<model_ficha> RegistrosFichaView;

    @FXML
    private TableColumn<model_ficha, String> IDFichaColumn;

    @FXML
    private TableColumn<model_ficha, String> DataFichaColumn;

    @FXML
    private Label ObservacaoFichaLabel;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        RegistroClientesView.setItems(this.mainApp.getDadosData().getClientesData());
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

        IDClienteColumn.setCellValueFactory(new PropertyValueFactory<>("ID_cliente"));
        NomeClienteColumn.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        IDFichaColumn.setCellValueFactory(new PropertyValueFactory<>("ID_Ficha"));;
        DataFichaColumn.setCellValueFactory(new PropertyValueFactory<>("data_ficha"));
        RegistroClientesView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> Informacoes(newValue));
        RegistrosFichaView.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> ObservacaoFicha(newValue));
    }

    private void Informacoes(model_cliente newValue) {
        try {

            CPFLabel.textProperty().bind(newValue.CPFProperty());
            CartaoSUSLabel.textProperty().bind(newValue.Cartao_SUSProperty());
            NascimentoLabel.textProperty().bind(newValue.Data_NascimentoProperty());
            SexoLabel.textProperty().bind(model_sexo.find(newValue.getID_Sexo(), mainApp).SexoProperty());
            MaeLabel.textProperty().bind(newValue.MaeProperty());
            EmailLabel.textProperty().bind(newValue.EmailProperty());
            NomeLabel.textProperty().bind(newValue.NomeProperty());
            PaiLabel.textProperty().bind(newValue.PaiProperty());
            try {
                RegistrosFichaView.setItems(Filtro.Cliente_para_Ficha(newValue.getID_cliente(), mainApp));
            } catch (Exception a) {
                RegistrosFichaView.setItems(null);
            }
        } catch (Exception ex) {
            CPFLabel.textProperty().bind(new SimpleStringProperty(""));
            CartaoSUSLabel.textProperty().bind(new SimpleStringProperty(""));
            NascimentoLabel.textProperty().bind(new SimpleStringProperty(""));
            SexoLabel.textProperty().bind(new SimpleStringProperty(""));
            MaeLabel.textProperty().bind(new SimpleStringProperty(""));
            EmailLabel.textProperty().bind(new SimpleStringProperty(""));
            NomeLabel.textProperty().bind(new SimpleStringProperty(""));
            PaiLabel.textProperty().bind(new SimpleStringProperty(""));

        }
        try {

            endereco_cliente = Filtro.Cliente_para_Endereco(newValue.getID_cliente(), mainApp);
            System.out.println(endereco_cliente);
            if (endereco_cliente != null && endereco_cliente.getID_Endereco_Cliente() != 0) {
                System.out.println("ok");
                ruas = model_ruas.find(endereco_cliente.getID_Rua(), mainApp);
                bairro = model_bairros.find(endereco_cliente.getID_Bairro(), mainApp);
                cidade = model_cidades.find(endereco_cliente.getID_cidade(), mainApp);
                estado = model_estado.find(cidade.getID_Estado(), mainApp);
                pais = model_pais.find(estado.getID_Pais(), mainApp);
                RuaLabel.textProperty().bind(ruas.RuaProperty());
                BairroLabel.textProperty().bind(bairro.BairroProperty());
                CidadeLabel.textProperty().bind(cidade.CidadeProperty());
                EstadoLabel.textProperty().bind(estado.EstadoProperty());
                PaisLabel.textProperty().bind(pais.PaisProperty());

                NCasaLabel.textProperty().bind(endereco_cliente.Numero_CasaProperty().asString());
                TelefoneLabel.textProperty().bind(endereco_cliente.TelefoneProperty());
                TelefoneFixoLabel.textProperty().bind(endereco_cliente.Telefone_FixoProperty());
            } else {
                RuaLabel.textProperty().bind(new SimpleStringProperty(""));;
                BairroLabel.textProperty().bind(new SimpleStringProperty(""));;
                CidadeLabel.textProperty().bind(new SimpleStringProperty(""));
                EstadoLabel.textProperty().bind(new SimpleStringProperty(""));
                PaisLabel.textProperty().bind(new SimpleStringProperty(""));

                NCasaLabel.textProperty().bind(new SimpleStringProperty(""));
                TelefoneLabel.textProperty().bind(new SimpleStringProperty(""));
                TelefoneFixoLabel.textProperty().bind(new SimpleStringProperty(""));
            }
        } catch (Exception a) {
            RuaLabel.textProperty().bind(new SimpleStringProperty(""));
            BairroLabel.textProperty().bind(new SimpleStringProperty(""));
            CidadeLabel.textProperty().bind(new SimpleStringProperty(""));
            EstadoLabel.textProperty().bind(new SimpleStringProperty(""));
            PaisLabel.textProperty().bind(new SimpleStringProperty(""));

            NCasaLabel.textProperty().bind(new SimpleStringProperty(""));
            TelefoneLabel.textProperty().bind(new SimpleStringProperty(""));
            TelefoneFixoLabel.textProperty().bind(new SimpleStringProperty(""));
        }

    }

    private void ObservacaoFicha(model_ficha newValue) {
        ObservacaoFichaLabel.setText(RegistrosFichaView.getSelectionModel().getSelectedItem().getObservacao());

    }

}
