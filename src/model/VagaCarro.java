package model;

public class VagaCarro extends Vaga {

    public VagaCarro() {
        super(VagasEnum.MAX_VAGAS_CARRO.getVagasMaximas());
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
