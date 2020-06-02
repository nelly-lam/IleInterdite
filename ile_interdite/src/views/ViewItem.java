package views;

import controllers.ControllerSwapKey;
import fonts.PantonFont;
import model.Cell;
import model.Island;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.SwingConstants.CENTER;

public class ViewItem extends JPanel implements Observer{
    private final Island model;
    private static JLabel nbKeyAir;
    private static JLabel nbKeyWater;
    private static JLabel nbKeyFire;
    private static JLabel nbKeyEarth;
    private static JLabel nbDry;
    private static JLabel nbTeleportation;
    private int coordy = 5;
    private JLabel[][] tabJLabel;
    private ArrayList<JLabel> tabName;

    public ViewItem(Island model) throws IOException, FontFormatException {
        this.model = model;
        this.model.addObserver(this);
        this.tabJLabel = new JLabel[this.model.players.size()][6];
        this.tabName = new ArrayList<>();
        this.setLayout(null);
        this.setOpaque(false);
        ControllerSwapKey ctrl = new ControllerSwapKey(this.model);

        for (Player p : this.model.players) {
            //JLABEL FOR NAME PLAYERS
            JLabel name = new JLabel(p.getName());
            name.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 20));
            name.setBounds(20, this.coordy, 150, 30);
            name.setHorizontalAlignment(SwingConstants.LEFT);
            name.setForeground(Color.GRAY);
            name.addMouseListener(ctrl);
            this.tabName.add(name);
            this.add(name);

            JLabel color = new JLabel();
            this.add(color);

            //JLABEL FOR ICON "DRY"
            ImageIcon dry = new ImageIcon(new ImageIcon("./src/images/sand.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel labeldry = new JLabel("Dry", dry, CENTER);
            labeldry.setFont(new Font("Panton", Font.PLAIN, 0));
            labeldry.setBounds(0, this.coordy + 12, 50, 50);
            labeldry.addMouseListener(ViewIsland.ctrl);
            this.add(labeldry);

            //JLABEL FOR NUMBER KEY "DRY"
            nbDry = new JLabel("0");
            nbDry.setFont(new Font("Panton", Font.PLAIN, 10));
            nbDry.setBounds(30, this.coordy + 18, 50, 50);
            nbDry.setForeground(Color.WHITE);
            tabJLabel[this.model.players.indexOf(p)][0] = nbDry;
            this.add(nbDry);

            //JLABEL FOR ICON "TELEPORTATION"
            ImageIcon teleportation = new ImageIcon(new ImageIcon("./src/images/helicopter.png").getImage().getScaledInstance(18, 18, Image.SCALE_DEFAULT));
            JLabel labelteleportation = new JLabel("Helicopter", teleportation, CENTER);
            labelteleportation.setFont(new Font("Panton", Font.PLAIN, 0));
            labelteleportation.setBounds(28, this.coordy + 12, 50, 50);
            labelteleportation.addMouseListener(ViewIsland.ctrl);
            this.add(labelteleportation);

            //JLABEL FOR NUMBER KEY "TELEPORTATION"
            nbTeleportation = new JLabel("0");
            nbTeleportation.setFont(new Font("Panton", Font.PLAIN, 10));
            nbTeleportation.setBounds(60, this.coordy + 18, 50, 50);
            nbTeleportation.setForeground(Color.WHITE);
            tabJLabel[this.model.players.indexOf(p)][1] = nbTeleportation;
            this.add(nbTeleportation);

            //JLABEL FOR ICON "AIR"
            ImageIcon keyAir = new ImageIcon(new ImageIcon("./src/images/key_air.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel labelKeyAir = new JLabel("A", keyAir, CENTER);
            labelKeyAir.setFont(new Font("Panton", Font.PLAIN, 0));
            labelKeyAir.setBounds(60, this.coordy + 12, 50, 50);
            this.add(labelKeyAir);
            labelKeyAir.addMouseListener(ctrl);

            //JLABEL FOR NUMBER KEY "AIR"
            nbKeyAir = new JLabel();
            nbKeyAir.setFont(new Font("Panton", Font.PLAIN, 10));
            nbKeyAir.setBounds(87, this.coordy + 18, 50, 50);
            nbKeyAir.setForeground(Color.WHITE);
            this.add(nbKeyAir);
            tabJLabel[this.model.players.indexOf(p)][2] = nbKeyAir;

            //JLABEL FOR ICON "WATER"
            ImageIcon keyWater = new ImageIcon(new ImageIcon("./src/images/key_water.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel labelKeyWater = new JLabel("W", keyWater, CENTER);
            labelKeyWater.setFont(new Font("Panton", Font.PLAIN, 0));
            labelKeyWater.setBounds(85, this.coordy + 12, 50, 50);
            this.add(labelKeyWater);
            labelKeyWater.addMouseListener(ctrl);

            //JLABEL FOR NUMBER KEY "WATER"
            nbKeyWater = new JLabel();
            nbKeyWater.setFont(new Font("Panton", Font.PLAIN, 10));
            nbKeyWater.setBounds(112, this.coordy + 18, 50, 50);
            nbKeyWater.setForeground(Color.WHITE);
            this.add(nbKeyWater);
            tabJLabel[this.model.players.indexOf(p)][3] = nbKeyWater;

            //JLABEL FOR ICON "FIRE"
            ImageIcon keyFire = new ImageIcon(new ImageIcon("./src/images/key_fire.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel labelKeyFire = new JLabel("F", keyFire, CENTER);
            labelKeyFire.setFont(new Font("Panton", Font.PLAIN, 0));
            labelKeyFire.setBounds(110, this.coordy + 12, 50, 50);
            this.add(labelKeyFire);
            labelKeyFire.addMouseListener(ctrl);

            //JLABEL FOR NUMBER KEY "FIRE"
            nbKeyFire = new JLabel();
            nbKeyFire.setFont(new Font("Panton", Font.PLAIN, 10));
            nbKeyFire.setBounds(137, this.coordy + 18, 50, 50);
            nbKeyFire.setForeground(Color.WHITE);
            this.add(nbKeyFire);
            tabJLabel[this.model.players.indexOf(p)][4] = nbKeyFire;

            //JLABEL FOR ICON "EARTH"
            ImageIcon keyEarth = new ImageIcon(new ImageIcon("./src/images/key_earth.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel labelKeyEarth = new JLabel("E", keyEarth, CENTER);
            labelKeyEarth.setFont(new Font("Panton", Font.PLAIN, 0));
            labelKeyEarth.setBounds(135, this.coordy + 12, 50, 50);
            this.add(labelKeyEarth);
            labelKeyEarth.addMouseListener(ctrl);

            //JLABEL FOR NUMBER KEY "EARTH"
            nbKeyEarth = new JLabel();
            nbKeyEarth.setFont(new Font("Panton", Font.PLAIN, 10));
            nbKeyEarth.setBounds(162, this.coordy + 18, 50, 50);
            nbKeyEarth.setForeground(Color.WHITE);
            this.add(nbKeyEarth);
            tabJLabel[this.model.players.indexOf(p)][5] = nbKeyEarth;

            coordy += 56;
        }
        update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = 12;
        for (int i = 0; i < this.model.players.size(); i++) {
            g.setColor(this.model.players.get(i).getColor());
            g.fillOval(5, y, 8, 8);
            y += 56;
        }
    }

    @Override
    public void update() {
        for(Player p : this.model.players) {
            tabJLabel[this.model.players.indexOf(p)][0].setText(String.valueOf(p.nbSpecialAction(Player.SpecialAction.SAND)));
            tabJLabel[this.model.players.indexOf(p)][1].setText(String.valueOf(p.nbSpecialAction(Player.SpecialAction.TELEPORTATION)));
            tabJLabel[this.model.players.indexOf(p)][2].setText(String.valueOf(p.nbKeyElement(Cell.Element.AIR)));
            tabJLabel[this.model.players.indexOf(p)][3].setText(String.valueOf(p.nbKeyElement(Cell.Element.WATER)));
            tabJLabel[this.model.players.indexOf(p)][4].setText(String.valueOf(p.nbKeyElement(Cell.Element.FIRE)));
            tabJLabel[this.model.players.indexOf(p)][5].setText(String.valueOf(p.nbKeyElement(Cell.Element.EARTH)));
            this.tabName.get(this.model.players.indexOf(p)).setForeground(new Color(80,80,80));
        }
        this.tabName.get(this.model.players.indexOf(this.model.currentPlayer)).setForeground(Color.WHITE);
    }
}
