package controllers;

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

public class ControllerSwapKey2 extends MouseAdapter {
    private final Island model;
    private java.awt.Color Color;

    public ControllerSwapKey2(Island model){
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(this.model.currentPlayer.keys.size() != 0 && this.model.currentPlayer.isOnSameCell().size() != 0){
            JLabel label = (JLabel) e.getComponent();
            String text = label.getText();
            Cell.Element el;
            if (text == "A"){
                this.Color = Color.WHITE;
                //ViewItem2.setVisibleButton(this.Color);
                //this.model.currentPlayer.updateKey(Cell.Element.AIR);
                ViewGame.updateDisplay("Echange de clé : Veuillez choisir un joueur");
            } else if (text == "W"){
                this.Color = Color.BLUE;
                //this.model.currentPlayer.updateKey(Cell.Element.WATER);
                ViewGame.updateDisplay("Echange de clé : Veuillez choisir un joueur");
            } else if (text == "F"){
                this.Color = Color.RED;
                //this.model.currentPlayer.updateKey(Cell.Element.FIRE);
                ViewGame.updateDisplay("Echange de clé : Veuillez choisir un joueur");
            } else if (text == "E"){
                this.Color = Color.GREEN;
                //this.model.currentPlayer.updateKey(Cell.Element.EARTH);
                ViewGame.updateDisplay("Echange de clé : Veuillez choisir un joueur");
            }
        }
    }

}



