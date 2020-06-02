package controllers;

import model.Island;
import model.Player;
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

        if(label.getText().equals("Dry")) {
            this.action = "Dry";
            ViewGame.updateDisplay("Assécher une case : veuillez choisir une case");
        } else if(label.getText().equals("Helicopter")) {
            this.action = "Helicopter";
            ViewGame.updateDisplay("Téléportation : veuillez choisir une case");
        } else if(this.action.equals("Dry")) {
            if(this.model.currentPlayer.hasAction(Player.SpecialAction.SAND)) {
                this.model.dry(label.getX() / ViewIsland.SIZE, label.getY() / ViewIsland.SIZE);
                this.model.currentPlayer.updateAction(Player.SpecialAction.SAND);
                ViewGame.updateDisplay("Assèchement effectué");
                this.action = "";
            } else {
                ViewGame.updateDisplay("Vous ne possédez pas cette action");
            }
        } else if(this.action.equals("Helicopter")) {
            if(this.model.currentPlayer.hasAction(Player.SpecialAction.TELEPORTATION)) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    this.model.teleportation(this.model.currentPlayer, label.getX() / ViewIsland.SIZE, label.getY() / ViewIsland.SIZE);
                } else if(SwingUtilities.isRightMouseButton(e)) {
                    for(Player p : this.model.players) {
                        if(this.model.currentPlayer.isOnSameCell(p)) {
                            this.model.teleportation(p, label.getX() / ViewIsland.SIZE, label.getY() / ViewIsland.SIZE);
                        }
                    }
                }
                this.model.currentPlayer.updateAction(Player.SpecialAction.TELEPORTATION);
                ViewGame.updateDisplay("Téléportation effectuée");
                this.action = "";
            } else {
                ViewGame.updateDisplay("Vous ne possédez pas cette action");
            }
        } else {
            ViewGame.updateDisplay("Vous n'avez pas sélectionné d'actions");
        }
        this.model.notifyObservers();
    }
}
