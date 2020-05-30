package controllers;

import exceptions.ExceptionNbHits;
import model.Cell;
import model.Island;
import model.Player;
import views.ViewGame;
import views.ViewItem2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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

        if(text == "A") {
            this.el = Cell.Element.AIR;
            ViewGame.updateDisplay("Echange de la clé d'AIR : Veuillez choisir un joueur");
        } else if(text == "W") {
            this.el = Cell.Element.WATER;
            ViewGame.updateDisplay("Echange de la clé d'EAU : Veuillez choisir un joueur");
        } else if(text == "F") {
            this.el = Cell.Element.FIRE;
            ViewGame.updateDisplay("Echange de la clé de FEU : Veuillez choisir un joueur");
        } else if(text == "E") {
            this.el = Cell.Element.EARTH;
            ViewGame.updateDisplay("Echange de la clé de TERRE : Veuillez choisir un joueur");
        }

        if(this.model.currentPlayer.nbKeyElement(this.el) > 0) {
            for(Player p : this.model.players) {
                if(p.getName() == text && this.model.currentPlayer.isOnSameCell2(p)) {
                    try {
                        this.model.currentPlayer.addHits();
                        p.addKey(this.el);
                        this.model.currentPlayer.updateKey(this.el);
                        this.el = Cell.Element.NONE;
                        ViewGame.updateDisplay("La clé a été transféré");
                    } catch (ExceptionNbHits exceptionNbHits) {
                        ViewGame.updateDisplay("Vous n'avez pas assez de coup");
                    }
                    break;
                }
            }
            this.model.notifyObservers();
        }
        else {
            ViewGame.updateDisplay("Vous n'avez pas de clés à donner");
        }
    }
}



