/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model;

import clinicafx.MainApp;
import clinicafx.Model.Dao.bairroDAO;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class model_bairros {

    private final IntegerProperty ID_Bairro = new SimpleIntegerProperty();
    private final IntegerProperty ID_Cidade = new SimpleIntegerProperty();
    private final StringProperty Bairro = new SimpleStringProperty();

    public int getID_Cidade() {
        return ID_Cidade.get();
    }

    public void setID_Cidade(int value) {
        ID_Cidade.set(value);
    }

    public IntegerProperty ID_CidadeProperty() {
        return ID_Cidade;
    }

    public String getBairro() {
        return Bairro.get();
    }

    public void setBairro(String value) {
        Bairro.set(value);
    }

    public StringProperty BairroProperty() {
        return Bairro;
    }

    public int getID_Bairro() {
        return ID_Bairro.get();
    }

    public void setID_Bairro(int value) {
        ID_Bairro.set(value);
    }

    public IntegerProperty ID_BairroProperty() {
        return ID_Bairro;
    }

    @Override
    public String toString() {
        return Bairro.get();
    }
    private static bairroDAO dao;
    private final MainApp mainapp;

    public model_bairros(MainApp mainApp) {
        dao = new bairroDAO(mainApp);
        this.mainapp = mainApp;
    }

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_Bairro.getValue() != null && ID_Bairro.get() != 0) {
            if (find(ID_Bairro.get(), mainapp) != null) {
                dao.updateBairro(this);
            } else {
                dao.creatBairro(this);
            }
        } else {
            dao.creatBairro(this);
        }
    }

    public void delete() {
        if (find(ID_Bairro.get(), mainapp) != null) {
            dao.deleteBairro(this);
        }
    }

    public static List<model_bairros> all(MainApp mainapp) {
        dao = new bairroDAO(mainapp);
        return dao.getBairroList(mainapp);
    }

    public static model_bairros find(int pk, MainApp mainapp) {
        dao = new bairroDAO(mainapp);
        return dao.getBairroID(pk, mainapp);
    }

}
