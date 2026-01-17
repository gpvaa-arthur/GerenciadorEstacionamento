package model;

public abstract class Vaga {
    protected int totalVagas;
    protected TiposEnum[] vagas;

    //construtor que recebe a quantidade total de vagas
    public Vaga(int totalVagas) {
        this.totalVagas = totalVagas;
        this.vagas = new TiposEnum[totalVagas];
    }

    // detecta se todas as vagas estão ocupadas
    public boolean estaCheia() {
        for (TiposEnum vaga : vagas) {
            if (vaga == null) {
                return false;
            }
        }
        return true;
    }

    //detecta se uma vaga específica está ocupada
    public boolean vagaOcupada(int numeroVaga) {
        validarNumeroVaga(numeroVaga);
        return vagas[numeroVaga] != null;
    }

    //ocupa a vaga do tipo correto de veículo
    public void ocuparVaga(int numeroVaga) {
        validarNumeroVaga(numeroVaga);

        if (vagaOcupada(numeroVaga)) {
            System.out.println("A vaga já está ocupada");
        } else {
            vagas[numeroVaga] = getTipoPermitido();
        }
    }

    // Libera uma vaga
    public void liberarVaga(int numeroVaga) {
        validarNumeroVaga(numeroVaga);

        if (!vagaOcupada(numeroVaga)) {
            System.out.println("A vaga já está livre");
        } else {
            vagas[numeroVaga] = null;
        }
    }

    //retorna o tipo de veículo ocupando a vaga
    public TiposEnum tipoVeiculoNaVaga(int numeroVaga) {
        validarNumeroVaga(numeroVaga);
        return vagas[numeroVaga];
    }

    //Mostra o estado de todas as vagas desse tipo
    public void show() {
        System.out.println("=== " + getDescricao() + " ===");

        for (int i = 0; i < totalVagas; i++) {
            if (vagas == null) {
                System.out.println("Vaga" + i + ": Livre");
            } else {
                System.out.println("Vaga" + i + ": Ocupada por" + vagas[i]);
            }
        }
        System.out.println(); //Linha em branco para organização
    }

    //verifica se o número da vaga é válido
    protected void validarNumeroVaga(int numeroVaga) {
        if (numeroVaga < 0 || numeroVaga >= totalVagas) {
            throw new IllegalArgumentException("Número da vaga é inválido");
        }
    }

    //Serve para indicar que cada classe filha escolhe qual é o tipo de veículo permitido
    protected abstract TiposEnum getTipoPermitido();

    //Retorna o nome do tipo de vaga
    public abstract String getDescricao();

    public int getTotalVagas() {
        return this.totalVagas;
    }
}
