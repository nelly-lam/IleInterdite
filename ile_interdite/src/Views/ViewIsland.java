package Views;

import Model.Cell;
import Model.Island;

import javax.swing.*;
import java.awt.*;

public class ViewIsland extends JPanel implements Observer {
    public final Island model;
    public ViewPlayer player;
    public static final int size = 25;

    public ViewIsland(Island model) {
        this.model = model;
        this.model.addObserver(this);
        this.player = new ViewPlayer(this.model);
        Dimension dim = new Dimension(this.size*this.model.width, this.size*this.model.height);
        this.setPreferredSize(dim);
    }

    public void paintComponent(Graphics g) {
        super.repaint();
        for(int i = 0; i < this.model.width; i++) {
            for(int j = 0; j < this.model.height; j++) {
                paint(g, model.getCell(i, j), i*this.size, j*this.size);
            }
        }
        player.paintComponent(g);
    }

    private void paint(Graphics g, Cell c, int x, int y) {
        switch(c.state) {
            case Normal:
                g.setColor(new Color(255, 186, 10));
                break;
            case Flooded:
                g.setColor(new Color(130, 159, 217));
                break;
            case Submerged:
                g.setColor(new Color(35, 10, 89));
        }
        g.fillRect(x, y, this.size-2, this.size-2);
    }

    @Override
    public void update() {
        repaint();
    }
}
