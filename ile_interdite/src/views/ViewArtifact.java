package views;

import fonts.PantonFont;
import model.Island;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.SwingConstants.CENTER;

public class ViewArtifact extends JPanel implements Observer {
    private final Island model;

    public ViewArtifact(Island model) throws IOException, FontFormatException {
        this.model = model;
        this.model.addObserver(this);

        ImageIcon img1 = new ImageIcon(new ImageIcon("").getImage().getScaledInstance(864, 648, Image.SCALE_DEFAULT));
        JLabel artifactAir = new JLabel("", img1, CENTER);
        artifactAir.setBounds(0,0,50,50);
        this.add(artifactAir);

        ImageIcon img2 = new ImageIcon(new ImageIcon("").getImage().getScaledInstance(864, 648, Image.SCALE_DEFAULT));
        JLabel artifactWater = new JLabel("", img2, CENTER);
        artifactWater.setBounds(0,40,50,50);
        this.add(artifactWater);

        ImageIcon img3 = new ImageIcon(new ImageIcon("").getImage().getScaledInstance(864, 648, Image.SCALE_DEFAULT));
        JLabel artifactFire = new JLabel("", img3, CENTER);
        artifactFire.setBounds(0,80,50,50);
        this.add(artifactFire);

        ImageIcon img4 = new ImageIcon(new ImageIcon("").getImage().getScaledInstance(864, 648, Image.SCALE_DEFAULT));
        JLabel artifactEarth = new JLabel("", img4, CENTER);
        artifactEarth.setBounds(0,120,50,50);
        this.add(artifactEarth);

        JLabel nbArtifactAir = new JLabel();
        nbArtifactAir.setFont(PantonFont.getPanton().deriveFont(Font.PLAIN, 14));
        nbArtifactAir.setBounds(740,320,100,35);
        nbArtifactAir.setForeground(Color.WHITE);
        this.add(nbArtifactAir);
    }


    @Override
    public void update() {

    }
}
