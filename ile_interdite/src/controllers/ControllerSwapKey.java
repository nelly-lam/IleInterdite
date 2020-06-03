package controllers;

import model.Cell;
import model.Island;
import model.Player;
import views.ViewGame;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControllerSwapKey extends MouseAdapter {
    private final Island model;
    private Cell.Element el;

    public ControllerSwapKey(Island model){
        this.model = model;
        this.el = Cell.Element.NONE;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel label = (JLabel) e.getComponent();
        String text = label.getText();

        switch (text) {
            case "A":
                this.el = Cell.Element.AIR;
                ViewGame.updateDisplay("Echange de la clé d'AIR : Veuillez choisir un joueur");
                break;
            case "W":
                this.el = Cell.Element.WATER;
                ViewGame.updateDisplay("Echange de la clé d'EAU : Veuillez choisir un joueur");
                break;
            case "F":
                this.el = Cell.Element.FIRE;
                ViewGame.updateDisplay("Echange de la clé de FEU : Veuillez choisir un joueur");
                break;
            case "E":
                this.el = Cell.Element.EARTH;
                ViewGame.updateDisplay("Echange de la clé de TERRE : Veuillez choisir un joueur");
                break;
            default:
                break;
        }

        for(Player p : this.model.getPlayers()) {
            if (p.getName().equals(text)) {
                if (this.model.getCurrentPlayer().isOnSameCell(p) && this.model.giveKey(p, this.el)) {
                    this.el = Cell.Element.NONE;
                } else {
                    ViewGame.updateDisplay("Vous n'êtes pas sur la même cellule");
                }
            }
        }
    }
}