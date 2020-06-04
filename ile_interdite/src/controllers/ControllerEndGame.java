package controllers;

import model.Island;
import model.Player;
import views.ViewEndGame;
import views.ViewMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerEndGame implements ActionListener {
    private final Island model;
    private static JFrame frameGame;

    public ControllerEndGame(Island model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Rejouer")) {
            ViewEndGame.hidden();
            frameGame.dispose();
            Island newIsland = new Island(12, 12);
            for (Player p : this.model.getPlayers()) {
                newIsland.addPlayer(p.getName(), p.getRole());
            }
            ViewMenu.restorePlayers();
            new ViewMenu(newIsland);
        } else if (e.getActionCommand().equals("Quitter")) {
            System.exit(0);
        }
    }

    public static void updateGame(JFrame game) {
        frameGame = game;
    }
}