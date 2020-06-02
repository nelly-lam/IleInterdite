package controllers;

import model.Island;
import views.ViewGame;
import views.ViewMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerReplay implements ActionListener {
    private JDialog game;
    private JFrame result;
    private Island model;

    public ControllerReplay(JFrame frame, JDialog dialog, Island model){
        this.result = frame;
        this.game = dialog;
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Rejouer") {
            ViewGame.hidden();
            ViewMenu.visible();
            this.game.dispose();
            // TODO
            //Island model = new Island(12, 12);
            /*try {
                ViewMenu viewMenu = new ViewMenu(model);
                viewMenu.requestFocus();
                this.result.dispatchEvent(new WindowEvent(this.result, WindowEvent.WINDOW_CLOSING));
                this.game.dispose();

            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (FontFormatException fontFormatException) {
                fontFormatException.printStackTrace();
            }*/
        } else if (e.getActionCommand() == "Quitter"){
            System.exit(0);
        }
    }
}
