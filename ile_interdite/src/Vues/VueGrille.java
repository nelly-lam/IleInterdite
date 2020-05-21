package Vues;

import InterfaceGraphique.Cellule;
import InterfaceGraphique.Modele;
import InterfaceGraphique.Observateur;

import javax.swing.*;
import java.awt.*;

public class VueGrille extends JPanel implements Observateur {
    private static Modele modele;
    private static final int taille = 25;

    public VueGrille(Modele modele) {
        this.modele = modele;
        modele.addObserver(this);
        Dimension dim = new Dimension(taille*Modele.largeur, taille*Modele.hauteur);
        this.setPreferredSize(dim);
    }

    public void paintComponent(Graphics g) {
        super.repaint();
        for(int i = 0; i < Modele.largeur; i++) {
            for(int j = 0; j < Modele.hauteur; j++) {
                paint(g, modele.getCellule(i, j), (i)*this.taille, (j)*this.taille);
            }
        }
    }

    private void paint(Graphics g, Cellule c, int x, int y) {
        switch(c.etat) {
            case Normale:
                g.setColor(Color.WHITE);
                break;
            case Inondee:
                g.setColor(Color.BLUE);
                break;
            case Submergee:
                g.setColor(Color.BLACK);
        }
        g.fillRect(x, y, this.taille, this.taille);
    }

    @Override
    public void update() {
        repaint();
    }
}
