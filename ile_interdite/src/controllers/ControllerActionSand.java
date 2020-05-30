package controllers;

import model.Cell;
import model.Island;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerActionSand implements MouseListener {
    private final Island model;

    public ControllerActionSand(Island model) {
        this.model = model;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Cell cell = this.model.getCell(x, y);
        this.model.dry(x, y);
        //NOT FINISHED
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //No need
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //No need
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //No need
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //No need
    }
}
