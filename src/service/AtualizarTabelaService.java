package service;

import model.Ticket;
import repository.TicketRepository;
import view.util.ModelView;

import javax.swing.table.DefaultTableModel;

public class AtualizarTabelaService {



    public void atualizarTabela(Ticket ticket){
        FormatHorarioService horarioService = new FormatHorarioService();
        DefaultTableModel modelo = ModelView.getModelo();

        modelo.addRow(new Object[]{
                ticket.getVeiculo().getTipo(),
                ticket.getVeiculo().getPlaca(),
                horarioService.formatHorario(ticket.getHorarioEntrada()),
                horarioService.formatHorario(ticket.getHorarioSaida()),
                ("R$ " + ticket.getValorCobrado())});


    }

}
