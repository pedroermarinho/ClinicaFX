/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Dao;

import clinicafx.MainApp;
import clinicafx.Util.BD.Banco_de_Dados_Cliente;
import clinicafx.Model.model_sexo;

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
public class sexoDAO {

    private MainApp mainapp = null;
    private Banco_de_Dados_Cliente db = null;
    private PreparedStatement stmt;
    protected Connection conexao = null;

    public sexoDAO(MainApp mainapp) {
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

    public model_sexo getSexoID(int ID, MainApp mainapp) {
        db.open();
        model_sexo obj = new model_sexo(mainapp);

        try {

            stmt = conexao.prepareStatement("SELECT * FROM `sexos` WHERE ID_sexo = '" + ID + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setID_Sexo(rs.getInt("ID_sexo"));
                obj.setSexo(rs.getString("sexo"));

            }

            return obj;
        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getSexoID");
            return null;
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getSexoID");
            return null;
        }
    }

    public List<model_sexo> getSexoList(MainApp mainapp) {
        db.open();
        ArrayList<model_sexo> result = new ArrayList<>();

        try {

            stmt = conexao.prepareStatement("SELECT * FROM `sexos`");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model_sexo obj = new model_sexo(mainapp);
                obj.setID_Sexo(rs.getInt("ID_sexo"));
                obj.setSexo(rs.getString("sexo"));
                result.add(obj);

            }

            return result;
        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getSexoList");
            return null;
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "open");
            return null;
        }
    }

    public void creatSexo(model_sexo obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("INSERT INTO sexos (`sexo`) VALUES (?);");

            stmt.setString(1, obj.getSexo());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatSexo");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatSexo");

        }
    }

    public void updateSexo(model_sexo obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("UPDATE sexos SET"
                    + " sexo = ?"
                    + " WHERE ID_sexo = ?;");

            stmt.setString(1, obj.getSexo());
            stmt.setInt(2, obj.getID_Sexo());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateSexo");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateSexo");

        }
    }

    public void deleteSexo(model_sexo obj) {
        db.open();

        try {
            stmt = conexao.prepareStatement("DELETE FROM sexos WHERE ID_sexo = ?;");
            System.out.println(obj.getID_Sexo());
            stmt.setInt(1, obj.getID_Sexo());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteSexo");

        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteSexo");

        }
    }
}
