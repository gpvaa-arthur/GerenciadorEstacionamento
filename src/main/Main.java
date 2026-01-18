package main;

import repository.TicketRepository;
import view.MainGUI;

public class Main{
    public static void main(String[] args) {
         TicketRepository ticketRepository = new TicketRepository();
         MainGUI mainGUI = new MainGUI(ticketRepository);
         mainGUI.configurarGUI();

    }
}