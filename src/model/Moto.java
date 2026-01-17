package model;

public class Moto extends Veiculo {

    // Tarifa mais barata por minuto
    private static final double TARIFA_POR_MINUTO = 0.10;

    public Moto(String placa) {
        super(placa);
    }

    @Override
    public double getTarifaPorMinuto() {
        return TARIFA_POR_MINUTO;
    }

    @Override
    public String getTipo() {
        return "MOTO";
    }
}
