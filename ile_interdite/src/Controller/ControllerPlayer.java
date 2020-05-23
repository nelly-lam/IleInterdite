package Controller;

import Model.Island;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerPlayer implements ActionListener {
    private Island model;

    public ControllerPlayer(Island model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.addPlayer("toto");
    }
}
