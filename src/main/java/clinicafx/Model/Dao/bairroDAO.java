/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Dao;

import clinicafx.MainApp;
import clinicafx.Util.BD.Banco_de_Dados_Cliente;
import clinicafx.Model.model_bairros;

import clinicafx.Util.MsgErro;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public class bairroDAO {

    private MainApp mainapp = null;
    private Banco_de_Dados_Cliente db = null;
    private PreparedStatement stmt;
    protected Connection conexao = null;

    public bairroDAO(MainApp mainapp) {

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

    public model_bairros getBairroID(int ID, MainApp mainapp) {
        db.open();
        model_bairros obj = new model_bairros(mainapp);

        try {

            stmt = conexao.prepareStatement("SELECT * FROM `bairros` WHERE ID_bairro = '" + ID + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setID_Bairro(rs.getInt("ID_bairro"));
                obj.setID_Cidade(rs.getInt("ID_cidade"));
                obj.setBairro(rs.getString("bairro"));

            }

            return obj;
        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getBairroID");
            return null;
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getBairroID");
            return null;
        }
    }

    public List<model_bairros> getBairroList(MainApp mainapp) {
        db.open();
        ArrayList<model_bairros> result = new ArrayList<>();
        try {

            stmt = conexao.prepareStatement("SELECT * FROM `bairros` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model_bairros obj = new model_bairros(mainapp);
                obj.setID_Bairro(rs.getInt("ID_bairro"));
                obj.setID_Cidade(rs.getInt("ID_cidade"));
                obj.setBairro(rs.getString("bairro"));
                result.add(obj);
            }
            return result;

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getBairroList");
            return null;

        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "open");
            return null;
        }
    }

    public void creatBairro(model_bairros obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("INSERT INTO bairros ( `ID_cidade`, `bairro`) VALUES(?,?);");

            stmt.setInt(1, obj.getID_Cidade());
            stmt.setString(2, obj.getBairro());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatBairro");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "open");

        }
    }

    public void updateBairro(model_bairros obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("UPDATE bairros SET"
                    + " ID_cidade = ?,"
                    + " bairro = ?"
                    + " WHERE ID_bairro = ?;");

            stmt.setInt(1, obj.getID_Cidade());
            stmt.setString(2, obj.getBairro());
            stmt.setInt(3, obj.getID_Bairro());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateBairro");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "open");

        }
    }

    public void deleteBairro(model_bairros obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("DELETE FROM bairros WHERE ID_bairro = ?;");

            stmt.setInt(1, obj.getID_Bairro());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteBairro");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "open");

        }
    }

}
