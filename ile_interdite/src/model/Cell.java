package model;

public class Cell {

    public enum State{ NORMAL, FLOODED, SUBMERGED }
    public enum Element{ AIR, WATER, FIRE, EARTH, NONE }

    private boolean helicopter;
    private Element key;
    private Element artifact;
    private State state;
    private Island model;
    private int x;
    private int y;

    public Cell(Island model, int x, int y) {
        this.helicopter = false;
        this.key = Cell.Element.NONE;
        this.artifact = Cell.Element.NONE;
        this.state = State.NORMAL;
        this.model = model;
        this.x = x;
        this.y = y;
    }

    public Cell(Island model, int x, int y, boolean h, Element k, Element a){
        this(model, x, y);
        this.helicopter = h;
        this.key = k;
        this.artifact = a;
        this.state = State.NORMAL;
    }

    /**
     * Donne la cle associee a la cellule
     */
    public Element getKey() { return this.key; }
    /**
     * Donne l'artifact associee a la cellule
     */
    public Element getArtifact() { return this.artifact; }
    public int getAbs() { return this.x; }
    public int getOrd() { return this.y; }
    public State getState() { return this.state; }


    /**
     * Teste si la cell possede une cle
     */
    public boolean hasKey(){
        return this.key != Element.NONE;
    }

    /**
     * Enleve la cle d'une case apres qu'elle soit prise
     */
    public void updateKey(){
        this.key = Element.NONE;
    }

    /**
     * Teste si la cell possede un artifact
     */
    public boolean hasArtifact(){
        return this.artifact != Element.NONE;
    }

    /**
     * Enleve l'artefact d'une case apres qu'il soit pris
     */
    public void updateArtifact(){
        this.artifact = Element.NONE;
    }

    /**
     * Teste si la cell est une case helicoptere
     */
    public boolean isHelicopter(){
        return this.helicopter;
    }

    /**
     * Teste si une cellule est submergee
     */
    public boolean isSubmerged() { return this.state == State.SUBMERGED; }

    public boolean isFlooded() { return this.state == State.FLOODED; }

    /**
     * Permet d'inonder puis submerger la cellule
     */
    public void flood(){
        switch(this.state) {
            case NORMAL:
                this.state = State.FLOODED;
                break;
            case FLOODED:
                this.state = State.SUBMERGED;
                break;
            default:
                System.out.println("Erreur dans flood");
                break;
        }
    }

    public void dewateringCell() {
        this.state = State.NORMAL;
    }

    /**
     * méthode equals() : de la classe junit.Assert
     * ICI réécriture : compare deux objets Cell, vrai si les attributs sont identiques et faux sinon
     * @param : Object obj
     * @return un boolean
     */
    @Override
    public boolean equals(Object obj){
        boolean status = false;
        if (this.helicopter == ((Cell) obj).helicopter
                && this.key == ((Cell) obj).key
                && this.artifact == ((Cell) obj).artifact
                && this.state == ((Cell) obj).state
                && this.x == ((Cell) obj).x
                && this.y == ((Cell) obj).y) {
            status = true;
        }
        return status;
    }

    public String toString(){
        return x + ", " + y;
    }
}
