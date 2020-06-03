package views;

import model.Island;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ViewEndGame {
    private Island model;
    private static JDialog endGame;

    public ViewEndGame(boolean isWinning, Island model){
        JFrame result = new JFrame();
        endGame = new JDialog(result, "result", true);
        endGame.setResizable(false);
        endGame.setLayout(null);
        endGame.setSize(610, 330);

        if(isWinning) {
            endGame.setTitle("Vous avez GAGNE");
            ImageIcon icon = new ImageIcon(new ImageIcon("./src/images/win.jpg").getImage().getScaledInstance(600,300, Image.SCALE_DEFAULT));
            JLabel background = new JLabel("", icon, CENTER);
            background.setBounds(0,0,600,300);
            endGame.add(background);
        }
        else {
            endGame.setTitle("Vous avez PERDU");
            ImageIcon icon = new ImageIcon(new ImageIcon("./src/images/loose.jpg").getImage().getScaledInstance(600,300, Image.SCALE_DEFAULT));
            JLabel background = new JLabel("", icon, CENTER);
            background.setBounds(0,0,600,300);
            endGame.add(background);
        }

        endGame.setVisible(true);
        endGame.pack();
        result.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
