package controllers;

import model.Island;
import model.Player;
import views.ViewMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerAddPlayer implements ActionListener {
    private final Island model;
    private final JTextField name;
    private final JFrame frame;
    private final JComboBox role;

    public ControllerAddPlayer(Island model, JTextField name, JFrame frame, JComboBox role) {
        this.model = model;
        this.name = name;
        this.frame = frame;
        this.role = role;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.model.getPlayers().size() < 8) {
            if (name.getText().length() <= 10) {
                if (name.getText().length() > 0) {
                    boolean validateRole = true;
                    for (Player p : this.model.getPlayers()) {
                        if (p.getRole() == this.role.getSelectedItem() && this.role.getSelectedItem() != Player.Role.NONE) {
                            validateRole = false;
                        }
                    }
                    if (validateRole) {
                        this.model.addPlayer(name.getText(), (Player.Role) this.role.getSelectedItem());
                        ViewMenu.addPlayer(name.getText(), this.model.getPlayers().size()-1);
                        ViewMenu.updateLabel("");
                    } else {
                        ViewMenu.updateLabel("Ce rôle est déjà utilisé par quelqu'un");
                    }
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