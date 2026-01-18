package view;

import service.AutenticarSenhaService;

import javax.swing.*;

public class AutenticadorGUI {
    public int autenticarSenha() {
        AutenticarSenhaService autenticarSenhaService = new AutenticarSenhaService();
        JPasswordField campoSenha = new JPasswordField();
        String[] botoes = {"Entrar", "Cancelar"};

        // Abrir OptionDialog
        int escolha = JOptionPane.showOptionDialog(
                null,
                campoSenha,
                "Admin",
                JOptionPane.NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                botoes,
                botoes[0]
        );

        if (escolha == 0) {
            return autenticarSenhaService.autenticarSenha(campoSenha);
        }

        return 0; // Usuário clicou em cancelar
    }
}
