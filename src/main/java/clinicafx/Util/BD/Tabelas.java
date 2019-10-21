/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Util.BD;

import clinicafx.Model.Configuracao_Local.model_banco_de_dados;
import clinicafx.Util.MsgErro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Pedro Marinho
 * <pedro.marinho238@gmail.com & https://github.com/TECFlyingCommunity>
 */
public class Tabelas {

    public static void CriarTabela(Connection conexao, model_banco_de_dados db) {

        String AUTO_INCREMENT = "AUTO_INCREMENT";
        if (db.getPrefix().equals("jdbc:sqlite:")) {
            AUTO_INCREMENT = "AUTOINCREMENT";
        } else if (db.getPrefix().equals("jdbc:mysql:")) {
            AUTO_INCREMENT = "AUTO_INCREMENT";
        }
        System.out.println(AUTO_INCREMENT);

        final String PAIS_STRING = "CREATE TABLE IF NOT EXISTS pais("
                + "  ID_pais INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  pais varchar(230) not null UNIQUE"
                //            + "  PRIMARY KEY (ID_pais)"
                + ");";
        final String ESTADO_STRING = "CREATE TABLE IF NOT EXISTS estado("
                + "  ID_estado INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  ID_pais INTEGER not null,"
                + "  estado varchar(100) not null UNIQUE,"
                + "  abreviacao varchar(4) not null,"
                + "  FOREIGN KEY (ID_pais) REFERENCES pais ( ID_pais )"
                //            + "  PRIMARY KEY (ID_estado)"
                + ");";
        final String CIDADE_STRING = "CREATE TABLE IF NOT EXISTS cidades("
                + "  ID_cidade INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  ID_estado INTEGER not null,"
                + "  cidade varchar(230) not null,"
                + "  FOREIGN KEY (ID_estado) REFERENCES estado ( ID_estado )"
                //            + "  PRIMARY KEY (ID_cidade)"
                + ");"
                + "";
        final String BAIRROS_STRING = "CREATE TABLE IF NOT EXISTS bairros("
                + "  ID_bairro INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  ID_cidade INTEGER not null,"
                + "  bairro varchar(230) not null,"
                + "  FOREIGN KEY (ID_cidade) REFERENCES cidades ( ID_cidade )"
                //            + "  PRIMARY KEY (ID_bairro)"
                + ");";
        final String RUAS_STRING = "CREATE TABLE IF NOT EXISTS ruas("
                + "  ID_rua INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  ID_bairro INTEGER not null,"
                + "  rua varchar(230) not null,"
                + "  FOREIGN KEY (ID_bairro) REFERENCES bairros ( ID_bairro )"
                //            + "  PRIMARY KEY (ID_rua)"
                + ");";
        final String SEXOS_STRING = "CREATE TABLE IF NOT EXISTS sexos("
                + "  ID_sexo INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  sexo varchar(200) not null UNIQUE"
                //            + "  PRIMARY KEY(ID_sexo)"
                + ");";
        final String CLIENTES_STRING = "CREATE TABLE IF NOT EXISTS clientes ("
                + "  ID_cliente INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  cpf varchar(16) not null,"
                + "  nome varchar(220) not null,"
                + "  mae varchar(220),"
                + "  pai varchar(220),"
                + "  data_nascimento date not null,"
                + "  cartao_sus varchar(50) UNIQUE,"
                + "  ID_sexo INTEGER not null,"
                + "  email VARCHAR (250) ,"
                + "  foto TEXT,"
                + "  FOREIGN KEY (ID_sexo) REFERENCES sexos(ID_sexo)"
                //            + "  PRIMARY KEY(ID_cliente)"
                + ""
                + ");";
        final String CORPO_CLIENTE_STRING = "CREATE TABLE IF NOT EXISTS corpo_cliente("
                + "  ID_corpo_cliente INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  ID_cliente INTEGER not null , "
                + "  peso float,"
                + "  altura float,"
                + "  quadril FLOAT,"
                + "  data_corpo_cliente date not null,"
                + "  FOREIGN KEY (ID_cliente) REFERENCES clientes ( ID_cliente )"
                //            + "  PRIMARY KEY(ID_corpo_cliente)"
                + ");";
        final String ENDERECO_CLIENTE_STRING = "CREATE TABLE IF NOT EXISTS endereco_cliente ("
                + "  ID_endereco_cliente INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  ID_cliente INTEGER not null UNIQUE, "
                + "  telefone varchar(21),"
                + "  telefone_fixo varchar(21),"
                + "  ID_cidade INTEGER not null,"
                + "  ID_rua INTEGER,"
                + "  ID_bairro INTEGER,"
                + "  numero_casa INTEGER,"
                + "  complemento TEXT,"
                + "  FOREIGN KEY (ID_cidade) REFERENCES cidades ( ID_cidade ),"
                + "  FOREIGN KEY (ID_rua) REFERENCES ruas ( ID_rua ),"
                + "  FOREIGN KEY (ID_bairro) REFERENCES bairros ( ID_bairro ),"
                + "  FOREIGN KEY (ID_cliente) REFERENCES clientes ( ID_cliente )"
                //            + "  PRIMARY KEY(ID_endereco_cliente)"
                + ");";
        final String FICHA_STRING = "CREATE TABLE IF NOT EXISTS ficha("
                + "  ID_ficha INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  ID_cliente INTEGER not null,"
                + "  data_ficha date,"
                + "  observacao text,"
                + "  FOREIGN KEY (ID_cliente) REFERENCES clientes ( ID_cliente )"
                //            + "  PRIMARY KEY(ID_ficha)"
                + ");";
        final String CLINICA_STRING = "CREATE TABLE IF NOT EXISTS clinica("
                + "    ID_clinica INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "    nome varchar(220) not null"
                //            + "    PRIMARY KEY(ID_clinica)"
                + ");";
        final String ENDERECO_CLINICA_STRING = "CREATE TABLE IF NOT EXISTS endereco_clinica ("
                + "  ID_endereco_clinica INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  ID_clinica INTEGER not null UNIQUE, "
                + "  telefone varchar(21),"
                + "  telefone_fixo varchar(21),"
                + "  ID_cidade INTEGER not null,"
                + "  ID_rua INTEGER,"
                + "  ID_bairro INTEGER,"
                + "  numero_casa INTEGER,"
                + "  complemento TEXT,"
                + "  FOREIGN KEY (ID_cidade) REFERENCES cidades ( ID_cidade ),"
                + "  FOREIGN KEY (ID_rua) REFERENCES ruas ( ID_rua ),"
                + "  FOREIGN KEY (ID_bairro) REFERENCES bairros ( ID_bairro ),"
                + "  FOREIGN KEY (ID_clinica) REFERENCES clinica ( ID_clinica )"
                //            + "  PRIMARY KEY(ID_endereco_clinica)"
                + ");";
        final String USUARIO_STRING = "CREATE TABLE IF NOT EXISTS usuario("
                + "    ID_usuario INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "    Nome varchar(220) not null,"
                + "    Sobrenome varchar(220) not null,"
                + "    Senha varchar(50) not null,"
                + "    ID_sexo INTEGER not null,"
                + "    DataNascimento date,"
                + "    Usuario varchar(100) not null UNIQUE,"
                + "    Email varchar(220) not null UNIQUE,"
                + "    FOREIGN KEY (ID_sexo) REFERENCES sexos(ID_sexo) "
                //            + "    PRIMARY KEY(ID_usuario)"
                + ");";
        final String AGENDA_STRING = "CREATE TABLE IF NOT EXISTS agenda( "
                + "    ID_agenda INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "    ID_usuario INTEGER not null,"
                + "    ID_cliente INTEGER not null,"
                + "    ID_clinica INTEGER not null,"
                + "    data_agenda date not null,"
                + "    horario time not null,"
                + "    Observacao text,"
                + "    FOREIGN KEY (ID_usuario) REFERENCES usuario ( ID_usuario ),"
                + "    FOREIGN KEY (ID_cliente) REFERENCES clientes ( ID_cliente ),"
                + "    FOREIGN KEY (ID_clinica) REFERENCES clinica ( ID_clinica )"
                //            + "    PRIMARY KEY(ID_agenda)"
                + ");";
        final String HOMOGRAMA_STRING = "CREATE TABLE IF NOT EXISTS homograma("
                + "  ID_homograma INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  ID_ficha INTEGER not null,"
                + "  data_do_exame date,"
                + "  ht char,"
                + "  hb char,"
                + "  hm char,"
                + "  basofilos_porcento float,"
                + "  basofilos_mm float,"
                + "  eosinofilos_porcento float,"
                + "  eosinofilos_mm float,"
                + "  metamielocitos_porcento float,"
                + "  metamielocitos_mm float,"
                + "  bastonetes_porcento float,"
                + "  bastonetes_mm float,"
                + "  linfocitos_porcento float,"
                + "  linfocitos_mm float,"
                + "  linfocitos_atip_porcento float,"
                + "  linfocitos_atip_mm float,"
                + "  monocitos_porcento float,"
                + "  monocitos_mm float,"
                + "  obs TEXT,"
                + "  global_leucocitos char, "
                + "  FOREIGN KEY(ID_ficha) REFERENCES ficha(ID_ficha)"
                //            + "  PRIMARY KEY(ID_homograma)"
                + ");";
        final String FEZES_STRING = "CREATE TABLE IF NOT EXISTS fezes("
                + "  ID_fezes INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  ID_ficha INTEGER not null,"
                + "  data_do_exame date,"
                + "  sangue_oculto char,"
                + "  observacao TEXT,"
                + "  FOREIGN KEY(ID_ficha) REFERENCES ficha(ID_ficha)"
                //            + "  PRIMARY KEY(ID_fezes)"
                + ");";
        final String HELMITO_STRING = "CREATE TABLE IF NOT EXISTS helmito("
                + "  ID_helmito INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  ID_fezes INTEGER not null,"
                + "  a_lumbricoides float,"
                + "  t_trichiura float,"
                + "  e_vermicularis float,"
                + "  ancilostomideos float,"
                + "  s_stercoralis float,"
                + "  taenia_sp float,"
                + "  outros text,"
                + "  FOREIGN KEY (ID_fezes) REFERENCES fezes(ID_fezes)"
                //            + "  PRIMARY KEY(ID_helmito)"
                + ");";
        final String PROTOZOARIOS_STRING = "CREATE TABLE IF NOT EXISTS protozoarios("
                + "  ID_protozoarios INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  ID_fezes INTEGER not null,"
                + "  e_histolitica float,"
                + "  e_coli float,"
                + "  g_lamblia float,"
                + "  outros text,"
                + "  FOREIGN KEY (ID_fezes) REFERENCES fezes(ID_fezes)"
                //            + "  PRIMARY KEY(ID_protozoarios)"
                + ");";
        final String EAS_STRING = "CREATE TABLE IF NOT EXISTS eas("
                + "  ID_eas INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  ID_ficha INTEGER not null,"
                + "  data_do_exame date,"
                + "  cor varchar(120),"
                + "  aspecto varchar(120),"
                + "  deposito  varchar(120),"
                + "  urobilinogenio  varchar(120),"
                + "  glicose  varchar(120),"
                + "  bilirrubina  varchar(120),"
                + "  cetona  varchar(120),"
                + "  densidade  varchar(120),"
                + "  sangue  varchar(120),"
                + "  ph  varchar(120),"
                + "  proteinas  varchar(120),"
                + "  nitritos  varchar(120),"
                + "  leucocitos  varchar(120),"
                + "  FOREIGN KEY(ID_ficha) REFERENCES ficha(ID_ficha)"
                //            + "  PRIMARY KEY(ID_eas)"
                + ");";
        final String SEDIMENTOSCOPIA_STRING = "CREATE TABLE IF NOT EXISTS sedimentoscopia("
                + "  ID_sedimentoscopia INTEGER not null PRIMARY KEY " + AUTO_INCREMENT + ","
                + "  ID_ficha INTEGER not null,"
                + "  data_do_exame date,"
                + "  celulas_epiteliais varchar(120),"
                + "  muco varchar(120),"
                + "  hemacia varchar(120),"
                + "  piocitos varchar(120),"
                + "  cilindros varchar(120),"
                + "  cristais varchar(120),"
                + "  flora varchar(120),"
                + "  obs text,"
                + "  FOREIGN KEY(ID_ficha) REFERENCES ficha(ID_ficha)"
                //            + "  PRIMARY KEY(ID_sedimentoscopia)"
                + ");";

        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(PAIS_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.PAIS_STRING");
        }
        try {
            stmt = conexao.prepareStatement(ESTADO_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.ESTADO_STRING");
        }
        try {
            stmt = conexao.prepareStatement(CIDADE_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.CIDADE_STRING");
        }
        try {
            stmt = conexao.prepareStatement(BAIRROS_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.BAIRROS_STRING");
        }
        try {
            stmt = conexao.prepareStatement(RUAS_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.RUAS_STRING");
        }
        try {
            stmt = conexao.prepareStatement(SEXOS_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.SEXOS_STRING");
        }
        try {
            stmt = conexao.prepareStatement(CLIENTES_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.CLIENTES_STRING");
        }
        try {
            stmt = conexao.prepareStatement(CORPO_CLIENTE_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.CORPO_CLIENTE_STRING");
        }
        try {
            stmt = conexao.prepareStatement(ENDERECO_CLIENTE_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.ENDERECO_CLIENTE_STRING");
        }
        try {
            stmt = conexao.prepareStatement(FICHA_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.FICHA_STRING");
        }
        try {
            stmt = conexao.prepareStatement(CLINICA_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.CLINICA_STRING");
        }
        try {
            stmt = conexao.prepareStatement(ENDERECO_CLINICA_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.ENDERECO_CLINICA_STRING");
        }
        try {
            stmt = conexao.prepareStatement(USUARIO_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.USUARIO_STRING");
        }
        try {
            stmt = conexao.prepareStatement(AGENDA_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.AGENDA_STRING");
        }
        try {
            stmt = conexao.prepareStatement(HOMOGRAMA_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.HOMOGRAMA_STRING");
        }
        try {
            stmt = conexao.prepareStatement(FEZES_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.FEZES_STRING");
        }
        try {
            stmt = conexao.prepareStatement(HELMITO_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.HELMITO_STRING");
        }
        try {
            stmt = conexao.prepareStatement(PROTOZOARIOS_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.PROTOZOARIOS_STRING");
        }
        try {
            stmt = conexao.prepareStatement(EAS_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.EAS_STRING");
        }
        try {
            stmt = conexao.prepareStatement(SEDIMENTOSCOPIA_STRING);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            MsgErro.MessagemErroBD(ex, "CriarTable.SEDIMENTOSCOPIA_STRING");
        }

    }

}
