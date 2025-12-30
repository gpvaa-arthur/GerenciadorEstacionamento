package model;

public abstract class Veiculo {
    private String placa;

    public Veiculo(String placa){
        this.placa = placa;
    }

    public String getPlaca(){
        return this.placa;
    }
    public abstract String getTipo();
    public abstract double getTarifaHora();
    public abstract double getTarifaDia();
}
