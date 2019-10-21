/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model;

import clinicafx.MainApp;
import clinicafx.Model.Dao.cidadeDAO;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class model_cidades {

    private final IntegerProperty ID_Cidade = new SimpleIntegerProperty();
    private final IntegerProperty ID_Estado = new SimpleIntegerProperty();
    private final StringProperty Cidade = new SimpleStringProperty();

    public String getCidade() {
        return Cidade.get();
    }

    public void setCidade(String value) {
        Cidade.set(value);
    }

    public StringProperty CidadeProperty() {
        return Cidade;
    }

    public int getID_Estado() {
        return ID_Estado.get();
    }

    public void setID_Estado(int value) {
        ID_Estado.set(value);
    }

    public IntegerProperty ID_EstadoProperty() {
        return ID_Estado;
    }

    public int getID_Cidade() {
        return ID_Cidade.get();
    }

    public void setID_Cidade(int value) {
        ID_Cidade.set(value);
    }

    public IntegerProperty ID_CidadeProperty() {
        return ID_Cidade;
    }

    @Override
    public String toString() {
        return Cidade.get();
    }
    private final MainApp mainapp;

    public model_cidades(MainApp mainApp) {
        dao = new cidadeDAO(mainApp);
        this.mainapp = mainApp;
    }

    private static cidadeDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_Cidade.getValue() != null && ID_Cidade.get() != 0) {
            if (find(ID_Cidade.get(), mainapp) != null) {
                dao.updateCidades(this);
            } else {
                dao.creatCidades(this);
            }
        } else {
            dao.creatCidades(this);
        }
    }

    public void delete() {
        if (find(ID_Cidade.get(), mainapp) != null) {
            dao.deleteCidades(this);
        }
    }

    public static List<model_cidades> all(MainApp mainApp) {
        dao = new cidadeDAO(mainApp);
        return dao.getCidadesList(mainApp);
    }

    public static model_cidades find(int pk, MainApp mainApp) {
        dao = new cidadeDAO(mainApp);
        return dao.getCidadesID(pk, mainApp);
    }
}
