package views;

import controllers.ControllerMovement;
import model.Island;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class View {
    private final Island model;
    private final JFrame frame;
    private ViewIsland island;
    private ViewCommand command;

    public View(Island island) {
        this.model = island;
        this.frame = new JFrame();
        this.frame.setTitle("L'île interdite");
        this.frame.setLayout(new BorderLayout());
        this.island = new ViewIsland(this.model);
        this.frame.add(this.island, BorderLayout.CENTER);
        this.command = new ViewCommand(this.model);
        this.frame.add(this.command, BorderLayout.SOUTH);
        this.frame.addKeyListener(new ControllerMovement(this.model));
        this.frame.setFocusable(true);
        this.frame.addFocusListener(
                new FocusListener() {
                    public void focusGained(FocusEvent e) {
                    }
                    public void focusLost(FocusEvent e) {
                        frame.requestFocusInWindow();
                    }
                });
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }
}
