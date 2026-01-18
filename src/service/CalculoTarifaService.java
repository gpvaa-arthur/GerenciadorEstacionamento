package service;

import exceptions.HorarioInvalidoException;
import model.Veiculo;

import java.time.Duration;
import java.time.LocalDateTime;

public class CalculoTarifaService {

    public double calcularValor(
            Veiculo veiculo,
            LocalDateTime entrada,
            LocalDateTime saida
    ) throws HorarioInvalidoException {

        if (saida.isBefore(entrada)) {
            throw new HorarioInvalidoException(
                    "Horário de saída não pode ser anterior ao de entrada"
            );
        }

        long minutos = Duration.between(entrada, saida).toMinutes();

        return minutos * veiculo.getTarifaPorMinuto();
    }
}
