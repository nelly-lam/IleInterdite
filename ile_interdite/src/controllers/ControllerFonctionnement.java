package controllers;

import views.ViewFonctionnement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControllerFonctionnement implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Instructions"){
            try {
                ViewFonctionnement fct = new ViewFonctionnement(true);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else if (e.getActionCommand() == "Contrôles"){
            try {
                ViewFonctionnement fct = new ViewFonctionnement(false);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
