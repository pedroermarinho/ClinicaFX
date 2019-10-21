/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Model.Exames.ExamesDAO;

import clinicafx.MainApp;
import clinicafx.Model.Exames.model_fezes;
import clinicafx.Util.BD.Banco_de_Dados_Cliente;

import clinicafx.Util.MsgErro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class fezesDAO {

    private MainApp mainapp = null;
    private Banco_de_Dados_Cliente db = null;
    private PreparedStatement stmt;
    protected Connection conexao = null;

    public fezesDAO(MainApp mainapp) {
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

    public model_fezes getFezesID(int ID, MainApp mainapp) {
        db.open();
        model_fezes obj = new model_fezes(mainapp);

        try {

            stmt = conexao.prepareStatement("SELECT * FROM `clientes` WHERE ID_cliente = '" + ID + "'");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
//                obj.setID_cliente(rs.getInt("ID_cliente"));//1
//                obj.setCPF(rs.getString("cpf"));//2
//                obj.setNome(rs.getString("nome"));//3
//                obj.setMae(rs.getString("mae"));//4
//                obj.setPai(rs.getString("pai"));//5
//                obj.setData_Nascimento(rs.getString("data_nascimento"));//6
//                obj.setCartao_SUS(rs.getString("cartao_sus"));//7
//                obj.setID_Sexo(rs.getInt("ID_sexo"));//8
//                obj.setEmail(rs.getString("email"));//9
//                obj.setFoto(rs.getString("foto"));//10

            }

            return obj;
        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getFezesID");
            return null;
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getFezesID");
            return null;
        }
    }

    public List<model_fezes> getFezesList(MainApp mainapp) {
        db.open();
        ArrayList<model_fezes> result = new ArrayList<>();
        try {

            stmt = conexao.prepareStatement("SELECT * FROM `clientes` ");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                model_fezes obj = new model_fezes(mainapp);

//                obj.setID_cliente(rs.getInt("ID_cliente"));//1
//                obj.setCPF(rs.getString("cpf"));//2
//                obj.setNome(rs.getString("nome"));//3
//                obj.setMae(rs.getString("mae"));//4
//                obj.setPai(rs.getString("pai"));//5
//                obj.setData_Nascimento(rs.getString("data_nascimento"));//6
//                obj.setCartao_SUS(rs.getString("cartao_sus"));//7
//                obj.setID_Sexo(rs.getInt("ID_sexo"));//8
//                obj.setEmail(rs.getString("email"));//9
//                obj.setFoto(rs.getString("foto"));//10
                result.add(obj);
            }
            return result;

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getFezesList");
            return result;

        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "getFezesList");
            return null;
        }
    }

    public void creatFezes(model_fezes obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("INSERT INTO clientes (`ID_ficha`, `data_do_exame`, `sangue_oculto`, `observacao`) VALUES(?,?,?,?,?,?,?,?,?,?);");

//            stmt.setInt(1,0);
//            stmt.setString(2, obj.getCPF());
//            stmt.setString(3, obj.getNome());
//            stmt.setString(4, obj.getMae());
//            stmt.setString(5, obj.getPai());
//            stmt.setString(6, obj.getData_Nascimento());
//            stmt.setString(7, obj.getCartao_SUS());
//            stmt.setInt(8, obj.getID_Sexo());
//            stmt.setString(9, obj.getEmail());
//            stmt.setString(10, obj.getFoto());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatFezes");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "creatFezes");

        }
    }

    public void updateFezes(model_fezes obj) {
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

//            stmt.setString(1, obj.getCPF());
//            stmt.setString(2, obj.getNome());
//            stmt.setString(3, obj.getMae());
//            stmt.setString(4, obj.getPai());
//            stmt.setString(5, obj.getData_Nascimento());
//            stmt.setString(6, obj.getCartao_SUS());
//            stmt.setInt(7, obj.getID_Sexo());
//            stmt.setString(8, obj.getEmail());
//            stmt.setString(9, obj.getFoto());
//            stmt.setInt(10, obj.getID_cliente());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateFezes");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "updateFezes");

        }
    }

    public void deleteFezes(model_fezes obj) {
        db.open();
        try {
            stmt = conexao.prepareStatement("DELETE FROM clientes WHERE ID_cliente = ?;");

            stmt.setInt(1, obj.getID_fezes());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteFezes");
        } catch (Exception ex) {
            db.close();
            MsgErro.MessagemErroBD(ex, "deleteFezes");

        }
    }
}
