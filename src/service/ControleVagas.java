package service;

import repository.TicketRepository;

public class ControleVagas {
    private static final int TOTAL_CARROS = 30;
    private static final int TOTAL_MOTOS = 20;
    private static final int TOTAL_ONIBUS = 5;

    private TicketRepository ticketRepository;

    public ControleVagas(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    //Conta a quantidades de Vagas de carro disponíveis
    public int vagasDisponiveisCarros() {
        return TOTAL_CARROS - ticketRepository.contarCarrosAtivos();
    }

    //Conta a quantidades de Vagas de Moto disponíveis
    public int vagasDisponiveisMotos() {
        return TOTAL_MOTOS - ticketRepository.contarMotosAtivas();
    }

    //Conta a quantidades de Vagas de Onibus disponíveis
    public int vagasDisponiveisOnibus() {
        return TOTAL_ONIBUS - ticketRepository.contarOnibusAtivos();
    }

    // confirma se pode ter a entrada de Carros
    public boolean podeEntrarCarro() {
        return vagasDisponiveisCarros() > 0;
    }

    // confirma se pode ter a entrada de Motos
    public boolean podeEntrarMoto() {
        return vagasDisponiveisMotos() > 0;
    }

    // confirma se pode ter a entrada de Onibus
    public boolean podeEntrarOnibus() {
        return vagasDisponiveisOnibus() > 0;
    }

}
