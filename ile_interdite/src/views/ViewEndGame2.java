package views;

import controllers.ControllerReplay;
import fonts.PantonFont;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.SwingConstants.CENTER;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ViewEndGame2{
    private JFrame result;
    //private static JDialog endGame;
    //private JPanel panel;
    private boolean isWinning;

    public ViewEndGame2(boolean isWinning){
        this.isWinning = isWinning;
        this.result = new JFrame("RESULT");
        //this.result.setPreferredSize(new Dimension(600, 300));
        //JDialog endGame = new JDialog(this.result, "", Dialog.ModalityType.DOCUMENT_MODAL);
        JDialog endGame = new JDialog(this.result, "RESULT", true);
        this.result.setLayout(null);
        endGame.setSize(610, 330);
        //this.endGame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        /*JPanel panel = new JPanel();
        panel.setLayout(new GridLayout());
        this.result.add(panel, BorderLayout.SOUTH);
         */

        JButton restart = new JButton("Rejouer");
        restart.setFont(new Font("Panton", Font.PLAIN, 15));
        restart.setBounds(150, 240, 100, 30);
        restart.setForeground(Color.WHITE);
        restart.setContentAreaFilled(false);
        restart.setBorderPainted(false);
        restart.setFocusPainted(false);
        endGame.add(restart);
        restart.addActionListener(new ControllerReplay(this.result, endGame));

        JButton close = new JButton("Quitter");
        close.setFont(new Font("Panton", Font.PLAIN, 15));
        close.setBounds(350, 240, 100, 30);
        close.setForeground(Color.WHITE);
        close.setContentAreaFilled(false);
        close.setBorderPainted(false);
        close.setFocusPainted(false);
        endGame.add(close);
        close.addActionListener(new ControllerReplay(this.result, endGame));


        if(this.isWinning) {
            ImageIcon icon = new ImageIcon(new ImageIcon("./ile_interdite/src/images/win.jpg").getImage().getScaledInstance(600,300, Image.SCALE_DEFAULT));
            JLabel background = new JLabel("", icon, CENTER);
            background.setBounds(0,0,600,300);
            endGame.add(background);
            //JOptionPane.showMessageDialog(this.endGame, "", "VOUS AVEZ GAGNE", JOptionPane.PLAIN_MESSAGE, icon);
            //JOptionPane.showOptionDialog(endGame, "", "VOUS AVEZ GAGNE", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, icon, new Object[] {}, null);
            //this.result.add(new JLabel("", icon, CENTER));
        }
        else {
            ImageIcon icon = new ImageIcon(new ImageIcon("./ile_interdite/src/images/loose.jpg").getImage().getScaledInstance(600,300, Image.SCALE_DEFAULT));
            JLabel background = new JLabel("", icon, CENTER);
            background.setBounds(0,0,600,300);
            endGame.add(background);
            //this.panel.add(background);
            //JOptionPane.showMessageDialog(this.endGame, "", "VOUS AVEZ PERDU", JOptionPane.PLAIN_MESSAGE, icon);
            //JOptionPane.showOptionDialog(endGame, "", "VOUS AVEZ PERDU", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, icon, new Object[] {}, null);

        }

        endGame.setVisible(true);

        this.result.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.result.setVisible(true);
        this.result.pack();

    }
}
