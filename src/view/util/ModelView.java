package view.util;

import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;

public class ModelView {
    //Formatador de Data para registro no Histórico
    private final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    //Modelos de tabelas para Histórico
    private static DefaultTableModel modeloFinalizado;




    public void configModelFinalizado() {
        modeloFinalizado = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Nenhuma célula editável
            }
        };
        modeloFinalizado.addColumn("Veículo");
        modeloFinalizado.addColumn("Placa");
        modeloFinalizado.addColumn("Entrada");
        modeloFinalizado.addColumn("Saída");
        modeloFinalizado.addColumn("Preço");
    }

    public DefaultTableModel configModelAtivo() {
        DefaultTableModel modeloAtivo;
        modeloAtivo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Nenhuma célula editável
            }
        };
        modeloAtivo.addColumn("Veículo");
        modeloAtivo.addColumn("Placa");
        modeloAtivo.addColumn("Entrada");
        return modeloAtivo;
    }


    public static DefaultTableModel getModeloFinalizado() {
        return modeloFinalizado;
    }


    public DateTimeFormatter getFORMATTER() {
        return FORMATTER;
    }
}
