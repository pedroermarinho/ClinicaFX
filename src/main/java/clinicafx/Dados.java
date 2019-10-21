/*
 * To change MainApp license header, choose License Headers in Project Properties.
 * To change MainApp template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx;

import clinicafx.Model.Cliente.model_cliente;
import clinicafx.Model.Cliente.model_corpo_cliente;
import clinicafx.Model.Cliente.model_endereco_cliente;
import clinicafx.Model.Cliente.model_ficha;
import clinicafx.Model.Clinicas.model_clinica;
import clinicafx.Model.Clinicas.model_endereco_clinica;
import clinicafx.Model.Configuracao_Local.model_banco_de_dados;
import clinicafx.Model.Usuario.model_agenda;
import clinicafx.Model.Usuario.model_usuario;
import clinicafx.Model.model_bairros;
import clinicafx.Model.model_cidades;
import clinicafx.Model.model_estado;
import clinicafx.Model.model_pais;
import clinicafx.Model.model_ruas;
import clinicafx.Model.model_sexo;
import clinicafx.Util.MsgErro;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Pedro Marinho
 * <pedro.marinho238@gmail.com & https://github.com/TECFlyingCommunity>
 */
public class Dados {

    private MainApp MainApp;

    public Dados(MainApp MainApp) {
        this.MainApp = MainApp;
    }

    private final ObservableList<model_bairros> BairrosData = FXCollections.observableArrayList();
    private final ObservableList<model_cidades> CidadesData = FXCollections.observableArrayList();
    private final ObservableList<model_cliente> ClientesData = FXCollections.observableArrayList();
    private final ObservableList<model_corpo_cliente> CorpoClienteData = FXCollections.observableArrayList();
    private final ObservableList<model_endereco_cliente> EnderecoClienteData = FXCollections.observableArrayList();
    private final ObservableList<model_estado> EstadoData = FXCollections.observableArrayList();
    private final ObservableList<model_ficha> FichaData = FXCollections.observableArrayList();
    private final ObservableList<model_pais> PaisData = FXCollections.observableArrayList();
    private final ObservableList<model_ruas> RuasData = FXCollections.observableArrayList();
    private final ObservableList<model_sexo> SexosData = FXCollections.observableArrayList();
    private final ObservableList<model_banco_de_dados> Bancos_de_DadosData = FXCollections.observableArrayList();
    private final ObservableList<model_clinica> ClinicaData = FXCollections.observableArrayList();
    private final ObservableList<model_endereco_clinica> EnderecoClinicaData = FXCollections.observableArrayList();
    private final ObservableList<model_agenda> AgendaData = FXCollections.observableArrayList();
    private final ObservableList<model_usuario> UsuariosData = FXCollections.observableArrayList();

    public ObservableList<model_banco_de_dados> getBancos_de_DadosData() {
        List<model_banco_de_dados> ObjData = model_banco_de_dados.all();
        if (ObjData != null) {
            Bancos_de_DadosData.clear();
            Bancos_de_DadosData.setAll(ObjData);
        }
        return Bancos_de_DadosData;
    }

    public ObservableList<model_usuario> getUsuariosData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            UsuariosDataNotThread();
        } else {
            new Thread(() -> {
                UsuariosDataNotThread();
            }).start();
        }
        return UsuariosData;
    }

    public ObservableList<model_agenda> getAgendaData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            AgendaDataNotThread();
        } else {
            new Thread(() -> {
                AgendaDataNotThread();
            }).start();

        }
        return AgendaData;
    }

    public ObservableList<model_endereco_clinica> getEnderecoClinicaData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            EnderecoClinicaDataNotThread();
        } else {
            new Thread(() -> {
                EnderecoClinicaDataNotThread();
            }).start();
        }
        return EnderecoClinicaData;
    }

    public ObservableList<model_clinica> getClinicaData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            ClinicaDataNotThread();
        } else {
            new Thread(() -> {
                ClinicaDataNotThread();
            }).start();
        }
        return ClinicaData;
    }

    public ObservableList<model_bairros> getBairrosData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            BairrosDataNotThread();
        } else {
            new Thread(() -> {
                BairrosDataNotThread();
            }).start();
        }
        return BairrosData;
    }

    public ObservableList<model_cidades> getCidadesData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            CidadesDataNotThread();

        } else {
            new Thread(() -> {
                CidadesDataNotThread();
            }).start();
        }
        return CidadesData;
    }

    public ObservableList<model_cliente> getClientesData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            ClientesDataNotThread();
        } else {
            new Thread(() -> {
                ClientesDataNotThread();
            }).start();
        }
        return ClientesData;
    }

    public ObservableList<model_corpo_cliente> getCorpoClienteData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            CorpoClienteDataNotThread();
        } else {
            new Thread(() -> {
                CorpoClienteDataNotThread();
            }).start();
        }
        return CorpoClienteData;
    }

    public ObservableList<model_endereco_cliente> getEnderecoClienteData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            EnderecoClienteDataNotThread();
        } else {
            new Thread(() -> {
                EnderecoClienteDataNotThread();
            }).start();
        }
        return EnderecoClienteData;
    }

    public ObservableList<model_estado> getEstadoData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            EstadoDataNotThread();
        } else {
            new Thread(() -> {
                EstadoDataNotThread();
            }).start();
        }
        return EstadoData;
    }

    public ObservableList<model_ficha> getFichaData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            FichaDataNotThread();
        } else {
            new Thread(() -> {
                FichaDataNotThread();
            }).start();
        }
        return FichaData;
    }

    public ObservableList<model_pais> getPaisData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            PaisDataNotThread();
        } else {
            new Thread(() -> {
                PaisDataNotThread();
            }).start();
        }
        return PaisData;
    }

    public ObservableList<model_ruas> getRuasData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            RuasDataNotThread();
        } else {
            new Thread(() -> {
                RuasDataNotThread();
            }).start();
        }
        return RuasData;
    }

    public ObservableList<model_sexo> getSexosData() {
        if (MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
            SexosDataNotThread();
        } else {
            new Thread(() -> {
                SexosDataNotThread();
            }).start();
        }
        return SexosData;
    }

    public ObservableList<model_usuario> UsuariosDataNotThread() {

        List<model_usuario> ObjData = model_usuario.all(MainApp);
        if (ObjData != null) {
            UsuariosData.clear();
            UsuariosData.setAll(ObjData);
        }

        return UsuariosData;
    }

    public ObservableList<model_agenda> AgendaDataNotThread() {

        List<model_agenda> ObjData = model_agenda.all(MainApp);
        if (ObjData != null) {
            AgendaData.clear();
            AgendaData.setAll(ObjData);
        }

        return AgendaData;
    }

    public ObservableList<model_endereco_clinica> EnderecoClinicaDataNotThread() {

        List<model_endereco_clinica> ObjData = model_endereco_clinica.all(MainApp);
        if (ObjData != null) {
            EnderecoClinicaData.clear();
            EnderecoClinicaData.setAll(ObjData);
        }

        return EnderecoClinicaData;
    }

    public ObservableList<model_clinica> ClinicaDataNotThread() {

        List<model_clinica> ObjData = model_clinica.all(MainApp);
        ClinicaData.clear();
        ClinicaData.setAll(ObjData);

        return ClinicaData;
    }

    public ObservableList<model_bairros> BairrosDataNotThread() {

        List<model_bairros> ObjData = model_bairros.all(MainApp);
        if (ObjData != null) {
            BairrosData.clear();
            BairrosData.setAll(ObjData);
        }

        return BairrosData;
    }

    public ObservableList<model_cidades> CidadesDataNotThread() {

        List<model_cidades> ObjData = model_cidades.all(MainApp);
        if (ObjData != null) {
            CidadesData.clear();
            CidadesData.setAll(ObjData);
        }

        return CidadesData;
    }

    public ObservableList<model_cliente> ClientesDataNotThread() {

        List<model_cliente> ObjData = model_cliente.all(MainApp);
        if (ObjData != null) {
            ClientesData.clear();
            ClientesData.setAll(ObjData);
        }

        return ClientesData;
    }

    public ObservableList<model_corpo_cliente> CorpoClienteDataNotThread() {

        List<model_corpo_cliente> ObjData = model_corpo_cliente.all(MainApp);
        if (ObjData != null) {
            CorpoClienteData.clear();
            CorpoClienteData.setAll(ObjData);
        }

        return CorpoClienteData;
    }

    public ObservableList<model_endereco_cliente> EnderecoClienteDataNotThread() {

        List<model_endereco_cliente> ObjData = model_endereco_cliente.all(MainApp);
        if (ObjData != null) {
            EnderecoClienteData.clear();
            EnderecoClienteData.setAll(ObjData);
        }

        return EnderecoClienteData;
    }

    public ObservableList<model_estado> EstadoDataNotThread() {

        List<model_estado> ObjData = model_estado.all(MainApp);
        if (ObjData != null) {
            EstadoData.clear();
            EstadoData.setAll(ObjData);
        }

        return EstadoData;
    }

    public ObservableList<model_ficha> FichaDataNotThread() {

        List<model_ficha> ObjData = model_ficha.all(MainApp);
        if (ObjData != null) {
            FichaData.clear();
            FichaData.setAll(ObjData);
        }

        return FichaData;
    }

    public ObservableList<model_pais> PaisDataNotThread() {

        List<model_pais> ObjData = model_pais.all(MainApp);
        if (ObjData != null) {
            PaisData.clear();
            PaisData.setAll(ObjData);
        }

        return PaisData;
    }

    public ObservableList<model_ruas> RuasDataNotThread() {

        List<model_ruas> ObjData = model_ruas.all(MainApp);
        if (ObjData != null) {
            RuasData.clear();
            RuasData.setAll(ObjData);
        }

        return RuasData;
    }

    public ObservableList<model_sexo> SexosDataNotThread() {

        List<model_sexo> ObjData = model_sexo.all(MainApp);
        if (ObjData != null) {
            SexosData.clear();
            SexosData.setAll(ObjData);
        }

        return SexosData;
    }

    public Thread SincronizarBD_Thread;

    public void SincronizarBD() {
        try {
            if (!MainApp.getDados_db().getPrefix().equals("jdbc:sqlite:")) {
                System.out.println("not jdbc:sqlite: ");
                SincronizarBD_Thread = new Thread(() -> {
                    while (true) {
                        System.out.println("SincronizarBD");
                        getPaisData();
                        getEstadoData();
                        getCidadesData();
                        getBairrosData();
                        getRuasData();
                        getSexosData();
                        getClientesData();
                        getCorpoClienteData();
                        getEnderecoClienteData();
                        getFichaData();
                        getClinicaData();
                        getEnderecoClinicaData();
                        getUsuariosData();
                        getAgendaData();

                        if (!MainApp.getTelas().primeriaCena.isShowing()) {
                            SincronizarBD_Thread.stop();
                        }
                        try {
                            Thread.sleep(60000);
                        } catch (InterruptedException ex) {
                            System.err.println("SincronizarBD");
                        }
                    }
                });

                SincronizarBD_Thread.start();
            }
        } catch (Exception e) {
            MsgErro.MessagemErroBD(e, "SincronizarBD");
        }
    }

}
