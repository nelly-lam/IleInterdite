package Views;

import Model.Island;
import Model.Player;

import javax.swing.*;
import java.awt.*;

public class ViewPlayer extends JPanel {
    public final Island model;

    public ViewPlayer(Island model) {
        this.model = model;
    }

    public void paintComponent(Graphics g) {
        super.repaint();
        for (Player player : this.model.players) {
            paint(g, player, player.getAbs()*ViewIsland.size, player.getOrd()*ViewIsland.size);
        }
    }

    private void paint(Graphics g, Player p, int x, int y) {
        g.setColor(Color.RED);
        g.fillOval(x, y, 10, 10);
    }
}
