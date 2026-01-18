package view;

import model.VagasEnum;

import javax.swing.*;
import java.awt.*;

public class PanelDisponibilidade {

    //Contadores de vagas
    private int vagasCarro = VagasEnum.MAX_VAGAS_CARRO.getVagasMaximas();
    private int vagasMoto = VagasEnum.MAX_VAGAS_MOTO.getVagasMaximas();
    private int vagasOnibus = VagasEnum.MAX_VAGAS_ONIBUS.getVagasMaximas();

    private JLabel labelCarro = new JLabel("Carros: " + vagasCarro);
    private JLabel labelMoto = new JLabel("Motos: " + vagasMoto);
    private JLabel labelOnibus = new JLabel("Ônibus: " + vagasOnibus);

    private JPanel panelDisponibilidade;

    public void criarPainelDisponibilidade() {
        JPanel painelVagas = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelVagas.setBorder(BorderFactory.createTitledBorder("Disponibilidade de Vagas"));

        // Estilização básica para ficar visualmente distinto do formulário
        labelCarro.setForeground(new Color(0, 100, 0)); // Verde escuro
        labelMoto.setForeground(new Color(0, 100, 0));
        labelOnibus.setForeground(new Color(0, 100, 0));

        // Adicionando ao painel
        painelVagas.add(labelCarro);
        painelVagas.add(labelMoto);
        painelVagas.add(labelOnibus);

        panelDisponibilidade = painelVagas;
    }
        public void entradaPorTipo(String tipo){
            switch(tipo){
                case "CARRO":
                    vagasCarro -= 1;
                    labelCarro.setText("Carros: " + vagasCarro);
                    break;
                case "MOTO":
                    vagasMoto -= 1;
                    labelMoto.setText("Motos: " + vagasMoto);
                case "ONIBUS":
                    vagasOnibus -= 1;
                    labelOnibus.setText("Ônibus: " + vagasOnibus);
            }
        }
        public void saidaPorTipo(String tipo){
            switch(tipo){
                case "CARRO":
                    vagasCarro += 1;
                    labelCarro.setText("Carros: " + vagasCarro);
                    break;
                case "MOTO":
                    vagasMoto += 1;
                    labelMoto.setText("Motos: " + vagasMoto);
                case "ONIBUS":
                    vagasOnibus += 1;
                    labelOnibus.setText("Ônibus: " + vagasOnibus);
            }
        }

    public JPanel getPanelDisponibilidade() {
        return panelDisponibilidade;
    }
}
