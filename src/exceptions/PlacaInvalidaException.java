package exceptions;
// Para placas fora do padrão ou nulas
public class PlacaInvalidaException extends EstacionamentoException {
    public PlacaInvalidaException(String placa) {
        super("A placa '" + placa + "' é inválida.");
    }
}

