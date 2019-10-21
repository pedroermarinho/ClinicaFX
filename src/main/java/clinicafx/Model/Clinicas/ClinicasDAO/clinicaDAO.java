/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Clinicas.ClinicasDAO;

import clinicafx.MainApp;
import clinicafx.Util.BD.Banco_de_Dados_Cliente;
import clinicafx.Model.Cliente.model_cliente;
import clinicafx.Model.Clinicas.model_clinica;
import clinicafx.Model.Usuario.model_agenda;

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
public class clinicaDAO {

    private MainApp mainapp = null;
    private Banco_de_Dados_Cliente db = null;
    private PreparedStatement stmt;
    protected Connection conexao = null;

    public clinicaDAO(MainApp mainapp) {
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

    public model_clinica getClinicaID(int ID, MainApp mainapp) {
        db.open();
        model_clinica obj = new model_clinica(mainapp);

        try {

            stmt = conexao.prepareStatement("SELECT * FROM `clinica` WHERE ID_clinica = '" + ID + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setID_clinica(rs.getInt("ID_clinica"));//1
                obj.setNome(rs.getString("nome"));//2

            }

            return obj;
        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getClinicaID");
            return null;
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getClinicaID");
            return null;
        }
    }

    public List<model_clinica> getClinicaList(MainApp mainapp) {
        db.open();
        ArrayList<model_clinica> result = new ArrayList<>();
        try {

            stmt = conexao.prepareStatement("SELECT * FROM `clinica` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model_clinica obj = new model_clinica(mainapp);

                obj.setID_clinica(rs.getInt("ID_clinica"));//1
                obj.setNome(rs.getString("nome"));//3
                result.add(obj);
            }
            return result;

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getClinicaList");
            return null;

        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getClinicaList");
            return null;
        }
    }

    public void creatClinica(model_clinica obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("INSERT INTO clinica ( `nome`) VALUES(?);");

          
            stmt.setString(1, obj.getNome());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatClinica");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatClinica");

        }
    }

    public void updateClinica(model_clinica obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("UPDATE clinica SET"
                    + " nome = ?"//2
                    + " WHERE ID_cliente = ?;");//10

            stmt.setString(1, obj.getNome());
            stmt.setInt(2, obj.getID_clinica());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateClinica");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateClinica");

        }
    }

    public void deleteClinica(model_clinica obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("DELETE FROM clinica WHERE ID_clinica = ?;");

            stmt.setInt(1, obj.getID_clinica());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteClinica");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteClinica");

        }
    }
}
