/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Util;

import clinicafx.Model.Cliente.model_ficha;
import clinicafx.Model.Cliente.model_endereco_cliente;
import clinicafx.Model.Cliente.model_corpo_cliente;
import clinicafx.MainApp;
import clinicafx.Model.*;
import clinicafx.Model.Cliente.model_cliente;
import clinicafx.Model.Clinicas.model_endereco_clinica;
import clinicafx.Model.Usuario.model_agenda;
import clinicafx.Model.Usuario.model_usuario;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class Filtro {

    public static ObservableList<model_estado> Pais_para_Estado(int ID_Pais, MainApp mainApp) {
        List<model_estado> Estados = model_estado.all(mainApp);
        ObservableList<model_estado> result = FXCollections.observableArrayList();
        for (model_estado Estado : Estados) {
            if (Estado.getID_Pais() == ID_Pais) {
                result.add(Estado);
            }
        }
        return result;
    }

    public static ObservableList<model_cidades> Estado_para_Cidade(int ID_Estado, MainApp mainApp) {
        List<model_cidades> Cidades = model_cidades.all(mainApp);
        ObservableList<model_cidades> result = FXCollections.observableArrayList();
        for (model_cidades Cidade : Cidades) {
            if (Cidade.getID_Estado() == ID_Estado) {
                result.add(Cidade);
            }
        }
        return result;
    }

    public static ObservableList<model_bairros> Cidade_para_Bairro(int ID_Cidade, MainApp mainApp) {
        List<model_bairros> Bairros = model_bairros.all(mainApp);
        ObservableList<model_bairros> result = FXCollections.observableArrayList();
        for (model_bairros Bairro : Bairros) {
            if (Bairro.getID_Cidade() == ID_Cidade) {
                result.add(Bairro);
            }
        }
        return result;
    }

    public static ObservableList<model_ruas> Bairro_para_Rua(int ID_Bairro, MainApp mainApp) {
        List<model_ruas> Ruas = model_ruas.all(mainApp);
        ObservableList<model_ruas> result = FXCollections.observableArrayList();
        for (model_ruas Rua : Ruas) {
            if (Rua.getID_Bairro() == ID_Bairro) {
                result.add(Rua);
            }
        }
        return result;
    }

    public static ObservableList<model_corpo_cliente> Cliente_para_Corpo(int ID_Cliente, MainApp mainApp) {
        List<model_corpo_cliente> corpos = model_corpo_cliente.all(mainApp);
        ObservableList<model_corpo_cliente> result = FXCollections.observableArrayList();
        for (model_corpo_cliente corpo : corpos) {
            if (corpo.getID_cliente() == ID_Cliente) {
                result.add(corpo);
            }
        }
        return result;
    }

    public static model_endereco_cliente Cliente_para_Endereco(int ID_Cliente, MainApp mainApp) {
        List<model_endereco_cliente> enderecos = model_endereco_cliente.all(mainApp);
        model_endereco_cliente result = null;
        for (model_endereco_cliente endereco : enderecos) {
            if (endereco.getID_cliente() == ID_Cliente) {
                result = endereco;
                break;
            }
        }
        return result;
    }

    public static model_endereco_clinica Clinica_para_Endereco(int ID_Clinica, MainApp mainApp) {
        List<model_endereco_clinica> enderecos = model_endereco_clinica.all(mainApp);
        model_endereco_clinica result = null;
        for (model_endereco_clinica endereco : enderecos) {
            if (endereco.getID_clinica() == ID_Clinica) {
                result = endereco;
                break;
            }
        }
        return result;
    }

    public static ObservableList<model_ficha> Cliente_para_Ficha(int ID_Cliente, MainApp mainApp) {
        List<model_ficha> fichas = model_ficha.all(mainApp);
        ObservableList<model_ficha> result = FXCollections.observableArrayList();
        for (model_ficha ficha : fichas) {
            if (ficha.getID_cliente() == ID_Cliente) {
                result.add(ficha);
            }
        }
        return result;
    }

    public static model_usuario Senha_Usuario(String Usuario, String Senha, MainApp mainApp) {
        List<model_usuario> usuarios = model_usuario.all(mainApp);
        model_usuario result = null;
        for (model_usuario usuario : usuarios) {
            if (usuario.getUsuario().equals(Usuario) && usuario.getSenha().equals(Senha)) {
                result = usuario;
                break;
            }
        }
        return result;
    }

    public static ObservableList<model_agenda> Agenda_Data_Atual(MainApp mainApp) {
        List<model_agenda> agendas = model_agenda.all(mainApp);
        Date date = new Date();
        java.sql.Date data = new java.sql.Date(date.getTime());
        ObservableList<model_agenda> result = FXCollections.observableArrayList();
        for (model_agenda agenda : agendas) {
            if (agenda.getData().toString().equals(data.toString())) {
                result.add(agenda);
            }
        }
        return result;
    }

    public static ObservableList<model_cliente> Agenda_Cliente_Data_Atual(MainApp mainApp, LocalDate date) {
        List<model_agenda> agendas = model_agenda.all(mainApp);
        java.sql.Date data = java.sql.Date.valueOf(date);
        ObservableList<model_cliente> result = FXCollections.observableArrayList();
        model_cliente cliente;
        for (model_agenda agenda : agendas) {
            if (agenda.getData().toString().equals(data.toString())) {
                cliente = model_cliente.find(agenda.getID_cliente(), mainApp);
                result.add(cliente);
            }
        }
        return result;
    }

}
