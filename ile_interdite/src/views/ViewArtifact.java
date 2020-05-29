package views;

import fonts.PantonFont;
import model.Cell;
import model.Island;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.SwingConstants.CENTER;

public class ViewArtifact extends JPanel implements Observer {
    private final Island model;
    private static JLabel artifactAir;
    private static JLabel artifactWater;
    private static JLabel artifactFire;
    private static JLabel artifactEarth;
    ImageIcon img1 = new ImageIcon(new ImageIcon("./ile_interdite/src/images/wind.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
    ImageIcon img2 = new ImageIcon(new ImageIcon("./ile_interdite/src/images/water.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
    ImageIcon img3 = new ImageIcon(new ImageIcon("./ile_interdite/src/images/fire.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
    ImageIcon img4 = new ImageIcon(new ImageIcon("./ile_interdite/src/images/earth.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));

    public ViewArtifact(Island model) throws IOException, FontFormatException {
        this.model = model;
        this.model.addObserver(this);
        this.setLayout(null);
        this.setOpaque(false);

        JLabel artifactAir = new JLabel();
        artifactAir.setBounds(0,0,50,50);
        this.add(artifactAir);

        JLabel artifactWater = new JLabel();
        artifactWater.setBounds(0,50,50,50);
        this.add(artifactWater);

        JLabel artifactFire = new JLabel();
        artifactFire.setBounds(0,100,50,50);
        this.add(artifactFire);

        JLabel artifactEarth = new JLabel();
        artifactEarth.setBounds(0,150,50,50);
        this.add(artifactEarth);

        update();
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
        System.out.println("artifact air acquis :" + artiAir);
        System.out.println("artifact eau acquis :" + artiWater);
        System.out.println("artifact feu acquis :" + artiFire);
        System.out.println("artifact terre acquis :" + artiEarth);
        if (artiAir == 1){
            artifactAir = new JLabel("",new ImageIcon(new ImageIcon("./ile_interdite/src/images/wind.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)), CENTER);
        }
        if (artiWater == 1){
            artifactWater = new JLabel("", new ImageIcon(new ImageIcon("./ile_interdite/src/images/wind.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)), CENTER);
        }
        if (artiWater == 1){
            artifactFire = new JLabel("", new ImageIcon(new ImageIcon("./ile_interdite/src/images/wind.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)), CENTER);
        }
        if (artiEarth == 1){
            artifactEarth = new JLabel("", new ImageIcon(new ImageIcon("./ile_interdite/src/images/wind.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)), CENTER);
        }
    }

}
