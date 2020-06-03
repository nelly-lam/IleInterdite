package model;

import views.ViewGame;

public class Cell {

    public enum State{ NORMAL, FLOODED, SUBMERGED }
    public enum Element{ AIR, WATER, FIRE, EARTH, NONE }

    private final int x;
    private final int y;
    private final boolean heliport;
    private Element key;
    private Element artifact;
    private State state;

    public Cell(int x, int y, boolean h) {
        this.x = x;
        this.y = y;
        this.heliport = h;
        this.artifact = Element.NONE;
        this.key = Element.NONE;
        this.state = State.NORMAL;
    }

    public Element getKey() { return this.key; }
    public Element getArtifact() { return this.artifact; }
    public int getAbs() { return this.x; }
    public int getOrd() { return this.y; }
    public State getState() { return this.state; }

    public void setKey(Element key) { this.key = key; }
    public void setArtifact(Element element) { this.artifact = element; }

    public boolean hasKey() { return this.key != Element.NONE; }
    public boolean hasArtifact() { return this.artifact != Element.NONE; }

    public boolean isHeliport() { return this.heliport; }
    public boolean isSubmerged() { return this.state == State.SUBMERGED; }
    public boolean isFlooded() { return this.state == State.FLOODED; }
    public boolean isNormal() { return this.state == State.NORMAL; }

    public void updateKey() { this.key = Element.NONE; }
    public void updateArtifact() { this.artifact = Element.NONE; }

    public void flood() {
        switch(this.state) {
            case NORMAL:
                this.state = State.FLOODED;
                break;
            case FLOODED:
                this.state = State.SUBMERGED;
                break;
            case SUBMERGED:
                break;
        }
    }

    public boolean dryCell() {
        if(this.isFlooded()) {
            this.state = State.NORMAL;
            return true;
        } else if(this.isSubmerged()) {
            ViewGame.updateDisplay("Cette case est submergée, vous ne pouvez pas l'assécher");
        } else if(this.isNormal()) {
            ViewGame.updateDisplay("Cette case est déjà sèche, vous ne pouvez pas l'assécher");
        }
        return false;
    }

    public String toString() { return x + ", " + y; }
}