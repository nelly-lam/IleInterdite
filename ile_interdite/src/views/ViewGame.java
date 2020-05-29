package views;

import controllers.Controller;
import controllers.ControllerMovement;
import fonts.PantonFont;
import model.Island;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

import static javax.swing.SwingConstants.CENTER;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ViewGame {
    private final Island model;
    private final JFrame game;
    private ViewIsland island;
    private ViewNbHits nbHits;
    private ViewItem item;
    private ViewArtifact artifact;
    private static JLabel display;

    public ViewGame(Island model) throws IOException, FontFormatException {
        this.model = model;
        this.game = new JFrame();
        this.game.setTitle("L'île interdite");
        this.game.setSize(875, 686);
        this.game.setLayout(null);

        JLabel title = new JLabel("L'île interdite");
        title.setFont(PantonFont.getPantonBold().deriveFont(Font.PLAIN, 32));
        title.setBounds(20,28,200,80);
        title.setForeground(Color.WHITE);
        this.game.add(title);

        this.island = new ViewIsland(this.model);
        this.island.setBounds(336,84,420,420);
        this.game.add(this.island);

        JButton button = new JButton("FIN DE TOUR");
        button.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 15));
        button.setBounds(470,552,147,39);
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.addActionListener(new Controller(this.model));
        this.game.add(button);

        display = new JLabel();
        display.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 15));
        display.setBounds(380,12,400,30);
        display.setForeground(Color.WHITE);
        this.game.add(display);

        this.nbHits = new ViewNbHits(this.model);
        this.nbHits.setBounds(800,110,50,50);
        this.game.add(this.nbHits);

        this.item = new ViewItem(this.model);
        this.item.setBounds(30,135,178,452);
        this.game.add(this.item);

        this.artifact = new ViewArtifact(this.model);
        this.artifact.setBounds(800,135,30,200);
        this.game.add(this.artifact);

        JLabel footer = new JLabel("Projet POGL - Antoine BARBANNAUD - Nelly LAM - Antonin PAOLI");
        footer.setFont(PantonFont.getPantonLight().deriveFont(Font.PLAIN, 10));
        footer.setBounds(550,588,300,80);
        footer.setForeground(new Color(89, 97, 125));
        this.game.add(footer);

        ImageIcon img = new ImageIcon(new ImageIcon("./src/images/background_game.jpg").getImage().getScaledInstance(864, 648, Image.SCALE_DEFAULT));
        JLabel background = new JLabel("", img, CENTER);
        background.setBounds(0,0,864,648);
        this.game.add(background);

        this.game.addKeyListener(new ControllerMovement(this.model));
        this.game.setFocusable(true);
        this.game.addFocusListener(
            new FocusListener() {
                public void focusGained(FocusEvent e) {
                    // Nous n'avons pas besoin de cette méthode
                }
                public void focusLost(FocusEvent e) {
                    game.requestFocusInWindow();
                }
            }
        );
        this.game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.game.setVisible(true);
    }

    public static void updateDisplay(String string) {
        display.setText(string);
    }
}
