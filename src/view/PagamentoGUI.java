package view;

import javax.swing.*;

public class PagamentoGUI {
    public static boolean confirmarPagamento() {
        int resposta = JOptionPane.showConfirmDialog(
                null,
                "Deseja realizar o pagamento?",
                "Pagamento",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (resposta == JOptionPane.YES_OPTION) {
            return true;
        }
        else{
            return false;
        }
    }
}
