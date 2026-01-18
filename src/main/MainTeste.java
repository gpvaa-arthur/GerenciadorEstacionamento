package main;

import model.*;
import repository.TicketRepository;
import service.*;

import exceptions.HorarioInvalidoException;
import exceptions.VeiculoNaoEncontradoException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MainTeste {

    public static void main(String[] args)
            throws HorarioInvalidoException, VeiculoNaoEncontradoException {


        TicketRepository repo = new TicketRepository();

        // VEÍCULOS
        Veiculo carro = new Carro("CAR1234");
        Veiculo moto = new Moto("MOT5678");
        Veiculo onibus = new Onibus("ONI9999");


        // HORÁRIOS EXPLÍCITOS (AQUI É O PONTO-CHAVE)
        LocalDateTime e1 = LocalDateTime.of(2025, 1, 18, 8, 0);
        LocalDateTime s1 = LocalDateTime.of(2025, 1, 18, 10, 0);

        LocalDateTime e2 = LocalDateTime.of(2025, 1, 18, 9, 0);
        LocalDateTime s2 = LocalDateTime.of(2025, 1, 18, 10, 30);

        LocalDateTime e3 = LocalDateTime.of(2025, 1, 18, 7, 0);
        LocalDateTime s3 = LocalDateTime.of(2025, 1, 18, 12, 0);

        // ENTRADAS
        repo.addTicketAtivo("CAR1234", new Ticket(carro, e1));
        repo.addTicketAtivo("MOT5678", new Ticket(moto, e2));
        repo.addTicketAtivo("ONI9999", new Ticket(onibus, e3));

        SaidaService saida = new SaidaService(repo);

        // SAÍDAS
        saida.registrarSaida("CAR1234", s1);
        saida.registrarSaida("MOT5678", s2);
        saida.registrarSaida("ONI9999", s3);

        // FATURAMENTO
        FaturamentoService faturamento = new FaturamentoService(repo);

        LocalDate dia = LocalDate.of(2025, 1, 18);

        double total = faturamento.calcularFaturamentoDiario(dia);

        System.out.println("FATURAMENTO DO DIA: R$ " + total);
        System.out.println("FINALIZADOS: " + repo.getTicketsFinalizados().size());
    }
}
