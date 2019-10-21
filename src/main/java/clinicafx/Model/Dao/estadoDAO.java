/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Dao;

import clinicafx.MainApp;
import clinicafx.Util.BD.Banco_de_Dados_Cliente;
import clinicafx.Model.model_estado;

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
public class estadoDAO {

    private MainApp mainapp = null;
    private Banco_de_Dados_Cliente db = null;
    private PreparedStatement stmt;
    protected Connection conexao = null;

    public estadoDAO(MainApp mainapp) {
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

    public model_estado getEstadoID(int ID, MainApp mainapp) {
        db.open();
        model_estado obj = new model_estado(mainapp);

        try {

            stmt = conexao.prepareStatement("SELECT * FROM `estado` WHERE ID_estado = '" + ID + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setID_Estado(rs.getInt("ID_estado"));
                obj.setID_Pais(rs.getInt("ID_pais"));
                obj.setEstado(rs.getString("estado"));
                obj.setAbreviacao(rs.getString("abreviacao"));

            }

            return obj;
        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getEstadoID");
            return null;
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getEstadoID");
            return null;
        }
    }

    public List<model_estado> getEstadoList(MainApp mainapp) {
        db.open();
        ArrayList<model_estado> result = new ArrayList<>();
        try {

            stmt = conexao.prepareStatement("SELECT * FROM `estado` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model_estado obj = new model_estado(mainapp);

                obj.setID_Estado(rs.getInt("ID_estado"));
                obj.setID_Pais(rs.getInt("ID_pais"));
                obj.setEstado(rs.getString("estado"));
                obj.setAbreviacao(rs.getString("abreviacao"));
                result.add(obj);
            }
            return result;

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getEstadoList");
            return null;

        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getEstadoList");
            return null;
        }
    }

    public void creatEstado(model_estado obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("INSERT INTO estado (`ID_pais`, `estado`, `abreviacao`) VALUES(?,?,?);");

         
            stmt.setInt(1, obj.getID_Pais());
            stmt.setString(2, obj.getEstado());
            stmt.setString(3, obj.getAbreviacao());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatEstado");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatEstado");

        }
    }

    public void updateEstado(model_estado obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("UPDATE estado SET"
                    + " ID_pais = ?,"
                    + " estado = ?,"
                    + " abreviacao = ?"
                    + " WHERE ID_estado = ?;");

            stmt.setInt(1, obj.getID_Pais());
            stmt.setString(2, obj.getEstado());
            stmt.setString(3, obj.getAbreviacao());
            stmt.setInt(4, obj.getID_Estado());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateEstado");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateEstado");

        }
    }

    public void deleteEstado(model_estado obj) {
        db.open();

        try {
            stmt = conexao.prepareStatement("DELETE FROM estado WHERE ID_estado = ? ;");

            stmt.setInt(1, obj.getID_Estado());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteEstado");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteEstado");

        }
    }

}
