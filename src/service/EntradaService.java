package service;

import exceptions.EstacionamentoLotadoException;
import exceptions.PlacaInvalidaException;
import exceptions.TipoVeiculoInvalidoException;
import exceptions.VeiculoJaEstacionadoException;
import model.*;
import repository.TicketRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EntradaService {

    private TicketRepository repository;



    private VagaCarro VagasCarro;
    private VagaMoto VagasMoto;
    private VagaOnibus VagasOnibus;

    private static final String PADRAO_PLACA_MERCOSUL = "^[A-Z]{3}[0-9][A-Z][0-9]{2}$";


    public EntradaService(TicketRepository repository, VagaCarro VagasCarro, VagaMoto VagasMoto,  VagaOnibus VagasOnibus) {
        this.repository = repository;
        this.VagasCarro = VagasCarro;
        this.VagasMoto = VagasMoto;
        this.VagasOnibus = VagasOnibus;
    }

    public void registrarEntrada(
            String placa,
            String tipoVeiculo,
            LocalDateTime horarioEntrada
    )throws EstacionamentoLotadoException, VeiculoJaEstacionadoException, TipoVeiculoInvalidoException, PlacaInvalidaException {

        // Validação do formato da placa
        if (!isPlacaValida(placa)) {
            throw new PlacaInvalidaException(placa);
        }

        int ocupados = repository.getQuantidadePorTipo(tipoVeiculo);
        // Verifica se tem vaga para o tipo de veículo
        if (tipoVeiculo.equalsIgnoreCase("CARRO") && ocupados >= VagasCarro.getTotalVagas()) {
            throw new EstacionamentoLotadoException();
        } else if (tipoVeiculo.equalsIgnoreCase("MOTO") && ocupados >= VagasMoto.getTotalVagas()) {
            throw new EstacionamentoLotadoException();
        } else if (tipoVeiculo.equalsIgnoreCase("ONIBUS") && ocupados >= VagasOnibus.getTotalVagas()) {
            throw new EstacionamentoLotadoException();
        }

        // Verifica se ja existe ticket ativo pra placa
        if (repository.getTicketAtivo(placa) != null) {
            throw new VeiculoJaEstacionadoException(placa);
        }

        //  Cria o veiculo
        Veiculo veiculo = criarVeiculo(placa, tipoVeiculo);

        // Cria o ticket
        Ticket ticket = new Ticket(veiculo, horarioEntrada);

        // Salva como ticket ativo
        repository.addTicketAtivo(placa, ticket);
    }

    // Metodo auxiliar para criar o veiculo correto
    private Veiculo criarVeiculo(String placa, String tipoVeiculo) throws  TipoVeiculoInvalidoException {

        switch (tipoVeiculo.toUpperCase()) {
            case "CARRO":
                return new Carro(placa);

            case "MOTO":
                return new Moto(placa);

            case "ONIBUS":
                return new Onibus(placa);

            default:
                throw new TipoVeiculoInvalidoException(tipoVeiculo);
        }
    }
    // Metodo auxiliar para verificar se a placa do veículo está no padrão do Mercosul/Brasil (Ex: ABC1D23)
    private boolean isPlacaValida(String placa) {
        return placa != null && placa.toUpperCase().matches(PADRAO_PLACA_MERCOSUL);
    }
}
