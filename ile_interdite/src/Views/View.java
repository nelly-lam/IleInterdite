package Views;

import Model.Island;

import javax.swing.*;
import java.awt.*;

public class View {
    private final Island model;
    public JFrame frame;
    //private JLayeredPane layer;
    private ViewIsland island;
    private ViewCommand command;
    //private  ViewPlayer player;

    public View(Island island) {
        this.model = island;
        this.frame = new JFrame();
        this.frame.setTitle("L'île interdite");
        this.frame.setLayout(new GridLayout());
        //this.player = new ViewPlayer(this.model);
        this.island = new ViewIsland(this.model);
        //this.layer = new JLayeredPane();
        //this.layer.add(this.island);
        //this.layer.add(this.player);
        //this.frame.add(this.layer);
        this.frame.add(this.island);
        this.command = new ViewCommand(this.model);
        this.frame.add(this.command);
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }
}
