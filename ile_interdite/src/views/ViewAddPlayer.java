package views;

import controllers.ControllerAddPlayer;
import fonts.PantonFont;
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
        this.addPlayer.setSize(395, 305);
        this.addPlayer.setLayout(null);

        JTextField name = new JTextField();
        name.setBounds(58,105,268,50);
        name.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 30));
        name.setForeground(Color.WHITE);
        name.setOpaque(false);
        name.setBorder(null);
        this.addPlayer.add(name);

        JButton buttonAdd = new JButton("Ajouter");
        buttonAdd.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 18));
        buttonAdd.setBounds(136,185,110,30);
        buttonAdd.setForeground(new Color(188, 199, 236));
        buttonAdd.setContentAreaFilled(false);
        buttonAdd.setBorderPainted(false);
        buttonAdd.setFocusPainted(false);
        buttonAdd.addActionListener(new ControllerAddPlayer(this.model, name, this.addPlayer));
        this.addPlayer.add(buttonAdd);

        ImageIcon img = new ImageIcon(new ImageIcon("./src/images/Ajouter_joueur.jpg").getImage().getScaledInstance(382, 268, Image.SCALE_DEFAULT));
        JLabel background = new JLabel("", img, CENTER);
        background.setBounds(0,0,382,268);
        this.addPlayer.add(background);

        this.addPlayer.setVisible(true);
    }
}