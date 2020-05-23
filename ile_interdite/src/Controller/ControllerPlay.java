package Controller;

import Model.Island;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerPlay implements ActionListener {
    private Island model;

    public ControllerPlay(Island model) {
        this.model = model;
    }

    /**
     * Invoked when an action occurs.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.model.play();
    }
}
