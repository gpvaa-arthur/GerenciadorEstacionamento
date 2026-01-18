package model;

public class VagaMoto extends Vaga {

    public VagaMoto() {
        super(VagasEnum.MAX_VAGAS_MOTO.getVagasMaximas());
    }

    @Override
    protected TiposEnum getTipoPermitido() {
        return TiposEnum.MOTO;
    }

    @Override
    public String getDescricao() {
        return "Vagas de Moto";
    }
}
