package clinicafx.Model.Cliente;

import clinicafx.MainApp;
import clinicafx.Model.Cliente.ClienteDAO.clienteDAO;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class model_cliente {

    private final IntegerProperty ID_cliente = new SimpleIntegerProperty(0);
    private final StringProperty Nome = new SimpleStringProperty("teste");
    private final StringProperty CPF = new SimpleStringProperty("1212331");
    private final StringProperty Mae = new SimpleStringProperty("teste");
    private final StringProperty Pai = new SimpleStringProperty("teste");
    private final StringProperty Data_Nascimento = new SimpleStringProperty("2000-01-02");
    private final StringProperty Cartao_SUS = new SimpleStringProperty();
    private final IntegerProperty ID_Sexo = new SimpleIntegerProperty();
    private final StringProperty Foto = new SimpleStringProperty("/e");
    private final StringProperty Email = new SimpleStringProperty("teste");

    public int getID_cliente() {
        return ID_cliente.get();
    }

    public void setID_cliente(int value) {
        ID_cliente.set(value);
    }

    public IntegerProperty ID_clienteProperty() {
        return ID_cliente;
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

    public String getFoto() {
        return Foto.get();
    }

    public void setFoto(String value) {
        Foto.set(value);
    }

    public StringProperty FotoProperty() {
        return Foto;
    }

    public int getID_Sexo() {
        return ID_Sexo.get();
    }

    public void setID_Sexo(int value) {
        ID_Sexo.set(value);
    }

    public IntegerProperty ID_SexoProperty() {
        return ID_Sexo;
    }

    public String getCartao_SUS() {
        return Cartao_SUS.get();
    }

    public void setCartao_SUS(String value) {
        Cartao_SUS.set(value);
    }

    public StringProperty Cartao_SUSProperty() {
        return Cartao_SUS;
    }

    public String getData_Nascimento() {
        return Data_Nascimento.get();
    }

    public void setData_Nascimento(String value) {
        Data_Nascimento.set(value);
    }

    public StringProperty Data_NascimentoProperty() {
        return Data_Nascimento;
    }

    public String getPai() {
        return Pai.get();
    }

    public void setPai(String value) {
        Pai.set(value);
    }

    public StringProperty PaiProperty() {
        return Pai;
    }

    public String getMae() {
        return Mae.get();
    }

    public void setMae(String value) {
        Mae.set(value);
    }

    public StringProperty MaeProperty() {
        return Mae;
    }

    public String getCPF() {
        return CPF.get();
    }

    public void setCPF(String value) {
        CPF.set(value);
    }

    public StringProperty CPFProperty() {
        return CPF;
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

    @Override
    public String toString() {
        return ID_cliente.get() + ") " + Nome.get() + "->" + CPF.get();
    }
    private final MainApp mainapp;

    public model_cliente(MainApp mainApp) {
        dao = new clienteDAO(mainApp);
        this.mainapp = mainApp;
    }

    private static clienteDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_cliente.getValue() != null && ID_cliente.get() != 0) {
            if (find(ID_cliente.get(), mainapp) != null) {
                dao.updateCliente(this);
            } else {
                dao.creatCliente(this);
            }
        } else {
            dao.creatCliente(this);
        }
    }

    public void delete() {
        if (find(ID_cliente.get(), mainapp) != null) {
            dao.deleteCliente(this);
        }
    }

    public static List<model_cliente> all(MainApp mainApp) {
        dao = new clienteDAO(mainApp);
        return dao.getClienteList(mainApp);
    }

    public static model_cliente find(int pk, MainApp mainApp) {
        dao = new clienteDAO(mainApp);
        return dao.getClienteID(pk, mainApp);
    }

}
