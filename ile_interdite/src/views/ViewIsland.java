package views;

import model.Cell;
import model.Island;

import javax.swing.*;
import java.awt.*;

public class ViewIsland extends JPanel implements Observer {
    private final Island model;
    private ViewPlayer player;
    public static final int SIZE = 35;

    public ViewIsland(Island model) {
        this.model = model;
        this.model.addObserver(this);
        this.player = new ViewPlayer(this.model);
        this.setBounds(400,100,SIZE * this.model.width,SIZE * this.model.height);
        this.setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.repaint();
        for(int i = 0; i < this.model.width; i++) {
            for(int j = 0; j < this.model.height; j++) {
                paint(g, model.getCell(i, j), i*SIZE, j*SIZE);
            }
        }
        player.paintComponent(g);
    }

    private void paint(Graphics g, Cell c, int x, int y) {
        switch(c.getState()) {
            case NORMAL:
                g.setColor(new Color(239, 228, 178, 192));
                break;
            case FLOODED:
                g.setColor(new Color(130, 159, 217));
                break;
            case SUBMERGED:
                g.setColor(new Color(35, 10, 89));
        }
        g.fillRect(x, y, SIZE -6, SIZE -6);

        if(c.hasArtifact()) {
            switch (c.getArtifact()) {
                case FIRE:
                    g.setColor(Color.RED);
                    break;
                case WATER:
                    g.setColor(Color.CYAN);
                    break;
                case EARTH:
                    g.setColor(Color.GREEN);
                    break;
                case AIR:
                    g.setColor(Color.WHITE);
                    break;
            }
            g.fillRoundRect(x, y, SIZE - 20, SIZE - 20, 6, 6);
        }
        if (c.isHeliport()) {
            g.setColor(Color.BLACK);
            g.fillOval(x, y, SIZE - 12, SIZE - 12);
            g.setColor(Color.WHITE);
            g.drawOval(x, y, SIZE - 12, SIZE - 12);
            g.drawString("H", x, y);
        }
    }

    @Override
    public void update() {
        repaint();
    }
}
