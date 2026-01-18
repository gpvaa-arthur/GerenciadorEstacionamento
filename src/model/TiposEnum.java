package model;

public enum TiposEnum {
    CARRO("CARRO"),
    MOTO("MOTO"),
    ONIBUS("ONIBUS");

    private final String tipoString;
    TiposEnum(String tipoString) {
        this.tipoString = tipoString;
    }

    public String getTipoString() {
        return tipoString;
    }
}
