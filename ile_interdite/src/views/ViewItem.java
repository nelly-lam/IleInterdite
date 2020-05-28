package views;

import model.Cell;
import model.Island;
import model.Player;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.SwingConstants.CENTER;

public class ViewItem extends JPanel implements Observer{
    private final Island model;
    private JPanel panKey;
    private JTable tableKey;
    private Object[][] listKey;
    private JPanel panArtifact;
    private JTable tableArtifact;
    private Object[][] listArtifact;
    private int nbPlayer;
    private String[] header = { "Joueurs", "AIR", "EAU", "FEU", "TERRE" };
    private int sizeCourante = 2;
    private static final int SIZE = 8;

    private ArrayList<JLabel> mmmm;

    public ViewItem(Island model) {
        this.model = model;
        this.model.addObserver(this);
        this.setLayout(null);
        this.setOpaque(false);

        for(Player p : this.model.players) {
            JLabel name = new JLabel(p.getName());
            name.setFont(new Font("Panton", Font.PLAIN, 20));
            name.setBounds(20,this.sizeCourante,200,30);
            name.setForeground(Color.WHITE);
            this.add(name);
            JLabel lblb = new JLabel();
            lblb.setForeground(p.getColor());
            this.add(lblb);

            ImageIcon keyFire = new ImageIcon(new ImageIcon("./src/images/key_fire.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel labelKeyFire = new JLabel("", keyFire, CENTER);
            labelKeyFire.setBounds(35,this.sizeCourante+12,50,50);
            this.add(labelKeyFire);
            JLabel nbKeyFire = new JLabel("0");
            nbKeyFire.setFont(new Font("Panton", Font.PLAIN, 10));
            nbKeyFire.setBounds(65,this.sizeCourante+18,50,50);
            nbKeyFire.setForeground(Color.WHITE);
            this.add(nbKeyFire);

            ImageIcon keyWater = new ImageIcon(new ImageIcon("./src/images/key_water.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel labelKeyWater = new JLabel("", keyWater, CENTER);
            labelKeyWater.setBounds(65,this.sizeCourante+12,50,50);
            this.add(labelKeyWater);
            JLabel nbKeyWater = new JLabel("0");
            nbKeyWater.setFont(new Font("Panton", Font.PLAIN, 10));
            nbKeyWater.setBounds(95,this.sizeCourante+18,50,50);
            nbKeyWater.setForeground(Color.WHITE);
            this.add(nbKeyWater);

            ImageIcon keyEarth = new ImageIcon(new ImageIcon("./src/images/key_earth.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel labelKeyEarth = new JLabel("", keyEarth, CENTER);
            labelKeyEarth.setBounds(95,this.sizeCourante+12,50,50);
            this.add(labelKeyEarth);
            JLabel nbKeyEarth = new JLabel("0");
            nbKeyEarth.setFont(new Font("Panton", Font.PLAIN, 10));
            nbKeyEarth.setBounds(125,this.sizeCourante+18,50,50);
            nbKeyEarth.setForeground(Color.WHITE);
            this.add(nbKeyEarth);

            ImageIcon keyAir = new ImageIcon(new ImageIcon("./src/images/key_air.png").getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
            JLabel labelKeyAir = new JLabel("", keyAir, CENTER);
            labelKeyAir.setBounds(125,this.sizeCourante+12,50,50);
            this.add(labelKeyAir);
            JLabel nbKeyAir = new JLabel("0");
            nbKeyAir.setFont(new Font("Panton", Font.PLAIN, 10));
            nbKeyAir.setBounds(155,this.sizeCourante+18,50,50);
            nbKeyAir.setForeground(Color.WHITE);
            this.add(nbKeyAir);

            this.sizeCourante += 56;
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int height = 8;
        for (int i = 0; i < this.model.players.size(); i++){
            paint(g, this.model.players.get(i),5, height);
            height += 56;
        }
    }

    public void paint(Graphics g, Player p, int x, int y){
        g.setColor(p.getColor());
        g.fillOval(x, y, SIZE, SIZE);
    }

    @Override
    public void update() { repaint(); }
}