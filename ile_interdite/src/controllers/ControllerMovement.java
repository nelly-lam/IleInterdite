
package controllers;

import model.Island;
import model.Player;
import views.ViewGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControllerMovement implements KeyListener {
    private final Island model;

    public ControllerMovement(Island model) {
        this.model = model;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

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
            case KeyEvent.VK_Z:
                this.model.dry(this.model.getCurrentPlayer().getAbs(), this.model.getCurrentPlayer().getOrd()-1);
                break;
            case KeyEvent.VK_X:
                this.model.dry(this.model.getCurrentPlayer().getAbs(), this.model.getCurrentPlayer().getOrd());
                break;
            case KeyEvent.VK_D:
                this.model.dry(this.model.getCurrentPlayer().getAbs()+1, this.model.getCurrentPlayer().getOrd());
                break;
            case KeyEvent.VK_Q:
                this.model.dry(this.model.getCurrentPlayer().getAbs()-1, this.model.getCurrentPlayer().getOrd());
                break;
            case KeyEvent.VK_S:
                this.model.dry(this.model.getCurrentPlayer().getAbs(), this.model.getCurrentPlayer().getOrd()+1);
                break;
            case KeyEvent.VK_ENTER:
                this.model.searchKey();
                break;
            case KeyEvent.VK_SPACE:
                this.model.recoverArtifact();
                break;
            default:
                ViewGame.updateDisplay("Cette commande n'a pas besoin d'être utilisé pour jouer");
        }
    }
}