package model;

public class VagaOnibus extends Vaga {

    public VagaOnibus() {
        super(5);
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
