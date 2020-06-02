package views;

import model.Island;

import javax.swing.*;
import java.awt.*;

public class ViewNbHits extends JPanel {
    private final Island model;
    private static JLabel nbHits = new JLabel("3");

    public ViewNbHits (Island model) {
        this.model = model;

        nbHits.setFont(new Font("Panton", Font.PLAIN, 20));
        nbHits.setForeground(Color.WHITE);
        this.setOpaque(false);
        this.add(nbHits);
    }

    public static void updateNbHits(int nb) {
        nbHits.setText(String.valueOf(nb));
    }
}