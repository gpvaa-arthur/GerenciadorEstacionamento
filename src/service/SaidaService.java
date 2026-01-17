package service;

import model.Ticket;
import repository.TicketRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaidaService {

    private TicketRepository repository;
    private CalculoTarifaService calculoTarifaService;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public SaidaService(TicketRepository repository) {
        this.repository = repository;
        this.calculoTarifaService = new CalculoTarifaService();
    }

    public double registrarSaida(String placa, String dataHoraSaidaTexto) {

        //  Busca ticket ativo
        Ticket ticket = repository.getTicketAtivo(placa);

        if (ticket == null) {
            throw new IllegalArgumentException(
                    "Veículo não encontrado no estacionamento"
            );
        }

        //  Converte horário de saída
        LocalDateTime horarioSaida =
                LocalDateTime.parse(dataHoraSaidaTexto, FORMATTER);

        //  Valida horário
        if (horarioSaida.isBefore(ticket.getHorarioEntrada())) {
            throw new IllegalArgumentException(
                    "Horário de saída inválido"
            );
        }

        //  Calcula valor
        double valor = calculoTarifaService.calcularValor(
                ticket.getVeiculo(),
                ticket.getHorarioEntrada(),
                horarioSaida
        );

        //  Fecha o ticket
        ticket.setHorarioSaida(horarioSaida);
        ticket.setValorCobrado(valor);

        //  Move para finalizados
        repository.removerTicketAtivo(placa);
        repository.addTicketFinalizado(placa, ticket);

        return valor;
    }
}
