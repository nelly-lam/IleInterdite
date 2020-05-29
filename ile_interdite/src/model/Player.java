package model;

import exceptions.ExceptionNbHits;
import views.ViewGame;
import views.ViewNbHits;

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
    public ArrayList<Cell.Element> keys;
    private ArrayList<Cell.Element> artifacts;
    private ArrayList<SpecialAction> actions;

    public enum Direction {UP, DOWN, RIGHT, LEFT}
    public enum SpecialAction {SAND, TELEPORTATION}

    public Player(Island model, String name, int x, int y){
        this.model = model;
        this.name = name;
        this.next = this;
        this.nbHits = 3;
        this.color = this.randomColor();
        this.abs = x;
        this.ord = y;
        this.keys = new ArrayList<>();
        this.artifacts = new ArrayList<>();
        this.actions = new ArrayList();
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

    public void updateKey(Cell.Element key) { this.keys.remove(key); }
    public boolean hasKey(Cell.Element key) { return this.artifacts.contains(key); }
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
            ViewGame.updateDisplay("Vous n'avez pas assez de coups pour vous déplacer");
        }
    }

    public void addHits() throws ExceptionNbHits {
        if(this.nbHits == 0) {
            throw new ExceptionNbHits();
        }
        else {
            this.nbHits--;
            ViewNbHits.updateNbHits(this.nbHits);
        }
    }
    public void restoreNbHits() {
        this.nbHits = 3;
        ViewNbHits.updateNbHits(this.nbHits);
    }

    /**
     * méthode addkey() : ajoute une clé (élément) à la liste des key du player
     * @param : un Element e
     */
    public void addKey(Cell.Element e){
        this.keys.add(e);
    }

    /**
     * méthode addArtifact() : ajoute un artefact à la liste des artifact du player
     * @param : un Element e
     */
    public void addArtifact(Cell.Element e){
        this.artifacts.add(e);
    }

    public boolean isDead(){
        boolean dead = true;
        ArrayList<Cell> cap = new ArrayList<>();
        if (this.ord != 0)  {
            cap.add(this.model.board[this.abs][this.ord-1]);
        }
        if (this.ord != this.model.height-1) {
            cap.add(this.model.board[this.abs][this.ord+1]);
        }
        if (this.abs != this.model.width-1) {
            cap.add(this.model.board[this.abs+1][this.ord]);
        }
        if (this.abs != 0) {
            cap.add(this.model.board[this.abs-1][this.ord]);
        }
        for(Cell c : cap) {
            if(!c.isSubmerged()) {
                dead = false;
            }
        }
        return this.model.board[this.abs][this.ord].isSubmerged() || dead;
    }

    /**
     * méthode nbKeyElement() : donne le nombre de clé d'un certain élément
     * que le player possède
     * @param : un Element e
     * @return int counter le nombre de clé
     */
    public int nbKeyElement(Cell.Element e){
        int counter = 0;
        for (Cell.Element temp : this.keys) {
            if(temp == e){
                counter++;
            }
        }
        return counter;
    }

    /**
     * méthode nbArtifactElement() : donne le nombre d'artifact d'un certain élément
     * que le player possède
     * @param : un Element e
     * @return int counter le nombre d'artifact
     */
    public int nbArtifactElement(Cell.Element e){
        int counter = 0;
        for (Cell.Element temp : this.artifacts) {
            if(temp == e){
                counter++;
            }
        }
        return counter;
    }

    public Color randomColor() {
        // On veut des couleurs foncées
        float r = (float) (this.model.random.nextFloat());
        float g = (float) (this.model.random.nextFloat());
        float b = (float) (this.model.random.nextFloat());
        return new Color(r,g,b);
    }

    /**
     *  Permet de donner une cle a un autre joueur
     * @param k la cle concernee
     * @param p le joueur
     */
    public void giveKey(Cell.Element k, Player p){
        try{
            if(this.nbKeyElement(k) >=1 && this.getAbs() == p.getAbs() && this.getOrd() == p.getOrd() ){
                int index = 0;
                for (Cell.Element tmp : this.keys) {
                    if(tmp == k){
                        break;
                    }
                    index++;
                }
                p.addKey(this.keys.get(index));
                this.updateKey(this.keys.get(index));
            }
        } catch (Exception notEnoughKeys){
            System.out.println("Vous n'avez pas assez de cles!");
        }
    }

    //for ECHANGE DE CLES
    public ArrayList<Player> isOnSameCell(){
        ArrayList<Player> listPlayer = new ArrayList<Player>();
        for (Player p : this.model.players){
            if (this.getAbs() == p.getAbs() && this.getOrd() == p.getOrd()){
                listPlayer.add(p);
            }
        }
        return listPlayer;
    }

    //ACTIONS SPECIALES PART 4
    public void setAbs(int x){ this.abs = x; }
    public void setOrd(int y){ this.ord = y; }
    public void addActions(Player.SpecialAction a){ this.actions.add(a); }

    public int nbActionSable(){
        int counter = 0;
        for (Player.SpecialAction temp : this.actions) {
            if(temp == SpecialAction.SAND){
                counter++;
            }
        }
        return counter;
    }

    public int nbActionTeleport(){
        int counter = 0;
        for (Player.SpecialAction temp : this.actions) {
            if(temp == SpecialAction.TELEPORTATION){
                counter++;
            }
        }
        return counter;
    }

    public void teleportPlayer(int x, int y){
        Cell cell = this.model.getCell(x, y);
        if (cell.getState() == Cell.State.NORMAL){
            this.setAbs(x);
            this.setOrd(y);
        }
    }
}