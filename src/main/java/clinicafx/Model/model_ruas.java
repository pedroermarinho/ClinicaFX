/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model;

import clinicafx.MainApp;
import clinicafx.Model.Dao.ruasDAO;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class model_ruas {

    private final IntegerProperty ID_rua = new SimpleIntegerProperty();
    private final StringProperty Rua = new SimpleStringProperty();
    private final IntegerProperty ID_Bairro = new SimpleIntegerProperty();

    public int getID_Bairro() {
        return ID_Bairro.get();
    }

    public void setID_Bairro(int value) {
        ID_Bairro.set(value);
    }

    public IntegerProperty ID_BairroProperty() {
        return ID_Bairro;
    }

    public String getRua() {
        return Rua.get();
    }

    public void setRua(String value) {
        Rua.set(value);
    }

    public StringProperty RuaProperty() {
        return Rua;
    }

    public int getID_rua() {
        return ID_rua.get();
    }

    public void setID_rua(int value) {
        ID_rua.set(value);
    }

    public IntegerProperty ID_ruaProperty() {
        return ID_rua;
    }

    @Override
    public String toString() {
        return Rua.get();
    }

    private final MainApp mainapp;

    public model_ruas(MainApp mainApp) {
        dao = new ruasDAO(mainApp);
        this.mainapp = mainApp;
    }
    private static ruasDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_rua.getValue() != null && ID_rua.get() != 0) {
            if (find(ID_rua.get(), mainapp) != null) {
                dao.updateRua(this);
            } else {
                dao.creatRua(this);
            }
        } else {
            dao.creatRua(this);
        }
    }

    public void delete() {
        if (find(ID_rua.get(), mainapp) != null && ID_rua.get() != 0) {
            dao.deleteRua(this);
        }
    }

    public static List<model_ruas> all(MainApp mainApp) {
        dao = new ruasDAO(mainApp);
        return dao.getRuasList(mainApp);
    }

    public static model_ruas find(int pk, MainApp mainApp) {
        dao = new ruasDAO(mainApp);
        return dao.getRuasID(pk, mainApp);
    }
}
