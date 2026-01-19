package view;

import service.FormatService;

import javax.swing.*;

public class PagamentoGUI {
    public static boolean confirmarPagamento(double valor) {
         JOptionPane.showMessageDialog(
                null,
                "Total: R$ "+ FormatService.formatValor(valor) +" - Deseja realizar o pagamento?",
                "Pagamento",
                JOptionPane.PLAIN_MESSAGE);

   return true;

    }
}
