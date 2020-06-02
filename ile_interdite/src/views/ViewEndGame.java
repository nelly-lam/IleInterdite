package views;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ViewEndGame {

    public static void display(Boolean iswinning) {
        JFrame endGame = new JFrame();
        endGame.setLayout(null);
        endGame.setSize(395, 305);

        if(iswinning) {
            endGame.setTitle("VOUS AVEZ GAGNE");
            ImageIcon img = new ImageIcon(new ImageIcon("./src/images/win.jpg").getImage().getScaledInstance(382,268, Image.SCALE_DEFAULT));
            JLabel win = new JLabel("", img, CENTER);
            win.setBounds(0,0,382, 268);
            endGame.add(win);
        }
        else {
            endGame.setTitle("VOUS AVEZ PERDU");
            ImageIcon img = new ImageIcon(new ImageIcon("./src/images/loose.jpg").getImage().getScaledInstance(382,268, Image.SCALE_DEFAULT));
            JLabel loose = new JLabel("", img, CENTER);
            loose.setBounds(0,0,382,268);
            endGame.add(loose);
        }
        endGame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        endGame.setVisible(true);
    }
}
