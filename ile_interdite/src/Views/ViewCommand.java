package Views;

import Model.Island;
import Controller.Controller;
import Controller.ControllerPlayer;
import Controller.ControllerPlay;

import javax.swing.*;

public class ViewCommand extends JPanel {
    private final Island model;

    public ViewCommand(Island model) {
        this.model = model;
        JButton button = new JButton("fin de tour");
        this.add(button);
        Controller ctrl = new Controller(this.model);
        button.addActionListener(ctrl);

        JButton buttonAdd = new JButton("Ajouter un joueur");
        this.add(buttonAdd);
        ControllerPlayer ctrlPlayer = new ControllerPlayer(this.model);
        buttonAdd.addActionListener(ctrlPlayer);

        JButton buttonPlay = new JButton("Jouer");
        this.add(buttonPlay);
        ControllerPlay ctrlPlay = new ControllerPlay(this.model);
        buttonPlay.addActionListener(ctrl);
    }
}
