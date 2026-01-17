package main;

import repository.TicketRepository;
import service.EntradaService;
import service.SaidaService;

public class MainTeste {

    public static void main(String[] args) {

        TicketRepository repository = new TicketRepository();

        EntradaService entradaService = new EntradaService(repository);
        SaidaService saidaService = new SaidaService(repository);

        // ================= CARRO =================
        entradaService.registrarEntrada(
                "CAR1234",
                "CARRO",
                "10/06/2025 22:30"
        );

        double valorCarro = saidaService.registrarSaida(
                "CAR1234",
                "11/06/2025 01:00"
        );

        System.out.println(
                "Valor a pagar pelo CARRO (placa CAR1234): R$ " + valorCarro
        );

        // ================= MOTO =================
        entradaService.registrarEntrada(
                "MOT5678",
                "MOTO",
                "10/06/2025 22:30"
        );

        double valorMoto = saidaService.registrarSaida(
                "MOT5678",
                "11/06/2025 01:00"
        );

        System.out.println(
                "Valor a pagar pela MOTO (placa MOT5678): R$ " + valorMoto
        );

        // ================= ÔNIBUS =================
        entradaService.registrarEntrada(
                "ONI9999",
                "ONIBUS",
                "10/06/2025 22:30"
        );

        double valorOnibus = saidaService.registrarSaida(
                "ONI9999",
                "11/06/2025 01:00"
        );

        System.out.println(
                "Valor a pagar pelo ÔNIBUS (placa ONI9999): R$ " + valorOnibus
        );
    }
}
