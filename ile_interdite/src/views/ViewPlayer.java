package views;

import model.Island;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewPlayer extends JPanel {
    private final Island model;

    public ViewPlayer(Island model) {
        this.model = model;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.repaint();
        ArrayList<Player> tabPlayers = this.model.players;
        for(Player player : tabPlayers) {
            ArrayList<Player> tab = player.playersOnSameCell();
            paint(g, tab, player.getAbs()*ViewIsland.SIZE, player.getOrd()*ViewIsland.SIZE);
        }
    }

    private void paint(Graphics g, ArrayList<Player> p, int x, int y) {
        if(p.size() == 1) {
            g.setColor(p.get(0).getColor());
            g.fillOval(x+8, y+8, 12, 12);
        } else if(p.size() == 2) {
            g.setColor(p.get(0).getColor());
            g.fillOval(x+3, y+8, 11, 11);
            g.setColor(p.get(1).getColor());
            g.fillOval(x+15, y+8, 11, 11);
        } else if(p.size() == 3) {
            g.setColor(p.get(0).getColor());
            g.fillOval(x+3, y+4, 10, 10);
            g.setColor(p.get(1).getColor());
            g.fillOval(x+15, y+4, 10, 10);
            g.setColor(p.get(2).getColor());
            g.fillOval(x+9, y+14, 10, 10);
        } else if(p.size() == 4) {
            g.setColor(p.get(0).getColor());
            g.fillOval(x+4, y+3, 9, 9);
            g.setColor(p.get(1).getColor());
            g.fillOval(x+16, y+3, 9, 9);
            g.setColor(p.get(2).getColor());
            g.fillOval(x+4, y+16, 9, 9);
            g.setColor(p.get(3).getColor());
            g.fillOval(x+16, y+16, 9, 9);
        } else if(p.size() == 5) {
            g.setColor(p.get(0).getColor());
            g.fillOval(x+3, y+3, 8, 8);
            g.setColor(p.get(1).getColor());
            g.fillOval(x+17, y+3, 8, 8);
            g.setColor(p.get(2).getColor());
            g.fillOval(x+10, y+10, 8, 8);
            g.setColor(p.get(3).getColor());
            g.fillOval(x+3, y+18, 8, 8);
            g.setColor(p.get(4).getColor());
            g.fillOval(x+17, y+18, 8, 8);
        } else if(p.size() == 6) {
            g.setColor(p.get(0).getColor());
            g.fillOval(x+5, y+3, 7, 7);
            g.setColor(p.get(1).getColor());
            g.fillOval(x+16, y+3, 7, 7);
            g.setColor(p.get(2).getColor());
            g.fillOval(x+5, y+11, 7, 7);
            g.setColor(p.get(3).getColor());
            g.fillOval(x+16, y+11, 7, 7);
            g.setColor(p.get(4).getColor());
            g.fillOval(x+5, y+19, 7, 7);
            g.setColor(p.get(5).getColor());
            g.fillOval(x+16, y+19, 7, 7);
        } else if(p.size() == 7) {
            g.setColor(p.get(0).getColor());
            g.fillOval(x+6, y+3, 6, 6);
            g.setColor(p.get(1).getColor());
            g.fillOval(x+17, y+3, 6, 6);
            g.setColor(p.get(2).getColor());
            g.fillOval(x+4, y+11, 6, 6);
            g.setColor(p.get(3).getColor());
            g.fillOval(x+11, y+11, 6, 6);
            g.setColor(p.get(4).getColor());
            g.fillOval(x+19, y+11, 6, 6);
            g.setColor(p.get(5).getColor());
            g.fillOval(x+6, y+20, 6, 6);
            g.setColor(p.get(6).getColor());
            g.fillOval(x+17, y+20, 6, 6);
        } else if(p.size() == 8) {
            g.setColor(p.get(0).getColor());
            g.fillOval(x+4, y+3, 5, 5);
            g.setColor(p.get(1).getColor());
            g.fillOval(x+12, y+3, 5, 5);
            g.setColor(p.get(2).getColor());
            g.fillOval(x+20, y+3, 5, 5);
            g.setColor(p.get(3).getColor());
            g.fillOval(x+4, y+11, 5, 5);
            g.setColor(p.get(4).getColor());
            g.fillOval(x+12, y+11, 5, 5);
            g.setColor(p.get(5).getColor());
            g.fillOval(x+20, y+11, 5, 5);
            g.setColor(p.get(6).getColor());
            g.fillOval(x+7, y+20, 5, 5);
            g.setColor(p.get(7).getColor());
            g.fillOval(x+17, y+20, 5, 5);
        }
    }
}
