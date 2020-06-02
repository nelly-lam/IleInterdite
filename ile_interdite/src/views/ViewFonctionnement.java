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

    public ViewFonctionnement(boolean fct) throws IOException {
        this.fct = fct;
        this.setTitle("Instructions");
        this.setSize(1000, 680);
        this.setLayout(new BorderLayout());

        if (this.fct == true) {
            BufferedImage image = ImageIO.read(new File("./ile_interdite/src/images/instructions.jpg"));
            ImageIcon img = new ImageIcon(image);
            //ImageIcon img = new ImageIcon(new ImageIcon("./ile_interdite/src/images/instructions.jpg").getImage().getScaledInstance(1000, 680, Image.SCALE_DEFAULT));
            JLabel background = new JLabel("", img, CENTER);
            background.setBounds(0, 0, 1000, 680);
            this.add(background, BorderLayout.CENTER);

        } else if (this.fct == false) {
            BufferedImage image = ImageIO.read(new File("./ile_interdite/src/images/Controles.jpg"));
            ImageIcon img = new ImageIcon(image);
            //ImageIcon img = new ImageIcon(new ImageIcon("./ile_interdite/src/images/Controles.jpg").getImage().getScaledInstance(1000, 680, Image.SCALE_DEFAULT));
            JLabel background = new JLabel("", img, CENTER);
            background.setBounds(0,0,1000,680);
            this.add(background, BorderLayout.CENTER);
        }

        this.setVisible(true);
    }

    /*public class ImagePanel extends JPanel{
        private BufferedImage image;
        public ImagePanel() {
            image = ImageIO.read(new File(monFichierImage));
        }
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, null);
        }
    }
     */
}