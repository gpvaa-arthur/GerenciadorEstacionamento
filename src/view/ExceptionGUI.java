package view;

import javax.swing.*;
import java.awt.*;

// Envia uma mensagem na tela para cada exceção
public class ExceptionGUI {
    public static void exceptionGUI(String message){
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(
                null,
                message,
                "Erro",
                JOptionPane.WARNING_MESSAGE
                );
    }
}
