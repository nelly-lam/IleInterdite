package controllers;

import model.Island;
import views.ViewGame;
import views.ViewIsland;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControllerAction extends MouseAdapter {
    private final Island model;
    private String action;

    public ControllerAction(Island model) {
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel label = (JLabel) e.getComponent();

        if (label.getText().equals("Dry")) {
            this.action = "Dry";
            ViewGame.updateDisplay("Assécher une case : veuillez choisir une case");
        } else if (label.getText().equals("Helicopter")) {
            this.action = "Helicopter";
            ViewGame.updateDisplay("Téléportation : veuillez choisir une case");
        } else if (this.action.equals("Dry")) {
            this.model.sandBag(label.getX() / ViewIsland.SIZE, label.getY() / ViewIsland.SIZE);
            this.action = "";
        } else if (this.action.equals("Helicopter")) {
            this.model.teleportation(label.getX() / ViewIsland.SIZE, label.getY() / ViewIsland.SIZE, SwingUtilities.isLeftMouseButton(e));
            this.action = "";
        } else {
            ViewGame.updateDisplay("Vous n'avez pas sélectionné d'actions");
        }
    }
}
