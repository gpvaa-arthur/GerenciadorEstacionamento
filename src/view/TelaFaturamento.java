package view;

import repository.TicketRepository;
import service.AtualizarTabelaService;
import service.FaturamentoService;
import view.util.AuxLayout;
import view.util.ModelView;
import view.util.Navegador;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaFaturamento implements ITela{
    private JPanel panel = new JPanel();
    private IDEnum ID = IDEnum.FATURAMENTO;
    private TicketRepository ticketRepository;
    private double valorTotal = 0.0;

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
        FaturamentoService faturamentoService = new FaturamentoService(ticketRepository);


        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());


        // Label
        JLabel labelFaturamento = new JLabel("Faturamento Total: R$ " + valorTotal);
        AuxLayout.setup(gbc, 0,1,1,1,0.0,0.0);
        gbc.insets = new Insets(5, 0, 5, 0);
        panel.add(labelFaturamento, gbc);

        // Botão atualizar
        JButton atualizar = new JButton("Atualizar");
        AuxLayout.setup(gbc, 0,0,1,1,0.0,0.0);

        panel.add(atualizar, gbc);
        atualizar.addActionListener(e -> {
            valorTotal = faturamentoService.calcularFaturamentoTotal();
            labelFaturamento.setText("Faturamento Total: R$ " + valorTotal);
        });

    }
}
