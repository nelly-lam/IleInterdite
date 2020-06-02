package views;

import model.Cell;
import model.Island;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.SwingConstants.CENTER;

public class ViewItem extends JPanel implements Observer{
    private final Island model;
    private int coordy = 2;
    private int coordx;
    private JLabel[][] tabJLabel;
    private ArrayList<JLabel> tabName;
    private String[] filename = {"./src/images/key_fire.png", "./src/images/key_water.png", "./src/images/key_earth.png", "./src/images/key_air.png"};

    public ViewItem(Island model) {
        this.model = model;
        this.model.addObserver(this);
        tabJLabel = new JLabel[this.model.players.size()][4];
        this.tabName = new ArrayList<>();
        this.setLayout(null);
        this.setOpaque(false);

        for(Player p : this.model.players) {
            JLabel name = new JLabel(p.getName());
            name.setFont(new Font("Panton", Font.PLAIN, 20));
            name.setBounds(20,this.coordy,200,30);
            name.setForeground(Color.GRAY);
            this.tabName.add(name);
            this.add(name);
            JLabel color = new JLabel();
            this.add(color);

            this.coordx = 35;

            for(int i = 0; i < 4; i ++) {
                ImageIcon key = new ImageIcon(new ImageIcon(this.filename[i]).getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
                JLabel labelKey = new JLabel("", key, CENTER);
                labelKey.setBounds(this.coordx,this.coordy+12,50,50);
                this.add(labelKey);
                JLabel nbKey = new JLabel();
                nbKey.setFont(new Font("Panton", Font.PLAIN, 10));
                nbKey.setBounds(this.coordx+30,this.coordy+18,50,50);
                nbKey.setForeground(Color.WHITE);
                tabJLabel[this.model.players.indexOf(p)][i] = nbKey;
                this.add(nbKey);
                this.coordx += 30;
            }
            this.coordy += 56;
        }
        repaint();
        update();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int y = 8;
        for (int i = 0; i < this.model.players.size(); i++){
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
        //this.tabName.get(this.model.players.indexOf(this.model.playerCourant)).setForeground(Color.WHITE);
    }
}