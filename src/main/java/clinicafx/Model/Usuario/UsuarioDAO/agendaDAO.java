/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Usuario.UsuarioDAO;

import clinicafx.MainApp;
import clinicafx.Util.BD.Banco_de_Dados_Cliente;
import clinicafx.Model.Cliente.model_cliente;
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
public class agendaDAO {

    private MainApp mainapp = null;
    private Banco_de_Dados_Cliente db = null;
    private PreparedStatement stmt;
    protected Connection conexao = null;

    public agendaDAO(MainApp mainapp) {
        if (mainapp != null) {
            try {
                this.mainapp = mainapp;
                this.db = this.mainapp.getBanco_de_dados();
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

    public model_agenda getAgendaID(int ID, MainApp mainapp) {
        db.open();
        model_agenda obj = new model_agenda(mainapp);

        try {

            stmt = conexao.prepareStatement("SELECT * FROM `agenda` WHERE ID_agenda = '" + ID + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setID_agenda(rs.getInt("ID_agenda"));
                obj.setID_usuario(rs.getInt("ID_usuario"));
                obj.setID_cliente(rs.getInt("ID_cliente"));
                obj.setID_clinica(rs.getInt("ID_clinica"));
                obj.setData(rs.getDate("data_agenda"));
                obj.setHorario(rs.getTime("horario"));
                obj.setObservacao(rs.getString("Observacao"));

            }

            return obj;
        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getAgendaID");
            return null;
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getAgendaID");
            return null;
        }
    }

    public List<model_agenda> getAgendaList(MainApp mainapp) {
        db.open();
        ArrayList<model_agenda> result = new ArrayList<>();
        try {

            stmt = conexao.prepareStatement("SELECT * FROM `agenda` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model_agenda obj = new model_agenda(mainapp);

                obj.setID_agenda(rs.getInt("ID_agenda"));
                obj.setID_usuario(rs.getInt("ID_usuario"));
                obj.setID_cliente(rs.getInt("ID_cliente"));
                obj.setID_clinica(rs.getInt("ID_clinica"));
                obj.setData(rs.getDate("data_agenda"));
                obj.setHorario(rs.getTime("horario"));
                obj.setObservacao(rs.getString("Observacao"));
                result.add(obj);
            }
            return result;

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getAgendaList");
            return null;

        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getAgendaList");
            return null;
        }
    }

    public void creatAgenda(model_agenda obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("INSERT INTO agenda ( `ID_usuario`, `ID_cliente`, `ID_clinica`, `data_agenda`, `horario`, `Observacao`) VALUES(?,?,?,?,?,?);");

            stmt.setInt(1, obj.getID_usuario());
            stmt.setInt(2, obj.getID_cliente());
            stmt.setInt(3, obj.getID_clinica());
            stmt.setDate(4, obj.getData());
            stmt.setTime(5, obj.getHorario());
            stmt.setString(6, obj.getObservacao());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatAgenda");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatAgenda");

        }
    }

    public void updateAgenda(model_agenda obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("UPDATE agenda SET"
                    + " ID_usuario = ?,"//1
                    + " ID_cliente = ?,"//2
                    + " ID_clinica = ?,"//3
                    + " data_agenda = ?,"//4
                    + " horario = ?,"//5
                    + " Observacao = ?"//6
                    + " WHERE ID_agenda = ?;");//7

            stmt.setInt(1, obj.getID_usuario());
            stmt.setInt(2, obj.getID_cliente());
            stmt.setInt(3, obj.getID_clinica());
            stmt.setDate(4, obj.getData());
            stmt.setTime(5, obj.getHorario());
            stmt.setString(6, obj.getObservacao());
            stmt.setInt(7, obj.getID_agenda());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateAgenda");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateAgenda");

        }
    }

    public void deleteAgenda(model_agenda obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("DELETE FROM agenda WHERE ID_agenda = ?;");

            stmt.setInt(1, obj.getID_agenda());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteAgenda");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteAgenda");

        }
    }
}
