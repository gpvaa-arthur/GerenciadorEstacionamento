package repository;

import model.Ticket;

import java.util.HashMap;
import java.util.Map;

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

    //TODO: Adicionar metodo que conta o numero de motos ativos

    //TODO: Adicionar metodo que conta o numero de carros ativos

    //TODO: Adicionar metodo que conta o numero de onibus ativos

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

    // UTILITÁRIOS

    public boolean existeTicketAtivo(String placa) {
        return ticketsAtivos.containsKey(placa);
    }

    // Quantidade de tickets por tipo de veículo
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
