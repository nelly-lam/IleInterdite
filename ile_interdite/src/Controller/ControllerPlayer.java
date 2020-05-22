package Controller;

import Model.Island;
import Model.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControllerPlayer implements KeyListener {
    public Island model;

    public ControllerPlayer(Island model) {
        this.model = model;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.model.movePlayer(model.players, Player.Direction.up);
                break;
            case KeyEvent.VK_DOWN:
                this.model.movePlayer(model.players, Player.Direction.down);
                break;
            case KeyEvent.VK_LEFT:
                this.model.movePlayer(model.players, Player.Direction.left);
                break;
            case KeyEvent.VK_RIGHT:
                this.model.movePlayer(model.players, Player.Direction.right);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
