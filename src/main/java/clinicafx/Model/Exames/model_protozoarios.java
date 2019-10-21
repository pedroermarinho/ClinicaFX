/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Exames;

import clinicafx.MainApp;
import clinicafx.Model.Exames.ExamesDAO.protozoariosDAO;
;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */


public class model_protozoarios {

    private final IntegerProperty ID_protozoarios = new SimpleIntegerProperty();

    public int getID_protozoarios() {
        return ID_protozoarios.get();
    }

    public void setID_protozoarios(int value) {
        ID_protozoarios.set(value);
    }

    public IntegerProperty ID_protozoariosProperty() {
        return ID_protozoarios;
    }

    private final MainApp mainapp;

    public model_protozoarios(MainApp mainApp) {
        dao = new protozoariosDAO(mainApp);
        this.mainapp = mainApp;
    }

    private static protozoariosDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_protozoarios.getValue() != null && ID_protozoarios.get() != 0) {
            if (find(ID_protozoarios.get(), mainapp) != null) {
                dao.updateProtozoarios(this);
            } else {
                dao.creatProtozoarios(this);
            }
        } else {
            dao.creatProtozoarios(this);
        }
    }

    public void delete() {
        if (find(ID_protozoarios.get(), mainapp) != null) {
            dao.deleteProtozoarios(this);
        }
    }

    public static List<model_protozoarios> all(MainApp mainApp) {
        dao = new protozoariosDAO(mainApp);
        return dao.getProtozoariosList(mainApp);
    }

    public static model_protozoarios find(int pk, MainApp mainApp) {
        dao = new protozoariosDAO(mainApp);
        return dao.getProtozoariosID(pk, mainApp);
    }
}
