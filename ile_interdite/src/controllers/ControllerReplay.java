package controllers;

import model.Island;
import views.ViewMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ControllerReplay implements ActionListener {
    private JDialog game;
    private JFrame result;

    public ControllerReplay(JFrame frame, JDialog dialog){
        this.result = frame;
        this.game = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Rejouer") {
            Island model = new Island(12, 12);
            try {
                ViewMenu viewMenu = new ViewMenu(model);
                viewMenu.requestFocus();
                this.result.dispatchEvent(new WindowEvent(this.result, WindowEvent.WINDOW_CLOSING));
                this.game.dispose();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (FontFormatException fontFormatException) {
                fontFormatException.printStackTrace();
            }
        } else if (e.getActionCommand() == "Quitter"){
            System.exit(0);
        }
    }
}
