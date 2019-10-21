/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model;

import clinicafx.MainApp;
import clinicafx.Model.Dao.paisDAO;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class model_pais {

    private final IntegerProperty ID_Pais = new SimpleIntegerProperty();
    private final StringProperty Pais = new SimpleStringProperty();

    public String getPais() {
        return Pais.get();
    }

    public void setPais(String value) {
        Pais.set(value);
    }

    public StringProperty PaisProperty() {
        return Pais;
    }

    public int getID_Pais() {
        return ID_Pais.get();
    }

    public void setID_Pais(int value) {
        ID_Pais.set(value);
    }

    public IntegerProperty ID_PaisProperty() {
        return ID_Pais;
    }

    @Override
    public String toString() {
        return Pais.get();
    }
    private final MainApp mainapp;

    public model_pais(MainApp mainApp) {
        dao = new paisDAO(mainApp);
        this.mainapp = mainApp;
    }
    private static paisDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_Pais.getValue() != null && ID_Pais.getValue() != 0) {
            if (find(ID_Pais.get(), mainapp) != null) {

                dao.updatePais(this);
            } else {

                dao.creatPais(this);
            }
        } else {

            dao.creatPais(this);
        }
    }

    public void delete() {
        if (find(ID_Pais.get(), mainapp) != null) {
            dao.deletePais(this);
        }
    }

    public static List<model_pais> all(MainApp mainApp) {
        dao = new paisDAO(mainApp);
        return dao.getPaisList(mainApp);
    }

    public static model_pais find(int pk, MainApp mainApp) {
        dao = new paisDAO(mainApp);
        return dao.getPaisID(pk, mainApp);
    }

}
