package views;

import controllers.ControllerPlay;
import controllers.ControllerPlayer;
import fonts.PantonFont;
import model.Island;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.SwingConstants.CENTER;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ViewMenu extends JPanel{
    private final Island model;
    private JFrame menu = new JFrame();
    private static ArrayList<JLabel> players;
    private static int coordy = 110;

    public ViewMenu(Island model) throws IOException, FontFormatException {
        this.model = model;
        players = new ArrayList<>();
        //TODO exception fichier

        this.menu.setTitle("Menu");
        this.menu.setSize(875, 686);
        this.menu.setLayout(null);

        JLabel title = new JLabel("L'île interdite");
        title.setFont(PantonFont.getPantonBold().deriveFont(Font.PLAIN, 32));
        title.setBounds(20,28,200,80);
        title.setForeground(Color.WHITE);
        this.menu.add(title);

        JLabel operation = new JLabel("Fonctionnement");
        operation.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 23));
        operation.setBounds(25,108,200,80);
        operation.setForeground(Color.WHITE);
        this.menu.add(operation);

        JButton buttonPlay = new JButton("Jouer");
        buttonPlay.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 25));
        buttonPlay.setBounds(48,354,130,45);
        buttonPlay.setForeground(new Color(188, 199, 236));
        buttonPlay.setContentAreaFilled(false);
        buttonPlay.setBorderPainted(false);
        buttonPlay.setFocusPainted(false);
        ControllerPlay ctrlplay = new ControllerPlay(this.model, menu);
        buttonPlay.addActionListener(ctrlplay);
        this.menu.add(buttonPlay);

        JLabel instructions = new JLabel("Instruction");
        instructions.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 18));
        instructions.setBounds(80,155,160,35);
        instructions.setForeground(new Color(188, 199, 236));
        this.menu.add(instructions);

        JLabel controles = new JLabel("Controles");
        controles.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 18));
        controles.setBounds(80,185,160,35);
        controles.setForeground(new Color(99, 108, 139));
        this.menu.add(controles);

        JButton buttonAddPlayer = new JButton("Ajouter joueur");
        buttonAddPlayer.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 12));
        buttonAddPlayer.setBounds(719,380,120,40);
        buttonAddPlayer.setForeground(Color.WHITE);
        buttonAddPlayer.setContentAreaFilled(false);
        buttonAddPlayer.setBorderPainted(false);
        buttonAddPlayer.setFocusPainted(false);
        ControllerPlayer ctrlAddPlayer = new ControllerPlayer(this.model);
        buttonAddPlayer.addActionListener(ctrlAddPlayer);
        this.menu.add(buttonAddPlayer);

        for(int i = 0; i < 8; i++) {
            JLabel player = new JLabel();
            player.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 14));
            player.setBounds(740,coordy,100,35);
            player.setForeground(Color.WHITE);
            players.add(player);
            coordy += 30;
            this.menu.add(player);
        }

        JLabel footer = new JLabel("Projet POGL - Antoine BARBANNAUD - Nelly LAM - Antonin PAOLI");
        footer.setFont(PantonFont.getPantonLight().deriveFont(Font.PLAIN, 10));
        footer.setBounds(550,588,300,80);
        footer.setForeground(Color.WHITE);
        this.menu.add(footer);

        ImageIcon img = new ImageIcon(new ImageIcon("./src/images/background_menu.jpg").getImage().getScaledInstance(864, 648, Image.SCALE_DEFAULT));
        JLabel background = new JLabel("", img, CENTER);
        background.setBounds(0,0,864,648);
        this.menu.add(background);

        this.menu.setVisible(true);
        this.menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void addPlayer(String name, int joueur) {
        players.get(joueur-1).setText(name);
    }
}