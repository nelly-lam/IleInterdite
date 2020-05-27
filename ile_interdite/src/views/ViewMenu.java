package views;

import controllers.ControllerPlay;
import controllers.ControllerPlayer;
import model.Island;

import javax.swing.*;
import java.awt.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ViewMenu extends JPanel{
    private final Island model;
    private JFrame menu;

    public ViewMenu(Island model) {
        this.model = model;
        this.menu = new JFrame();
        this.menu.setTitle("Menu");

        this.menu.setLayout(new BorderLayout());
        JLabel label = new JLabel("Le jeu de l'île interdite");
        this.menu.add(label, BorderLayout.NORTH);

        JButton buttonAddPlayer = new JButton("Ajouter un joueur");
        ControllerPlayer ctrlAddPlayer = new ControllerPlayer(this.model);
        buttonAddPlayer.addActionListener(ctrlAddPlayer);
        this.menu.add(buttonAddPlayer, BorderLayout.WEST);

        JButton buttonPlay = new JButton("Play");
        ControllerPlay ctrlplay = new ControllerPlay(this.model, this.menu);
        buttonPlay.addActionListener(ctrlplay);
        this.menu.add(buttonPlay, BorderLayout.EAST);

        this.menu.pack();
        this.menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.menu.setVisible(true);
    }
}
