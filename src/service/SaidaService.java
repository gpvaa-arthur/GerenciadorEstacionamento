package service;

import exceptions.HorarioInvalidoException;
import exceptions.VeiculoNaoEncontradoException;
import model.Ticket;
import repository.TicketRepository;
import view.AutenticadorGUI;
import view.PagamentoGUI;
import view.util.ModelView;

import javax.swing.table.DefaultTableModel;
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

    public double registrarSaida(String placa, LocalDateTime horarioSaida) throws VeiculoNaoEncontradoException, HorarioInvalidoException {

        //Service e View
        AtualizarTabelaService tabelaService = new AtualizarTabelaService();

        //  Busca ticket ativo
        Ticket ticket = repository.getTicketAtivo(placa);

        // Verifica se o veículo existe no estacionamento
        if (ticket == null) {
            throw new VeiculoNaoEncontradoException(placa);
        }

        //  Valida horário
        if (horarioSaida.isBefore(ticket.getHorarioEntrada())) {
            //TODO: Substituir por exceção personalizada
            throw new HorarioInvalidoException(
                    "Horário de saída inválido: o Horário de saída não pode ser anterior ao de entrada"
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
        tabelaService.atualizarTabela(ticket);

        return valor;
    }
}
