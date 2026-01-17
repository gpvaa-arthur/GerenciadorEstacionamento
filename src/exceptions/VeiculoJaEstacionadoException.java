package exceptions;
// Para impedir entrada do mesmo veículo
public class VeiculoJaEstacionadoException extends EstacionamentoException {
    public VeiculoJaEstacionadoException(String placa) {
        super("O veículo com placa: " + placa + " já está estacionado.");
    }
}
