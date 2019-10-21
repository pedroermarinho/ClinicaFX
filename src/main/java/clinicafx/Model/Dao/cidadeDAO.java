/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Dao;

import clinicafx.MainApp;
import clinicafx.Util.BD.Banco_de_Dados_Cliente;
import clinicafx.Model.model_cidades;

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
public class cidadeDAO {

    private MainApp mainapp = null;
    private Banco_de_Dados_Cliente db = null;
    private PreparedStatement stmt;
    protected Connection conexao = null;

    public cidadeDAO(MainApp mainapp) {
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

    public model_cidades getCidadesID(int ID, MainApp mainapp) {
        db.open();
        model_cidades obj = new model_cidades(mainapp);

        try {

            stmt = conexao.prepareStatement("SELECT * FROM `cidades` WHERE ID_cidade = '" + ID + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setID_Cidade(rs.getInt("ID_cidade"));
                obj.setID_Estado(rs.getInt("ID_estado"));
                obj.setCidade(rs.getString("cidade"));

            }

            return obj;
        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getCidadesID");
            return null;
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getCidadesID");
            return null;
        }
    }

    public List<model_cidades> getCidadesList(MainApp mainapp) {
        db.open();
        ArrayList<model_cidades> result = new ArrayList<>();
        try {

            stmt = conexao.prepareStatement("SELECT * FROM `cidades` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model_cidades obj = new model_cidades(mainapp);

                obj.setID_Cidade(rs.getInt("ID_cidade"));
                obj.setID_Estado(rs.getInt("ID_estado"));
                obj.setCidade(rs.getString("cidade"));
                result.add(obj);
            }
            return result;

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getCidadesList");
            return null;

        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getCidadesList");
            return null;
        }
    }

    public void creatCidades(model_cidades obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("INSERT INTO cidades ( `ID_estado`, `cidade`) VALUES(?,?);");

            
            stmt.setInt(1, obj.getID_Estado());
            stmt.setString(2, obj.getCidade());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatCidades");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatCidades");

        }
    }

    public void updateCidades(model_cidades obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("UPDATE cidades SET "
                    + " ID_estado = ?,"
                    + " cidade = ? "
                    + " WHERE ID_cidade = ?;");

            stmt.setInt(1, obj.getID_Estado());
            stmt.setString(2, obj.getCidade());
            stmt.setInt(3, obj.getID_Cidade());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateCidades");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateCidades");

        }
    }

    public void deleteCidades(model_cidades obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("DELETE FROM cidades WHERE ID_cidade = ?;");

            stmt.setInt(1, obj.getID_Cidade());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteCidades");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteCidades");

        }
    }

}
