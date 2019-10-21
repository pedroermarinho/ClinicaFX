/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Cliente;

import clinicafx.MainApp;
import clinicafx.Model.Cliente.ClienteDAO.corpo_clienteDAO;

import java.util.List;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class model_corpo_cliente {

    private final IntegerProperty ID_Corpo_Cliente = new SimpleIntegerProperty();
    private final FloatProperty Peso = new SimpleFloatProperty();
    private final FloatProperty Altura = new SimpleFloatProperty();
    private final FloatProperty Quadril = new SimpleFloatProperty();
    private final StringProperty Data_Corpo_Cliente = new SimpleStringProperty("2019-02-11");
    private final IntegerProperty ID_cliente = new SimpleIntegerProperty();

    public int getID_cliente() {
        return ID_cliente.get();
    }

    public void setID_cliente(int value) {
        ID_cliente.set(value);
    }

    public IntegerProperty ID_clienteProperty() {
        return ID_cliente;
    }

    public String getData_Corpo_Cliente() {
        return Data_Corpo_Cliente.get();
    }

    public void setData_Corpo_Cliente(String value) {
        Data_Corpo_Cliente.set(value);
    }

    public StringProperty Data_Corpo_ClienteProperty() {
        return Data_Corpo_Cliente;
    }

    public float getQuadril() {
        return Quadril.get();
    }

    public void setQuadril(float value) {
        Quadril.set(value);
    }

    public FloatProperty QuadrilProperty() {
        return Quadril;
    }

    public float getAltura() {
        return Altura.get();
    }

    public void setAltura(float value) {
        Altura.set(value);
    }

    public FloatProperty AlturaProperty() {
        return Altura;
    }

    public float getPeso() {
        return Peso.get();
    }

    public void setPeso(float value) {
        Peso.set(value);
    }

    public FloatProperty PesoProperty() {
        return Peso;
    }

    public int getID_Corpo_Cliente() {
        return ID_Corpo_Cliente.get();
    }

    public void setID_Corpo_Cliente(int value) {
        ID_Corpo_Cliente.set(value);
    }

    public IntegerProperty ID_Corpo_ClienteProperty() {
        return ID_Corpo_Cliente;
    }
    private final MainApp mainapp;

    public model_corpo_cliente(MainApp mainApp) {
        dao = new corpo_clienteDAO(mainApp);
        this.mainapp = mainApp;
    }
    private static corpo_clienteDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_Corpo_Cliente.getValue() != null && ID_Corpo_Cliente.get() != 0) {
            if (find(ID_Corpo_Cliente.get(), mainapp) != null) {
                dao.updateCorpoCliente(this);

            } else {
                dao.creatCorpoCliente(this);
            }
        } else {
            dao.creatCorpoCliente(this);
        }
    }

    public void delete() {
        if (find(ID_Corpo_Cliente.get(), mainapp) != null) {
            dao.deleteCorpoCliente(this);
        }
    }

    public static List<model_corpo_cliente> all(MainApp mainApp) {
        dao = new corpo_clienteDAO(mainApp);
        return dao.getCorpoClienteList(mainApp);
    }

    public static model_corpo_cliente find(int pk, MainApp mainApp) {
        dao = new corpo_clienteDAO(mainApp);
        return dao.getCorpoClienteID(pk, mainApp);
    }
}
