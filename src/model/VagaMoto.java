package model;

public class VagaMoto extends Vaga {

    public VagaMoto() {
        super(20);
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
