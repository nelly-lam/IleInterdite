package InterfaceGraphique;

public class Cellule {
    private Modele modele;
    private final int coordX;
    private final int coordY;
    public Zone etat;

    public Cellule(Modele modele, int coordX, int coordY) {
        this.modele = modele;
        this.coordX = coordX;
        this.coordY = coordY;
        this.etat = Zone.Normale;
    }

    public void changementEtat() {
        switch(this.etat) {
            case Normale:
                this.etat = Zone.Inondee;
                break;
            case Inondee:
                this.etat = Zone.Submergee;
                break;
        }
    }

    public boolean isSubmergee() {
        return this.etat == Zone.Submergee;
    }
}
