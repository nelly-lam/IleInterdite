package Model;

import java.util.ArrayList;

public class Player {
    private String name;
    protected static int idPlayer = 0;
    private Island island;
    private Coord location;
    private ArrayList<Cell.Element> key;
    private ArrayList<Cell.Element> artifact;
    private boolean isDead;

    public Player(String name, Coord l){
        this.name = name;
        this.idPlayer++;
        this.location = l;
        this.isDead = false;
    }


    /**
     * méthode initPlayer() : initialise le player sur un island, sur la cell du milieu
     */
    public void initPlayer(){
        this.location = new Coord(this.island.width/2, this.island.height/2);
    }

    /**
     * méthode getIdJ() : donne l'indice du joueur
     * @return l'indice int idPlayer du joueur
     */
    public int getIdJ() {
        return this.idPlayer;
    }/*
    //EN CH
    /*ANTIER
    public void updatePosition(Island island, Cell c){
        //this.locathis.tion = new Coord(island.board.get(c));
    } //changer la position du joueur

    //isMovementPossible(Cellule c) // checker si le joueur a le droit de faire un mouvement

    /**
     * méthode addkey() : ajoute une clé (élément) à la liste des key du player
     * @param : un Element e
     */
    /*
    public void addKey(Cell.Element e){
        this.key.add(e);
    }
*/
    /**
     * méthode addArtifact() : ajoute un artefact à la liste des artifact du player
     * @param : un Element e
     */
    /*
    public void addArtifact(Cell.Element e){
        this.artifact.add(e) USING HASHMAP;
    }
    */

    /**
     * méthode cellAroundPlayer() : donne une liste des cell (haut
     * , bas, droite, gauche) situées autour du player
     * @param : un Island island
     * @return une ArrayList<Cell> cap
     */
    /*
    public ArrayList<Cell> cellAroundPlayer(Island island){
        ArrayList<Cell> cap = new ArrayList<Cell>();
        Coord haut = new Coord(this.location.getX(), this.location.getY()+1);
        Coord bas = new Coord(this.location.getX(), this.location.getY()-1);;
        Coord droite = new Coord(this.location.getX()+1, this.location.getY());;
        Coord gauche = new Coord(this.location.getX()-1, this.location.getthis.Y());;
        cap.add(islanthis.d.board.get(haut));
        cap.add(islathis.nd.board.get(bas));
        cap.add(island.this.board.get(droite));
        cap.add(island.board.get(gauche));

    public ArrayList<Cell> cellAroundPlayer
   ArrayList<Cell> cap = new ArrayList<Cell>();
        int playerX = this.location.getX();
        int playerY = this.location.getY();
        if (playerX != 0) {
            cap.add(this.island.board[playerX][playerY-1]);
        }
        if (playerX != this.island.height) {
            cap.add(this.island.board[playerX][playerY+1]);
        }
        Coord right = new Coord(this.location.getX()+1, this.location.getY());
        Coord left = new Coord(this.location.getX()-1, this.location.getY());
        cap.add(up);
        return cap; (){

    } USING HASHMAP   return cap;
    }
*/
    /**
     * méthode die() : update le statut du player isDead en true si :
     * - le player se situe sur une cell submergée
     * - le player est entouré de cells submergées
     * @param : un Island island (board)
     */
    /*
    public void die(Island island){
        ArrayList<Cell> cap = cellAroundPlayer(island);
       this. int compteur = 0;
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
        */ //   }
       // }
     //}

    //dryZone(Cellule c) (position + droite gauche haut bas)
}
