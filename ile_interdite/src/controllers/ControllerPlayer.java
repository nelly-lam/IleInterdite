package controllers;

import model.Island;
import views.ViewAddPlayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerPlayer implements ActionListener {
    private final Island model;

    public ControllerPlayer(Island model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new ViewAddPlayer(this.model);
    }
}