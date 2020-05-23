package Views;

import Controller.ControllerMovement;
import Model.Island;
import javax.swing.*;
import java.awt.*;

public class View implements Observer {
    private final Island model;
    public static JFrame frame;
    //private JLayeredPane layer;
    private ViewIsland island;
    private ViewCommand command;

    public View(Island island) {
        this.model = island;
        this.model.addObserver(this);
        this.frame = new JFrame();
        this.frame.setTitle("L'île interdite");
        this.frame.setLayout(new GridLayout());
        this.island = new ViewIsland(this.model);
        this.frame.add(this.island);
        //this.layer = new JLayeredPane();
        //this.layer.add(this.island);
        //this.layer.add(this.player);
        this.command = new ViewCommand(this.model);
        this.frame.add(this.command);
        this.frame.addKeyListener(new ControllerMovement(this.model));
        this.frame.setFocusable(true);
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    @Override
    public void update() {
        this.frame.setFocusable(true);
    }
}
