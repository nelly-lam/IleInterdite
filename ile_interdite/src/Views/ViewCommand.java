package Views;

import Model.Island;
import Controller.Controller;

import javax.swing.*;

public class ViewCommand extends JPanel {
    private final Island model;

    public ViewCommand(Island model) {
        this.model = model;
        JButton button = new JButton("fin de tour");
        this.add(button);
        Controller ctrl = new Controller(this.model);
        button.addActionListener(ctrl);
    }
}
