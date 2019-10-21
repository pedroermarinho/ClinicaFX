/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Cliente.ClienteDAO;

import clinicafx.MainApp;
import clinicafx.Util.BD.Banco_de_Dados_Cliente;
import clinicafx.Model.Cliente.model_cliente;

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
public class clienteDAO {

    private MainApp mainapp = null;
    private Banco_de_Dados_Cliente db = null;
    private PreparedStatement stmt;
    protected Connection conexao = null;

    public clienteDAO(MainApp mainapp) {
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

    public model_cliente getClienteID(int ID, MainApp mainapp) {
        db.open();
        model_cliente obj = new model_cliente(mainapp);

        try {

            stmt = conexao.prepareStatement("SELECT * FROM `clientes` WHERE ID_cliente = '" + ID + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                obj.setID_cliente(rs.getInt("ID_cliente"));//1
                obj.setCPF(rs.getString("cpf"));//2
                obj.setNome(rs.getString("nome"));//3
                obj.setMae(rs.getString("mae"));//4
                obj.setPai(rs.getString("pai"));//5
                obj.setData_Nascimento(rs.getString("data_nascimento"));//6
                obj.setCartao_SUS(rs.getString("cartao_sus"));//7
                obj.setID_Sexo(rs.getInt("ID_sexo"));//8
                obj.setEmail(rs.getString("email"));//9
                obj.setFoto(rs.getString("foto"));//10

            }

            return obj;
        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getClienteID");
            return null;
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getClienteID");
            return null;
        }
    }

    public List<model_cliente> getClienteList(MainApp mainapp) {
        db.open();
        ArrayList<model_cliente> result = new ArrayList<>();
        try {

            stmt = conexao.prepareStatement("SELECT * FROM `clientes` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model_cliente obj = new model_cliente(mainapp);

                obj.setID_cliente(rs.getInt("ID_cliente"));//1
                obj.setCPF(rs.getString("cpf"));//2
                obj.setNome(rs.getString("nome"));//3
                obj.setMae(rs.getString("mae"));//4
                obj.setPai(rs.getString("pai"));//5
                obj.setData_Nascimento(rs.getString("data_nascimento"));//6
                obj.setCartao_SUS(rs.getString("cartao_sus"));//7
                obj.setID_Sexo(rs.getInt("ID_sexo"));//8
                obj.setEmail(rs.getString("email"));//9
                obj.setFoto(rs.getString("foto"));//10
                result.add(obj);
            }
            return result;

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getClienteList");
            return null;

        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getClienteList");
            return null;
        }
    }

    public void creatCliente(model_cliente obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("INSERT INTO clientes (`cpf`, `nome`, `mae`, `pai`, `data_nascimento`, `cartao_sus`, `ID_sexo`, `email`, `foto`) VALUES(?,?,?,?,?,?,?,?,?);");

            stmt.setString(1, obj.getCPF());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getMae());
            stmt.setString(4, obj.getPai());
            stmt.setString(5, obj.getData_Nascimento());
            stmt.setString(6, obj.getCartao_SUS());
            stmt.setInt(7, obj.getID_Sexo());
            stmt.setString(8, obj.getEmail());
            stmt.setString(9, obj.getFoto());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatCliente");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatCliente");

        }
    }

    public void updateCliente(model_cliente obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("UPDATE clientes SET"
                    + " cpf = ?,"//1
                    + " nome = ?,"//2
                    + " mae = ?,"//3
                    + " pai = ?,"//4
                    + " data_nascimento = ?,"//5
                    + " cartao_sus = ?,"//6
                    + " ID_sexo = ?,"//7
                    + " email = ?,"//8
                    + " foto = ?"//9
                    + " WHERE ID_cliente = ?;");//10

            stmt.setString(1, obj.getCPF());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getMae());
            stmt.setString(4, obj.getPai());
            stmt.setString(5, obj.getData_Nascimento());
            stmt.setString(6, obj.getCartao_SUS());
            stmt.setInt(7, obj.getID_Sexo());
            stmt.setString(8, obj.getEmail());
            stmt.setString(9, obj.getFoto());
            stmt.setInt(10, obj.getID_cliente());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateCliente");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateCliente");

        }
    }

    public void deleteCliente(model_cliente obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("DELETE FROM clientes WHERE ID_cliente = ?;");

            stmt.setInt(1, obj.getID_cliente());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteCliente");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteCliente");

        }
    }
}
