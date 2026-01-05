package view;

import view.util.Navegador;

import javax.swing.*;
import java.text.ParseException;

// Cada Tela que pode ser navegada por botões se configura sozinha
// e implementa a seguinte interface:

public interface ITela {
    public void configurar(Navegador nav);
    public JPanel getPanel();
    public IDEnum getID();

}
