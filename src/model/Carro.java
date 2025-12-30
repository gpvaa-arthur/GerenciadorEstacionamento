package model;

public class Carro extends Veiculo{

    public Carro(String placa){
        super(placa);
    }

    @Override
    public String getPlaca() {
        return "";
    }

    @Override
    public String getTipo() {
        return "";
    }

    @Override
    public double getTarifaHora() {
        return 0;
    }

    @Override
    public double getTarifaDia() {
        return 0;
    }
}
