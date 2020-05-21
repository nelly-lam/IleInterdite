package Model;

import javax.swing.*;

enum State{Normal, Flooded, Submerged};
enum Element{Air, Water, Fire, None};

public class Cell extends JPanel{
    boolean helicopter;
    Element key;
    Element artifact;
    State state;

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
}
