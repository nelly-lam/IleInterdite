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

        /*JButton buttonAdd = new JButton("Ajouter un joueur");
        this.add(buttonAdd);
        ControllerPlayer ctrlPlayer = new ControllerPlayer(this.model);
        buttonAdd.addActionListener(ctrlPlayer);

        JButton buttonPlay = new JButton("Jouer");
        this.add(buttonPlay);
        ControllerPlay ctrlPlay = new ControllerPlay(this.model);
        buttonPlay.addActionListener(ctrlPlay);*/
    }
}
