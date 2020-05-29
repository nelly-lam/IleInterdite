package views;

import fonts.PantonFont;
import model.Cell;
import model.Island;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.SwingConstants.CENTER;

public class ViewArtifact extends JPanel implements Observer{
    private final Island model;
    private static ImageIcon img1 = new ImageIcon(new ImageIcon("./ile_interdite/src/images/wind.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
    private static ImageIcon img2 = new ImageIcon(new ImageIcon("./ile_interdite/src/images/water.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
    private static ImageIcon img3 = new ImageIcon(new ImageIcon("./ile_interdite/src/images/fire.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
    private static ImageIcon img4 = new ImageIcon(new ImageIcon("./ile_interdite/src/images/earth.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));

    public ViewArtifact(Island model) {
        this.model = model;
        this.model.addObserver(this);
        this.setLayout(null);
        this.setOpaque(false);

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
        if (artiAir == 1 ){
            //artifactAir = new JLabel("",new ImageIcon(new ImageIcon("./ile_interdite/src/images/wind.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)), CENT ER);
            //artifactAir.setIcon(img1);
            JLabel artifactAir = new JLabel("", img1, CENTER);
            artifactAir.setBounds(0,0,50,50);
            this.add(artifactAir);
        }
         if (artiWater == 1){
            //artifactWater = new JLabel("", new ImageIcon(new ImageIcon("./ile_interdite/src/images/wind.png").getImage().getScaledInstance( 25, 25, Image.JLabel SCALE_DEFAULT)), CENTER);
             JLabel artifactWater = new JLabel("", img2, CENTER);
            artifactWater.setBounds(0,50,50,50);
            this.add(artifactWater);
        }
        if (artiFire == 1){
           //artifactFire = new JLabel("", new ImageIcon(new ImageIcon("./ile_interdite/src/images/wind.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)), CENTER);
            JLabel artifactFire = new JLabel("", img3, CENTER);
            artifactFire.setBounds(0,100,50,50);
            this.add(artifactFire);
        }
        if (artiEarth == 1){
           //artifactEarth = new JLabel("", new ImageIcon(new ImageIcon("./ile_interdite/src/images/wind.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)), CENTER);
            JLabel artifactEarth = new JLabel("", img4, CENTER);
            artifactEarth.setBounds(0,150,50,50);artifactEarth = new JLabel("", img4, CENTER);
            artifactEarth.setBounds(0,150,50,50);

            this.add(artifactEarth);
        }
    }
}
