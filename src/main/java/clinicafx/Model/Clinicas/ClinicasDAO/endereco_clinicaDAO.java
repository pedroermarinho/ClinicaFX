/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Clinicas.ClinicasDAO;

import clinicafx.Model.Cliente.ClienteDAO.*;
import clinicafx.MainApp;
import clinicafx.Util.BD.Banco_de_Dados_Cliente;
import clinicafx.Model.Cliente.model_endereco_cliente;
import clinicafx.Model.Clinicas.model_endereco_clinica;

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
public class endereco_clinicaDAO {

    private MainApp mainapp = null;
    private Banco_de_Dados_Cliente db = null;
    private PreparedStatement stmt;
    protected Connection conexao = null;

    public endereco_clinicaDAO(MainApp mainapp) {
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

    public model_endereco_clinica getEnderecoClinicaID(int ID, MainApp mainapp) {
        db.open();
        model_endereco_clinica obj = new model_endereco_clinica(mainapp);

        try {

            stmt = conexao.prepareStatement("SELECT * FROM `endereco_clinica` WHERE ID_endereco_clinica = '" + ID + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setID_Endereco_Cliente(rs.getInt("ID_endereco_clinica"));
                obj.setID_clinica(rs.getInt("ID_clinica"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setTelefone_Fixo(rs.getString("telefone_fixo"));
                obj.setID_cidade(rs.getInt("ID_cidade"));
                obj.setID_Rua(rs.getInt("ID_rua"));
                obj.setID_Bairro(rs.getInt("ID_bairro"));
                obj.setNumero_Casa(rs.getInt("numero_casa"));
                obj.setComplemento(rs.getString("complemento"));
            }

            return obj;
        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getEnderecoClinicaID");
            return null;
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getEnderecoClinicaID");
            return null;
        }
    }

    public List<model_endereco_clinica> getEnderecoClinicaList(MainApp mainapp) {
        db.open();
        ArrayList<model_endereco_clinica> result = new ArrayList<>();
        try {

            stmt = conexao.prepareStatement("SELECT * FROM `endereco_clinica` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model_endereco_clinica obj = new model_endereco_clinica(mainapp);

                obj.setID_Endereco_Cliente(rs.getInt("ID_endereco_clinica"));
                obj.setID_clinica(rs.getInt("ID_clinica"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setTelefone_Fixo(rs.getString("telefone_fixo"));
                obj.setID_cidade(rs.getInt("ID_cidade"));
                obj.setID_Rua(rs.getInt("ID_rua"));
                obj.setID_Bairro(rs.getInt("ID_bairro"));
                obj.setNumero_Casa(rs.getInt("numero_casa"));
                obj.setComplemento(rs.getString("complemento"));
                result.add(obj);
            }
            return result;

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getEnderecoClinicaList");
            return null;

        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getEnderecoClinicaList");
            return null;
        }
    }

    public void creatEnderecoClinica(model_endereco_clinica obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("INSERT INTO endereco_clinica ( `ID_clinica`, `telefone`, `telefone_fixo`, `ID_cidade`, `ID_rua`, `ID_bairro`, `numero_casa`, `complemento`) VALUES(?,?,?,?,?,?,?,?);");

          
            stmt.setInt(1, obj.getID_clinica());
            stmt.setString(2, obj.getTelefone());
            stmt.setString(3, obj.getTelefone_Fixo());
            stmt.setInt(4, obj.getID_cidade());
            stmt.setInt(5, obj.getID_Rua());
            stmt.setInt(6, obj.getID_Bairro());
            stmt.setInt(7, obj.getNumero_Casa());
            stmt.setString(8, obj.getComplemento());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatEnderecoClinica");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatEnderecoClinica");

        }
    }

    public void updateEnderecoClinica(model_endereco_clinica obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("UPDATE endereco_clinica SET"
                    + " ID_clinica = ?,"
                    + " telefone = ?,"
                    + " telefone_fixo = ?,"
                    + " ID_cidade = ?,"
                    + " ID_rua = ?,"
                    + " ID_bairro = ?,"
                    + " numero_casa = ?,"
                    + " complemento = ?"
                    + " WHERE ID_endereco_clinica = ?;");

            stmt.setInt(1, obj.getID_clinica());
            stmt.setString(2, obj.getTelefone());
            stmt.setString(3, obj.getTelefone_Fixo());
            stmt.setInt(4, obj.getID_cidade());
            stmt.setInt(5, obj.getID_Rua());
            stmt.setInt(6, obj.getID_Bairro());
            stmt.setInt(7, obj.getNumero_Casa());
            stmt.setString(8, obj.getComplemento());
            stmt.setInt(9, obj.getID_Endereco_Cliente());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateEnderecoClinica");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateEnderecoClinica");

        }
    }

    public void deleteEnderecoClinica(model_endereco_clinica obj) {
        db.open();

        try {
            stmt = conexao.prepareStatement("DELETE FROM endereco_clinica WHERE ID_endereco_clinica = ?;");

            stmt.setInt(1, obj.getID_Endereco_Cliente());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteEnderecoClinica");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteEnderecoClinica");

        }
    }

}
