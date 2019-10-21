/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Dao;

import clinicafx.MainApp;
import clinicafx.Util.BD.Banco_de_Dados_Cliente;
import clinicafx.Model.model_pais;

import clinicafx.Util.MsgErro;
import static clinicafx.Util.MsgErro.MessagemErroBD;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class paisDAO {

    private MainApp mainapp = null;
    private Banco_de_Dados_Cliente db = null;
    private PreparedStatement stmt;
    protected Connection conexao = null;

    public paisDAO(MainApp mainapp) {
        if (mainapp != null) {
            try {
                this.mainapp = mainapp;
                this.db = mainapp.getBanco_de_dados();
                conexao = this.db.open();
            } catch (Exception ex) {
                db = new Banco_de_Dados_Cliente();
                conexao = this.db.open();
            }
        } else {
            db = new Banco_de_Dados_Cliente();
            conexao = this.db.open();
        }

    }

    public model_pais getPaisID(int ID, MainApp mainapp) {
        db.open();
        model_pais obj = new model_pais(mainapp);

        try {

            stmt = conexao.prepareStatement("SELECT * FROM `pais` WHERE ID_pais = '" + ID + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setID_Pais(rs.getInt("ID_pais"));
                obj.setPais(rs.getString("pais"));

            }

            return obj;
        } catch (SQLException ex) {
            db.close();
            MessagemErroBD(ex, "getPaisID");
            return null;
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getPaisID");
            return null;
        }
    }

    public List<model_pais> getPaisList(MainApp mainapp) {
        db.open();
        ArrayList<model_pais> result = new ArrayList<>();
        try {

            stmt = conexao.prepareStatement("SELECT * FROM `pais` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model_pais obj = new model_pais(mainapp);

                obj.setID_Pais(rs.getInt("ID_pais"));
                obj.setPais(rs.getString("pais"));
                result.add(obj);
            }
            return result;

        } catch (SQLException ex) {
            db.close();
            MessagemErroBD(ex, "getPaisList");
            return null;

        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getPaisList");
            return null;
        }
    }

    public void creatPais(model_pais _obj) {

        new Thread(() -> {
            db.open();
            model_pais obj = _obj;

            try {
                stmt = conexao.prepareStatement("INSERT INTO pais ( `pais`) VALUES(?);");

                
                stmt.setString(1, obj.getPais());

                stmt.executeUpdate();

            } catch (SQLException ex) {
                db.close();
                MessagemErroBD(ex, "creatPais");
            } catch (Exception ex) {
                db.close();
                MsgErro.MessagemErroBD(ex, "creatPais");

            }
        }).start();
    }

    public void updatePais(model_pais _obj) {

        db.open();
        new Thread(() -> {
            model_pais obj = _obj;
            try {
                stmt = conexao.prepareStatement("UPDATE pais SET "
                        + " pais = ?"
                        + " WHERE ID_pais = ?;");

                stmt.setString(1, obj.getPais());
                stmt.setInt(2, obj.getID_Pais());

                stmt.executeUpdate();

            } catch (SQLException ex) {
                db.close();
                MessagemErroBD(ex, "updatePais");
            } catch (Exception ex) {
                db.close();
                MsgErro.MessagemErroBD(ex, "updatePais");

            }
        }).start();
    }

    public void deletePais(model_pais obj) {

        db.open();
        new Thread(() -> {
            try {
                stmt = conexao.prepareStatement("DELETE FROM pais WHERE ID_pais = ?;");

                stmt.setInt(1, obj.getID_Pais());

                stmt.executeUpdate();

            } catch (SQLException ex) {
                db.close();
                MessagemErroBD(ex, "deletePais");
            } catch (Exception ex) {
                db.close();
                MsgErro.MessagemErroBD(ex, "deletePais");

            }
        }).start();
    }

}
