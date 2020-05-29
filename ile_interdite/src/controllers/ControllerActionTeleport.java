package controllers;

import model.Cell;
import model.Island;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerActionTeleport implements MouseListener {
    private final Island model;

    public ControllerActionTeleport(Island model) {
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Cell cell = this.model.getCell(x, y);
        this.model.playerCourant.teleportPlayer(x, y);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
