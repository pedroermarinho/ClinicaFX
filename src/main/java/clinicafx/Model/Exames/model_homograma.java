/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Exames;

import clinicafx.MainApp;
import clinicafx.Model.Exames.ExamesDAO.homogramaDAO;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class model_homograma {

    private final IntegerProperty ID_homograma = new SimpleIntegerProperty();

    public int getID_homograma() {
        return ID_homograma.get();
    }

    public void setID_homograma(int value) {
        ID_homograma.set(value);
    }

    public IntegerProperty ID_homogramaProperty() {
        return ID_homograma;
    }

    private final MainApp mainapp;

    public model_homograma(MainApp mainApp) {
        dao = new homogramaDAO(mainApp);
        this.mainapp = mainApp;
    }

    private static homogramaDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_homograma.getValue() != null && ID_homograma.get() != 0) {
            if (find(ID_homograma.get(), mainapp) != null) {
                dao.updateHomograma(this);
            } else {
                dao.creatHomograma(this);
            }
        } else {
            dao.creatHomograma(this);
        }
    }

    public void delete() {
        if (find(ID_homograma.get(), mainapp) != null) {
            dao.deleteHomograma(this);
        }
    }

    public static List<model_homograma> all(MainApp mainApp) {
        dao = new homogramaDAO(mainApp);
        return dao.getHomogramaList(mainApp);
    }

    public static model_homograma find(int pk, MainApp mainApp) {
        dao = new homogramaDAO(mainApp);
        return dao.getHomogramaID(pk, mainApp);
    }
}
