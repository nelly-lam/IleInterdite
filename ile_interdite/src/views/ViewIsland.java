package views;

import model.Cell;
import model.Island;

import javax.swing.*;
import java.awt.*;

public class ViewIsland extends JPanel implements Observer {
    private final Island model;
    private ViewPlayer player;
    public static final int SIZE = 25;

    public ViewIsland(Island model) {
        this.model = model;
        this.model.addObserver(this);
        this.player = new ViewPlayer(this.model);
        Dimension dim = new Dimension(this.SIZE *this.model.width, this.SIZE *this.model.height);
        this.setPreferredSize(dim);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.repaint();
        for(int i = 0; i < this.model.width; i++) {
            for(int j = 0; j < this.model.height; j++) {
                paint(g, model.getCell(i, j), i*this.SIZE, j*this.SIZE);
            }
        }
        player.paintComponent(g);
    }

    private void paint(Graphics g, Cell c, int x, int y) {
        switch(c.getState()) {
            case NORMAL:
                g.setColor(new Color(255, 186, 10));
                break;
            case FLOODED:
                g.setColor(new Color(130, 159, 217));
                break;
            case SUBMERGED:
                g.setColor(new Color(35, 10, 89));
        }
        g.fillRect(x, y, this.SIZE -2, this.SIZE -2);
    }

    @Override
    public void update() {
        repaint();
    }
}
