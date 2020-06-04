package views;

import controllers.ControllerEndGame;
import fonts.PantonFont;
import model.Island;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ViewEndGame {
    private static JDialog endGame;

    public ViewEndGame(boolean isWinning, Island model){
        JFrame result = new JFrame();
        endGame = new JDialog(result, "result", true);
        endGame.setResizable(false);
        endGame.setLayout(null);
        endGame.setSize(610, 330);

        JButton restart = new JButton("Rejouer");
        restart.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 15));
        restart.setBounds(150, 240, 100, 30);
        restart.setForeground(Color.WHITE);
        restart.setContentAreaFilled(false);
        restart.setBorderPainted(false);
        restart.setFocusPainted(false);
        endGame.add(restart);
        restart.addActionListener(new ControllerEndGame(model));

        JButton close = new JButton("Quitter");
        close.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 15));
        close.setBounds(350, 240, 100, 30);
        close.setForeground(Color.WHITE);
        close.setContentAreaFilled(false);
        close.setBorderPainted(false);
        close.setFocusPainted(false);
        endGame.add(close);
        close.addActionListener(new ControllerEndGame(model));

        if (isWinning) {
            endGame.setTitle("Vous avez GAGNE");
            ImageIcon icon = new ImageIcon(new ImageIcon("./src/images/win.jpg").getImage().getScaledInstance(600,300, Image.SCALE_DEFAULT));
            JLabel background = new JLabel("", icon, CENTER);
            background.setBounds(0,0,600,300);
            endGame.add(background);
        } else {
            endGame.setTitle("Vous avez PERDU");
            ImageIcon icon = new ImageIcon(new ImageIcon("./src/images/loose.jpg").getImage().getScaledInstance(600,300, Image.SCALE_DEFAULT));
            JLabel background = new JLabel("", icon, CENTER);
            background.setBounds(0,0,600,300);
            endGame.add(background);
        }

        endGame.setVisible(true);
        endGame.pack();
        endGame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void hidden() {
        endGame.dispose();
    }
}
