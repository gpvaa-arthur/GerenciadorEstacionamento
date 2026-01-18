package model;

public enum VagasEnum {
    MAX_VAGAS_CARRO(30),
    MAX_VAGAS_MOTO(20),
    MAX_VAGAS_ONIBUS(5);

    private final int vagasMaximas;

    VagasEnum(int vagasMaximas) {
        this.vagasMaximas = vagasMaximas;
    }

    public int getVagasMaximas() {
        return vagasMaximas;
    }
}
