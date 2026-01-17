package exceptions;
// Para erros de lógica de tempo (Saída antes da Entrada)
public class HorarioInvalidoException extends EstacionamentoException {
    public HorarioInvalidoException(String message) {
        super(message);
    }
}
