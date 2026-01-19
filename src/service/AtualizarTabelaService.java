package service;

import model.Ticket;
import view.util.ModelView;

import javax.swing.table.DefaultTableModel;

public class AtualizarTabelaService {

    private FormatService horarioService = new FormatService();

    public void TabelaFinalizada(Ticket ticket){
        DefaultTableModel modelo = ModelView.getModeloFinalizado();

        modelo.addRow(new Object[]{
                ticket.getVeiculo().getTipo(),
                ticket.getVeiculo().getPlaca().toUpperCase(),
                horarioService.formatHorario(ticket.getHorarioEntrada()),
                horarioService.formatHorario(ticket.getHorarioSaida()),
                ("R$ " + FormatService.formatValor(ticket.getValorCobrado()))});
    }

    public void TabelaAtiva(Ticket ticket, DefaultTableModel modeloAtivo){
        modeloAtivo.addRow(new Object[]{
                ticket.getVeiculo().getTipo(),
                ticket.getVeiculo().getPlaca().toUpperCase(),
                horarioService.formatHorario(ticket.getHorarioEntrada())
        });

    }



}
