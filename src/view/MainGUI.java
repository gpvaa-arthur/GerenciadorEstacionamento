package view;

import view.util.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//Classe responsável pela configuração da tela principal

public class MainGUI {
    public static void main(String[] args) {
        JFrame janela = new JFrame();
        JPanel mainPanel = new JPanel(new GridBagLayout());
        janela.setContentPane(mainPanel);
        janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        GridBagConstraints gbc = new GridBagConstraints();

        //-------------- Painel cinza esquerdo -----------------//
        JPanel panelCinza = new JPanel(new GridBagLayout());
        panelCinza.setMinimumSize(new Dimension(150, 0));
        panelCinza.setPreferredSize(new Dimension(150, 300));
        panelCinza.setBackground(new Color(200,200,200));

        AuxLayout.setup(gbc, 0,0,1,1,0.0,0.0);
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(panelCinza, gbc);

        //-------------- Card Layout direito -----------------//

        CardLayout cards = new CardLayout();
        JPanel cardsPanel = new JPanel(cards);
        Navegador navegador = new Navegador(cards, cardsPanel);

        List<ITela> listaTela = List.of(
                new TelaEntrada(),
                new TelaSaida(),
                new TelaOcupacao(),
                new TelaHistorico()
        );

        for (ITela iTela : listaTela) {
            iTela.configurar(navegador);
            cardsPanel.add(iTela.getPanel(), iTela.getID().toString());
        }

        AuxLayout.reset(gbc);
        AuxLayout.setup(gbc, 1,0,1,1, 1.0,1.0);
        mainPanel.add(cardsPanel, gbc);

        //-------- (Panel Cinza). Botão Entrada -----------//

        AuxLayout.reset(gbc);
        JButton entrada = new JButton("Entrada");

        entrada.addActionListener(e ->{
            if(!(navegador.getIdAtual().equals(IDEnum.ENTRADA))){
                navegador.setIdAtual(IDEnum.ENTRADA);
                navegador.irPara(IDEnum.ENTRADA);
            }
        });

        AuxLayout.setup(gbc, 0,0,1,1,0.0,0.0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panelCinza.add(entrada, gbc);

        //-------- (Panel Cinza). Botão Saida -----------//
        JButton saida = new JButton("Saida");

        saida.addActionListener(e ->{
            if(!(navegador.getIdAtual().equals(IDEnum.SAIDA))){
                navegador.setIdAtual(IDEnum.SAIDA);
                navegador.irPara(IDEnum.SAIDA);
            }
        });

        AuxLayout.setup(gbc, 0,1,1,1,0.0,0.0);
        gbc.insets = new Insets(10,0,0,0);
        panelCinza.add(saida, gbc);

        //-------- (Panel Cinza). Botão Ocupação -----------//
        JButton ocupacao = new JButton("Disponibilidade");


        ocupacao.addActionListener(e ->{
            if(!(navegador.getIdAtual().equals(IDEnum.OCUPACAO))){
                navegador.setIdAtual(IDEnum.OCUPACAO);
                navegador.irPara(IDEnum.OCUPACAO);
            }
        });

        AuxLayout.setup(gbc, 0,2,1,1,0.0,0.0);
        panelCinza.add(ocupacao, gbc);

        //-------- (Panel Cinza). Botão Histórico -----------//
        JButton historico = new JButton("Histórico");


        historico.addActionListener(e ->{
            if(!(navegador.getIdAtual().equals(IDEnum.HISTORICO))){
                navegador.setIdAtual(IDEnum.HISTORICO);
                navegador.irPara(IDEnum.HISTORICO);
            }
        });

        AuxLayout.setup(gbc, 0,3,1,1,0.0,0.0);
        panelCinza.add(historico, gbc);

        //-------------- Configurações finais -----------------//
        janela.setTitle("Gerenciador de Estacionamento");
        janela.setSize(500,400);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
    }
}
