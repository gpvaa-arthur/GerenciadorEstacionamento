package view;

import repository.TicketRepository;
import view.util.AuxLayout;
import view.util.ModelView;
import view.util.Navegador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaHistorico implements ITela{
    private JPanel panel = new JPanel();
    private IDEnum ID = IDEnum.HISTORICO;
    private TicketRepository ticketRepository;

    public TelaHistorico(TicketRepository ticketRepository){
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

    //----------------- Label -------------//
        JLabel labelHistorico = new JLabel("Histórico - Tickets Finalizados");
        AuxLayout.setup(gbc, 0,0,1,1,0.0,0.0);
        panel.add(labelHistorico, gbc);
    //----------------- Configuração modelo tabela -------------//
        ModelView modelView = new ModelView();
        modelView.configModelo();
        DefaultTableModel modelo = modelView.getModelo();

    //----------------- Configurações Tabela -------------//
        JTable tabela = new JTable(modelo);
        tabela.getTableHeader().setReorderingAllowed(false);
        tabela.getTableHeader().setResizingAllowed(false);

        tabela.getColumnModel().getColumn(2).setPreferredWidth(110);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(110);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(60);


        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setPreferredSize(new Dimension(430, 200));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        AuxLayout.setup(gbc,0,1,1,1,1.0,1.0);
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);
    }

}
