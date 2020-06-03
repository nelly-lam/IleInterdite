package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.swing.SwingConstants.CENTER;

public class ViewFonctionnement extends JFrame {
    private final boolean fct;

    public ViewFonctionnement(boolean fct) {
        this.fct = fct;
        this.setSize(1000, 700);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        JLabel background = new JLabel();
        background.setSize(1000, 700);

        if(this.fct) {
            this.setTitle("Instructions");
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("./src/images/instructions.jpg"));
            } catch(IOException e) {
                System.out.println("Problème lors de la lecture du fichier \"instructions\"");
            }
            Image dimg = image.getScaledInstance(background.getWidth(), background.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon img = new ImageIcon(dimg);
            background = new JLabel("Instructions", img, CENTER);

        } else {
            this.setTitle("Contrôles");
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("./src/images/Controles.jpg"));
            } catch (IOException e) {
                System.out.println("Problème lors de la lecture du fichier \"Contrôles\"");
            }
            Image dimg = image.getScaledInstance(background.getWidth(), background.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon img = new ImageIcon(dimg);
            background = new JLabel("Contrôles", img, CENTER);

        }
        this.add(background, BorderLayout.CENTER);
        this.setVisible(true);
    }
}



