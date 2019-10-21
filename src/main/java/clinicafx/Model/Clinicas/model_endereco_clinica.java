/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Clinicas;

import clinicafx.Model.Cliente.*;
import clinicafx.MainApp;
import clinicafx.Model.Cliente.ClienteDAO.endereco_clienteDAO;
import clinicafx.Model.Clinicas.ClinicasDAO.endereco_clinicaDAO;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class model_endereco_clinica {

    private final IntegerProperty ID_endereco_clinica = new SimpleIntegerProperty();

    private final IntegerProperty ID_cidade = new SimpleIntegerProperty();
    private final IntegerProperty ID_Rua = new SimpleIntegerProperty();
    private final IntegerProperty ID_Bairro = new SimpleIntegerProperty();
    private final IntegerProperty Numero_Casa = new SimpleIntegerProperty();
    private final StringProperty Telefone = new SimpleStringProperty();
    private final StringProperty Telefone_Fixo = new SimpleStringProperty();
    private final StringProperty Complemento = new SimpleStringProperty();
    private final IntegerProperty ID_clinica = new SimpleIntegerProperty();

    public int getID_clinica() {
        return ID_clinica.get();
    }

    public void setID_clinica(int value) {
        ID_clinica.set(value);
    }

    public IntegerProperty ID_clinicaProperty() {
        return ID_clinica;
    }

    public String getComplemento() {
        return Complemento.get();
    }

    public void setComplemento(String value) {
        Complemento.set(value);
    }

    public StringProperty ComplementoProperty() {
        return Complemento;
    }

    public String getTelefone_Fixo() {
        return Telefone_Fixo.get();
    }

    public void setTelefone_Fixo(String value) {
        Telefone_Fixo.set(value);
    }

    public StringProperty Telefone_FixoProperty() {
        return Telefone_Fixo;
    }

    public String getTelefone() {
        return Telefone.get();
    }

    public void setTelefone(String value) {
        Telefone.set(value);
    }

    public StringProperty TelefoneProperty() {
        return Telefone;
    }

    public int getNumero_Casa() {
        return Numero_Casa.get();
    }

    public void setNumero_Casa(int value) {
        Numero_Casa.set(value);
    }

    public IntegerProperty Numero_CasaProperty() {
        return Numero_Casa;
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

    public int getID_Rua() {
        return ID_Rua.get();
    }

    public void setID_Rua(int value) {
        ID_Rua.set(value);
    }

    public IntegerProperty ID_RuaProperty() {
        return ID_Rua;
    }

    public int getID_cidade() {
        return ID_cidade.get();
    }

    public void setID_cidade(int value) {
        ID_cidade.set(value);
    }

    public IntegerProperty ID_cidadeProperty() {
        return ID_cidade;
    }

    public int getID_Endereco_Cliente() {
        return ID_endereco_clinica.get();
    }

    public void setID_Endereco_Cliente(int value) {
        ID_endereco_clinica.set(value);
    }

    public IntegerProperty ID_Endereco_ClienteProperty() {
        return ID_endereco_clinica;
    }
    private final MainApp mainapp;

    public model_endereco_clinica(MainApp mainApp) {
        dao = new endereco_clinicaDAO(mainApp);
        this.mainapp = mainApp;
    }

    private static endereco_clinicaDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_endereco_clinica != null && ID_endereco_clinica.get() != 0) {
            if (find(ID_endereco_clinica.get(), mainapp) != null) {
                dao.updateEnderecoClinica(this);
            } else {
                dao.creatEnderecoClinica(this);
            }
        } else {
            dao.creatEnderecoClinica(this);
        }
    }

    public void delete() {
        if (find(ID_endereco_clinica.get(), mainapp) != null) {
            dao.deleteEnderecoClinica(this);
        }
    }

    public static List<model_endereco_clinica> all(MainApp mainApp) {
        dao = new endereco_clinicaDAO(mainApp);
        return dao.getEnderecoClinicaList(mainApp);
    }

    public static model_endereco_clinica find(int pk, MainApp mainApp) {
        dao = new endereco_clinicaDAO(mainApp);
        return dao.getEnderecoClinicaID(pk, mainApp);
    }

}
