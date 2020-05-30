package views;

import model.Cell;
import model.Island;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingConstants.CENTER;

public class ViewArtifact extends JPanel implements Observer{
    private final Island model;
    private JLabel artifactAir;
    private JLabel artifactWater;
    private JLabel artifactFire;
    private JLabel artifactEarth;

    public ViewArtifact(Island model) {
        this.model = model;
        this.model.addObserver(this);
        this.setLayout(null);
        this.setOpaque(false);

        artifactAir = new JLabel("", new ImageIcon(new ImageIcon("./src/images/wind.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)), CENTER);
        artifactAir.setBounds(0,0,50,50);
        artifactAir.setVisible(false);
        this.add(artifactAir);

        artifactWater = new JLabel("", new ImageIcon(new ImageIcon("./src/images/water.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)), CENTER);
        artifactWater.setBounds(0,50,50,50);
        artifactWater.setVisible(false);
        this.add(artifactWater);

        artifactFire = new JLabel("", new ImageIcon(new ImageIcon("./src/images/fire.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)), CENTER);
        artifactFire.setBounds(0,100,50,50);
        artifactFire.setVisible(false);
        this.add(artifactFire);

        artifactEarth = new JLabel("", new ImageIcon(new ImageIcon("./src/images/earth.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)), CENTER);
        artifactEarth.setBounds(0,150,50,50);
        artifactEarth.setVisible(false);
        this.add(artifactEarth);
    }

    @Override
    public void update() {
        int artiAir = 0;
        int artiWater = 0;
        int artiFire = 0;
        int artiEarth = 0;
        for (int i = 0; i < this.model.players.size(); i++){
            artiAir += this.model.players.get(i).nbArtifactElement(Cell.Element.AIR);
            artiWater += this.model.players.get(i).nbArtifactElement(Cell.Element.WATER);
            artiFire += this.model.players.get(i).nbArtifactElement(Cell.Element.FIRE);
            artiEarth += this.model.players.get(i).nbArtifactElement(Cell.Element.EARTH);
        }

        if (artiAir == 1 ){
            artifactAir.setVisible(true);
        }
        if (artiWater == 1){
             artifactWater.setVisible(true);
        }
        if (artiFire == 1){
            artifactFire.setVisible(true);
        }
        if (artiEarth == 1){
            artifactEarth.setVisible(true);
        }
    }
}
