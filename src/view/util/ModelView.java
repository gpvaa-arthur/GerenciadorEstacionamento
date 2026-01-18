package view.util;

import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;

public class ModelView {
    //Formatador de Data para registro no Histórico
    private final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    //Modelo de tabela para Histórico
    private static DefaultTableModel modelo;

    public void configModelo() {
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Nenhuma célula editável
            }
        };
        modelo.addColumn("Veículo");
        modelo.addColumn("Placa");
        modelo.addColumn("Entrada");
        modelo.addColumn("Saída");
        modelo.addColumn("Preço");
    }

    public static DefaultTableModel getModelo() {
        return modelo;
    }

    public DateTimeFormatter getFORMATTER() {
        return FORMATTER;
    }
}
