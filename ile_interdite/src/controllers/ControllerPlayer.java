package controllers;

import model.Island;
import views.ViewAddPlayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControllerPlayer implements ActionListener {
    private final Island model;

    public ControllerPlayer(Island model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ViewAddPlayer viewAddPlayer = new ViewAddPlayer(this.model);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (FontFormatException fontFormatException) {
            fontFormatException.printStackTrace();
        }
    }
}
