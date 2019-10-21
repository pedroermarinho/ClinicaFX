/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx;

import clinicafx.Controller.*;
import clinicafx.Controller.Cadastros.*;
import clinicafx.Controller.Configuracao.*;
import clinicafx.Controller.Exames.*;
import clinicafx.Controller.Usuario.CadastroUsuarioController;
import clinicafx.Controller.Usuario.SelectUsuarioController;
import clinicafx.Controller.Usuario.UsuarioController;
import clinicafx.Controller.Usuario.UsuariosController;
import clinicafx.Controller.Util.SobreController;
import clinicafx.Controller.Util.ErroController;
import clinicafx.Controller.Util.SenhaController;
import clinicafx.Model.Cliente.model_cliente;
import clinicafx.Model.Clinicas.model_clinica;
import clinicafx.Model.Configuracao_Local.model_banco_de_dados;
import clinicafx.Model.Usuario.model_usuario;
import clinicafx.Model.model_bairros;
import clinicafx.Util.MsgErro;
import static clinicafx.Util.MsgErro.MessagemErroTela;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class ChamadasDeTela {

    /**
     *
     */
    protected Stage primeriaCena;

    /**
     *
     */
    protected BorderPane PalcoPrincipal;

    /**
     *
     */
    protected MainApp mainapp;

    public Stage getPrimeriaCena() {
        return primeriaCena;
    }

    public BorderPane getPalcoPrincipal() {
        return PalcoPrincipal;
    }

    /**
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        System.out.println("setMainApp");
        this.mainapp = mainApp;
    }

    /**
     *
     */
    public void PalcoPrincipal() {
        System.out.println("PalcoPrincipal");
        try {

            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/PalcoPrincipal.fxml"));
            PalcoPrincipal = (BorderPane) loader.load();

            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(PalcoPrincipal);
            primeriaCena.setScene(scene);

            // Dá ao controller o acesso ao main app.
            PalcoPrincipalController controller = loader.getController();
            controller.setMainApp(mainapp);

            primeriaCena.show();
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "PalcoPrincipal");
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "PalcoPrincipal");
        }

    }

    /**
     *
     */
    public void MenuDireto() {
        System.out.println("MenuDireto");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/MenuDireto.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setLeft(personOverview);

            // Dá ao controlador acesso à the main app.
            MenuDiretoController controller = loader.getController();
            controller.setMainApp(mainapp);

        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "MenuDireto");
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "MenuDireto");
        }

    }

    /**
     *
     */
    public void CentralTexto() {
        System.out.println("CentralTexto");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/CentralTexto.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setCenter(personOverview);

            // Dá ao controlador acesso à the main app.
            CentralTextoController controller = loader.getController();
            controller.setMainApp(mainapp);

//            controller.setMainApp(this);
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CentralTexto");
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "CentralTexto");
        }
    }

    /**
     *
     */
    public void MenuBottom() {
        System.out.println("MenuBottom");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/MenuBottom.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setBottom(personOverview);

            // Dá ao controlador acesso à the main app.
            MenuBottomController controller = loader.getController();
            controller.setMainApp(mainapp);

//            controller.setMainApp(this,primeriaCena);
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "MenuBottom");
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "MenuBottom");
        }
    }

    /**
     *
     */
    public void MenuTop() {
        System.out.println("MenuTop");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/MenuTop.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            PalcoPrincipal.setTop(personOverview);

            // Dá ao controlador acesso à the main app.
            MenuTopController controller = loader.getController();
            controller.setMainApp(mainapp);

        } catch (IOException e) {

            MsgErro.MessagemErroTela(e, "MenuTop");
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "MenuTop");
        }
    }

    /**
     *
     */
    public void Cadastros() {
        System.out.println("Cadastros");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Cadastro.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setCenter(personOverview);

            // Dá ao controlador acesso à the main app.
            CadastroController controller = loader.getController();
            controller.setMainApp(mainapp);

        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Cadastros");
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "Cadastros");
        }
    }

    public void Agenda() {
        System.out.println("Agenda");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Agenda.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setCenter(personOverview);

            // Dá ao controlador acesso à the main app.
            AgendaController controller = loader.getController();
            controller.setMainApp(mainapp);

        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Cadastros");
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "Cadastros");
        }
    }

    public void Dados() {
        System.out.println("Dados");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Dados.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setCenter(personOverview);

            // Dá ao controlador acesso à the main app.
            DadosController controller = loader.getController();
            controller.setMainApp(mainapp);

        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Dados");
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "Dados");
        }
    }

    public void Exames() {
        System.out.println("Exames");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Exames.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setCenter(personOverview);

            // Dá ao controlador acesso à the main app.
            ExamesController controller = loader.getController();
            controller.setMainApp(mainapp);

        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Exames");
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "Exames");
        }
    }

    public void Informacao() {
        System.out.println("Informacao");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Informacao.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setCenter(personOverview);

            // Dá ao controlador acesso à the main app.
            InformacaoController controller = loader.getController();
            controller.setMainApp(mainapp);

        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Informacao");
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "Informacao");
        }
    }

    public void Perfil() {
        System.out.println("perfil");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Profiles.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setCenter(personOverview);

            // Dá ao controlador acesso à the main app.
            ProfilesController controller = loader.getController();
            controller.setMainApp(mainapp);

        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Perfil");
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "Perfil");
        }
    }

    public void Menu_Configuracao() {
        System.out.println("Menu_Configuracao");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Configuracao/Menu_Configuracao.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setCenter(personOverview);

            // Dá ao controlador acesso à the main app.
            Menu_ConfiguracaoController controller = loader.getController();
            controller.setMainApp(mainapp);

        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Menu_Configuracao");
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "Menu_Configuracao");
        }
    }

    public void Alertas() {
        System.out.println("Alertas");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Alertas.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Define a person overview no centro do PalcoPrincipal.
            //PalcoPrincipal.setCenter(personOverview);
            PalcoPrincipal.setCenter(personOverview);

            // Dá ao controlador acesso à the main app.
            AlertasController controller = loader.getController();
            controller.setMainApp(mainapp);

        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Alertas");
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "Alertas");
        }
    }

    /**
     *
     * @return
     */
    public AnchorPane CadastroBairro() {
        System.out.println("CadastroBairro");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Cadastros/Bairro.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            BairroController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CadastroBairro");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "CadastroBairro");
            return null;
        }
    }

    /**
     *
     * @return
     */
    public AnchorPane CadastroCidade() {
        System.out.println("CadastroBairro");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Cadastros/Cidade.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            CidadeController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CadastroCidade");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "CadastroCidade");
            return null;
        }
    }

    /**
     *
     * @return
     */
    public AnchorPane CadastroCliente() {
        System.out.println("CadastroCliente");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Cadastros/Cliente.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            ClienteController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CadastroCliente");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "CadastroCliente");
            return null;
        }
    }

    /**
     *
     * @return
     */
    public AnchorPane CadastroClinica() {
        System.out.println("CadastroClinica");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Cadastros/Clinica.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            ClinicaController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CadastroClinica");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "CadastroClinica");
            return null;
        }
    }

    /**
     *
     * @return
     */
    public AnchorPane CadastroCorpoCliente() {
        System.out.println("CadastroCorpoCliente");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Cadastros/Corpo_Cliente.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            Corpo_ClienteController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CadastroCorpoCliente");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "CadastroCorpoCliente");
            return null;
        }
    }

    /**
     *
     * @return
     */
    public AnchorPane CadastroEnderecoCliente() {
        System.out.println("CadastroEnderecoCliente");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Cadastros/Endereco_Cliente.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            Endereco_ClienteController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CadastroEnderecoCliente");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "CadastroEnderecoCliente");
            return null;
        }
    }

    /**
     *
     * @return
     */
    public AnchorPane CadastroEnderecoClinica() {
        System.out.println("CadastroEnderecoClinica");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Cadastros/Endereco_Clinica.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            Endereco_ClinicaController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CadastroEnderecoClinica");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "CadastroEnderecoClinica");
            return null;
        }
    }

    /**
     *
     * @return
     */
    public AnchorPane CadastroEstado() {
        System.out.println("CadastroEstado");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Cadastros/Estado.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            EstadoController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CadastroEstado");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "CadastroEstado");
            return null;
        }
    }

    /**
     *
     * @return
     */
    public AnchorPane CadastroFicha() {
        System.out.println("CadastroFicha");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Cadastros/Ficha.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            FichaController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CadastroFicha");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "CadastroFicha");
            return null;
        }
    }

    /**
     *
     * @return
     */
    public AnchorPane CadastroPais() {
        System.out.println("CadastroPais");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Cadastros/Pais.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            PaisController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CadastroPais");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "CadastroPais");
            return null;
        }
    }

    /**
     *
     * @return
     */
    public AnchorPane CadastroRua() {
        System.out.println("CadastroRua");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Cadastros/Rua.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            RuaController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CadastroRua");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "CadastroRua");
            return null;
        }
    }

    /**
     *
     * @return
     */
    public AnchorPane CadastroSexo() {
        System.out.println("CadastroSexo");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Cadastros/Sexo.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            SexoController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "CadastroSexo");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "CadastroSexo");
            return null;
        }
    }

    public AnchorPane Eas() {
        System.out.println("Eas");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Exames/Eas.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            EasController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Eas");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "Eas");
            return null;
        }
    }

    public AnchorPane Fezes() {
        System.out.println("Fezes");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Exames/Fezes.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            FezesController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Fezes");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "Fezes");
            return null;
        }
    }

    public AnchorPane Helmito() {
        System.out.println("Helmito");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Exames/Helmito.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            HelmitoController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Helmito");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "Helmito");
            return null;
        }
    }

    public AnchorPane Homograma() {
        System.out.println("Homograma");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Exames/Homograma.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            HomogramaController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Homograma");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "Homograma");
            return null;
        }
    }

    public AnchorPane Protozoarios() {
        System.out.println("Protozoarios");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Exames/Protozoarios.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            ProtozoariosController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Protozoarios");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "Protozoarios");
            return null;
        }
    }

    public AnchorPane Sedimentoscopia() {
        System.out.println("Sedimentoscopia");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Exames/Sedimentoscopia.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            SedimentoscopiaController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Sedimentoscopia");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "Sedimentoscopia");
            return null;
        }
    }

    /**
     *
     * @return
     */
    public AnchorPane Configuracao_Banco_de_Dados() {
        System.out.println("Configuracao_Banco_de_Dados");
        try {

            // Carrega a TabelaDeJogos.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Configuracao/Configuracao_Banco_de_Dados.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Dá ao controlador acesso à the main app.
            Configuracao_Banco_de_DadosController controller = loader.getController();
            controller.setMainApp(mainapp);
            return personOverview;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "Configuracao_Banco_de_Dados");
            return null;
        } catch (Exception e) {
            MsgErro.MessagemErroTela(e, "Configuracao_Banco_de_Dados");
            return null;
        }
    }

    public boolean Erro(Exception ex) {
        System.out.println("Erro");
        try {
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Util/Erro.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Erro");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primeriaCena);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            Image image = new Image("/Icons/icon.png");
            dialogStage.getIcons().add(image);
            // Define a pessoa no controller.
            ErroController controller = loader.getController();
            controller.setMainApp(mainapp, ex);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            return true;
        } catch (IOException e) {
            MsgErro.MessagemErroTela(e, "erro");
            return false;
        }

    }

    public void sobre() {
        try {
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Util/Sobre.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Sobre");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primeiraCena);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            Image image = new Image("/Icons/icon.png");
            dialogStage.getIcons().add(image);

            // Define a pessoa no controller.
            SobreController controller = loader.getController();
            //controller.setMainApp(this);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

        } catch (IOException e) {
            Erro(e);

        } catch (Exception e) {
            Erro(e);
        }

    }

    public boolean senha(model_usuario pessoa) {
        try {

            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Util/Senha.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("senha");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primeiraCena);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            Image image = new Image("/Icons/icon.png");
            dialogStage.getIcons().add(image);

            // Define a pessoa no controller.
            SenhaController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(mainapp, pessoa);
            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();
            return controller.senha();

        } catch (IOException e) {
            Erro(e);
            return false;

        } catch (Exception e) {
            Erro(e);
            return false;

        }
    }

    public void usuarios() {
        try {

            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Usuario/Usuarios.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            PalcoPrincipal.setCenter(page);

            // Define a pessoa no controller.
            UsuariosController controller = loader.getController();
            //controller.setDialogStage(dialogStage);
            controller.setMainApp(mainapp);

            // Mostra a janela e espera até o usuário fechar.
//            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public model_usuario usuario() {
        try {

            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Usuario/Usuario.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Usuário");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primeiraCena);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            Image image = new Image("/Icons/icon.png");
            dialogStage.getIcons().add(image);

            // Define a pessoa no controller.
            UsuarioController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(mainapp);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            return controller.usuario();
        } catch (IOException e) {
            Erro(e);

            return null;

        } catch (Exception e) {
            Erro(e);

            return null;
        }
    }

    public model_usuario CadastroUsuario() {
        try {
            System.out.println("CadastroUsuario");
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Usuario/CadastroUsuario.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro Usuario");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(primeiraCena);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            Image image = new Image("/Icons/icon.png");
            dialogStage.getIcons().add(image);

            // Define a pessoa no controller.
            CadastroUsuarioController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(mainapp);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            return controller.getUsuario();
        } catch (IOException e) {
            Erro(e);

            return null;

        } catch (Exception e) {
            Erro(e);

            return null;
        }
    }

    public model_cliente Clientes() {
        try {
            System.out.println("Clientes");
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Clientes.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Clientes");
            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primeriaCena);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            Image image = new Image("/Icons/icon.png");
            dialogStage.getIcons().add(image);

            // Define a pessoa no controller.
            ClientesController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(mainapp);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            return controller.getclinte();
        } catch (IOException e) {
            MessagemErroTela(e, "Clientes");

            return null;

        } catch (Exception e) {
            MessagemErroTela(e, "Clientes");

            return null;
        }

    }

    public model_clinica Clinicas() {
        try {
            System.out.println("Clinicas");
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Clinicas.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Clientes");
            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primeriaCena);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            Image image = new Image("/Icons/icon.png");
            dialogStage.getIcons().add(image);

            // Define a pessoa no controller.
            ClinicasController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(mainapp);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            return controller.getclinica();
        } catch (IOException e) {
            MessagemErroTela(e, "Clientes");

            return null;

        } catch (Exception e) {
            MessagemErroTela(e, "Clientes");

            return null;
        }

    }

    public model_usuario SelectUsuario() {
        try {
            System.out.println("Usuarios");
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Usuario/SelectUsuario.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Usuarios");
            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primeriaCena);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            Image image = new Image("/Icons/icon.png");
            dialogStage.getIcons().add(image);

            // Define a pessoa no controller.
            SelectUsuarioController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(mainapp);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            return controller.getUsuario();
        } catch (IOException e) {
            MessagemErroTela(e, "Usuarios");

            return null;

        } catch (Exception e) {
            MessagemErroTela(e, "Usuarios");

            return null;
        }

    }

    public void CadastroSexoDialogStage() {
        try {
            System.out.println("CadastroSexoDialogStage");
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Cadastros/CadastroSexo.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Cadastro Sexo");
            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primeriaCena);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            Image image = new Image("/Icons/icon.png");
            dialogStage.getIcons().add(image);

            // Define a pessoa no controller.
            CadastroSexoController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(mainapp);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

        } catch (IOException e) {
            MessagemErroTela(e, "CadastroSexoDialogStage");

        } catch (Exception e) {
            MessagemErroTela(e, "CadastroSexoDialogStage");

        }

    }

    public model_banco_de_dados SelectBanco_de_Dados() {
        try {
            System.out.println("SelectBanco_de_Dados");
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Configuracao/Bancos_de_Dados.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("SelectBanco_de_Dados");
            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primeriaCena);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            Image image = new Image("/Icons/icon.png");
            dialogStage.getIcons().add(image);

            // Define a pessoa no controller.
            Bancos_de_DadosController controller = loader.getController();

            controller.setDialogStage(dialogStage);
            controller.setMainApp(mainapp);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();
            return controller.getBanco_de_Dados();
        } catch (IOException e) {
            MessagemErroTela(e, "SelectBanco_de_Dados");
            return null;
        } catch (Exception e) {
            MessagemErroTela(e, "SelectBanco_de_Dados");
            return null;
        }
    }

    public void CadastroBancoDeDados() {
        try {
            System.out.println("CadastroBancoDeDados");
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/Fxml/Configuracao/CadastroBancoDeDados.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("CadastroBancoDeDados");
            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primeriaCena);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            Image image = new Image("/Icons/icon.png");
            dialogStage.getIcons().add(image);

            // Define a pessoa no controller.
            CadastroBancoDeDadosController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(mainapp);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();
        } catch (IOException e) {
            MessagemErroTela(e, "CadastroBancoDeDados");
        } catch (Exception e) {
            MessagemErroTela(e, "CadastroBancoDeDados");
        }
    }

}
