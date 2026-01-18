package service;

import exceptions.HorarioInvalidoException;
import exceptions.VeiculoNaoEncontradoException;
import model.Ticket;
import repository.TicketRepository;
import view.ExceptionGUI;
import view.PagamentoGUI;

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

    public double registrarSaida(String placa, LocalDateTime horarioSaida) throws HorarioInvalidoException, VeiculoNaoEncontradoException {

        //Service e View
        AtualizarTabelaService tabelaService = new AtualizarTabelaService();

        //  Busca ticket ativo
        Ticket ticket = repository.getTicketAtivo(placa);

        if (ticket == null) {
            ExceptionGUI.exceptionGUI("Veículo não encontrado no estacionameno!");
            throw new VeiculoNaoEncontradoException(
                    "Veículo não encontrado no estacionamento"
            );
        }

        //  Valida horário
        if (horarioSaida.isBefore(ticket.getHorarioEntrada())) {
            ExceptionGUI.exceptionGUI("Horário de saída inválido");
                throw new HorarioInvalidoException(
                        "Horário de saída inválido"
                );
        }

        //  Calcula valor
        double valor = calculoTarifaService.calcularValor(
                ticket.getVeiculo(),
                ticket.getHorarioEntrada(),
                horarioSaida
        );
        // Janela de confirmação
        PagamentoGUI.confirmarPagamento(valor);

        //  Fecha o ticket
        ticket.setHorarioSaida(horarioSaida);
        ticket.setValorCobrado(valor);

        //  Move para finalizados
        repository.removerTicketAtivo(placa);
        repository.addTicketFinalizado(placa, ticket);

        //Atualiza tabela
        tabelaService.TabelaHistorico(ticket);

        return valor;
    }
}
