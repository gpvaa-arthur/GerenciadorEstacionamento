package exceptions;

// para caso tente registrar a saída de um veículo que não está no estacionamento
public class VeiculoNaoEncontradoException extends EstacionamentoException {
    public VeiculoNaoEncontradoException(String placa) {
        super("O veículo com placa " + placa + " não foi encontrado no sistema.");
    }
}
