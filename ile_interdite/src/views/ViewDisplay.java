package views;

import model.Island;

import javax.swing.*;
import java.awt.*;

public class ViewDisplay extends JPanel {
    public final Island model;

    public ViewDisplay(Island model) {
        this.model = model;
        this.setBackground(Color.CYAN);
        this.setLayout(new GridLayout(7, 1));

        JTextArea turnPlayer = new JTextArea();
        //turnPlayer.setBounds(50,25,100,30);
        turnPlayer.setBackground(Color.CYAN);
        turnPlayer.setText("It is " + model.playerCourant.getName() + " turn");
        this.add(turnPlayer);

        JTextArea textAction = new JTextArea();
        textAction.setBackground(Color.CYAN);
        textAction.setText("What do you want to do darling ? Actions : " + this.model.playerCourant.getNbHits());
        this.add(textAction);
        //JScrollPane scrollPane = new JScrollPane(textArea);
        textAction.setEditable(false);

        JTextArea textChoice1 = new JTextArea();
        textChoice1.setBackground(Color.CYAN);
        textChoice1.setText("Move: with directional keys | Dry a cell: with ZWDQ | Acquire an artifact: M");
        this.add(textChoice1);
        //JScrollPane scrollPane = new JScrollPane(textArea);
        textChoice1.setEditable(false);

        JTextArea textSearchKey = new JTextArea();
        textSearchKey.setBackground(Color.CYAN);
        textSearchKey.setText("Now look for a key: S");
        this.add(textSearchKey);
        //JScrollPane scrollPane = new JScrollPane(textArea);
        textSearchKey.setEditable(false);

        JTextArea textEndTurn = new JTextArea();
        textEndTurn.setBackground(Color.CYAN);
        textEndTurn.setText("That's it! Press \"Fin de tour\" ");
        this.add(textEndTurn);
        //JScrollPane scrollPane = new JScrollPane(textArea);
        textEndTurn.setEditable(false);

    }

}
