package controllers;

import model.Island;
import model.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControllerMovement implements KeyListener {
    private Island model;

    public ControllerMovement(Island model) {
        this.model = model;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Nous n'avons pas besoin de cette méthode
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Nous n'avons pas besoin de cette méthode
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.model.movePlayer(Player.Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                this.model.movePlayer(Player.Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                this.model.movePlayer(Player.Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                this.model.movePlayer(Player.Direction.RIGHT);
                break;
            case KeyEvent.VK_A:
                this.model.addPlayer("toto");
                break;
            case KeyEvent.VK_Z:
                this.model.dry(this.model.playerCourant.getAbs(), this.model.playerCourant.getOrd()-1);
                break;
            case KeyEvent.VK_S:
                this.model.dry(this.model.playerCourant.getAbs(), this.model.playerCourant.getOrd());
                break;
            case KeyEvent.VK_D:
                this.model.dry(this.model.playerCourant.getAbs()+1, this.model.playerCourant.getOrd());
                break;
            case KeyEvent.VK_Q:
                this.model.dry(this.model.playerCourant.getAbs()-1, this.model.playerCourant.getOrd());
                break;
            case KeyEvent.VK_W:
                this.model.dry(this.model.playerCourant.getAbs(), this.model.playerCourant.getOrd()+1);
                break;
            case KeyEvent.VK_ENTER:
                this.model.searchKey();
                break;
            case KeyEvent.VK_SPACE:

                break;
        }
    }
}