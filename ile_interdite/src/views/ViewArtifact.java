package views;

import model.Cell;
import model.Island;
import model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static javax.swing.SwingConstants.CENTER;

public class ViewArtifact extends JPanel implements Observer{
    private final Island model;
    private String[] fileName = {"wind", "water", "fire", "earth"};
    private ArrayList<JLabel> images;

    public ViewArtifact(Island model) {
        this.model = model;
        this.model.addObserver(this);
        this.setLayout(null);
        this.setOpaque(false);
        this.images = new ArrayList<>();

        int coordy = 0;
        for(int i = 0; i < 4; i++) {
            JLabel artifact = new JLabel("", new ImageIcon(new ImageIcon("./src/images/"+fileName[i]+".png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)), CENTER);
            artifact.setBounds(0,coordy,50,50);
            artifact.setVisible(false);
            this.add(artifact);
            this.images.add(artifact);
            coordy += 50;
        }
    }

    @Override
    public void update() {
        for (Player p : this.model.getPlayers()) {
            if (p.hasArtifact(Cell.Element.AIR)) {
                System.out.println("air");
                this.images.get(0).setVisible(true);
            } else if (p.hasArtifact(Cell.Element.WATER)) {
                System.out.println("water");
                this.images.get(1).setVisible(true);
            } else if (p.hasArtifact(Cell.Element.FIRE)) {
                System.out.println("dire");
                this.images.get(2).setVisible(true);
            } else if (p.hasArtifact(Cell.Element.EARTH)) {
                System.out.println("earth");
                this.images.get(3).setVisible(true);
            }
        }
    }
}
