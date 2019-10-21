/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Configuracao_Local;

import clinicafx.MainApp;
import clinicafx.Model.Configuracao_Local.Dao_Configuracao.banco_de_dadosDAO;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class model_banco_de_dados {

    private final IntegerProperty ID_banco_de_dados = new SimpleIntegerProperty();
    private final StringProperty Hosts = new SimpleStringProperty("localhost");
    private final StringProperty Users = new SimpleStringProperty("root");
    private final StringProperty Passwords = new SimpleStringProperty("");
    private final StringProperty DataBases = new SimpleStringProperty("clinica");
    private final StringProperty Prefixs = new SimpleStringProperty("jdbc:mysql:");
    private final IntegerProperty Ports = new SimpleIntegerProperty(3306);

    public int getPorts() {
        return Ports.get();
    }

    public void setPorts(int value) {
        Ports.set(value);
    }

    public IntegerProperty PortsProperty() {
        return Ports;
    }

    public String getPrefix() {
        return Prefixs.get();
    }

    public void setPrefix(String value) {
        Prefixs.set(value);
    }

    public StringProperty PrefixProperty() {
        return Prefixs;
    }

    public String getDataBase() {
        return DataBases.get();
    }

    public void setDataBase(String value) {
        DataBases.set(value);
    }

    public StringProperty DataBaseProperty() {
        return DataBases;
    }

    public String getPassword() {
        return Passwords.get();
    }

    public void setPassword(String value) {
        Passwords.set(value);
    }

    public StringProperty PasswordProperty() {
        return Passwords;
    }

    public String getUser() {
        return Users.get();
    }

    public void setUser(String value) {
        Users.set(value);
    }

    public StringProperty UserProperty() {
        return Users;
    }

    public String getHost() {
        return Hosts.get();
    }

    public void setHost(String value) {
        Hosts.set(value);
    }

    public StringProperty HostProperty() {
        return Hosts;
    }

    public int getID_banco_de_dados() {
        return ID_banco_de_dados.get();
    }

    public void setID_banco_de_dados(int value) {
        ID_banco_de_dados.set(value);
    }

    public IntegerProperty ID_banco_de_dadosProperty() {
        return ID_banco_de_dados;
    }

    @Override
    public String toString() {
        return Hosts.get() + " -> " + DataBases.get();
    }

    public model_banco_de_dados() {
        dao = new banco_de_dadosDAO();
    }

    private static banco_de_dadosDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_banco_de_dados.getValue() != null && ID_banco_de_dados.get() != 0) {
            if (find(ID_banco_de_dados.get()) != null) {
                dao.updateDados_db(this);
            } else {
                dao.creatDados_db(this);
            }
        } else {
            dao.creatDados_db(this);
        }
    }

    public void delete() {
        if (find(ID_banco_de_dados.get()) != null) {
            dao.deleteDados_db(this);
        }
    }

    public static List<model_banco_de_dados> all() {

        return dao.getDados_dbList();
    }

    public static model_banco_de_dados find(int pk) {

        return dao.getDados_dbID(pk);
    }
}
