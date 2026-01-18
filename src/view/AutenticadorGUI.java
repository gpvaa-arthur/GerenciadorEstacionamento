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
                campoSenha,                    // O componente de senha vai aqui
                "Admin",       // Título
                JOptionPane.NO_OPTION,         // Não usamos as opções padrão
                JOptionPane.PLAIN_MESSAGE,     // Sem ícone de alerta
                null,                          // Sem ícone customizado
                botoes,                        // Nossos botões "Entrar" e "Cancelar"
                botoes[0]                      // Foco inicial no "Entrar"
        );

        //todo Adicionar método service para autenticar senha
        if (escolha == 0) { // 0 é o índice do botão "Entrar"
            return autenticarSenhaService.autenticarSenha(campoSenha);
        }

        return 0; // Usuário cancelou
    }
}
