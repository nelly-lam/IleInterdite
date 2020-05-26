package views;

import model.Cell;
import model.Island;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class ViewItem extends JPanel {
    private final Island model;
    private JPanel panKey;
    private JTable tableKey;
    private JPanel panArtifact;
    private JTable tableArtifact;

    public ViewItem(Island model){
        this.model = model;

        this.setLayout(new GridLayout(2, 1));
        Dimension d = new Dimension(250, 100);
        this.setPreferredSize(d);
        this.panKey = new JPanel();
        this.add(panKey);
        this.panArtifact = new JPanel();
        this.add(panArtifact);

        //Table des keys en possession
        this.panKey.setLayout(new BorderLayout());
        JLabel labelKey = new JLabel("Liste des clés");
        this.panKey.add(labelKey, BorderLayout.NORTH);
        String[] nounElements = { "Joueurs", "AIR", "EAU", "FEU", "TERRE" };
        int nbPlayer = this.model.players.size();
        Object[][] listElements = new Object[nbPlayer][5]; //row column

        for (int i = 0; i < model.players.size(); i++) {
            listElements[i][0] = model.players.get(i).getName();
            listElements[i][1] = model.players.get(i).nbKeyElement(Cell.Element.AIR);
            listElements[i][2] = model.players.get(i).nbKeyElement(Cell.Element.WATER);
            listElements[i][3] = model.players.get(i).nbKeyElement(Cell.Element.FIRE);
            listElements[i][4] = model.players.get(i).nbKeyElement(Cell.Element.EARTH);
            tableKey = new JTable(listElements, nounElements);
        }

        TableColumnModel columnModel = tableKey.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(4).setPreferredWidth(80);
        //tableKey.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.panKey.add(tableKey, BorderLayout.CENTER);
        this.panKey.add(new JScrollPane(tableKey));
        //tableKey.setPreferredSize(d);

        //Table des artifacts
        this.panArtifact.setLayout(new BorderLayout());
        JLabel labelArtifact = new JLabel("Liste des Artefacts");
        this.panArtifact.add(labelArtifact, BorderLayout.NORTH);
        Object[][] listArtifact = new Object[nbPlayer][5]; //row column

        for (int i = 0; i < model.players.size(); i++) {
            listArtifact[i][0] = model.players.get(i).getName();
            listArtifact[i][1] = model.players.get(i).nbArtifactElement(Cell.Element.AIR);
            listArtifact[i][2] = model.players.get(i).nbArtifactElement(Cell.Element.WATER);
            listArtifact[i][3] = model.players.get(i).nbArtifactElement(Cell.Element.FIRE);
            listArtifact[i][4] = model.players.get(i).nbArtifactElement(Cell.Element.EARTH);
            tableArtifact = new JTable(listArtifact, nounElements);
        }

        TableColumnModel columnModel2 = tableArtifact.getColumnModel();
        columnModel2.getColumn(0).setPreferredWidth(150);
        columnModel2.getColumn(1).setPreferredWidth(80);
        columnModel2.getColumn(2).setPreferredWidth(80);
        columnModel2.getColumn(3).setPreferredWidth(80);
        columnModel2.getColumn(4).setPreferredWidth(80);
        //tableKey.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.panArtifact.add(tableArtifact, BorderLayout.CENTER);
        this.panArtifact.add(new JScrollPane(tableArtifact));
        //tableArtifact.setPreferredSize(d);

    }

    /*public ViewItem(Island model){
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
    */

}