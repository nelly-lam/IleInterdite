package views;

import controllers.ControllerMovement;
import model.Island;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ViewGame {
    private final Island model;
    private final JFrame game;
    private ViewIsland island;
    private ViewButton button;
    private ViewNbHits nbHits;
    private ViewItem item;
    //private ViewArtifact artifact;

    public ViewGame(Island model) {
        this.model = model;
        this.game = new JFrame();
        this.game.setTitle("L'île interdite");
        this.game.setSize(875, 686);
        this.game.setLayout(null);

        this.island = new ViewIsland(this.model);
        this.island.setBounds(336,84,420,420);
        this.game.add(this.island);

        this.button = new ViewButton(this.model);
        this.button.setBounds(470,545,147,39);
        this.game.add(this.button);

        this.nbHits = new ViewNbHits(this.model);
        this.nbHits.setBounds(800,110,50,50);
        this.game.add(this.nbHits);

        this.item = new ViewItem(this.model);
        this.item.setBounds(30,135,178,452);
        this.game.add(this.item);

        //this.artifact = new ViewArtifact(this.model);

        //this.artefact = new ViewArtifact(this.model);
        //this.game.add(this.artefact);

        ImageIcon img = new ImageIcon(new ImageIcon("./src/images/background_game.jpg").getImage().getScaledInstance(864, 648, Image.SCALE_DEFAULT));
        JLabel background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0,0,864,648);
        this.game.add(background);

        this.game.addKeyListener(new ControllerMovement(this.model));
        this.game.setFocusable(true);
        this.game.addFocusListener(
                new FocusListener() {
                    public void focusGained(FocusEvent e) {
                        // Nous n'avons pas besoin de cette méthode
                    }
                    public void focusLost(FocusEvent e) {
                        game.requestFocusInWindow();
                    }
                }
        );

        this.game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.game.setVisible(true);
    }
}
