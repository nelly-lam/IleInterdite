package out;

import javax.swing.*;
import java.awt.*;

public class Swing{

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(1456, 1110);
        jf.setLayout(null);

        JLabel Jouer = new JLabel("Jouer");
        Jouer.setFont(new Font("Panton", Font.PLAIN, 43));
        Jouer.setBounds(131,597,200,60);
        Jouer.setForeground(Color.WHITE);
        jf.add(Jouer);

        JLabel Instructions = new JLabel("Instruction");
        Instructions.setFont(new Font("Panton", Font.PLAIN, 31));
        Instructions.setBounds(109,271,160,35);
        Instructions.setForeground(new Color(99, 108, 139));
        jf.add(Instructions);

        JLabel Controles = new JLabel("Controles");
        Controles.setFont(new Font("Panton", Font.PLAIN, 31));
        Controles.setBounds(109,320,131,35);
        Controles.setForeground(new Color(99, 108, 139));
        jf.add(Controles);

        ImageIcon img= new ImageIcon("D:\\School\\L2\\S4\\POGL\\SAVE\\background_menu.jpg");
        JLabel background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0,0,1440,1080);
        jf.add(background);

        jf.setVisible(true);
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);

    }
}