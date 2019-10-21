/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Exames;

import clinicafx.MainApp;
import clinicafx.Model.Exames.ExamesDAO.helmitoDAO;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class model_helmito {

    private final IntegerProperty ID_helmito = new SimpleIntegerProperty();

    public int getID_helmito() {
        return ID_helmito.get();
    }

    public void setID_helmito(int value) {
        ID_helmito.set(value);
    }

    public IntegerProperty ID_helmitoProperty() {
        return ID_helmito;
    }

    private final MainApp mainapp;

    public model_helmito(MainApp mainApp) {
        dao = new helmitoDAO(mainApp);
        this.mainapp = mainApp;
    }

    private static helmitoDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_helmito.getValue() != null && ID_helmito.get() != 0) {
            if (find(ID_helmito.get(), mainapp) != null) {
                dao.updateHelmito(this);
            } else {
                dao.creatHelmito(this);
            }
        } else {
            dao.creatHelmito(this);
        }
    }

    public void delete() {
        if (find(ID_helmito.get(), mainapp) != null) {
            dao.deleteHelmito(this);
        }
    }

    public static List<model_helmito> all(MainApp mainApp) {
        dao = new helmitoDAO(mainApp);
        return dao.getHelmitoList(mainApp);
    }

    public static model_helmito find(int pk, MainApp mainApp) {
        dao = new helmitoDAO(mainApp);
        return dao.getHelmitoID(pk, mainApp);
    }
}
