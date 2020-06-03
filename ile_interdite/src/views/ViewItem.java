package views;

import controllers.ControllerSwapKey;
import fonts.PantonFont;
import model.Cell;
import model.Island;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.SwingConstants.CENTER;

public class ViewItem extends JPanel implements Observer{
    private final Island model;
    private JLabel[][] tabJLabel;
    private ArrayList<JLabel> tabName;

    public ViewItem(Island model) {
        this.model = model;
        this.model.addObserver(this);
        this.tabJLabel = new JLabel[this.model.getPlayers().size()][6];
        this.tabName = new ArrayList<>();
        this.setLayout(null);
        this.setOpaque(false);
        ControllerSwapKey ctrl = new ControllerSwapKey(this.model);

        int coordy = 5;
        for (Player p : this.model.getPlayers()) {
            //JLABEL FOR NAME PLAYERS
            JLabel name = new JLabel(p.getName());
            name.setFont(PantonFont.getPanton().deriveFont(20));
            name.setBounds(20, coordy, 150, 30);
            name.setHorizontalAlignment(SwingConstants.LEFT);
            name.addMouseListener(ctrl);
            this.tabName.add(name);
            this.add(name);

            JLabel color = new JLabel();
            this.add(color);

            //JLABEL FOR ICON "DRY"
            ImageIcon dry = new ImageIcon(new ImageIcon("./src/images/sand.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel labeldry = new JLabel("Dry", dry, CENTER);
            labeldry.setFont(new Font("Panton", Font.PLAIN, 0));
            labeldry.setBounds(15, coordy + 12, 20, 40);
            labeldry.addMouseListener(ViewIsland.ctrl);
            this.add(labeldry);

            //JLABEL FOR NUMBER KEY "DRY"
            JLabel nbDry = new JLabel();
            nbDry.setFont(PantonFont.getPantonLight().deriveFont(10));
            nbDry.setBounds(30, coordy + 18, 20, 40);
            nbDry.setForeground(Color.WHITE);
            tabJLabel[this.model.getPlayers().indexOf(p)][0] = nbDry;
            this.add(nbDry);

            //JLABEL FOR ICON "TELEPORTATION"
            ImageIcon teleportation = new ImageIcon(new ImageIcon("./src/images/helicopter.png").getImage().getScaledInstance(18, 18, Image.SCALE_DEFAULT));
            JLabel labelteleportation = new JLabel("Helicopter", teleportation, CENTER);
            labelteleportation.setFont(new Font("Panton", Font.PLAIN, 0));
            labelteleportation.setBounds(42, coordy + 12, 20, 40);
            labelteleportation.addMouseListener(ViewIsland.ctrl);
            this.add(labelteleportation);

            //JLABEL FOR NUMBER KEY "TELEPORTATION"
            JLabel nbTeleportation = new JLabel();
            nbTeleportation.setFont(PantonFont.getPantonLight().deriveFont(10));
            nbTeleportation.setBounds(60, coordy + 18, 20, 40);
            nbTeleportation.setForeground(Color.WHITE);
            tabJLabel[this.model.getPlayers().indexOf(p)][1] = nbTeleportation;
            this.add(nbTeleportation);

            //JLABEL FOR ICON "AIR"
            ImageIcon keyAir = new ImageIcon(new ImageIcon("./src/images/key_air.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel labelKeyAir = new JLabel("A", keyAir, CENTER);
            labelKeyAir.setFont(new Font("Panton", Font.PLAIN, 0));
            labelKeyAir.setBounds(75, coordy + 12, 20, 40);
            this.add(labelKeyAir);
            labelKeyAir.addMouseListener(ctrl);

            //JLABEL FOR NUMBER KEY "AIR"
            JLabel nbKeyAir = new JLabel();
            nbKeyAir.setFont(PantonFont.getPantonLight().deriveFont(10));
            nbKeyAir.setBounds(87, coordy + 18, 20, 40);
            nbKeyAir.setForeground(Color.WHITE);
            this.add(nbKeyAir);
            tabJLabel[this.model.getPlayers().indexOf(p)][2] = nbKeyAir;

            //JLABEL FOR ICON "WATER"
            ImageIcon keyWater = new ImageIcon(new ImageIcon("./src/images/key_water.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel labelKeyWater = new JLabel("W", keyWater, CENTER);
            labelKeyWater.setFont(new Font("Panton", Font.PLAIN, 0));
            labelKeyWater.setBounds(100, coordy + 12, 20, 40);
            this.add(labelKeyWater);
            labelKeyWater.addMouseListener(ctrl);

            //JLABEL FOR NUMBER KEY "WATER"
            JLabel nbKeyWater = new JLabel();
            nbKeyWater.setFont(PantonFont.getPantonLight().deriveFont(10));
            nbKeyWater.setBounds(112, coordy + 18, 20, 40);
            nbKeyWater.setForeground(Color.WHITE);
            this.add(nbKeyWater);
            tabJLabel[this.model.getPlayers().indexOf(p)][3] = nbKeyWater;

            //JLABEL FOR ICON "FIRE"
            ImageIcon keyFire = new ImageIcon(new ImageIcon("./src/images/key_fire.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel labelKeyFire = new JLabel("F", keyFire, CENTER);
            labelKeyFire.setFont(new Font("Panton", Font.PLAIN, 0));
            labelKeyFire.setBounds(125, coordy + 12, 20, 40);
            this.add(labelKeyFire);
            labelKeyFire.addMouseListener(ctrl);

            //JLABEL FOR NUMBER KEY "FIRE"
            JLabel nbKeyFire = new JLabel();
            nbKeyFire.setFont(PantonFont.getPantonLight().deriveFont(10));
            nbKeyFire.setBounds(137, coordy + 18, 20, 40);
            nbKeyFire.setForeground(Color.WHITE);
            this.add(nbKeyFire);
            tabJLabel[this.model.getPlayers().indexOf(p)][4] = nbKeyFire;

            //JLABEL FOR ICON "EARTH"
            ImageIcon keyEarth = new ImageIcon(new ImageIcon("./src/images/key_earth.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel labelKeyEarth = new JLabel("E", keyEarth, CENTER);
            labelKeyEarth.setFont(new Font("Panton", Font.PLAIN, 0));
            labelKeyEarth.setBounds(150, coordy + 12, 20, 40);
            this.add(labelKeyEarth);
            labelKeyEarth.addMouseListener(ctrl);

            //JLABEL FOR NUMBER KEY "EARTH"
            JLabel nbKeyEarth = new JLabel();
            nbKeyEarth.setFont(PantonFont.getPantonLight().deriveFont(10));
            nbKeyEarth.setBounds(162, coordy + 18, 20, 40);
            nbKeyEarth.setForeground(Color.WHITE);
            this.add(nbKeyEarth);
            tabJLabel[this.model.getPlayers().indexOf(p)][5] = nbKeyEarth;

            coordy += 56;
        }
        this.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = 12;
        for (int i = 0; i < this.model.getPlayers().size(); i++) {
            g.setColor(this.model.getPlayers().get(i).getColor());
            g.fillOval(5, y, 8, 8);
            y += 56;
        }
    }

    @Override
    public void update() {
        for(Player p : this.model.getPlayers()) {
            tabJLabel[this.model.getPlayers().indexOf(p)][0].setText(String.valueOf(p.nbSpecialAction(Player.SpecialAction.SAND)));
            tabJLabel[this.model.getPlayers().indexOf(p)][1].setText(String.valueOf(p.nbSpecialAction(Player.SpecialAction.TELEPORTATION)));
            tabJLabel[this.model.getPlayers().indexOf(p)][2].setText(String.valueOf(p.nbKeyElement(Cell.Element.AIR)));
            tabJLabel[this.model.getPlayers().indexOf(p)][3].setText(String.valueOf(p.nbKeyElement(Cell.Element.WATER)));
            tabJLabel[this.model.getPlayers().indexOf(p)][4].setText(String.valueOf(p.nbKeyElement(Cell.Element.FIRE)));
            tabJLabel[this.model.getPlayers().indexOf(p)][5].setText(String.valueOf(p.nbKeyElement(Cell.Element.EARTH)));
            this.tabName.get(this.model.getPlayers().indexOf(p)).setForeground(new Color(80,80,80));
        }
        this.tabName.get(this.model.getPlayers().indexOf(this.model.getCurrentPlayer())).setForeground(Color.WHITE);
    }
}