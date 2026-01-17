package exceptions;

// para caso não exista mais vagas no estacionamento
public class EstacionamentoLotadoException extends EstacionamentoException {
    public EstacionamentoLotadoException() {
        super("Não foi possível estacionar: não há vagas disponíveis no momento.");
    }
}
