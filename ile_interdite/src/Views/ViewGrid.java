package Views;

import Model.Cell;
import Model.Island;
import Model.Location;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ViewGrid extends JPanel implements Observer {
    private final Island model;
    private static final int taille = 25;

    public ViewGrid(Island model) {
        this.model = model;
        this.model.addObserver(this);
        Dimension dim = new Dimension(this.taille*this.model.width, this.taille*this.model.height);
        this.setPreferredSize(dim);
    }

    public void paintComponent(Graphics g) {
        super.repaint();
        for(int i = 0; i < this.model.width; i++) {
            for(int j = 0; j < this.model.height; j++) {
                paint(g, this.model.board.get(new Location(i,j)), (i)*this.taille, (j)*this.taille);
            }
        }
    }

    private void paint(Graphics g, Cell c, int x, int y) {
        switch(c.state) {
            case Normal:
                g.setColor(Color.WHITE);
                break;
            case Flooded:
                g.setColor(Color.BLUE);
                break;
            case Submerged:
                g.setColor(Color.BLACK);
        }
        g.fillRect(x, y, this.taille, this.taille);
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
