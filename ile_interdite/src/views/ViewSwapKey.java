package views;

import controllers.ControllerAddPlayer;
import controllers.ControllerSwapKey;
import model.Cell;
import model.Island;
import model.Observable;

import javax.swing.*;
import java.awt.*;

public class ViewSwapKey extends JPanel implements Observer {
    private final Island model;
    private JFrame swapKey;
    private static JLabel nbKeyAir;
    private static JLabel nbKeyWater;
    private static JLabel nbKeyFire;
    private static JLabel nbKeyEarth;

    public ViewSwapKey(Island model){
        this.model = model;
        this.model.addObserver(this);
        this.swapKey = new JFrame();
        this.swapKey.setTitle("Echange de clés");
        this.swapKey.setSize(550, 400);
        this.swapKey.setLayout(null);

        JButton buttonAir = new JButton("A");
        buttonAir.setBounds(20,28,200,80);
        buttonAir.setForeground(Color.WHITE);
        this.add(buttonAir);
        buttonAir.addActionListener(new ControllerSwapKey(this.model, swapKey));
        this.swapKey.add(buttonAir);
        nbKeyAir = new JLabel();
        nbKeyAir.setBounds(40,28,50,50);
        this.add(nbKeyAir);

        JButton buttonWater = new JButton("W");
        buttonWater.setBounds(50,28,200,80);
        buttonWater.setForeground(Color.WHITE);
        this.add(buttonWater);
        buttonWater.addActionListener(new ControllerSwapKey(this.model, swapKey));
        this.swapKey.add(buttonWater);
        nbKeyWater = new JLabel();
        nbKeyWater.setBounds(70,28,50,50);
        this.add(nbKeyWater);

        JButton buttonFire = new JButton("F");
        buttonFire.setBounds(80,28,200,80);
        buttonFire.setForeground(Color.WHITE);
        this.add(buttonFire);
        buttonFire.addActionListener(new ControllerSwapKey(this.model, swapKey));
        this.swapKey.add(buttonFire);
        nbKeyFire = new JLabel();
        nbKeyFire.setBounds(100,28,50,50);
        this.add(nbKeyFire);

        JButton buttonEarth = new JButton("E");
        buttonEarth.setBounds(110,28,200,80);
        buttonEarth.setForeground(Color.WHITE);
        this.add(buttonEarth);
        buttonEarth.addActionListener(new ControllerSwapKey(this.model, swapKey));
        this.swapKey.add(buttonEarth);
        nbKeyEarth = new JLabel();
        nbKeyEarth.setBounds(130,28,50,50);
        this.add(nbKeyEarth);

        update();
    }

    @Override
    public void update() {
        int keyAir = this.model.currentPlayer.nbKeyElement(Cell.Element.AIR);
        int keyWater = this.model.currentPlayer.nbKeyElement(Cell.Element.AIR);
        int keyFire = this.model.currentPlayer.nbKeyElement(Cell.Element.AIR);
        int keyEarth = this.model.currentPlayer.nbKeyElement(Cell.Element.AIR);
        nbKeyAir = new JLabel(String.valueOf(keyAir));
        nbKeyWater = new JLabel(String.valueOf(keyWater));
        nbKeyFire = new JLabel(String.valueOf(keyFire));
        nbKeyEarth = new JLabel(String.valueOf(keyEarth));
    }
}
