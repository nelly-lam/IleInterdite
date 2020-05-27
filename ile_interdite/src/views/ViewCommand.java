package views;

import model.Island;

import javax.swing.*;
import java.awt.*;

public class ViewCommand extends JPanel {
    private final Island model;
    private ViewButton button;
    private ViewDisplay display;

    public ViewCommand(Island model) {
        this.model = model;

        //this.setLayout(new FlowLayout());
        //this.display = new ViewDisplay(this.model);
        //this.add(display);
        this.button = new ViewButton(this.model);
        this.add(button);
    }
}
