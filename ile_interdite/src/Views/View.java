package Views;

import Model.Island;

import javax.swing.*;
import java.awt.*;

public class View {
    private final Island model;
    private JFrame frame;
    private ViewGrid grid;
    private ViewCommand command;

    public View(Island island) {
        this.model = island;
        this.frame = new JFrame();
        this.frame.setTitle("L'île interdite");
        this.frame.setLayout(new GridLayout());
        this.grid = new ViewGrid(this.model);
        this.frame.add(this.grid);
        this.command = new ViewCommand(this.model);
        this.frame.add(this.command);
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

}
