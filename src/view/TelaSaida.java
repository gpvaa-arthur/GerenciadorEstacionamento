package view;

import view.util.Navegador;

import javax.swing.*;
import java.awt.*;

public class TelaSaida implements ITela{
    private JPanel panel = new JPanel();
    private IDEnum ID = IDEnum.SAIDA;

    @Override
    public JPanel getPanel(){
        return this.panel;
    }

    @Override
    public IDEnum getID(){
        return ID;
    }

    @Override
    public void configurar(Navegador nav){

        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());
        panel.add(new JButton("REGISTRAR SAIDA"));


    }
}
