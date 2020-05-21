package Controller;

import Model.Island;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private final Island model;

    public Controller(Island model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.risingWater();
    }
}
