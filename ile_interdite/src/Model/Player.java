package Model;

import java.util.ArrayList;

public class Player {
    private String name;
    protected static int idPlayer = 0;
    private Island model;
    //private Coord location;
    private int abs;
    private int ord;
    private ArrayList<Cell.Element> key;
    private ArrayList<Cell.Element> artifact;
    private boolean isDead;

    public enum Direction {up, down, right, left}

    public Player(Island model, String name, int x, int y){
        this.model = model;
        this.name = name;
        this.idPlayer++;
        this.abs = x;
        this.ord = y;
        this.isDead = false;
        key = new ArrayList<Cell.Element>();
        artifact = new ArrayList<Cell.Element>();
    }

    /**
     * méthode getIdJ() : donne l'indice du joueur
     * @return l'indice int idPlayer du joueur
     */
    public int getIdJ() { return this.idPlayer; }
    public String getName(){ return this.name; }
    public int getAbs() { return this.abs; }
    public int getOrd() { return this.ord; }

    public void move(Direction key) {
        switch(key) {
            case up:
                this.ord++;
                break;
            case down:
                this.ord--;
                break;
            case right:
                this.abs++;
                break;
            case left:
                this.abs--;
                break;
        }
    }

    /*
    //EN CHANTIER
    public void updatePosition(Island island, Cell c){
        //this.locathis.tion = new Coord(island.board.get(c));
    } //changer la position du joueur
    //isMovementPossible(Cellule c) // checker si le joueur a le droit de faire un mouvement
    */

    /**
     * méthode addkey() : ajoute une clé (élément) à la liste des key du player
     * @param : un Element e
     */
    public void addKey(Cell.Element e){
        this.key.add(e);
    }

    public ArrayList<Cell.Element> getKeyArray(){ return this.key; }
    /**
     * méthode addArtifact() : ajoute un artefact à la liste des artifact du player
     * @param : un Element e
     */
    public void addArtifact(Cell.Element e){
        this.artifact.add(e);
    }

    public ArrayList<Cell.Element> getArtifactArray(){ return this.artifact; }

    /**  USING HASHMAP
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
        cap.add(this.island.board.get(haut));
        cap.add(this.island.board.get(bas));
        cap.add(island.this.board.get(droite));
        cap.add(island.board.get(gauche));
        return cap;
    }
    */

    /**  USING ARRAY
     * méthode cellAroundPlayer() : donne une liste des cell (haut
     * , bas, droite, gauche) situées autour du player
     * @return une ArrayList<Cell> cap
     */
    public ArrayList<Cell> nearbyCells(){
        ArrayList<Cell> cap = new ArrayList<Cell>();
        int playerX = this.getAbs();
        int playerY = this.getOrd();
        if (playerY != 0) {
            cap.add(this.model.board[playerX][playerY-1]);
        }
        if (playerY != this.model.height) {
            cap.add(this.model.board[playerX][playerY+1]);
        }
        if (playerX != this.model.width) {
            cap.add(this.model.board[playerX+1][playerY]);
        }
        if (playerX != 0) {
            cap.add(this.model.board[playerX-1][playerY]);
        }
        return cap;
    }

    /** USING HASHMAP
     * méthode die() : update le statut du player isDead en true si :
     * - le player se situe sur une cell submergée
     * - le player est entouré de cells submergées
     * @param : un Island island (board)
     */
    /*
    public void die(){
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
    */

    public void die(){
        ArrayList<Cell> cap = nearbyCells();
        int compteur = 0;
        if (model.board[this.getAbs()][this.getOrd()].state == Cell.State.Submerged){ //si la cell où se trouve le player est submergée
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

    public boolean getIsDead(){ return this.isDead; }

    //dryZone(Cellule c) (position + droite gauche haut bas)

}