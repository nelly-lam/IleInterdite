package views;

import controllers.ControllerSwapKey;
import controllers.ControllerSwapKey2;
import controllers.ControllerSwapKey3;
import model.Cell;
import model.Island;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.SwingConstants.CENTER;

public class ViewItem2 extends JPanel implements Observer{
    private final Island model;
    private static JLabel nbKeyAir;
    private static JLabel nbKeyWater;
    private static JLabel nbKeyFire;
    private static JLabel nbKeyEarth;
    /*private static JButton buttonP1;
    private static JButton buttonP2;
    private static JButton buttonP3;
    private static JButton buttonP4;
    private static JButton buttonP5;
    private static JButton buttonP6;
    private static JButton buttonP7;
    private static JButton buttonP8;*/
    private int coordx = 35;
    private int coordy = 5;
    private JLabel[][] tabJLabel;
    private ArrayList<JLabel> tabName;

    public ViewItem2(Island model){
        this.model = model;
        this.model.addObserver(this);
        this.tabJLabel = new JLabel[this.model.players.size()][4];
        this.tabName = new ArrayList<>();
        this.setLayout(null);
        this.setOpaque(false);
        ControllerSwapKey ctrl = new ControllerSwapKey(this.model);

        for (Player p : this.model.players) {
            //JLABEL FOR NAME PLAYERS
            JLabel name = new JLabel(p.getName());
            name.setFont(new Font("Panton", Font.PLAIN, 20));
            name.setBounds(20, this.coordy, 150, 30);
            name.setHorizontalAlignment(SwingConstants.LEFT);
            name.setForeground(Color.GRAY);
            name.addMouseListener(ctrl);
            this.tabName.add(name);
            this.add(name);

            JLabel color = new JLabel();
            this.add(color);

            /*buttonP1 = new JButton(p.getName());
            buttonP1.setFont(new Font("Panton", Font.PLAIN, 20));
            buttonP1.setBounds(5, this.coordy, 150, 30);
            buttonP1.setContentAreaFilled(false);
            buttonP1.setBorderPainted(false);
            buttonP1.setFocusPainted(false);
            buttonP1.setVisible(false);
            buttonP1.addActionListener(new ControllerSwapKey3(this.model, buttonP1.getBackground()));*/

            //JLABEL FOR ICON "AIR"
            ImageIcon keyAir = new ImageIcon(new ImageIcon("./src/images/key_air.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel labelKeyAir = new JLabel("A", keyAir, CENTER);
            labelKeyAir.setFont(new Font("Panton", Font.PLAIN, 2));
            labelKeyAir.setBounds(35, this.coordy + 12, 50, 50);
            this.add(labelKeyAir);
            labelKeyAir.addMouseListener(ctrl);

            //JLABEL FOR NUMBER KEY "AIR"
            nbKeyAir = new JLabel();
            nbKeyAir.setFont(new Font("Panton", Font.PLAIN, 10));
            nbKeyAir.setBounds(65, this.coordy + 18, 50, 50);
            nbKeyAir.setForeground(Color.WHITE);
            this.add(nbKeyAir);
            tabJLabel[this.model.players.indexOf(p)][0] = nbKeyAir;

            //JLABEL FOR ICON "WATER"
            ImageIcon keyWater = new ImageIcon(new ImageIcon("./src/images/key_water.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel labelKeyWater = new JLabel("W", keyWater, CENTER);
            labelKeyWater.setFont(new Font("Panton", Font.PLAIN, 2));
            labelKeyWater.setBounds(65, this.coordy + 12, 50, 50);
            this.add(labelKeyWater);
            labelKeyWater.addMouseListener(ctrl);

            //JLABEL FOR NUMBER KEY "WATER"
            nbKeyWater = new JLabel();
            nbKeyWater.setFont(new Font("Panton", Font.PLAIN, 10));
            nbKeyWater.setBounds(95, this.coordy + 18, 50, 50);
            nbKeyWater.setForeground(Color.WHITE);
            this.add(nbKeyWater);
            tabJLabel[this.model.players.indexOf(p)][1] = nbKeyWater;

            //JLABEL FOR ICON "FIRE"
            ImageIcon keyFire = new ImageIcon(new ImageIcon("./src/images/key_fire.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel labelKeyFire = new JLabel("F", keyFire, CENTER);
            labelKeyFire.setFont(new Font("Panton", Font.PLAIN, 2));
            labelKeyFire.setBounds(95, this.coordy + 12, 50, 50);
            this.add(labelKeyFire);
            labelKeyFire.addMouseListener(ctrl);

            //JLABEL FOR NUMBER KEY "FIRE"
            nbKeyFire = new JLabel();
            nbKeyFire.setFont(new Font("Panton", Font.PLAIN, 10));
            nbKeyFire.setBounds(125, this.coordy + 18, 50, 50);
            nbKeyFire.setForeground(Color.WHITE);
            this.add(nbKeyFire);
            tabJLabel[this.model.players.indexOf(p)][2] = nbKeyFire;

            //JLABEL FOR ICON "EARTH"
            ImageIcon keyEarth = new ImageIcon(new ImageIcon("./src/images/key_earth.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel labelKeyEarth = new JLabel("E", keyEarth, CENTER);
            labelKeyEarth.setFont(new Font("Panton", Font.PLAIN, 2));
            labelKeyEarth.setBounds(125, this.coordy + 12, 50, 50);
            this.add(labelKeyEarth);
            labelKeyEarth.addMouseListener(ctrl);

            //JLABEL FOR NUMBER KEY "EARTH"
            nbKeyEarth = new JLabel();
            nbKeyEarth.setFont(new Font("Panton", Font.PLAIN, 10));
            nbKeyEarth.setBounds(155, this.coordy + 18, 50, 50);
            nbKeyEarth.setForeground(Color.WHITE);
            this.add(nbKeyEarth);
            tabJLabel[this.model.players.indexOf(p)][3] = nbKeyEarth;

            coordy += 56;
        }

        update();
    }

    /*public static void setVisibleButton(Color c){
        buttonP1.setVisible(true);
        buttonP1.setBackground(c);
    }*/

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = 8;
        for (int i = 0; i < this.model.players.size(); i++) {
            g.setColor(this.model.players.get(i).getColor());
            g.fillOval(5, y, 8, 8);
            y += 56;
        }
    }

    @Override
    public void update() {
        for(Player p : this.model.players) {
            tabJLabel[this.model.players.indexOf(p)][0].setText(String.valueOf(p.nbKeyElement(Cell.Element.AIR)));
            tabJLabel[this.model.players.indexOf(p)][1].setText(String.valueOf(p.nbKeyElement(Cell.Element.WATER)));
            tabJLabel[this.model.players.indexOf(p)][2].setText(String.valueOf(p.nbKeyElement(Cell.Element.FIRE)));
            tabJLabel[this.model.players.indexOf(p)][3].setText(String.valueOf(p.nbKeyElement(Cell.Element.EARTH)));
            this.tabName.get(this.model.players.indexOf(p)).setForeground(Color.gray);
        }
        this.tabName.get(this.model.players.indexOf(this.model.currentPlayer)).setForeground(Color.WHITE);
    }

}
