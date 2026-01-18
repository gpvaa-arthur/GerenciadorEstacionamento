package main;

import exceptions.EstacionamentoLotadoException;
import exceptions.PlacaInvalidaException;
import exceptions.TipoVeiculoInvalidoException;
import exceptions.VeiculoJaEstacionadoException;
import repository.TicketRepository;
import service.EntradaService;
import service.FormatHorarioService;
import service.SaidaService;

import java.time.LocalDateTime;

public class MainTeste {

    public static void main(String[] args) {

        TicketRepository repository = new TicketRepository();

        EntradaService entradaService = new EntradaService(repository);
        FormatHorarioService horarioService = new FormatHorarioService();
        SaidaService saidaService = new SaidaService(repository);

        // ================= CARRO =================
        LocalDateTime horarioEntrada1 = horarioService.criarHorario(10, 22, 30);
        try {
            entradaService.registrarEntrada(
                    "CAR1234",
                    "CARRO",
                    horarioEntrada1

            );
        } catch (EstacionamentoLotadoException e) {
            throw new RuntimeException(e);
        } catch (VeiculoJaEstacionadoException e) {
            throw new RuntimeException(e);
        } catch (TipoVeiculoInvalidoException e) {
            throw new RuntimeException(e);
        } catch (PlacaInvalidaException e) {
            throw new RuntimeException(e);
        }
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
        try {
            entradaService.registrarEntrada(
                    "MOT5678",
                    "MOTO",
                    horarioEntrada2
            );
        } catch (EstacionamentoLotadoException e) {
            throw new RuntimeException(e);
        } catch (VeiculoJaEstacionadoException e) {
            throw new RuntimeException(e);
        } catch (TipoVeiculoInvalidoException e) {
            throw new RuntimeException(e);
        } catch (PlacaInvalidaException e) {
            throw new RuntimeException(e);
        }

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
        try {
            entradaService.registrarEntrada(
                    "ONI9999",
                    "ONIBUS",
                    horarioEntrada3
            );
        } catch (EstacionamentoLotadoException e) {
            throw new RuntimeException(e);
        } catch (VeiculoJaEstacionadoException e) {
            throw new RuntimeException(e);
        } catch (TipoVeiculoInvalidoException e) {
            throw new RuntimeException(e);
        } catch (PlacaInvalidaException e) {
            throw new RuntimeException(e);
        }
        LocalDateTime horarioSaida3 = horarioService.criarHorario(11,1,30);
        double valorOnibus = saidaService.registrarSaida(
                "ONI9999",
                horarioSaida3
        );

        System.out.println(
                "Valor a pagar pelo ÔNIBUS (placa ONI9999): R$ " + valorOnibus
        );
    }
}
