package views;

import controllers.Controller;
import model.Island;

import javax.swing.*;
import java.awt.*;

public class ViewButton extends JPanel {
    private final Island model;

    public ViewButton(Island model) {
        this.model = model;
        JButton button = new JButton("FIN DE TOUR");

        button.setPreferredSize(new Dimension(146,40));
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        this.setOpaque(false);
        this.add(button);
        Controller ctrl = new Controller(this.model);
        button.addActionListener(ctrl);
    }
}
