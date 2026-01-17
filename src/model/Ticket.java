package model;

import java.time.LocalDateTime;

public class Ticket {

    private Veiculo veiculo;
    private LocalDateTime horarioEntrada;
    private LocalDateTime horarioSaida;
    private double valorCobrado;

    public Ticket(Veiculo veiculo, LocalDateTime horarioEntrada) {
        this.veiculo = veiculo;
        this.horarioEntrada = horarioEntrada;
    }

    // GETTERS

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDateTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public LocalDateTime getHorarioSaida() {
        return horarioSaida;
    }

    public double getValorCobrado() {
        return valorCobrado;
    }

    // SETTERS

    public void setHorarioSaida(LocalDateTime horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public void setValorCobrado(double valorCobrado) {
        this.valorCobrado = valorCobrado;
    }
}
