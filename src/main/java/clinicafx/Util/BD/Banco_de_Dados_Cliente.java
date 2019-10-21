package clinicafx.Util.BD;

import clinicafx.Model.Configuracao_Local.model_banco_de_dados;
import clinicafx.Util.MsgErro;
import clinicafx.Util.MsgErro;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

/**
 * @author Pedro Marinho  < pedro.marinho238@gmail.com >
 */
public final class Banco_de_Dados_Cliente {

    public Connection conexao = null;
    private model_banco_de_dados banco_de_dados = null;

    public model_banco_de_dados getBanco_de_dados() {
        return banco_de_dados;
    }

    public void setBanco_de_dados(model_banco_de_dados banco_de_dados) {
        this.banco_de_dados = banco_de_dados;
    }

    public Connection open() {
        if (banco_de_dados != null) {
//          
            model_banco_de_dados obj = banco_de_dados;
            String url = obj.getPrefix() + "//" + obj.getHost() + ":" + obj.getPorts() + "/" + obj.getDataBase();
//        System.out.println(url);
            try {
                if (conexao == null) {
                    System.out.println(obj.getUser());
                    System.out.println(obj.getPassword());
                    if (obj.getPrefix().equals("jdbc:sqlite:")) {

                        conexao = DriverManager.getConnection(obj.getPrefix() + obj.getHost());
                        System.out.println("Sqlite->" + obj.getPrefix() + obj.getHost());
                    } else {
                        conexao = DriverManager.getConnection(url, obj.getUser(), obj.getPassword());
                    }
                    System.out.println("conexão realizada");
                }
                return conexao;
            } catch (SQLException ex) {
                close();
                System.out.println("erro");
                MsgErro.MessagemErroBD(ex, "open");
                return null;
            } catch (Exception ex) {
                close();
                MsgErro.MessagemErroBD(ex, "open");
                return null;
            }
        } else {
            return conexao;
        }

    }

    public void close() {

        try {
            if (conexao != null) {
                conexao.close();
                conexao = null;
            }
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "close");
        } catch (Exception ex) {
            MsgErro.MessagemErroBD(ex, "close");
        }

    }

}
