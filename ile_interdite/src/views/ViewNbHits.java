package views;

import fonts.PantonFont;
import model.Island;

import javax.swing.*;
import java.awt.*;

public class ViewNbHits extends JPanel implements Observer {
    private final Island model;
    private JLabel nbHits;

    public ViewNbHits (Island model) {
        this.model = model;
        this.model.addObserver(this);

        this.nbHits = new JLabel("3");
        this.nbHits.setFont(PantonFont.getPantonLight().deriveFont(Font.PLAIN, 20));
        this.nbHits.setForeground(Color.WHITE);
        this.setOpaque(false);
        this.add(this.nbHits);
    }

    @Override
    public void update() {
        nbHits.setText(String.valueOf(this.model.getCurrentPlayer().getNbEvents()));
    }
}