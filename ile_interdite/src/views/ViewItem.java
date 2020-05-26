package views;

import model.Cell;
import model.Island;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ViewItem extends JTabbedPane implements ChangeListener {
    private final Island model;

    public ViewItem(Island model){
        this.model = model;
        Dimension d = new Dimension(200, 200);
        this.setPreferredSize(d);

        String[] nounElements = { "AIR", "WATER", "FIRE", "EARTH" };
        int nbPlayer = this.model.players.size();
        Object[][] listElements = new Object[nbPlayer][4]; //row column

        for (int i = 0; i < model.players.size(); i++){
            JPanel pan = new JPanel();
            this.addTab(model.players.get(i).getName(), pan);
            listElements[i][0] = model.players.get(i).nbKeyElement(Cell.Element.AIR);
            listElements[i][1] = model.players.get(i).nbKeyElement(Cell.Element.WATER);
            listElements[i][2] = model.players.get(i).nbKeyElement(Cell.Element.FIRE);
            listElements[i][3] = model.players.get(i).nbKeyElement(Cell.Element.EARTH);
            JTable table =  new JTable(listElements, nounElements);
            table.setPreferredSize(d);
            pan.add(table);
        }


        this.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane sourceTabbedPane = (JTabbedPane) e.getSource();
                int index = sourceTabbedPane.getSelectedIndex();
                //JTable table = new JTable(listElements, nounElements);
            }
        });

    }

    @Override
    public void stateChanged(ChangeEvent e) {
    }
}