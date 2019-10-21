/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model;

import clinicafx.MainApp;
import clinicafx.Model.Dao.sexoDAO;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class model_sexo {

    private final IntegerProperty ID_Sexo = new SimpleIntegerProperty();
    private final StringProperty Sexo = new SimpleStringProperty();

    public String getSexo() {
        return Sexo.get();
    }

    public void setSexo(String value) {
        Sexo.set(value);
    }

    public StringProperty SexoProperty() {
        return Sexo;
    }

    public int getID_Sexo() {
        return ID_Sexo.get();
    }

    public void setID_Sexo(int value) {
        ID_Sexo.set(value);
    }

    public IntegerProperty ID_SexoProperty() {
        return ID_Sexo;
    }

    @Override
    public String toString() {
        return Sexo.get();
    }

    private final MainApp mainapp;

    public model_sexo(MainApp mainApp) {
        dao = new sexoDAO(mainApp);
        this.mainapp = mainApp;
    }
    private static sexoDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_Sexo.getValue() != null && ID_Sexo.getValue() != 0) {
            if (find(ID_Sexo.get(), mainapp) != null) {
                dao.updateSexo(this);
            } else {
                dao.creatSexo(this);
            }
        } else {
            dao.creatSexo(this);
        }
    }

    public void delete() {
        if (find(ID_Sexo.get(), mainapp) != null) {
            dao.deleteSexo(this);
        }
    }

    public static List<model_sexo> all(MainApp mainApp) {
        dao = new sexoDAO(mainApp);
        return dao.getSexoList(mainApp);
    }

    public static model_sexo find(int pk, MainApp mainApp) {
        dao = new sexoDAO(mainApp);
        return dao.getSexoID(pk, mainApp);
    }
}
