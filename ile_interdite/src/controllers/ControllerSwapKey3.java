package controllers;

import model.Cell;
import model.Island;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.image.ColorModel;

public class ControllerSwapKey3 implements ActionListener {
    private final Island model;
    private Color c;

    public ControllerSwapKey3(Island model, Color c){
        this.model = model;
        this.c = c;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        for (Player p : this.model.currentPlayer.isOnSameCell())
        if (str == p.getName()) {
            if (c == Color.WHITE) {
                this.model.currentPlayer.giveKey(Cell.Element.AIR, p);
            } else if (c == Color.BLUE) {
                this.model.currentPlayer.giveKey(Cell.Element.WATER, p);
            } else if (c == Color.RED) {
                this.model.currentPlayer.giveKey(Cell.Element.FIRE, p);
            } else if (c == Color.GREEN) {
                this.model.currentPlayer.giveKey(Cell.Element.EARTH, p);
            }
        }
    }
}
