package Model;

import java.util.ArrayList;

public class Player {
    protected static int idPlayer = 0;
    private Coord location;
    private ArrayList<Cell.Element> key;
    private ArrayList<Cell.Element> artifact;
    private boolean isDead;

    public Player(Coord l){
        this.idPlayer++;
        this.location = l;
        this.isDead = false;
    }

    /**
     * méthode initPlayer() : initialise le player sur un island, sur une cell hélicoptère
     */
    public void initPlayer(){
    }

    /**
     * méthode getIdJ() : donne l'indice du joueur
     * @return l'indice int idPlayer du joueur
     */
    public int getIdJ() {
        return this.idPlayer;
    }

    //EN CHANTIER
    public void updatePosition(Island island, Cell c){
        //this.location = new Coord(island.board.get(c));
    } //changer la position du joueur

    //isMovementPossible(Cellule c) // checker si le joueur a le droit de faire un mouvement

    /**
     * méthode addkey() : ajoute une clé (élément) à la liste des key du player
     * @param : un Element e
     */
    public void addKey(Cell.Element e){
        this.key.add(e);
    }

    /**
     * méthode addArtifact() : ajoute un artefact à la liste des artifact du player
     * @param : un Element e
     */
    public void addArtifact(Cell.Element e){
        this.artifact.add(e);
    }

    /**
     * méthode cellAroundPlayer() : donne une liste des cell (haut, bas, droite, gauche) situées autour du player
     * @param : un Island island
     * @return une ArrayList<Cell> cap
     */
    public ArrayList<Cell> cellAroundPlayer(Island island){
        ArrayList<Cell> cap = new ArrayList<Cell>();
        Coord haut = new Coord(this.location.getX(), this.location.getY()+1);
        Coord bas = new Coord(this.location.getX(), this.location.getY()-1);;
        Coord droite = new Coord(this.location.getX()+1, this.location.getY());;
        Coord gauche = new Coord(this.location.getX()-1, this.location.getY());;
        cap.add(island.board.get(haut));
        cap.add(island.board.get(bas));
        cap.add(island.board.get(droite));
        cap.add(island.board.get(gauche));
        return cap;
    }

    /**
     * méthode die() : update le statut du player isDead en true si :
     * - le player se situe sur une cell submergée
     * - le player est entouré de cells submergées
     * @param : un Island island (board)
     */
    public void die(Island island){
        ArrayList<Cell> cap = cellAroundPlayer(island);
        int compteur = 0;
        if (island.board.get(this.location).state == Cell.State.Submerged){ //si la cell où se trouve le player est submergée
            System.out.println("Mskn you are dead");
            this.isDead = true;
        } else {
            for (int i = 0; i < cap.size(); i++){ //pour toutes les cell de cap
                if (cap.get(i).state == Cell.State.Submerged){
                    compteur++; //incrémenter le compteur si la cell est submergée
                }
            }
            if (compteur == cap.size()){ //si toutes les cells de cap sont submergées
                System.out.println("Mskn you are dead");
                this.isDead = true;
            }
        }
     }

    //dryZone(Cellule c) (position + droite gauche haut bas)

}
