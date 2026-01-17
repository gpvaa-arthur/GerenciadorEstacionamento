package model;

public class Onibus extends Veiculo {

    // Tarifa mais cara por minuto
    private static final double TARIFA_POR_MINUTO = 0.35;

    public Onibus(String placa) {
        super(placa);
    }

    @Override
    public double getTarifaPorMinuto() {
        return TARIFA_POR_MINUTO;
    }

    @Override
    public String getTipo() {
        return "ONIBUS";
    }
}
