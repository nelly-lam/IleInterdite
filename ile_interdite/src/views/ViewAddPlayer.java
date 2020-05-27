package views;

import controllers.ControllerAddPlayer;
import model.Island;

import javax.swing.*;
import java.awt.*;

public class ViewAddPlayer extends JPanel {
    private final Island model;
    private JFrame addPlayer;

    public ViewAddPlayer(Island model) {
        this.model = model;
        this.addPlayer = new JFrame();
        this.addPlayer.setTitle("Ajouter un joueur");

        this.addPlayer.setLayout(new BorderLayout());
        JTextArea name = new JTextArea();
        this.addPlayer.add(name, BorderLayout.NORTH);

        JButton buttonPlay = new JButton("Ajouter");
        buttonPlay.addActionListener(new ControllerAddPlayer(this.model, name, this.addPlayer));
        this.addPlayer.add(buttonPlay, BorderLayout.EAST);

        this.addPlayer.pack();
        this.addPlayer.setVisible(true);
    }
}
