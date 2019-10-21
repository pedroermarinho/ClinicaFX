package clinicafx;

import clinicafx.Model.Configuracao_Local.model_banco_de_dados;
import clinicafx.Model.Usuario.model_usuario;
import clinicafx.Util.BD.Banco_de_Dados_Cliente;
import static clinicafx.Util.BD.Tabelas.CriarTabela;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com > marinho
 */
public class MainApp extends Application {
    
    private final Dados DadosData =new Dados(this);

    public Dados getDadosData() {
        return DadosData;
    }
    private final ChamadasDeTela telas = new ChamadasDeTela();
    
    public ChamadasDeTela getTelas() {
        return telas;
    }
    

    private model_usuario usuario = null;

    private final Banco_de_Dados_Cliente banco_de_dados = new Banco_de_Dados_Cliente();

    

    private model_banco_de_dados dados_db = new model_banco_de_dados();

    public model_usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(model_usuario usuario) {
        this.usuario = usuario;
    }

    public model_banco_de_dados getDados_db() {

        return dados_db;
    }

    public void setDados_db(model_banco_de_dados dados_db) {
        this.dados_db = dados_db;
    }

    

    public Banco_de_Dados_Cliente getBanco_de_dados() {
        return banco_de_dados;
    }

    
 

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("start");
        this.telas.primeriaCena = stage;
        this.telas.primeriaCena.setTitle("Clinica");
        telas.setMainApp(this);

        setDados_db(telas.SelectBanco_de_Dados());
        banco_de_dados.setBanco_de_dados(dados_db);

        banco_de_dados.open();
        if (banco_de_dados.conexao != null) {
            CriarTabela(banco_de_dados.conexao, dados_db);
          
            Image image = new Image("/Icons/icon.png");

            if (!getDadosData().UsuariosDataNotThread().isEmpty()) {
                if (telas.usuario() != null) {
                    telas.primeriaCena.getIcons().add(image);
                    getDadosData().SincronizarBD();
                    telas.PalcoPrincipal();
                    telas.MenuTop();
                    telas.CentralTexto();
                }
            } else {
                setUsuario(telas.CadastroUsuario());
                if (getUsuario() != null) {
                    if (telas.usuario() != null) {
                        getDadosData().SincronizarBD();
                        telas.PalcoPrincipal();
                        telas.MenuTop();
                        telas.CentralTexto();
                    }
                }

            }
        } 
//        else {
//            MsgErro.MessagemErroBD(null, "Sem Conexão");
//
//        }

// 
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        Banco_de_Dados db = new Banco_de_Dados();

        launch(args);
    }

}
