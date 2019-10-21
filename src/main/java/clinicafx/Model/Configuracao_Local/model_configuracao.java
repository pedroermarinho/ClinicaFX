/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Configuracao_Local;

import clinicafx.MainApp;
import clinicafx.Model.Configuracao_Local.Dao_Configuracao.configuracaoDAO;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class model_configuracao {

    private final IntegerProperty ID_configuracao = new SimpleIntegerProperty();
    private final IntegerProperty ID_db = new SimpleIntegerProperty();

    public int getID_db() {
        return ID_db.get();
    }

    public void setID_db(int value) {
        ID_db.set(value);
    }

    public IntegerProperty ID_dbProperty() {
        return ID_db;
    }

    public int getID_configuracao() {
        return ID_configuracao.get();
    }

    public void setID_configuracao(int value) {
        ID_configuracao.set(value);
    }

    public IntegerProperty ID_configuracaoProperty() {
        return ID_configuracao;
    }

    private final MainApp mainapp;

    public model_configuracao(MainApp mainApp) {
        dao = new configuracaoDAO(mainApp);
        this.mainapp = mainApp;
    }

    private static configuracaoDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_configuracao.getValue() != null && ID_configuracao.get() != 0) {
            if (find(ID_configuracao.get(), mainapp) != null) {
                dao.updateConfiguracao(this);
            } else {
                dao.creatConfiguracao(this);
            }
        } else {
            dao.creatConfiguracao(this);
        }
    }

    public void delete() {
        if (find(ID_configuracao.get(), mainapp) != null) {
            dao.deleteConfiguracao(this);
        }
    }

    public static List<model_configuracao> all(MainApp mainApp) {
        dao = new configuracaoDAO(mainApp);
        return dao.getConfiguracaoList(mainApp);
    }

    public static model_configuracao find(int pk, MainApp mainApp) {
        dao = new configuracaoDAO(mainApp);
        return dao.getConfiguracaoID(pk, mainApp);
    }
}
