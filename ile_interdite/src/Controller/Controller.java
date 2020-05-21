package Controller;

import Model.Island;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private final Island island;

    public Controller(Island island) {
        this.island = island;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
