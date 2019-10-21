/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Dao;

import clinicafx.MainApp;
import clinicafx.Util.BD.Banco_de_Dados_Cliente;
import clinicafx.Model.model_ruas;

import clinicafx.Util.MsgErro;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class ruasDAO {

    private MainApp mainapp = null;
    private Banco_de_Dados_Cliente db = null;
    private PreparedStatement stmt;
    protected Connection conexao = null;

    public ruasDAO(MainApp mainapp) {
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

    public model_ruas getRuasID(int ID, MainApp mainapp) {
        db.open();
        model_ruas obj = new model_ruas(mainapp);

        try {

            stmt = conexao.prepareStatement("SELECT * FROM `ruas` WHERE ID_rua = '" + ID + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setID_rua(rs.getInt("ID_rua"));
                obj.setID_Bairro(rs.getInt("ID_bairro"));
                obj.setRua(rs.getString("rua"));

            }

            return obj;
        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getRuasID");
            return null;
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getRuasID");
            return null;
        }

    }

    public List<model_ruas> getRuasList(MainApp mainapp) {
        db.open();

        ArrayList<model_ruas> result = new ArrayList<>();
        try {

            stmt = conexao.prepareStatement("SELECT * FROM `ruas` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model_ruas obj = new model_ruas(mainapp);

                obj.setID_rua(rs.getInt("ID_rua"));
                obj.setID_Bairro(rs.getInt("ID_bairro"));
                obj.setRua(rs.getString("rua"));
                result.add(obj);
            }
            return result;

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getRuasList");
            return null;

        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getRuasList");
            return null;
        }
    }

    public void creatRua(model_ruas obj) {
        new Thread(() -> {
            db.open();

            try {
                stmt = conexao.prepareStatement("INSERT INTO ruas ( `ID_bairro`, `rua`) VALUES(?,?);");

                stmt.setInt(1, obj.getID_Bairro());
                stmt.setString(2, obj.getRua());

                stmt.executeUpdate();

            } catch (SQLException ex) {
                MsgErro.MessagemErroBD(ex, "creatRua");
            } catch (Exception ex) {
                db.close();
                MsgErro.MessagemErroBD(ex, "creatRua");
            }
        }).start();
    }

    public void updateRua(model_ruas obj) {
        db.open();
        new Thread(() -> {

            try {
                stmt = conexao.prepareStatement("UPDATE ruas SET"
                        + " ID_bairro = ?,"
                        + " rua = ?"
                        + " WHERE ID_rua = ?;");

                stmt.setString(1, obj.getRua());
                stmt.setInt(2, obj.getID_rua());

                stmt.executeUpdate();

            } catch (SQLException ex) {
                db.close();
                MsgErro.MessagemErroBD(ex, "updateRua");
            } catch (Exception ex) {
                db.close();
                MsgErro.MessagemErroBD(ex, "updateRua");

            }
        }).start();
    }

    public void deleteRua(model_ruas obj) {
        db.open();
        new Thread(() -> {
            try {
                stmt = conexao.prepareStatement("DELETE FROM ruas WHERE ID_rua = ?;");

                stmt.setInt(1, obj.getID_rua());

                stmt.executeUpdate();

            } catch (SQLException ex) {
                db.close();
                MsgErro.MessagemErroBD(ex, "deleteRua");
            } catch (Exception ex) {
                db.close();
                MsgErro.MessagemErroBD(ex, "deleteRua");

            }
        }).start();
    }

}
