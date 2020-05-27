package views;

import model.Cell;
import model.Island;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class ViewItem extends JPanel implements Observer {
    private final Island model;
    private JPanel panKey;
    private JTable tableKey;
    private Object[][] listKey;
    private JPanel panArtifact;
    private JTable tableArtifact;
    private Object[][] listArtifact;
    private String[] nounElements = { "Joueurs", "AIR", "EAU", "FEU", "TERRE" };

    public ViewItem(Island model){
        this.model = model;
        this.model.addObserver(this);
        this.setLayout(new GridLayout(2, 1));
        Dimension d = new Dimension(250, 100);
        this.setPreferredSize(d);
        this.panKey = new JPanel();
        this.add(panKey);
        this.panArtifact = new JPanel();
        this.add(panArtifact);
        int nbPlayer = this.model.players.size();

        //Table des keys en possession
        this.panKey.setLayout(new BorderLayout());
        JLabel labelKey = new JLabel("Liste des clés");
        this.panKey.add(labelKey, BorderLayout.NORTH);

        this.listKey = new Object[nbPlayer][5]; //row column
        this.updateListKey(5);

        //Table des artifacts en possession
        this.panArtifact.setLayout(new BorderLayout());
        JLabel labelArtifact = new JLabel("Liste des Artefacts");
        this.panArtifact.add(labelArtifact, BorderLayout.NORTH);

        this.listArtifact = new Object[nbPlayer][5]; //row column
        this.updateListArtifact();
    }

    public void updateListKey(int nb) {
        for (int i = 0; i < this.model.players.size(); i++) {
            listKey[i][0] = this.model.players.get(i).getName();
            listKey[i][1] = this.model.players.get(i).nbKeyElement(Cell.Element.FIRE);
            listKey[i][2] = nb;
            listKey[i][3] = this.model.players.get(i).nbKeyElement(Cell.Element.FIRE);
            listKey[i][4] = this.model.players.get(i).nbKeyElement(Cell.Element.EARTH);
            tableKey = new JTable(listKey, this.nounElements);
        }
        var columnModel = tableKey.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150);
        columnModel.getColumn(1).setPreferredWidth(80);
        columnModel.getColumn(2).setPreferredWidth(80);
        columnModel.getColumn(3).setPreferredWidth(80);
        columnModel.getColumn(4).setPreferredWidth(80);
        this.panKey.add(tableKey, BorderLayout.CENTER);
        this.panKey.add(new JScrollPane(tableKey));
    }

    public void updateListArtifact() {
        for (int i = 0; i < model.players.size(); i++) {
            listArtifact[i][0] = model.players.get(i).getName();
            listArtifact[i][1] = model.players.get(i).nbArtifactElement(Cell.Element.AIR);
            listArtifact[i][2] = model.players.get(i).nbArtifactElement(Cell.Element.WATER);
            listArtifact[i][3] = model.players.get(i).nbArtifactElement(Cell.Element.FIRE);
            listArtifact[i][4] = model.players.get(i).nbArtifactElement(Cell.Element.EARTH);
            tableArtifact = new JTable(listArtifact, this.nounElements);
        }
        TableColumnModel columnModel2 = tableArtifact.getColumnModel();
        columnModel2.getColumn(0).setPreferredWidth(150);
        columnModel2.getColumn(1).setPreferredWidth(80);
        columnModel2.getColumn(2).setPreferredWidth(80);
        columnModel2.getColumn(3).setPreferredWidth(80);
        columnModel2.getColumn(4).setPreferredWidth(80);
        this.panArtifact.add(tableArtifact, BorderLayout.CENTER);
        this.panArtifact.add(new JScrollPane(tableArtifact));
    }

    @Override
    public void update() {
        this.updateListKey(3);
        this.updateListArtifact();
    }
}