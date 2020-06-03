package controllers;

import model.Island;
import views.ViewMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerAddPlayer implements ActionListener {
    private final Island model;
    private final JTextField name;
    private final JFrame frame;

    public ControllerAddPlayer(Island model, JTextField name, JFrame frame) {
        this.model = model;
        this.name = name;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.model.getPlayers().size() < 8) {
            if(name.getText().length() <= 10) {
                if(name.getText().length() > 0) {
                    this.model.addPlayer(name.getText());
                    ViewMenu.addPlayer(name.getText(), this.model.getPlayers().size()-1);
                    ViewMenu.updateLabel("");
                } else {
                    ViewMenu.updateLabel("Il doit y avoir au moins 1 caractère");
                }
            } else {
                ViewMenu.updateLabel("Il ne doit pas dépasser 10 caractères");
            }
        } else {
            ViewMenu.updateLabel("Vous avez atteint la limite maximale");
        }
        frame.dispose();
    }
}