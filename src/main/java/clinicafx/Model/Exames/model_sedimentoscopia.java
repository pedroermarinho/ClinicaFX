/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Exames;

import clinicafx.MainApp;
import clinicafx.Model.Exames.ExamesDAO.sedimentoscopiaDAO;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class model_sedimentoscopia {

    private final IntegerProperty ID_sedimentoscopia = new SimpleIntegerProperty();

    public int getID_sedimentoscopia() {
        return ID_sedimentoscopia.get();
    }

    public void setID_sedimentoscopia(int value) {
        ID_sedimentoscopia.set(value);
    }

    public IntegerProperty ID_sedimentoscopiaProperty() {
        return ID_sedimentoscopia;
    }

    private final MainApp mainapp;

    public model_sedimentoscopia(MainApp mainApp) {
        dao = new sedimentoscopiaDAO(mainApp);
        this.mainapp = mainApp;
    }

    private static sedimentoscopiaDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_sedimentoscopia.getValue() != null && ID_sedimentoscopia.get() != 0) {
            if (find(ID_sedimentoscopia.get(), mainapp) != null) {
                dao.updateSedimentoscopia(this);
            } else {
                dao.creatSedimentoscopia(this);
            }
        } else {
            dao.creatSedimentoscopia(this);
        }
    }

    public void delete() {
        if (find(ID_sedimentoscopia.get(), mainapp) != null) {
            dao.deleteSedimentoscopia(this);
        }
    }

    public static List<model_sedimentoscopia> all(MainApp mainApp) {
        dao = new sedimentoscopiaDAO(mainApp);
        return dao.getSedimentoscopiaList(mainApp);
    }

    public static model_sedimentoscopia find(int pk, MainApp mainApp) {
        dao = new sedimentoscopiaDAO(mainApp);
        return dao.getSedimentoscopiaID(pk, mainApp);
    }
}
