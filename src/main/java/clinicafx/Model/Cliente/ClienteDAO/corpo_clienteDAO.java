/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Cliente.ClienteDAO;

import clinicafx.MainApp;
import clinicafx.Util.BD.Banco_de_Dados_Cliente;
import clinicafx.Model.Cliente.model_corpo_cliente;
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
public class corpo_clienteDAO {

    private MainApp mainapp = null;
    private Banco_de_Dados_Cliente db = null;
    private PreparedStatement stmt;
    protected Connection conexao = null;

    public corpo_clienteDAO(MainApp mainapp) {
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

    public model_corpo_cliente getCorpoClienteID(int ID, MainApp mainapp) {
        db.open();
        model_corpo_cliente obj = new model_corpo_cliente(mainapp);

        try {

            stmt = conexao.prepareStatement("SELECT * FROM `corpo_cliente` WHERE ID_corpo_cliente = '" + ID + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setID_Corpo_Cliente(rs.getInt("ID_corpo_cliente"));
                obj.setID_cliente(rs.getInt("ID_cliente"));
                obj.setPeso(rs.getFloat("peso"));
                obj.setAltura(rs.getFloat("altura"));
                obj.setQuadril(rs.getFloat("quadril"));
                obj.setData_Corpo_Cliente(rs.getString("data_corpo_cliente"));

            }

            return obj;
        } catch (SQLException ex) {
            db.close();
            MessagemErroBD(ex, "getCorpoClienteID");
            return null;
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getCorpoClienteID");
            return null;
        }
    }

    public List<model_corpo_cliente> getCorpoClienteList(MainApp mainapp) {
        db.open();
        ArrayList<model_corpo_cliente> result = new ArrayList<>();
        try {

            stmt = conexao.prepareStatement("SELECT * FROM `corpo_cliente`");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model_corpo_cliente obj = new model_corpo_cliente(mainapp);

                obj.setID_Corpo_Cliente(rs.getInt("ID_corpo_cliente"));
                obj.setID_cliente(rs.getInt("ID_cliente"));
                obj.setPeso(rs.getFloat("peso"));
                obj.setAltura(rs.getFloat("altura"));
                obj.setQuadril(rs.getFloat("quadril"));
                obj.setData_Corpo_Cliente(rs.getString("data_corpo_cliente"));
                result.add(obj);
            }
            return result;

        } catch (SQLException ex) {
            db.close();
            MessagemErroBD(ex, "getCorpoClienteList");
            return null;

        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getCorpoClienteList");
            return null;
        }
    }

    public void creatCorpoCliente(model_corpo_cliente obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("INSERT INTO corpo_cliente ( `ID_cliente`, `peso`, `altura`, `quadril`, `data_corpo_cliente`) VALUES(?,?,?,?,?);");

            stmt.setInt(1, obj.getID_cliente());
            stmt.setFloat(2, obj.getPeso());
            stmt.setFloat(3, obj.getAltura());
            stmt.setFloat(4, obj.getQuadril());
            stmt.setString(5, obj.getData_Corpo_Cliente());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MessagemErroBD(ex, "creatCorpoCliente");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatCorpoCliente");

        }
    }

    public void updateCorpoCliente(model_corpo_cliente obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("UPDATE corpo_cliente SET"
                    + " ID_cliente = ?,"
                    + " peso = ?,"
                    + " altura = ?,"
                    + " quadril = ?,"
                    + " data_corpo_cliente = ?"
                    + " WHERE ID_corpo_cliente = ?;");

            stmt.setInt(1, obj.getID_cliente());
            stmt.setFloat(2, obj.getPeso());
            stmt.setFloat(3, obj.getAltura());
            stmt.setFloat(4, obj.getQuadril());
            stmt.setString(5, obj.getData_Corpo_Cliente());
            stmt.setInt(6, obj.getID_Corpo_Cliente());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MessagemErroBD(ex, "updateCorpoCliente");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateCorpoCliente");

        }
    }

    public void deleteCorpoCliente(model_corpo_cliente obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("DELETE FROM corpo_cliente WHERE ID_corpo_cliente = ?;");

            stmt.setInt(1, obj.getID_Corpo_Cliente());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MessagemErroBD(ex, "deleteCorpoCliente");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteCorpoCliente");

        }
    }

}
