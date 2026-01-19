package service;

import view.util.ModelView;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FormatService {

    // USADO PELAS TELAS (dia, hora, minuto)
    public LocalDateTime criarHorario(int dia, int horas, int minutos) {
        return LocalDateTime.of(
                LocalDate.now().getYear(),
                LocalDate.now().getMonth(),
                dia,
                horas,
                minutos
        );
    }

    // USADO PARA TESTES / MAIN / CASOS FIXOS
    public LocalDateTime criarHorario(
            int ano, int mes, int dia, int horas, int minutos
    ) {
        return LocalDateTime.of(ano, mes, dia, horas, minutos);
    }

    // USADO PELA TABELA (VIEW)
    public String formatHorario(LocalDateTime horario) {
        ModelView modelView = new ModelView();
        return horario.format(modelView.getFORMATTER());
    }

    public static String formatValor(double valorTotal){
        return String.format("%.2f", valorTotal);
    }
}
