package views;

import controllers.ControllerMovement;
import model.Island;
import javax.swing.*;
import java.awt.*;

public class View {
    private final Island model;
    private final JFrame frame;
    private ViewIsland island;
    private ViewCommand command;
    private ViewItem item;

    public View(Island island) {
        this.model = island;

        this.frame = new JFrame();
        this.frame.setTitle("L'île interdite");
        this.frame.setLayout(new BorderLayout());

        this.island = new ViewIsland(this.model);
        this.frame.add(this.island, BorderLayout.CENTER);
        this.command = new ViewCommand(this.model);
        this.frame.add(this.command, BorderLayout.SOUTH);
        this.item = new ViewItem(this.model);
        this.frame.add(this.item, BorderLayout.EAST);

        this.frame.addKeyListener(new ControllerMovement(this.model));
        this.frame.setFocusable(true);
        this.frame.requestFocusInWindow();
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }
}
