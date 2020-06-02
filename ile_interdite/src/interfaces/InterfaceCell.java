package interfaces;

// Je te laisse faire Antoine, comme tu le sentais

public interface InterfaceCell {

    /**
     * Donne la cle associee a la cellule
     * @return
     */
    public model.Cell.Element getKey();

    /**
     * Donne l'artifact associee a la cellule
     * @return
     */
    public model.Cell.Element getArtifact();

    /**
     *
     * @return
     */
    public int getAbs();

    /**
     *
     * @return
     */
    public int getOrd();

    /**
     *
     * @return
     */
    public model.Cell.State getState();

    /**
     *
     * @param key
     */
    public void setKey(model.Cell.Element key);

    /**
     *
     * @param element
     */
    public void setArtifact(model.Cell.Element element);

    /**
     * Teste si la cell possede une cle
     */
    public boolean hasKey();

    /**
     * Teste si la cell possede un artifact
     */
    public boolean hasArtifact();

    /**
     * Teste si la cell est une case helicoptere
     */
    public boolean isHeliport();

    /**
     * Teste si une cellule est submergee
     */
    public boolean isSubmerged();

     /**
      *
      * @return
      */
     public boolean isFlooded();

     /**
      * Enleve la cle d'une case apres qu'elle soit prise
      */
     public void updateKey();

     /**
      * Enleve l'artefact d'une case apres qu'il soit pris
      */
     public void updateArtifact();

     /**
      * Permet d'inonder puis submerger la cellule
      */
     public void flood();

     /**
      *
      */
     public void dryCell();

     /**
      * méthode equals() : de la classe junit.Assert
      * ICI réécriture : compare deux objets Cell, vrai si les attributs sont identiques et faux sinon
      * @param : Object obj
      * @return un boolean
      */
     @Override
     public boolean equals(Object obj);

     /**
      *
      * @return
      */
     public String toString();
}
