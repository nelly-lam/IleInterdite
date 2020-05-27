package controllers;

import model.Island;
import views.View;

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

    /**
     * Invoked when an action occurs.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.model.players.size() > 0) {
            this.menu.dispose();
            View view = new View(this.model);
        }
    }
}
