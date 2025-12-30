package view.util;

import view.IDEnum;

import javax.swing.*;
import java.awt.*;

public class Navegador {
    private CardLayout cards;
    private JPanel cardsPanel;
    private IDEnum idAtual = IDEnum.ENTRADA;

    public Navegador(CardLayout cards, JPanel cardsPanel) {
        this.cards = cards;
        this.cardsPanel = cardsPanel;
    }

    public void irPara(IDEnum ID){
        cards.show(cardsPanel, ID.toString());
    }

    public IDEnum getIdAtual() {
        return idAtual;
    }

    public void setIdAtual(IDEnum idAtual) {
        this.idAtual = idAtual;
    }
}
