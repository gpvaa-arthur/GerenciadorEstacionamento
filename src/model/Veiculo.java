package model;

public abstract class Veiculo {

    private String placa;

    public Veiculo(String placa) {
        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("Placa não pode ser nula ou vazia");
        }
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    public abstract double getTarifaPorMinuto();

    public abstract String getTipo();
}
