package controllers;

import model.Island;
import views.ViewGame;
import views.ViewMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerPlay implements ActionListener {
    private final Island model;
    private final JFrame menu;

    public ControllerPlay(Island model, JFrame menu) {
        this.model = model;
        this.menu = menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!this.model.getPlayers().isEmpty()) {
            this.menu.dispose();
            new ViewGame(this.model);
        } else {
            ViewMenu.updateLabel("Vous devez ajouter au moins 1 joueur");
        }
    }
}
