package Model;

import javax.swing.*;

public class Cell extends JPanel{

    public enum State{Normal, Flooded, Submerged}
    public enum Element{Air, Water, Fire, None}

    public boolean helicopter;
    public Element key;
    public Element artifact;
    public State state;

    public Cell(boolean h, Element k, Element a){
        this.helicopter = h;
        this.key = k;
        this.artifact = a;
        this.state = State.Normal;
    }

    /**
     * Teste si la cell possede une cle
     */
    public boolean hasKey(){
        return this.key != Element.None;
    }

    /**
     * Teste si la cell possede un artifact
     */
    public boolean hasArtifact(){
        return this.key != Element.None;
    }

    /**
     * Teste si la cell est une case helicoptere
     */
    public boolean isHelicopter(){
        return this.helicopter;
    }

    /**
     * Enleve la cle d'une case apres qu'elle soit prise
     */
    public void updateKey(){
        this.key = Element.None;
    }

    /**
     * Enleve l'artefact d'une case apres qu'il soit pris
     */
    public void updateArtifact(){
        this.artifact = Element.None;
    }

    /**
     * Permet d'inonder puis submerger la cellule
     */
    public void flood(){
        if(this.state == State.Normal){
            this.state = State.Flooded;
        } else if (this.state == State.Flooded){
            this.state = State.Submerged;
        }
    }

    public boolean isSubmerged() { return this.state == State.Submerged; }

    public void changeState() {
        switch(this.state) {
            case Normal:
                this.state = State.Flooded;
                break;
            case Flooded:
                this.state = State.Submerged;
                break;
        }
    }
}
