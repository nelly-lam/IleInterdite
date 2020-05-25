package Controllers;

import Exceptions.ExceptionNbHits;
import Model.Island;
import Model.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControllerMovement implements KeyListener {
    public Island model;

    public ControllerMovement(Island model) {
        this.model = model;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            this.model.playerCourant.addHits();
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    this.model.movePlayer(Player.Direction.Up);
                    break;
                case KeyEvent.VK_DOWN:
                    this.model.movePlayer(Player.Direction.Down);
                    break;
                case KeyEvent.VK_LEFT:
                    this.model.movePlayer(Player.Direction.Left);
                    break;
                case KeyEvent.VK_RIGHT:
                    this.model.movePlayer(Player.Direction.Right);
                    break;
                case KeyEvent.VK_SPACE:
                    this.model.risingWater();
                    break;
                case KeyEvent.VK_A:
                    this.model.addPlayer("toto");
                    break;
                case KeyEvent.VK_Z:
                    this.model.dewatering(this.model.playerCourant.getAbs(), this.model.playerCourant.getOrd()-1);
                    break;
                case KeyEvent.VK_S:
                    this.model.dewatering(this.model.playerCourant.getAbs(), this.model.playerCourant.getOrd());
                    break;
                case KeyEvent.VK_D:
                    this.model.dewatering(this.model.playerCourant.getAbs()+1, this.model.playerCourant.getOrd());
                    break;
                case KeyEvent.VK_Q:
                    this.model.dewatering(this.model.playerCourant.getAbs()-1, this.model.playerCourant.getOrd());
                    break;
                case KeyEvent.VK_W:
                    this.model.dewatering(this.model.playerCourant.getAbs(), this.model.playerCourant.getOrd()+1);
                    break;
            }
        } catch (ExceptionNbHits exceptionNbHits) {
            // TODO afficher un message au joueur
            exceptionNbHits.printStackTrace();
        }
    }
}