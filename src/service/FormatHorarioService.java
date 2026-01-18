package service;

import view.util.ModelView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormatHorarioService {

    public LocalDateTime criarHorario(int dia, int horas, int minutos){
        LocalDateTime horario = LocalDateTime.of(
                LocalDate.now().getYear(),
                LocalDate.now().getMonth(),
                dia, horas, minutos
        );
        return horario;
    }
    public String formatHorario(LocalDateTime horario){
        ModelView modelView = new ModelView();
        return horario.format(modelView.getFORMATTER());
    }
}
