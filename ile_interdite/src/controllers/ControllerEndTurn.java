package controllers;

import model.Island;
import views.ViewGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerEndTurn implements ActionListener {
    private final Island model;

    public ControllerEndTurn(Island model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.risingWater();
        ViewGame.updateDisplay("");
    }
}
