/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model;

import clinicafx.MainApp;
import clinicafx.Model.Dao.estadoDAO;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class model_estado {

    private final IntegerProperty ID_Estado = new SimpleIntegerProperty();
    private final IntegerProperty ID_Pais = new SimpleIntegerProperty();
    private final StringProperty Estado = new SimpleStringProperty();
    private final StringProperty abreviacao = new SimpleStringProperty();

    public String getAbreviacao() {
        return abreviacao.get();
    }

    public void setAbreviacao(String value) {
        abreviacao.set(value);
    }

    public StringProperty abreviacaoProperty() {
        return abreviacao;
    }

    public String getEstado() {
        return Estado.get();
    }

    public void setEstado(String value) {
        Estado.set(value);
    }

    public StringProperty EstadoProperty() {
        return Estado;
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

    public int getID_Estado() {
        return ID_Estado.get();
    }

    public void setID_Estado(int value) {
        ID_Estado.set(value);
    }

    public IntegerProperty ID_EstadoProperty() {
        return ID_Estado;
    }

    @Override
    public String toString() {
        return Estado.get();
    }
    private final MainApp mainapp;

    public model_estado(MainApp mainApp) {
        dao = new estadoDAO(mainApp);
        this.mainapp = mainApp;
    }
    private static estadoDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_Estado.getValue() != null && ID_Estado.get() != 0) {
            if (find(ID_Estado.get(), mainapp) != null) {
                dao.updateEstado(this);
            } else {
                dao.creatEstado(this);
            }
        } else {
            dao.creatEstado(this);
        }
    }

    public void delete() {
        if (find(ID_Estado.get(), mainapp) != null) {
            dao.deleteEstado(this);
        }
    }

    public static List<model_estado> all(MainApp mainApp) {
        dao = new estadoDAO(mainApp);
        return dao.getEstadoList(mainApp);
    }

    public static model_estado find(int pk, MainApp mainApp) {
        dao = new estadoDAO(mainApp);
        return dao.getEstadoID(pk, mainApp);
    }
}
