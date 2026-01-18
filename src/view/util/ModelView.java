package view.util;

import javax.swing.table.DefaultTableModel;
import java.time.format.DateTimeFormatter;

public class ModelView {
    //Formatador de Data para registro no Histórico
    private final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    //Modelo de tabela para Histórico
    private static DefaultTableModel modeloHistorico;
    private static DefaultTableModel modeloDia;
    private static DefaultTableModel modeloMes;


    public void configModelHistorico() {
        modeloHistorico = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Nenhuma célula editável
            }
        };
        modeloHistorico.addColumn("Veículo");
        modeloHistorico.addColumn("Placa");
        modeloHistorico.addColumn("Entrada");
        modeloHistorico.addColumn("Saída");
        modeloHistorico.addColumn("Preço");
    }
    public void configModelDia(){
        modeloDia = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Nenhuma célula editável
            }
        };
        modeloDia.addColumn("Data");
        modeloDia.addColumn("Faturamento Diário");
    }

    public static DefaultTableModel getModeloHistorico() {
        return modeloHistorico;
    }


    public DateTimeFormatter getFORMATTER() {
        return FORMATTER;
    }
}
