/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Exames;

import clinicafx.MainApp;
import clinicafx.Model.Exames.ExamesDAO.easDAO;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class model_eas {

    private final IntegerProperty ID_eas = new SimpleIntegerProperty();

    public int getID_eas() {
        return ID_eas.get();
    }

    public void setID_eas(int value) {
        ID_eas.set(value);
    }

    public IntegerProperty ID_easProperty() {
        return ID_eas;
    }

    private final MainApp mainapp;

    public model_eas(MainApp mainApp) {
        dao = new easDAO(mainApp);
        this.mainapp = mainApp;
    }

    private static easDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_eas.getValue() != null && ID_eas.get() != 0) {
            if (find(ID_eas.get(), mainapp) != null) {
                dao.updateEas(this);
            } else {
                dao.creatEas(this);
            }
        } else {
            dao.creatEas(this);
        }
    }

    public void delete() {
        if (find(ID_eas.get(), mainapp) != null) {
            dao.deleteEas(this);
        }
    }

    public static List<model_eas> all(MainApp mainApp) {
        dao = new easDAO(mainApp);
        return dao.getEasList(mainApp);
    }

    public static model_eas find(int pk, MainApp mainApp) {
        dao = new easDAO(mainApp);
        return dao.getEasID(pk, mainApp);
    }
}
