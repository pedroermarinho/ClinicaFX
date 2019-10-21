/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Usuario;

import clinicafx.MainApp;
import clinicafx.Model.Usuario.UsuarioDAO.usuarioDAO;
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
public class model_usuario {

    private final IntegerProperty ID_usuario = new SimpleIntegerProperty();
    private final StringProperty Usuario = new SimpleStringProperty();
    private final StringProperty Email = new SimpleStringProperty();
    private final StringProperty Nome = new SimpleStringProperty();
    private final StringProperty Sobrenome = new SimpleStringProperty();
    private final StringProperty Senha = new SimpleStringProperty();
    private final IntegerProperty ID_sexo = new SimpleIntegerProperty();
    private final ObjectProperty<java.sql.Date> DataNascimento = new SimpleObjectProperty<>();

    public int getID_usuario() {
        return ID_usuario.get();
    }

    public void setID_usuario(int value) {
        ID_usuario.set(value);
    }

    public IntegerProperty ID_usuarioProperty() {
        return ID_usuario;
    }

    public String getEmail() {
        return Email.get();
    }

    public void setEmail(String value) {
        Email.set(value);
    }

    public StringProperty EmailProperty() {
        return Email;
    }

    public String getUsuario() {
        return Usuario.get();
    }

    public void setUsuario(String value) {
        Usuario.set(value);
    }

    public StringProperty UsuarioProperty() {
        return Usuario;
    }

    public java.sql.Date getDataNascimento() {
        return DataNascimento.get();
    }

    public void setDataNascimento(java.sql.Date value) {
        DataNascimento.set(value);
    }

    public ObjectProperty DataNascimentoProperty() {
        return DataNascimento;
    }

    public int getSexo() {
        return ID_sexo.get();
    }

    public void setSexo(int value) {
        ID_sexo.set(value);
    }

    public IntegerProperty SexoProperty() {
        return ID_sexo;
    }

    public String getSenha() {
        return Senha.get();
    }

    public void setSenha(String value) {
        Senha.set(value);
    }

    public StringProperty SenhaProperty() {
        return Senha;
    }

    public String getSobrenome() {
        return Sobrenome.get();
    }

    public void setSobrenome(String value) {
        Sobrenome.set(value);
    }

    public StringProperty SobrenomeProperty() {
        return Sobrenome;
    }

    public String getNome() {
        return Nome.get();
    }

    public void setNome(String value) {
        Nome.set(value);
    }

    public StringProperty NomeProperty() {
        return Nome;
    }

    private final MainApp mainapp;

    public model_usuario(MainApp mainApp) {
        dao = new usuarioDAO(mainApp);
        this.mainapp = mainApp;
    }

    private static usuarioDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_usuario.getValue() != null && ID_usuario.get() != 0) {
            if (find(ID_usuario.get(), mainapp) != null) {
                dao.updateUsuario(this);
            } else {
                dao.creatUsuario(this);
            }
        } else {
            dao.creatUsuario(this);
        }
    }

    public void delete() {
        if (find(ID_usuario.get(), mainapp) != null) {
            dao.deleteUsuario(this);
        }
    }

    public static List<model_usuario> all(MainApp mainApp) {
        dao = new usuarioDAO(mainApp);
        return dao.getUsuarioList(mainApp);
    }

    public static model_usuario find(int pk, MainApp mainApp) {
        dao = new usuarioDAO(mainApp);
        return dao.getUsuarioID(pk, mainApp);
    }

}
