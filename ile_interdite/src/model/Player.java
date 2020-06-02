package model;

import exceptions.ExceptionNbHits;
import views.ViewGame;
import views.ViewNbHits;

import java.awt.*;
import java.util.ArrayList;

public class Player {
    private final Island model;
    private final String name;
    private final Color color;
    private Player next;
    private int abs;
    private int ord;
    private int nbEvents;
    private ArrayList<Cell.Element> keys;
    private ArrayList<Cell.Element> artifacts;
    private ArrayList<SpecialAction> actions;

    public enum Direction {UP, DOWN, RIGHT, LEFT}
    public enum SpecialAction {SAND, TELEPORTATION}

    public Player(Island model, String name, int abs, int ord){
        this.model = model;
        this.name = name;
        this.color = this.randomColor();
        this.abs = abs;
        this.ord = ord;
        this.nbEvents = 3;
        this.keys = new ArrayList<>();
        this.artifacts = new ArrayList<>();
        this.actions = new ArrayList<>();
        if(this.model.getPlayers().isEmpty()) {
            this.next = this;
        } else {
            this.next = this.model.getPlayers().get(0);
        }
    }

    public String getName() { return this.name; }
    public Player getNext() { return this.next; }
    public Color getColor() { return this.color; }
    public int getNbEvents() { return this.nbEvents; }
    public int getAbs() { return this.abs; }
    public int getOrd() { return this.ord; }

    public void setNext(Player p) { this.next = p; }

    public void updateKey(Cell.Element key) { this.keys.remove(key); }
    public void updateAction(SpecialAction action) { this.actions.remove(action); }

    public boolean hasKey(Cell.Element key) { return this.keys.contains(key); }
    public boolean hasAction(SpecialAction action) { return this.actions.contains(action); }

    public void addKey(Cell.Element e) { this.keys.add(e); }
    public void addArtifact(Cell.Element e) { this.artifacts.add(e); }
    public void addActions(Player.SpecialAction a) { this.actions.add(a); }

    public boolean isOnSameCell(Player p) { return this.ord == p.getOrd() && this.abs == p.getAbs(); }

    public void move(Direction key) {
        try {
            switch(key) {
                case UP:
                    if((this.ord != 0) && !(this.model.getCell(this.abs, this.ord-1).isSubmerged())) {
                        this.addEvents();
                        this.ord--;
                    }
                    break;
                case DOWN:
                    if((this.ord != this.model.getHeight()-1) && !(this.model.getCell(this.abs, this.ord+1).isSubmerged())) {
                        this.addEvents();
                        this.ord++;
                    }
                    break;
                case RIGHT:
                    if((this.abs != this.model.getWidth()-1) && !(this.model.getCell(this.abs+1, this.ord).isSubmerged())) {
                        this.addEvents();
                        this.abs++;
                    }
                    break;
                case LEFT:
                    if((this.abs != 0) && !(this.model.getCell(this.abs-1, this.ord).isSubmerged())) {
                        this.addEvents();
                        this.abs--;
                    }
                    break;
            }
        } catch (ExceptionNbHits exceptionNbHits) {
            ViewGame.updateDisplay("Vous n'avez pas assez d'actions pour vous déplacer");
        }
    }

    public void teleportPlayer(int x, int y) {
        this.abs = x;
        this.ord = y;
    }

    public void addEvents() throws ExceptionNbHits {
        if(this.nbEvents == 0) {
            throw new ExceptionNbHits();
        } else {
            this.nbEvents--;
            ViewNbHits.updateNbHits(this.nbEvents);
        }
    }

    public void restoreNbEvents() {
        this.nbEvents = 3;
        ViewNbHits.updateNbHits(this.nbEvents);
    }

    public boolean isDead(){
        boolean dead = true;
        ArrayList<Cell> cells = new ArrayList<>();
        if(this.ord != 0)  {
            cells.add(this.model.getCell(this.abs,this.ord-1));
        }
        if(this.ord != this.model.getHeight()-1) {
            cells.add(this.model.getCell(this.abs, this.ord+1));
        }
        if(this.abs != this.model.getWidth()-1) {
            cells.add(this.model.getCell(this.abs+1, this.ord));
        }
        if(this.abs != 0) {
            cells.add(this.model.getCell(this.abs-1, this.ord));
        }
        for(Cell c : cells) {
            if(!c.isSubmerged()) {
                dead = false;
            }
        }
        return this.model.getCell(this.abs, this.ord).isSubmerged() || dead;
    }

    public int nbKeyElement(Cell.Element e) {
        int count = 0;
        for (Cell.Element temp : this.keys) {
            if(temp == e) {
                count++;
            }
        }
        return count;
    }

    public int nbArtifactElement(Cell.Element e) {
        int count = 0;
        for (Cell.Element temp : this.artifacts) {
            if(temp == e) {
                count++;
            }
        }
        return count;
    }

    public int nbSpecialAction(SpecialAction a) {
        int count = 0;
        for (Player.SpecialAction temp : this.actions) {
            if(temp == a){
                count++;
            }
        }
        return count;
    }

    public Color randomColor() {
        float r = (Island.random.nextFloat());
        float g = (Island.random.nextFloat());
        float b = (Island.random.nextFloat());
        return new Color(r,g,b);
    }

    public ArrayList<Player> playersOnSameCell() {
        ArrayList<Player> tabPlayersOSC = new ArrayList<>();
        for(Player p : this.model.getPlayers()) {
            if(this.isOnSameCell(p)) {
                tabPlayersOSC.add(p);
            }
        }
        return tabPlayersOSC;
    }
}