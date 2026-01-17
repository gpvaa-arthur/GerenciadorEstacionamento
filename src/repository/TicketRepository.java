package repository;

import model.Carro;
import model.Moto;
import model.Onibus;
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

    //Done: Adicionar metodo que conta o numero de motos ativos
    public int contarMotosAtivas() {
        int count = 0;
        for (Ticket ticket : ticketsAtivos.values()) {
            if (ticket.getVeiculo() instanceof Moto) {
                count++;
            }
        }
        return count;
    }

    //DONE: Adicionar metodo que conta o numero de carros ativos
    public int contarCarrosAtivos() {
        int count = 0;
        for (Ticket ticket : ticketsAtivos.values()) {
            if (ticket.getVeiculo() instanceof Carro) {
                count++;
            }
        }
        return count;
    }

    //Done: Adicionar metodo que conta o numero de onibus ativos
    public int contarOnibusAtivos() {
        int count = 0;
        for (Ticket ticket : ticketsAtivos.values()) {
            if (ticket.getVeiculo() instanceof Onibus) {
                count++;
            }
        }
        return count;
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

    // UTILITÁRIOS

    public boolean existeTicketAtivo(String placa) {
        return ticketsAtivos.containsKey(placa);
    }
}
