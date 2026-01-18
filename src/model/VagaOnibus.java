package model;

public class VagaOnibus extends Vaga {

    public VagaOnibus() {
        super(VagasEnum.MAX_VAGAS_ONIBUS.getVagasMaximas());
    }

    @Override
    protected TiposEnum getTipoPermitido() {
        return TiposEnum.ONIBUS;
    }

    @Override
    public String getDescricao() {
        return "Vagas de Ônibus";
    }
}
