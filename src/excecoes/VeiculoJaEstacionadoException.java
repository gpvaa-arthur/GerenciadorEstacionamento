// se houver uma tentativa de entrada de um veículo (mesma placa) que o sistema indica que já está dentro do estacionamento
package excecoes;

public class VeiculoJaEstacionadoException extends RuntimeException {
    public VeiculoJaEstacionadoException(String message) {
        super(message);
    }
}

