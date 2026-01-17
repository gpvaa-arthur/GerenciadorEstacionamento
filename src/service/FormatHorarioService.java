package service;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FormatHorarioService {
    public LocalDateTime criarHorario(int dia, int horas, int minutos){
        LocalDateTime horario = LocalDateTime.of(
                LocalDate.now().getYear(),
                LocalDate.now().getMonth(),
                dia, horas, minutos
        );
        return horario;
    }
}
