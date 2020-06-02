package controllers;

import model.Island;
import views.ViewGame;
import views.ViewMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerPlay implements ActionListener {
    private final Island model;

    public ControllerPlay(Island model) {
        this.model = model;
    }

    /**
     * Invoked when an action occurs.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!this.model.getPlayers().isEmpty()) {
            ViewMenu.hidden();
            new ViewGame(this.model);
        }
        else {
            ViewMenu.updateLabel("Vous devez ajouter au moins 1 joueur");
        }
    }
}
