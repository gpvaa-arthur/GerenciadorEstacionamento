package service;

import model.Carro;
import model.Moto;
import model.Onibus;
import model.Ticket;
import model.Veiculo;
import repository.TicketRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EntradaService {

    private TicketRepository repository;



    public EntradaService(TicketRepository repository) {
        this.repository = repository;
    }

    public void registrarEntrada(
            String placa,
            String tipoVeiculo,
            LocalDateTime horarioEntrada
    ) {

        // Verifica se ja existe ticket ativo pra placa
        if (repository.getTicketAtivo(placa) != null) {
            throw new IllegalArgumentException(
                    "Veículo já está estacionado"
            );
        }

        //  Cria o veiculo
        Veiculo veiculo = criarVeiculo(placa, tipoVeiculo);

        // Cria o ticket
        Ticket ticket = new Ticket(veiculo, horarioEntrada);

        // Salva como ticket ativo
        repository.addTicketAtivo(placa, ticket);
    }

    // Metodo auxiliar para criar o veiculo correto
    private Veiculo criarVeiculo(String placa, String tipoVeiculo) {

        switch (tipoVeiculo.toUpperCase()) {
            case "CARRO":
                return new Carro(placa);

            case "MOTO":
                return new Moto(placa);

            case "ONIBUS":
                return new Onibus(placa);

            default:
                throw new IllegalArgumentException(
                        "Tipo de veículo inválido"
                );
        }
    }
}
