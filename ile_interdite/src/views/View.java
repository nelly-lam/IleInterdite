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

       /* |-----------------------------------------------------|
          |                   L'ile interdite !!!!'             |
          |                                                     |
          |------------------------      -----------------------|
          |   Ajouter un joueur   |      |        Jouer         |
          |------------------------      -----------------------|
          | Joueur 1 : Patrick                                  |
          | Joueur 2 : Ancelle           -----------------------|
          | Joueur 3 : Janine            |       Notice         |
          |-----------------------------------------------------|
        */

        this.game.setTitle("L'île interdite");
        this.game.setLayout(new BorderLayout());

        this.island = new ViewIsland(this.model);
        this.game.add(this.island, BorderLayout.CENTER);
        this.command = new ViewCommand(this.model);
        this.game.add(this.command, BorderLayout.SOUTH);
        this.item = new ViewItem(this.model);
        this.game.add(this.item, BorderLayout.EAST);

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
                });
        this.game.pack();
        this.game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.game.setVisible(true);
    }
}
