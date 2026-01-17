package service;

import model.Veiculo;

import java.time.Duration;
import java.time.LocalDateTime;

public class CalculoTarifaService {

    public double calcularValor(
            Veiculo veiculo,
            LocalDateTime entrada,
            LocalDateTime saida
    ) {

        if (saida.isBefore(entrada)) {
            throw new IllegalArgumentException(
                    "Horário de saída não pode ser anterior ao de entrada"
            );
        }

        long minutos = Duration.between(entrada, saida).toMinutes();

        return minutos * veiculo.getTarifaPorMinuto();
    }
}
