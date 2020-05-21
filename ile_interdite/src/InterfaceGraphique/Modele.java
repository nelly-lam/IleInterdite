package InterfaceGraphique;

import java.util.HashSet;
import java.util.Random;

public class Modele extends Observable {
    public static final int hauteur = 20;
    public static final int largeur = 20;
    private Cellule[][] cellules;
    private HashSet<Joueur> joueurs;
    Random random = new Random();

    public Modele() {
        this.cellules = new Cellule[this.largeur][this.hauteur];
        for(int i = 0; i < largeur; i++) {
            for(int j = 0; j < hauteur; j++) {
                this.cellules[i][j] = new Cellule(this,i, j);
            }
        }
        this.joueurs = new HashSet<Joueur>();
        this.joueurs.add(new Joueur(this));
    }

    public Cellule getCellule(int x, int y) {
        return cellules[x][y];
    }

    public void monteeDesEaux() {
        int nbcase = 0;
        while (nbcase < 3) {
            Cellule cel = this.cellules[random.nextInt(this.largeur)][random.nextInt(this.hauteur)];
            if(!cel.isSubmergee()) {
                // TODO : Un test lorsqu'il reste moins de 2 cases
                cel.changementEtat();
                nbcase++;
            }
        }
        notifyObservers();
    }
}
