/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Exames;

import clinicafx.MainApp;
import clinicafx.Model.Exames.ExamesDAO.fezesDAO;
import java.sql.Date;
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
public class model_fezes {

    private final IntegerProperty ID_fezes = new SimpleIntegerProperty();
    private final IntegerProperty ID_ficha = new SimpleIntegerProperty();
    private final ObjectProperty<Date> data_do_exame = new SimpleObjectProperty<>();
    private final StringProperty sangue_oculto = new SimpleStringProperty();
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

    public String getSangue_oculto() {
        return sangue_oculto.get();
    }

    public void setSangue_oculto(String value) {
        sangue_oculto.set(value);
    }

    public StringProperty sangue_ocultoProperty() {
        return sangue_oculto;
    }

    public Date getData_do_exame() {
        return data_do_exame.get();
    }

    public void setData_do_exame(Date value) {
        data_do_exame.set(value);
    }

    public ObjectProperty data_do_exameProperty() {
        return data_do_exame;
    }

    public int getID_ficha() {
        return ID_ficha.get();
    }

    public void setID_ficha(int value) {
        ID_ficha.set(value);
    }

    public IntegerProperty ID_fichaProperty() {
        return ID_ficha;
    }

    public int getID_fezes() {
        return ID_fezes.get();
    }

    public void setID_fezes(int value) {
        ID_fezes.set(value);
    }

    public IntegerProperty ID_fezesProperty() {
        return ID_fezes;
    }
    private final MainApp mainapp;

    public model_fezes(MainApp mainApp) {
        dao = new fezesDAO(mainApp);
        this.mainapp = mainApp;
    }

    private static fezesDAO dao;

    public void save() {
        //  System.out.println("Verificação para save: Registro ->" + id + " Resultado do DAO.find ->" + dao.find(id));

        if (ID_fezes.getValue() != null && ID_fezes.get() != 0) {
            if (find(ID_fezes.get(), mainapp) != null) {
                dao.updateFezes(this);
            } else {
                dao.creatFezes(this);
            }
        } else {
            dao.creatFezes(this);
        }
    }

    public void delete() {
        if (find(ID_fezes.get(), mainapp) != null) {
            dao.deleteFezes(this);
        }
    }

    public static List<model_fezes> all(MainApp mainApp) {
        dao = new fezesDAO(mainApp);
        return dao.getFezesList(mainApp);
    }

    public static model_fezes find(int pk, MainApp mainApp) {
        dao = new fezesDAO(mainApp);
        return dao.getFezesID(pk, mainApp);
    }
}
