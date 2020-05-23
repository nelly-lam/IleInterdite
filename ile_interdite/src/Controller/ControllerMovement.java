package Controller;

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
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.model.movePlayer(Player.Direction.up);
                break;
            case KeyEvent.VK_DOWN:
                this.model.movePlayer(Player.Direction.down);
                break;
            case KeyEvent.VK_LEFT:
                this.model.movePlayer(Player.Direction.left);
                break;
            case KeyEvent.VK_RIGHT:
                this.model.movePlayer(Player.Direction.right);
        }
    }
}
