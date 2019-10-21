package clinicafx.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import clinicafx.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Pedro Marinho  < pedro.marinho238@gmail.com > marinho
 */
public class PalcoPrincipalController implements Initializable {

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        System.out.println("setMainApp");
        this.mainApp = mainApp;
    }

    @FXML
    void Salvar() {

    }

    /**
     * Cria uma agenda vazia.
     */
    @FXML
    private void cadastrados() {
        System.out.println("cadastrados");
//         mainApp.cadastrados();
    }

    //    @FXML
//    private void handleNew() {
//        System.out.println("handleNew");
//        mainApp.getPersonData().clear();
//        mainApp.setPersonFilePath(null);
//    }
    public boolean NovaPessoaOk() {
        return true;
    }

    /**
     * Abre o FileChooser para permitir o usuário selecionar uma agenda para
     * carregar.
     */
    @FXML
    private void NovaPessoa() {
        System.out.println("NovaPessoa");

//        boolean okClicked = mainApp.showPersonEditDialog(null);
        /**
         * Inicializa a classe controller. Este método é chamado automaticamente
         * após o arquivo fxml ter sido carregado.
         */
    }

    //private Label lbNome;
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // lbNome.setText(pessoa.getNome()+" "+pessoa.getSegundoNome());
    }

}
