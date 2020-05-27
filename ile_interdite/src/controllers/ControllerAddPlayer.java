package controllers;

import model.Island;
import views.ViewMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerAddPlayer implements ActionListener {
    private final Island model;
    private final JTextArea name;
    private final JFrame frame;

    public ControllerAddPlayer(Island model, JTextArea name, JFrame frame) {
        this.model = model;
        this.name = name;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((this.model.players.size() < 8)) {
            this.model.addPlayer(name.getText());
            ViewMenu.addPlayer(name.getText());
        }
        else {
            System.out.println("Vous avez atteint le nombre de joueur max");
        }
        frame.dispose();
    }
}
