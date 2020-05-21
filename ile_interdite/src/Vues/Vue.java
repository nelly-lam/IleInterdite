package Vues;

import InterfaceGraphique.Modele;

import javax.swing.*;
import java.awt.*;

public class Vue {
    private JFrame frame;
    private VueGrille grille;
    private VueCommandes commandes;

    public Vue(Modele modele) {
        this.frame = new JFrame();
        this.frame.setTitle("L'île interdite");
        this.frame.setLayout(new FlowLayout());
        this.grille = new VueGrille(modele);
        this.frame.add(this.grille);
        this.commandes = new VueCommandes(modele);
        this.frame.add(this.commandes);
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }
}
