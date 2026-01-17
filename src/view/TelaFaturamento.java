package view;

import repository.TicketRepository;
import view.util.Navegador;

import javax.swing.*;
import java.awt.*;

public class TelaFaturamento implements ITela{
    private JPanel panel = new JPanel();
    private IDEnum ID = IDEnum.FATURAMENTO;
    private TicketRepository ticketRepository;

    public TelaFaturamento(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

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
        panel.add(new JButton("VERIFICAR FATURAMENTO"));


    }
}
