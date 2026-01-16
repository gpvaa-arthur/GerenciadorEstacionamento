package model;

public class VagaCarro extends Vaga {

    public VagaCarro() {
        super(30);
    }


    @Override
    protected TiposEnum getTipoPermitido() {
        return TiposEnum.CARRO;
    }

    @Override
    public String getDescricao() {
        return "Vagas de Carro";
    }
}
