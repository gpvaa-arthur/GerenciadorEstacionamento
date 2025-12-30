package view;

import view.util.Navegador;

import javax.swing.*;
import java.awt.*;

public class TelaHistorico implements ITela{
    private JPanel panel = new JPanel();
    private IDEnum ID = IDEnum.HISTORICO;

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
        panel.add(new JButton("VER HISTÓRICO"));


    }
}
