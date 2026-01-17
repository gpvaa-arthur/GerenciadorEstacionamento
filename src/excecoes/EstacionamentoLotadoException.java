// Para quando não tiver mais vagas no estacionamento
package excecoes;

public class EstacionamentoLotadoException extends Exception {
    public EstacionamentoLotadoException(String mensagem) {
        super(mensagem);
    }
}

