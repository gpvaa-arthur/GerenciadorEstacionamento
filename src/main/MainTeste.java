package main;

import repository.TicketRepository;
import service.EntradaService;
import service.FormatHorarioService;
import service.SaidaService;
import service.FaturamentoService;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.YearMonth;


public class MainTeste {

    public static void main(String[] args) {

        TicketRepository repository = new TicketRepository();

        EntradaService entradaService = new EntradaService(repository);
        FormatHorarioService horarioService = new FormatHorarioService();
        SaidaService saidaService = new SaidaService(repository);

        // ================= CARRO =================
        LocalDateTime horarioEntrada1 = horarioService.criarHorario(10, 22, 30);
        entradaService.registrarEntrada(
                "CAR1234",
                "CARRO",
                horarioEntrada1

        );
        LocalDateTime horarioSaida1 = horarioService.criarHorario(11,1,30);
        double valorCarro = saidaService.registrarSaida(
                "CAR1234",
                horarioSaida1
        );

        System.out.println(
                "Valor a pagar pelo CARRO (placa CAR1234): R$ " + valorCarro
        );

        // ================= MOTO =================
        LocalDateTime horarioEntrada2 = horarioService.criarHorario(10, 22, 30);
        entradaService.registrarEntrada(
                "MOT5678",
                "MOTO",
                horarioEntrada2
        );

        LocalDateTime horarioSaida2 = horarioService.criarHorario(11,1,30);
        double valorMoto = saidaService.registrarSaida(
                "MOT5678",
                horarioSaida2
        );

        System.out.println(
                "Valor a pagar pela MOTO (placa MOT5678): R$ " + valorMoto
        );

        // ================= ÔNIBUS =================
        LocalDateTime horarioEntrada3 = horarioService.criarHorario(10, 22, 30);
        entradaService.registrarEntrada(
                "ONI9999",
                "ONIBUS",
                horarioEntrada3
        );
        LocalDateTime horarioSaida3 = horarioService.criarHorario(11,1,30);
        double valorOnibus = saidaService.registrarSaida(
                "ONI9999",
                horarioSaida3
        );

        System.out.println(
                "Valor a pagar pelo ÔNIBUS (placa ONI9999): R$ " + valorOnibus
        );

        FaturamentoService faturamento = new FaturamentoService(repository);

        double hoje = faturamento.calcularFaturamentoDiario(LocalDate.of(2025, 6, 11));
        System.out.println("Faturamento do dia: R$ " + hoje);

        double mes = faturamento.calcularFaturamentoMensal(YearMonth.of(2025, 6));
        System.out.println("Faturamento do mês: R$ " + mes);

    }
}
