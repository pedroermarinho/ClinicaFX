/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Cliente.ClienteDAO;

import clinicafx.MainApp;
import clinicafx.Util.BD.Banco_de_Dados_Cliente;
import clinicafx.Model.Cliente.model_ficha;

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
public class fichaDAO {

    private MainApp mainapp = null;
    private Banco_de_Dados_Cliente db = null;
    private PreparedStatement stmt;
    protected Connection conexao = null;

    public fichaDAO(MainApp mainapp) {
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

    public model_ficha getFichaID(int ID, MainApp mainapp) {
        db.open();
        model_ficha obj = new model_ficha(mainapp);

        try {

            stmt = conexao.prepareStatement("SELECT * FROM `ficha` WHERE ID_ficha = '" + ID + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setID_Ficha(rs.getInt("ID_ficha"));
                obj.setID_cliente(rs.getInt("ID_cliente"));
                obj.setData_ficha(rs.getString("data_ficha"));
                obj.setObservacao(rs.getString("observacao"));

            }

            return obj;
        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getFichaID");
            return null;
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getFichaID");
            return null;
        }
    }

    public List<model_ficha> getFichaList(MainApp mainapp) {
        db.open();
        ArrayList<model_ficha> result = new ArrayList<>();
        try {

            stmt = conexao.prepareStatement("SELECT * FROM `ficha` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model_ficha obj = new model_ficha(mainapp);

                obj.setID_Ficha(rs.getInt("ID_ficha"));
                obj.setID_cliente(rs.getInt("ID_cliente"));
                obj.setData_ficha(rs.getString("data_ficha"));
                obj.setObservacao(rs.getString("observacao"));
                result.add(obj);
            }
            return result;

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getFichaList");
            return null;

        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getFichaList");
            return null;
        }
    }

    public void creatFicha(model_ficha obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("INSERT INTO ficha (`ID_cliente`, `data_ficha`, `observacao`) VALUES(?,?,?);");

            stmt.setInt(1, obj.getID_cliente());
            stmt.setString(2, obj.getData_ficha());
            stmt.setString(3, obj.getObservacao());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatFicha");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatFicha");

        }
    }

    public void updateFicha(model_ficha obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("UPDATE ficha SET"
                    + " ID_cliente = ?,"
                    + " data_ficha = ?,"
                    + " observacao = ?"
                    + " WHERE ID_ficha = ?;");

            stmt.setInt(1, obj.getID_cliente());
            stmt.setString(2, obj.getData_ficha());
            stmt.setString(3, obj.getObservacao());
            stmt.setInt(4, obj.getID_Ficha());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateFicha");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateFicha");

        }
    }

    public void deleteFicha(model_ficha obj) {
        db.open();

        try {
            stmt = conexao.prepareStatement("DELETE FROM ficha WHERE ID_ficha = ?;");

            stmt.setInt(1, obj.getID_Ficha());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteFicha");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteFicha");

        }
    }
}
