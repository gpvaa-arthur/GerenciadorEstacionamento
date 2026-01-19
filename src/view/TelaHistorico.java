package view;

import model.Ticket;
import repository.TicketRepository;
import service.AtualizarTabelaService;
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
    private JButton botaoFinalizado;
    private int valorAtual = 0;

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

    // Services
        AtualizarTabelaService tabelaService = new AtualizarTabelaService();

    // Configuração modeloFinalizado tabela
    ModelView modelView = new ModelView();
    modelView.configModelFinalizado();
    DefaultTableModel modeloFinalizado = modelView.getModeloFinalizado();

    // Configuração modeloAtivo tabela
    DefaultTableModel modeloAtivo = modelView.configModelAtivo();

    // Configurações Tabela
    JTable tabela = new JTable(modeloFinalizado);
    tabela.getTableHeader().setReorderingAllowed(false);
    tabela.getTableHeader().setResizingAllowed(false);

    tabela.getColumnModel().getColumn(2).setPreferredWidth(110);
    tabela.getColumnModel().getColumn(3).setPreferredWidth(110);
    tabela.getColumnModel().getColumn(4).setPreferredWidth(60);


    JScrollPane scrollPane = new JScrollPane(tabela);
    scrollPane.setPreferredSize(new Dimension(430, 200));
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    AuxLayout.setup(gbc,0,2,2,1,0.0,1.0);
    gbc.insets = new Insets(0, 8, 5, 8);
    gbc.fill = GridBagConstraints.BOTH;
    panel.add(scrollPane, gbc);

    // Label
    AuxLayout.reset(gbc);
    JLabel labelHistorico = new JLabel("Histórico - Tickets Finalizados");
    AuxLayout.setup(gbc, 0,1,2,1,0.0,0.0);
    gbc.insets = new Insets(5, 0, 5, 0);
    panel.add(labelHistorico, gbc);

    // Botões (Tickets Ativos e Tickets Finalizados)

    AuxLayout.reset(gbc);
    botaoFinalizado = new JButton("Tickets Finalizados");
    AuxLayout.setup(gbc, 0,0,1,1,0.5,0.0);
    gbc.anchor =GridBagConstraints.EAST;
    gbc.insets = new Insets(5,0, 2, 8);

    botaoFinalizado.addActionListener(e -> {
        if(valorAtual == 1){
                tabela.setModel(modeloFinalizado);
                labelHistorico.setText("Histórico - Tickets Finalizados");
                this.valorAtual = 0;
        }
    });
    panel.add(botaoFinalizado, gbc);

    JButton botaoAtivo = new JButton("Tickets Ativos");
    AuxLayout.setup(gbc, 1,0,1,1,0.5,0.0);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5,0, 2, 0);

    botaoAtivo.addActionListener(e -> {
        if(valorAtual == 0){
            tabela.setModel(modeloAtivo);
            labelHistorico.setText("Histórico - Tickets Ativos");
            valorAtual = 1;

            modeloAtivo.setRowCount(0);
            for(Ticket ticket : ticketRepository.getTicketsAtivos()){
                tabelaService.TabelaAtiva(ticket, modeloAtivo);
            }
        }
    });

    panel.add(botaoAtivo, gbc);
    }

    public JButton getBotaoFinalizado() {
        return botaoFinalizado;
    }
}

