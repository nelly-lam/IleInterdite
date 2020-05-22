package Views;

import Controller.ControllerPlayer;
import Model.Island;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ViewPlayer extends JPanel implements KeyListener {
    public final Island model;

    public ViewPlayer(Island model) {
        this.model = model;
    }

    public void paintComponent(Graphics g) {
        super.repaint();
        /*for (Player player : this.model.players) {
                paint(g, player, player.getOrd()*ViewIsland.size, player.getAbs()*ViewIsland.size);
        }*/
        paint(g, model.players, model.players.getOrd()*ViewIsland.size, model.players.getAbs()*ViewIsland.size);
    }

    private void paint(Graphics g, Player p, int x, int y) {
        g.setColor(Color.RED);
        g.fillOval(x, y, 10, 10);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.print("test");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.print("test");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.print("down");
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
