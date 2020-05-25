package views;

import model.Island;

import javax.swing.*;
import java.awt.*;

public class ViewDisplay extends JPanel {
    public final Island model;

    public ViewDisplay(Island model) {
        this.model = model;
        this.setBackground(Color.CYAN);

        JTextArea test = new JTextArea("Hello World");
    }
}
