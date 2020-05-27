package views;

import controllers.ControllerPlay;
import controllers.ControllerPlayer;
import model.Island;

import javax.swing.*;
import java.awt.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ViewMenu extends JPanel{
    private final Island model;
    private static JFrame menu = new JFrame();

    public ViewMenu(Island model) {
        this.model = model;
        menu.setTitle("Menu");
        menu.setSize(875, 686);
        menu.setLayout(null);

        JButton buttonPlay = new JButton("Jouer");
        buttonPlay.setFont(new Font("Panton", Font.PLAIN, 25));
        buttonPlay.setBounds(60,345,100,50);
        buttonPlay.setForeground(Color.WHITE);
        buttonPlay.setContentAreaFilled(false);
        buttonPlay.setBorderPainted(false);
        buttonPlay.setFocusPainted(false);
        ControllerPlay ctrlplay = new ControllerPlay(this.model, menu);
        buttonPlay.addActionListener(ctrlplay);
        menu.add(buttonPlay);

        JLabel Instructions = new JLabel("Instruction");
        Instructions.setFont(new Font("Panton", Font.PLAIN, 20));
        Instructions.setBounds(80,155,160,35);
        Instructions.setForeground(new Color(99, 108, 139));
        menu.add(Instructions);

        JLabel Controles = new JLabel("Controles");
        Controles.setFont(new Font("Panton", Font.PLAIN, 20));
        Controles.setBounds(80,185,160,35);
        Controles.setForeground(new Color(99, 108, 139));
        menu.add(Controles);

        JButton buttonAddPlayer = new JButton("<HTML><BODY><CENTER>Ajouter un <BR> joueur</CENTER></BODY></HTML>");
        buttonAddPlayer.setFont(new Font("Panton", Font.PLAIN, 15));
        buttonAddPlayer.setBounds(680,370,200,35);
        buttonAddPlayer.setForeground(Color.WHITE);
        buttonAddPlayer.setContentAreaFilled(false);
        buttonAddPlayer.setBorderPainted(false);
        buttonAddPlayer.setFocusPainted(false);
        ControllerPlayer ctrlAddPlayer = new ControllerPlayer(this.model);
        buttonAddPlayer.addActionListener(ctrlAddPlayer);
        menu.add(buttonAddPlayer);

        ImageIcon img = new ImageIcon(new ImageIcon("./src/images/background_menu.jpg").getImage().getScaledInstance(864, 648, Image.SCALE_DEFAULT));
        JLabel background = new JLabel("", img, JLabel.CENTER);
        //menu.setComponentZOrder(background, 99);
        background.setBounds(0,0,864,648);
        menu.add(background);

        menu.setVisible(true);
        menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void addPlayer(String name) {
        JLabel player = new JLabel(name);
        player.setFont(new Font("Panton", Font.PLAIN, 15));
        player.setBounds(540,150,100,35);
        player.setForeground(Color.WHITE);
        menu.add(player);
    }
}
