/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Cliente;

import clinicafx.MainApp;
import clinicafx.Model.Cliente.ClienteDAO.fichaDAO;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class model_ficha {

    private final IntegerProperty ID_Ficha = new SimpleIntegerProperty();
    private final IntegerProperty ID_cliente = new SimpleIntegerProperty();
    private final StringProperty data_ficha = new SimpleStringProperty();
    private final StringProperty observacao = new SimpleStringProperty();

    public String getObservacao() {
        return observacao.get();
    }

    public void setObservacao(String value) {
        observacao.set(value);
    }

    public StringProperty observacaoProperty() {
        return observacao;
    }

    public String getData_ficha() {
        return data_ficha.get();
    }

    public void setData_ficha(String value) {
        data_ficha.set(value);
    }

    public StringProperty data_fichaProperty() {
        return data_ficha;
    }

    public int getID_cliente() {
        return ID_cliente.get();
    }

    public void setID_cliente(int value) {
        ID_cliente.set(value);
    }

    public IntegerProperty ID_clienteProperty() {
        return ID_cliente;
    }

    public int getID_Ficha() {
        return ID_Ficha.get();
    }

    public void setID_Ficha(int value) {
        ID_Ficha.set(value);
    }

    public IntegerProperty ID_FichaProperty() {
        return ID_Ficha;
    }

    private final MainApp mainapp;

    public model_ficha(MainApp mainApp) {
        dao = new fichaDAO(mainApp);
        this.mainapp = mainApp;
    }
    private static fichaDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_Ficha.getValue() != null && ID_Ficha.get() != 0) {
            if (find(ID_Ficha.get(), mainapp) != null) {
                dao.updateFicha(this);
            } else {
                dao.creatFicha(this);
            }
        } else {
            dao.creatFicha(this);
        }
    }

    public void delete() {
        if (find(ID_Ficha.get(), mainapp) != null) {
            dao.deleteFicha(this);
        }
    }

    public static List<model_ficha> all(MainApp mainApp) {
        dao = new fichaDAO(mainApp);
        return dao.getFichaList(mainApp);
    }

    public static model_ficha find(int pk, MainApp mainApp) {
        dao = new fichaDAO(mainApp);
        return dao.getFichaID(pk, mainApp);
    }
}
