package views;

import controllers.ControllerEndTurn;
import controllers.ControllerMovement;
import fonts.PantonFont;
import model.Island;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static javax.swing.SwingConstants.CENTER;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ViewGame {
    private final Island model;
    private static final JFrame game = new JFrame();
    private static JLabel display = new JLabel();

    public ViewGame(Island model) {
        this.model = model;
        game.setTitle("L'île interdite");
        game.setSize(875, 686);
        game.setResizable(false);
        game.setLayout(null);

        JLabel title = new JLabel("L'île interdite");
        title.setFont(PantonFont.getPantonBold().deriveFont(Font.PLAIN, 32));
        title.setBounds(20,28,200,80);
        title.setForeground(Color.WHITE);
        game.add(title);

        ViewIsland island = new ViewIsland(this.model);
        island.setBounds(336,84,420,420);
        game.add(island);

        JButton button = new JButton("FIN DE TOUR");
        button.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 15));
        button.setBounds(470,552,147,39);
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(new ControllerEndTurn(this.model));
        game.add(button);

        display.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 15));
        display.setBounds(360,12,400,30);
        display.setHorizontalAlignment(CENTER);
        display.setForeground(Color.WHITE);
        game.add(display);

        ViewNbHits nbHits = new ViewNbHits(this.model);
        nbHits.setBounds(800,116,50,50);
        game.add(nbHits);

        ViewItem item = new ViewItem(this.model);
        item.setBounds(30,135,178,452);
        game.add(item);

        ViewArtifact artifact = new ViewArtifact(this.model);
        artifact.setBounds(800,160,60,200);
        game.add(artifact);

        JLabel footer = new JLabel("Projet POGL - Antoine BARBANNAUD - Nelly LAM - Antonin PAOLI");
        footer.setFont(PantonFont.getPantonLight().deriveFont(Font.PLAIN, 10));
        footer.setBounds(550,588,300,80);
        footer.setForeground(new Color(89, 97, 125));
        game.add(footer);

        ImageIcon img = new ImageIcon(new ImageIcon("./src/images/background_game.jpg").getImage().getScaledInstance(864, 648, Image.SCALE_DEFAULT));
        JLabel background = new JLabel("", img, CENTER);
        background.setBounds(0,0,864,648);
        game.add(background);

        game.addKeyListener(new ControllerMovement(this.model));
        game.setFocusable(true);
        game.addFocusListener(
            new FocusListener() {
                public void focusGained(FocusEvent e) { /* */ }
                public void focusLost(FocusEvent e) {
                    game.requestFocusInWindow();
                }
            }
        );
        game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        game.setVisible(true);
    }

    public static void updateDisplay(String string) {
        display.setText(string);
    }

    public static void hidden() {
        game.dispose();
    }
}
