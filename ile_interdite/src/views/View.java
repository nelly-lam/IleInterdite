package views;

import controllers.ControllerMovement;
import model.Island;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class View {
    private final Island model;
    private final JFrame game;
    private ViewIsland island;
    private ViewCommand command;
    private ViewItem item;

    public View(Island model) {
        this.model = model;
        this.game = new JFrame();
        this.game.setTitle("L'île interdite");
        this.game.setSize(730, 558);
        this.game.setLayout(null);

        this.island = new ViewIsland(this.model);
        this.island.setBounds(240,35,600,400);
        this.game.add(this.island);
        this.command = new ViewCommand(this.model);
        this.command.setBounds(240,520,100,50);
        this.game.add(this.command);
        //this.item = new ViewItem(this.model);
        //this.game.add(this.item, BorderLayout.EAST);

        ImageIcon img = new ImageIcon(new ImageIcon("./src/images/background_game.jpg").getImage().getScaledInstance(720, 520, Image.SCALE_DEFAULT));
        JLabel background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0,0,720,520);
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
