package model;

import exceptions.ExceptionNbHits;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    private String name;
    private Color color;
    private Player next;
    private Island model;
    private int abs;
    private int ord;
    private int nbHits;
    private ArrayList<Cell.Element> key;
    private ArrayList<Cell.Element> artifact;
    private boolean isDead;

    public enum Direction {UP, DOWN, RIGHT, LEFT} //majuscules!

    public Player(Island model, String name, int x, int y){
        this.model = model;
        this.name = name;
        this.next = this;
        this.nbHits = 0;
        this.color = Color.RED;
        this.abs = x;
        this.ord = y;
        this.isDead = false;
        this.key = new ArrayList<>();
        this.artifact = new ArrayList<>();
    }

    public Player(Island model, String name, Player next, int x, int y) {
        this(model, name, x, y);
        this.next = next;
    }

    public String getName(){ return this.name; }
    public Player getNext() { return this.next; }
    public Color getColor() { return this.color; }
    public int getNbHits() { return this.nbHits; }
    public int getAbs() { return this.abs; }
    public int getOrd() { return this.ord; }
    public boolean getIsDead(){ return this.isDead; }
    public ArrayList<Cell.Element> getKeyArray(){ return this.key; }
    public ArrayList<Cell.Element> getArtifactArray(){ return this.artifact; }



    public void setNext(Player p) { this.next = p; }

    public void move(Direction key) {
        try {
            switch(key) {
                case UP:
                    if((this.ord != 0) && !(this.model.board[this.abs][this.ord-1].isSubmerged())) {
                        this.addHits();
                        this.ord--;
                    }
                    break;
                case DOWN:
                    if((this.ord != this.model.height-1) && !(this.model.board[this.abs][this.ord+1].isSubmerged())) {
                        this.addHits();
                        this.ord++;
                    }
                    break;
                case RIGHT:
                    if((this.abs != this.model.width-1) && !(this.model.board[this.abs+1][this.ord].isSubmerged())) {
                        this.addHits();
                        this.abs++;
                    }
                    break;
                case LEFT:
                    if((this.abs != 0) && !(this.model.board[this.abs-1][this.ord].isSubmerged())) {
                        this.addHits();
                        this.abs--;
                    }
                    break;
            }
        } catch (ExceptionNbHits exceptionNbHits) {
            // TODO afficher un message au joueur
            //exceptionNbHits.printStackTrace();
        }
    }

    public void addHits() throws ExceptionNbHits {
        if(this.nbHits == 3) {
            throw new ExceptionNbHits();
        }
        else {
            this.nbHits++;
        }
    }
    public void restoreNbHits() {
        this.nbHits = 0;
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

    /**
     * méthode addArtifact() : ajoute un artefact à la liste des artifact du player
     * @param : un Element e
     */
    public void addArtifact(Cell.Element e){
        this.artifact.add(e);
    }


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
        if (playerY != this.model.height-1) {
            cap.add(this.model.board[playerX][playerY+1]);
        }
        if (playerX != this.model.width-1) {
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

    /** USING ARRAY
     * méthode die() : update le statut du player isDead en true si :
     * - le player se situe sur une cell submergée
     * - le player est entouré de cells submergées
     * @param : un Island island (board)
     */
    public void die(){
        ArrayList<Cell> cap = nearbyCells();
        int compteur = 0;
        if (model.board[this.getAbs()][this.getOrd()].getState() == Cell.State.SUBMERGED){ //si la cell où se trouve le player est submergée
            System.out.println("Mskn you are dead");
            this.isDead = true;
        } else {
            for (int i = 0; i < cap.size(); i++){ //pour toutes les cell de cap
                if (cap.get(i).getState() == Cell.State.SUBMERGED){
                    compteur++; //incrémenter le compteur si la cell est submergée
                }
            }
            if (compteur == cap.size()){ //si toutes les cells de cap sont submergées
                System.out.println("Mskn you are dead");
                this.isDead = true;
            }
        }
    }

}