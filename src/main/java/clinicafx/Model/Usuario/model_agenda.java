/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Usuario;

import clinicafx.MainApp;
import clinicafx.Model.Usuario.UsuarioDAO.agendaDAO;
import clinicafx.Model.Usuario.UsuarioDAO.usuarioDAO;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class model_agenda {

    private final IntegerProperty ID_agenda = new SimpleIntegerProperty();
    private final IntegerProperty ID_usuario = new SimpleIntegerProperty();
    private final IntegerProperty ID_cliente = new SimpleIntegerProperty();
    private final IntegerProperty ID_clinica = new SimpleIntegerProperty();
    private final ObjectProperty<java.sql.Time> horario = new SimpleObjectProperty<>();
    private final StringProperty Observacao = new SimpleStringProperty();
    private final ObjectProperty<java.sql.Date> Data = new SimpleObjectProperty<>();

    public java.sql.Time getHorario() {
        return horario.get();
    }

    public void setHorario(java.sql.Time value) {
        horario.set(value);
    }

    public ObjectProperty horarioProperty() {
        return horario;
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

    public String getObservacao() {
        return Observacao.get();
    }

    public void setObservacao(String value) {
        Observacao.set(value);
    }

    public StringProperty ObservacaoProperty() {
        return Observacao;
    }

    public java.sql.Date getData() {
        return Data.get();
    }

    public void setData(java.sql.Date value) {
        Data.set(value);
    }

    public ObjectProperty DataProperty() {
        return Data;
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

    public int getID_usuario() {
        return ID_usuario.get();
    }

    public void setID_usuario(int value) {
        ID_usuario.set(value);
    }

    public IntegerProperty ID_usuarioProperty() {
        return ID_usuario;
    }

    public int getID_agenda() {
        return ID_agenda.get();
    }

    public void setID_agenda(int value) {
        ID_agenda.set(value);
    }

    public IntegerProperty ID_agendaProperty() {
        return ID_agenda;
    }

    private final MainApp mainapp;

    public model_agenda(MainApp mainApp) {
        dao = new agendaDAO(mainApp);
        this.mainapp = mainApp;
    }

    private static agendaDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_agenda.getValue() != null && ID_agenda.get() != 0) {
            if (find(ID_agenda.get(), mainapp) != null) {
                dao.updateAgenda(this);
            } else {
                dao.creatAgenda(this);
            }
        } else {
            dao.creatAgenda(this);
        }
    }

    public void delete() {
        if (find(ID_agenda.get(), mainapp) != null) {
            dao.deleteAgenda(this);
        }
    }

    public static List<model_agenda> all(MainApp mainApp) {
        dao = new agendaDAO(mainApp);
        return dao.getAgendaList(mainApp);
    }

    public static model_agenda find(int pk, MainApp mainApp) {
        dao = new agendaDAO(mainApp);
        return dao.getAgendaID(pk, mainApp);
    }

}
