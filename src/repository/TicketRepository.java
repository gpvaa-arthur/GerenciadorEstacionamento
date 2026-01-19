package repository;

import model.Ticket;

import java.time.YearMonth;
import java.util.*;

public class TicketRepository {

    private Map<String, Ticket> ticketsAtivos;
    private Map<String, Ticket> ticketsFinalizados;

    public TicketRepository() {
        this.ticketsAtivos = new HashMap<>();
        this.ticketsFinalizados = new HashMap<>();
    }

    // TICKETS ATIVOS
    public void addTicketAtivo(String placa, Ticket ticket) {
        ticketsAtivos.put(placa, ticket);
    }

    public Ticket getTicketAtivo(String placa) {
        return ticketsAtivos.get(placa);
    }

    public void removerTicketAtivo(String placa) {
        ticketsAtivos.remove(placa);
    }

    // TICKETS FINALIZADOS
    public void addTicketFinalizado(String placa, Ticket ticket) {
        ticketsFinalizados.put(placa, ticket);
    }

    public Ticket getTicketFinalizado(String placa) {
        return ticketsFinalizados.get(placa);
    }

    // Métodos que retornam o conjunto de tickets
    public Collection<Ticket> getTicketsFinalizados() {
        return ticketsFinalizados.values();
    }

    public Collection<Ticket> getTicketsAtivos() {
        return ticketsAtivos.values();
    }

    // UTILITÁRIOS
    public boolean existeTicketAtivo(String placa) {
        return ticketsAtivos.containsKey(placa);
    }

    public int getQuantidadePorTipo(String tipo) {
        int contagem = 0;
        for (Ticket ticket : ticketsAtivos.values()) {
            if (ticket.getVeiculo().getTipo().equalsIgnoreCase(tipo)) {
                contagem++;
            }
        }
        return contagem;
    }
}
