package Model;

public class Cell {

    public enum State{Normal, Flooded, Submerged}
    public enum Element{Air, Water, Fire, None}

    public boolean helicopter;
    public Element key;
    public Element artifact;
    public State state;

    public Island model;
    public int x;
    public int y;

    public Cell(boolean h, Element k, Element a){
        this.helicopter = h;
        this.key = k;
        this.artifact = a;
        this.state = State.Normal;
    }

    public Cell(Island model, int x, int y) {
        this.model = model;
        this.x = x;
        this.y = y;
        this.state = State.Normal;
    }

    /**
     * Teste si la cell possede une cle
     */
    public boolean hasKey(){
        return this.key != Element.None;
    }

    /**
     * Donne la cle associee a la cellule
     */
    public Element getKey() { return this.key; }

    /**
     * Enleve la cle d'une case apres qu'elle soit prise
     */
    public void updateKey(){
        this.key = Element.None;
    }

    /**
     * Teste si la cell possede un artifact
     */
    public boolean hasArtifact(){
        return this.artifact != Element.None;
    }

    /**
     * Enleve l'artefact d'une case apres qu'il soit pris
     */
    public void updateArtifact(){
        this.artifact = Element.None;
    }

    /**
     * Donne l'artifact associee a la cellule
     */
    public Element getArtifact() { return this.artifact; }

    /**
     * Teste si la cell est une case helicoptere
     */
    public boolean isHelicopter(){
        return this.helicopter;
    }

    /**
     * Teste si une cellule est submergee
     */
    public boolean isSubmerged() { return this.state == State.Submerged; }

    /**
     * Permet d'inonder puis submerger la cellule
     */
    public void flood(){
        switch(this.state) {
            case Normal:
                this.state = State.Flooded;
                break;
            case Flooded:
                this.state = State.Submerged;
                break;
        }
    }

    public String toString(){
        return x + ", " + y;
    }
}
