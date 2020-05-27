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
    public ArrayList<Cell.Element> key;
    private ArrayList<Cell.Element> artifact;

    public enum Direction {UP, DOWN, RIGHT, LEFT}

    public Player(Island model, String name, int x, int y){
        this.model = model;
        this.name = name;
        this.next = this;
        this.nbHits = 0;
        this.color = this.randomColor();
        this.abs = x;
        this.ord = y;
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
    public ArrayList<Cell.Element> getArtifactArray(){ return this.artifact; }

    public void updateKey(Cell.Element key) { this.artifact.remove(key); }
    public boolean hasKey(Cell.Element key) { return this.artifact.contains(key); }
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
            System.out.println("Vous ne passerez pas !");
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
        for (Cell.Element temp : this.key) {
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
        for (Cell.Element temp : this.artifact) {
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
                for (Cell.Element tmp : this.key) {
                    if(tmp == k){
                        break;
                    }
                    index++;
                }
                p.addKey(this.key.get(index));
                this.updateKey(this.key.get(index));
            }
        } catch (Exception notEnoughKeys){
            System.out.println("Vous n'avez pas assez de cles!");
        }
    }
}