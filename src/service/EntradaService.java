package service;

import exceptions.EstacionamentoLotadoException;
import exceptions.PlacaInvalidaException;
import exceptions.TipoVeiculoInvalidoException;
import exceptions.VeiculoJaEstacionadoException;
import model.*;
import repository.TicketRepository;
import view.ExceptionGUI;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EntradaService {

    private TicketRepository repository;



    private VagaCarro VagasCarro;
    private VagaMoto VagasMoto;
    private VagaOnibus VagasOnibus;

    private static final String PADRAO_PLACA_MERCOSUL = "^[A-Z]{3}[0-9][A-Z][0-9]{2}$";
    private static final String PADRAO_PLACA_ANTIGO = "^[A-Z]{3}-?[0-9]{4}$";

    public EntradaService(TicketRepository repository) {
        this.repository = repository;
    }

    public void registrarEntrada(
            String placa,
            String tipoVeiculo,
            LocalDateTime horarioEntrada
    )throws EstacionamentoLotadoException, VeiculoJaEstacionadoException, TipoVeiculoInvalidoException, PlacaInvalidaException {

        // Validação do formato da placa
        if (!isPlacaValida(placa)) {
            ExceptionGUI.exceptionGUI("Placa inválida!");
            throw new PlacaInvalidaException(placa);
        }

        int ocupados = repository.getQuantidadePorTipo(tipoVeiculo);

        // Verifica se tem vaga para o tipo de veículo
        if (tipoVeiculo.equalsIgnoreCase("CARRO") && ocupados >= VagasEnum.MAX_VAGAS_CARRO.getVagasMaximas()) {
            ExceptionGUI.exceptionGUI("Vagas de carro lotadas.");
            throw new EstacionamentoLotadoException();

        } else if (tipoVeiculo.equalsIgnoreCase("MOTO") && ocupados >= VagasEnum.MAX_VAGAS_MOTO.getVagasMaximas()) {
            ExceptionGUI.exceptionGUI("Vagas de moto lotadas.");
            throw new EstacionamentoLotadoException();

        } else if (tipoVeiculo.equalsIgnoreCase("ONIBUS") && ocupados >= VagasEnum.MAX_VAGAS_ONIBUS.getVagasMaximas()) {
            ExceptionGUI.exceptionGUI("Vagas de onibus lotadas.");
            throw new EstacionamentoLotadoException();
        }

        // Verifica se ja existe ticket ativo pra placa
        if (repository.getTicketAtivo(placa) != null) {
            ExceptionGUI.exceptionGUI("Veículo já estacionado!");
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
                ExceptionGUI.exceptionGUI("Tipo de veículo inválido!");
                throw new TipoVeiculoInvalidoException(tipoVeiculo);
        }
    }
    // Metodo auxiliar para verificar se a placa do veículo está no padrão do Mercosul/Brasil (Ex: ABC1D23)
    private boolean isPlacaValida(String placa) {
        return placa != null && (placa.toUpperCase().matches(PADRAO_PLACA_MERCOSUL)
        || placa.toUpperCase().matches(PADRAO_PLACA_ANTIGO));

    }
}
