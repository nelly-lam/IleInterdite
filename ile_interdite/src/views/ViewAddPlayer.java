package views;

import controllers.ControllerAddPlayer;
import model.Island;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;

public class ViewAddPlayer extends JPanel {
    private final Island model;
    private JFrame addPlayer;

    public ViewAddPlayer(Island model) {
        this.model = model;
        this.addPlayer = new JFrame();
        this.addPlayer.setTitle("Ajouter un joueur");
        this.addPlayer.setSize(382, 268);

        this.addPlayer.setLayout(null);
        JTextField name = new JTextField();
        this.addPlayer.add(name);

        JButton buttonPlay = new JButton("Ajouter");
        buttonPlay.addActionListener(new ControllerAddPlayer(this.model, name, this.addPlayer));
        this.addPlayer.add(buttonPlay);

        ImageIcon img = new ImageIcon(new ImageIcon("./src/images/Ajouter_joueur.jpg").getImage().getScaledInstance(382, 268, Image.SCALE_DEFAULT));
        JLabel background = new JLabel("", img, CENTER);
        background.setBounds(0,0,382,268);
        this.addPlayer.add(background);

        this.addPlayer.setVisible(true);
    }
}
