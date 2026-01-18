package service;

import repository.TicketRepository;
import model.Ticket;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.Collection;

public class FaturamentoService {

    private TicketRepository repository;

    public FaturamentoService(TicketRepository repository) {
        this.repository = repository;
    }

    public double calcularFaturamentoDiario(LocalDate dia) {
        double total = 0.0;

        Collection<Ticket> tickets = repository.getTicketsFinalizados();

        for (Ticket ticket : tickets) {
            if (ticket.getHorarioSaida() != null &&
                    ticket.getHorarioSaida().toLocalDate().equals(dia)) {

                total += ticket.getValorCobrado();
            }
        }

        return total;
    }

    public double calcularFaturamentoMensal(YearMonth mes) {
        double total = 0.0;

        Collection<Ticket> tickets = repository.getTicketsFinalizados();

        for (Ticket ticket : tickets) {
            if (ticket.getHorarioSaida() != null &&
                    YearMonth.from(ticket.getHorarioSaida()).equals(mes)) {

                total += ticket.getValorCobrado();
            }
        }

        return total;
    }
    public double calcularFaturamentoTotal(){
        double valorTotal = 0.0;
        for(Ticket t : repository.getTicketsFinalizados()){
            valorTotal += t.getValorCobrado();
        }
        return valorTotal;
    }
}
