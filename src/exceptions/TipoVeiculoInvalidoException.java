package exceptions;
// Exceção lançada quando o tipo de veículo não for carro, moto ou ônibus
public class TipoVeiculoInvalidoException extends EstacionamentoException {
    public TipoVeiculoInvalidoException(String tipo) {
        super("O tipo de veículo '" + tipo + "' é inválido. Aceitamos apenas carros, motos e ônibus");
    }
}
