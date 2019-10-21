/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Clinicas;

import clinicafx.MainApp;
import clinicafx.Model.Clinicas.ClinicasDAO.clinicaDAO;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class model_clinica {

    private final IntegerProperty ID_clinica = new SimpleIntegerProperty();
    private final StringProperty nome = new SimpleStringProperty();

    public String getNome() {
        return nome.get();
    }

    public void setNome(String value) {
        nome.set(value);
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public int getID_clinica() {
        return ID_clinica.get();
    }

    public void setID_clinica(int value) {
        ID_clinica.set(value);
    }

    public IntegerProperty ID_clinicaProperty() {
        return ID_clinica;
    }

    private final MainApp mainapp;

    public model_clinica(MainApp mainApp) {
        dao = new clinicaDAO(mainApp);
        this.mainapp = mainApp;
    }

    private static clinicaDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_clinica.getValue() != null && ID_clinica.get() != 0) {
            if (find(ID_clinica.get(), mainapp) != null) {
                dao.updateClinica(this);
            } else {
                dao.creatClinica(this);
            }
        } else {
            dao.creatClinica(this);
        }
    }

    public void delete() {
        if (find(ID_clinica.get(), mainapp) != null) {
            dao.deleteClinica(this);
        }
    }

    public static List<model_clinica> all(MainApp mainApp) {
        dao = new clinicaDAO(mainApp);
        return dao.getClinicaList(mainApp);
    }

    public static model_clinica find(int pk, MainApp mainApp) {
        dao = new clinicaDAO(mainApp);
        return dao.getClinicaID(pk, mainApp);
    }

}
