package views;

import controllers.Controller;
import model.Island;

import javax.swing.*;
import java.awt.*;

public class ViewButton extends JPanel {
    private final Island model;

    public ViewButton(Island model) {
        this.model = model;
        this.setBackground(Color.YELLOW);
        JButton button = new JButton("fin de tour");
        this.add(button);
        Controller ctrl = new Controller(this.model);
        button.addActionListener(ctrl);
    }
}
