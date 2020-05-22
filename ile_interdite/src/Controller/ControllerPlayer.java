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
        System.out.print("up");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.print("up");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.print("up");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                this.model.movePlayer(model.players, Player.Direction.up);
                System.out.print("up");
                break;
            case KeyEvent.VK_DOWN:
                this.model.movePlayer(model.players, Player.Direction.down);
                System.out.print("down");
                break;
            case KeyEvent.VK_LEFT:
                this.model.movePlayer(model.players, Player.Direction.left);
                System.out.print("left");
                break;
            case KeyEvent.VK_RIGHT:
                this.model.movePlayer(model.players, Player.Direction.right);
                System.out.print("right");
        }
    }
}
