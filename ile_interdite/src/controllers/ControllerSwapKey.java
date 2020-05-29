package controllers;

import model.Cell;
import model.Island;
import views.ViewGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerSwapKey implements ActionListener {
    private final Island model;
    private final JFrame swapKey;

    public ControllerSwapKey(Island model, JFrame swapKey){
        this.model = model;
        this.swapKey = swapKey;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*if (e.getActionCommand() == "A"){
            this.model.playerCourant.giveKey(Cell.Element.AIR, );
        } else if (e.getActionCommand() == "W"){

        } else if (e.getActionCommand() == "F"){

        } else if (e.getActionCommand() == "E"){

        }
        this.swapKey.dispose();
        */
    }

}
