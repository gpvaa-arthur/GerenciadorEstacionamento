package service;

import model.Ticket;
import repository.TicketRepository;
import view.util.ModelView;

import javax.swing.table.DefaultTableModel;
import java.time.YearMonth;

public class AtualizarTabelaService {

    public void TabelaHistorico(Ticket ticket){
        FormatHorarioService horarioService = new FormatHorarioService();
        DefaultTableModel modelo = ModelView.getModeloHistorico();

        modelo.addRow(new Object[]{
                ticket.getVeiculo().getTipo(),
                ticket.getVeiculo().getPlaca().toUpperCase(),
                horarioService.formatHorario(ticket.getHorarioEntrada()),
                horarioService.formatHorario(ticket.getHorarioSaida()),
                ("R$ " + ticket.getValorCobrado())});
    }



}
