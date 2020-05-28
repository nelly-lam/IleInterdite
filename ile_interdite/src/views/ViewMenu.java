package views;

import controllers.ControllerPlay;
import controllers.ControllerPlayer;
import model.Island;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ViewMenu extends JPanel{
    private final Island model;
    private static JFrame menu = new JFrame();
    private static JLabel player1;
    private static JLabel player2;
    private static JLabel player3;
    private static JLabel player4;
    private static JLabel player5;
    private static JLabel player6;
    private static JLabel player7;
    private static JLabel player8;

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


        player1 = new JLabel();
        player1.setFont(new Font("Panton", Font.PLAIN, 15));
        player1.setBounds(740,110,100,35);
        player1.setForeground(Color.WHITE);
        menu.add(player1);

        player2 = new JLabel();
        player2.setFont(new Font("Panton", Font.PLAIN, 15));
        player2.setBounds(740,130,100,35);
        player2.setForeground(Color.WHITE);
        menu.add(player2);

        player3 = new JLabel();
        player3.setFont(new Font("Panton", Font.PLAIN, 15));
        player3.setBounds(740,150,100,35);
        player3.setForeground(Color.WHITE);
        menu.add(player3);

        player4 = new JLabel();
        player4.setFont(new Font("Panton", Font.PLAIN, 15));
        player4.setBounds(740,170,100,35);
        player4.setForeground(Color.WHITE);
        menu.add(player4);

        player5 = new JLabel();
        player5.setFont(new Font("Panton", Font.PLAIN, 15));
        player5.setBounds(740,190,100,35);
        player5.setForeground(Color.WHITE);
        menu.add(player5);

        player6 = new JLabel();
        player6.setFont(new Font("Panton", Font.PLAIN, 15));
        player6.setBounds(740,210,100,35);
        player6.setForeground(Color.WHITE);
        menu.add(player6);

        player7 = new JLabel();
        player7.setFont(new Font("Panton", Font.PLAIN, 15));
        player7.setBounds(740,230,100,35);
        player7.setForeground(Color.WHITE);
        menu.add(player7);

        player8 = new JLabel();
        player8.setFont(new Font("Panton", Font.PLAIN, 15));
        player8.setBounds(740,250,100,35);
        player8.setForeground(Color.WHITE);
        menu.add(player8);

        ImageIcon img = new ImageIcon(new ImageIcon("./src/images/background_menu.jpg").getImage().getScaledInstance(864, 648, Image.SCALE_DEFAULT));
        JLabel background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0,0,864,648);
        menu.add(background);

        menu.setVisible(true);
        menu.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void addPlayer(String name, int taille) {
        /*JLabel player = new JLabel(name);
        player.setFont(new Font("Panton", Font.PLAIN, 15));
        player.setBounds(640,150,100,35);
        player.setForeground(Color.WHITE);
        player.setFocusable(false);
        menu.setContentPane(player);
         */
        switch(taille){
            case 1:
                player1.setText(name);
                break;
            case 2:
                player2.setText(name);
                break;
            case 3:
                player3.setText(name);
                break;
            case 4:
                player4.setText(name);
                break;
            case 5:
                player5.setText(name);
                break;
            case 6:
                player6.setText(name);
                break;
            case 7:
                player7.setText(name);
                break;
            case 8:
                player8.setText(name);
                break;
            default:
                break;
        }
    }

}