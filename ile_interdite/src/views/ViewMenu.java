package views;

import controllers.ControllerPlay;
import controllers.ControllerPlayer;
import model.Island;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ViewMenu extends JPanel{
    private final Island model;
    private JFrame menu;

    public ViewMenu(Island model) {
        this.model = model;
        this.menu = new JFrame();
        this.menu.setTitle("Menu");
        this.menu.setSize(730, 558);
        this.menu.setLayout(null);

        JButton buttonPlay = new JButton("Jouer");
        buttonPlay.setFont(new Font("Panton", Font.PLAIN, 25));
        buttonPlay.setBounds(45,270,100,50);
        buttonPlay.setForeground(Color.WHITE);
        buttonPlay.setContentAreaFilled(false);
        buttonPlay.setBorderPainted(false);
        buttonPlay.setFocusPainted(false);
        ControllerPlay ctrlplay = new ControllerPlay(this.model, this.menu);
        buttonPlay.addActionListener(ctrlplay);
        this.menu.add(buttonPlay);

        JLabel Instructions = new JLabel("Instruction");
        Instructions.setFont(new Font("Panton", Font.PLAIN, 20));
        Instructions.setBounds(40,120,160,35);
        Instructions.setForeground(new Color(99, 108, 139));
        this.menu.add(Instructions);

        JLabel Controles = new JLabel("Controles");
        Controles.setFont(new Font("Panton", Font.PLAIN, 20));
        Controles.setBounds(40,150,131,35);
        Controles.setForeground(new Color(99, 108, 139));
        this.menu.add(Controles);

        JButton buttonAddPlayer = new JButton("<HTML><BODY><CENTER>Ajouter un <BR> joueur</CENTER></BODY></HTML>");
        buttonAddPlayer.setFont(new Font("Panton", Font.PLAIN, 15));
        buttonAddPlayer.setBounds(550,300,200,35);
        buttonAddPlayer.setForeground(Color.WHITE);
        buttonAddPlayer.setContentAreaFilled(false);
        buttonAddPlayer.setBorderPainted(false);
        buttonAddPlayer.setFocusPainted(false);
        ControllerPlayer ctrlAddPlayer = new ControllerPlayer(this.model);
        buttonAddPlayer.addActionListener(ctrlAddPlayer);
        this.menu.add(buttonAddPlayer);

        ImageIcon img = new ImageIcon(new ImageIcon("./src/images/background_menu.jpg").getImage().getScaledInstance(720, 520, Image.SCALE_DEFAULT));
        JLabel background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0,0,720,520);
        this.menu.add(background);

        this.menu.setVisible(true);
        this.menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
