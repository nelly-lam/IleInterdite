package controllers;

import views.ViewFonctionnement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerFonctionnement implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Instructions")){
            new ViewFonctionnement(true);
        } else if (e.getActionCommand().equals("Contrôles")){
            new ViewFonctionnement(false);
        }
    }
}
