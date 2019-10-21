/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clinicafx.Util;

import java.awt.HeadlessException;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Marinho < pedro.marinho238@gmail.com >
 */
public class MsgErro {

    public static void MessagemErroTela(Exception ex, String msg) {
        ex.printStackTrace();

        try {
            StackTraceElement[] erros = ex.getStackTrace();
//            JFrame frame = new JFrame("Erro Tela");

            String mensagem_erro = msg + "\n";
            for (StackTraceElement erro : erros) {
                mensagem_erro += erro.toString() + "\n";
            }

            // Cria o JOptionPane por showMessageDialog
            JOptionPane.showMessageDialog(null,
                    "Houve um problema no carregamento da tela:\n\n '" + mensagem_erro + "'.", //mensagem
                    "Erro 404", // titulo da janela 
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }

    }

    public static void MessagemErroBD(Exception ex, String msg) {
        ex.printStackTrace();


//            JFrame frame = new JFrame("Erro Tela");
        String mensagem_erro = msg + "\n";
        try {
            StackTraceElement[] erros = ex.getStackTrace();
            for (StackTraceElement erro : erros) {
                mensagem_erro += erro.toString() + "\n";
            }
        } catch (HeadlessException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }

        // Cria o JOptionPane por showMessageDialog
        JOptionPane.showMessageDialog(null,
                "Houve um problema no Banco de Dados:\n\n '" + mensagem_erro + "'.", //mensagem
                "Erro 404", // titulo da janela 
                JOptionPane.INFORMATION_MESSAGE);

    }

    public static void MessagemErroFormulario(String msg) {


        try {
//            JFrame frame = new JFrame("Erro Tela");

            String mensagem_erro = msg + "\n";

            // Cria o JOptionPane por showMessageDialog
            JOptionPane.showMessageDialog(null,
                    "Houve um problema no Formulario:\n\n '" + mensagem_erro + "'.", //mensagem
                    "Erro 404", // titulo da janela 
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        }

    }
}
