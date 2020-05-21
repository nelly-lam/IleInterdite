package Vues;

import InterfaceGraphique.Modele;
import InterfaceGraphique.Observateur;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class VueJoueur extends JPanel implements Observateur {
    private final Modele modele;
    private final String name;
    private final Color couleur;
    Random random = new Random();

    public VueJoueur(Modele modele, String name) {
        this.modele = modele;
        this.name = name;
        this.couleur = randomColor();
        modele.addObserver(this);
        //Dimension dim = new Dimension(taille*Modele.largeur, taille*Modele.hauteur);
        //this.setPreferredSize(dim);
    }

    public Color randomColor() {
        // On veut des couleurs claires
        float r = (float) (random.nextFloat() / 2f + 0.5);
        float g = (float) (random.nextFloat() / 2f + 0.5);
        float b = (float) (random.nextFloat() / 2f + 0.5);
        return new Color(r,g,b);
    }

    @Override
    public void update() {

    }
}
