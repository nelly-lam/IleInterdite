package views;

import controllers.ControllerPlay;
import controllers.ControllerPlayer;
import model.Island;
import org.junit.internal.runners.statements.FailOnTimeout;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static javax.swing.SwingConstants.CENTER;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ViewMenu extends JPanel{
    private final Island model;
    private JFrame menu = new JFrame();
    private static ArrayList<JLabel> players;

    public ViewMenu(Island model) throws IOException, FontFormatException {
        this.model = model;
        players = new ArrayList<>();
        //TODO exception fichier
        InputStream file = new FileInputStream("./src/images/Panton-Regular.ttf");
        Font panton = Font.createFont(Font.TRUETYPE_FONT, file);
        InputStream file2 = new FileInputStream("./src/images/Panton-Light.ttf");
        Font pantonLight = Font.createFont(Font.TRUETYPE_FONT, file2);
        InputStream file3 = new FileInputStream("./src/images/Panton-Bold.ttf");
        Font pantonBold = Font.createFont(Font.TRUETYPE_FONT, file3);

        this.menu.setTitle("Menu");
        this.menu.setSize(875, 686);
        this.menu.setLayout(null);

        JLabel title = new JLabel("L'île interdite");
        title.setFont(pantonBold.deriveFont(Font.PLAIN, 32));
        title.setBounds(20,28,200,80);
        title.setForeground(Color.WHITE);
        this.menu.add(title);

        JLabel operation = new JLabel("Fonctionnement");
        operation.setFont(panton.deriveFont(Font.PLAIN, 23));
        operation.setBounds(25,108,200,80);
        operation.setForeground(Color.WHITE);
        this.menu.add(operation);

        JButton buttonPlay = new JButton("Jouer");
        buttonPlay.setFont(panton.deriveFont(Font.PLAIN, 25));
        buttonPlay.setBounds(48,354,130,45);
        buttonPlay.setForeground(new Color(188, 199, 236));
        buttonPlay.setContentAreaFilled(false);
        buttonPlay.setBorderPainted(false);
        buttonPlay.setFocusPainted(false);
        ControllerPlay ctrlplay = new ControllerPlay(this.model, menu);
        buttonPlay.addActionListener(ctrlplay);
        this.menu.add(buttonPlay);

        JLabel instructions = new JLabel("Instruction");
        instructions.setFont(panton.deriveFont(Font.PLAIN, 18));
        instructions.setBounds(75,160,160,35);
        instructions.setForeground(new Color(188, 199, 236));
        this.menu.add(instructions);

        JLabel controles = new JLabel("Controles");
        controles.setFont(panton.deriveFont(Font.PLAIN, 18));
        controles.setBounds(75,185,160,35);
        controles.setForeground(new Color(99, 108, 139));
        this.menu.add(controles);

        JButton buttonAddPlayer = new JButton("<HTML><BODY><CENTER>Ajouter un <BR> joueur</CENTER></BODY></HTML>");
        buttonAddPlayer.setFont(panton.deriveFont(Font.PLAIN, 12));
        buttonAddPlayer.setBounds(680,380,200,35);
        buttonAddPlayer.setForeground(Color.WHITE);
        buttonAddPlayer.setContentAreaFilled(false);
        buttonAddPlayer.setBorderPainted(false);
        buttonAddPlayer.setFocusPainted(false);
        ControllerPlayer ctrlAddPlayer = new ControllerPlayer(this.model);
        buttonAddPlayer.addActionListener(ctrlAddPlayer);
        this.menu.add(buttonAddPlayer);

        JLabel player1 = new JLabel();
        player1.setFont(panton.deriveFont(Font.PLAIN, 14));
        player1.setBounds(740,110,100,35);
        player1.setForeground(Color.WHITE);
        players.add(player1);
        this.menu.add(player1);

        JLabel player2 = new JLabel();
        player2.setFont(panton.deriveFont(Font.PLAIN, 14));
        player2.setBounds(740,140,100,35);
        player2.setForeground(Color.WHITE);
        players.add(player2);
        this.menu.add(player2);

        JLabel player3 = new JLabel();
        player3.setFont(panton.deriveFont(Font.PLAIN, 14));
        player3.setBounds(740,170,100,35);
        player3.setForeground(Color.WHITE);
        players.add(player3);
        this.menu.add(player3);

        JLabel player4 = new JLabel();
        player4.setFont(panton.deriveFont(Font.PLAIN, 14));
        player4.setBounds(740,200,100,35);
        player4.setForeground(Color.WHITE);
        players.add(player4);
        this.menu.add(player4);

        JLabel player5 = new JLabel();
        player5.setFont(panton.deriveFont(Font.PLAIN, 14));
        player5.setBounds(740,230,100,35);
        player5.setForeground(Color.WHITE);
        players.add(player5);
        this.menu.add(player5);

        JLabel player6 = new JLabel();
        player6.setFont(panton.deriveFont(Font.PLAIN, 14));
        player6.setBounds(740,260,100,35);
        player6.setForeground(Color.WHITE);
        players.add(player6);
        this.menu.add(player6);

        JLabel player7 = new JLabel();
        player7.setFont(panton.deriveFont(Font.PLAIN, 14));
        player7.setBounds(740,290,100,35);
        player7.setForeground(Color.WHITE);
        players.add(player7);
        this.menu.add(player7);

        JLabel player8 = new JLabel();
        player8.setFont(panton.deriveFont(Font.PLAIN, 14));
        player8.setBounds(740,320,100,35);
        player8.setForeground(Color.WHITE);
        players.add(player8);
        this.menu.add(player8);

        JLabel footer = new JLabel("Projet POGL - Antoine BARBANNAUD - Nelly LAM - Antonin PAOLI");
        footer.setFont(pantonLight.deriveFont(Font.PLAIN, 10));
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