package interfaces;

public interface InterfaceCell {

    /**
     * Attribut une cle a une cellule
     * @param key
     *      un Element
     */
    void setKey(model.Cell.Element key);

    /**
     * Attribut un artefact a une cellule
     * @param element
     *          un Element
     */
    void setArtifact(model.Cell.Element element);

    /**
     * Indique si une cellule possede une cle
     * @return true si elle en possede une, false sinon
     */
    boolean hasKey();

    /**
     * Indique si une cellule possede un artefact
     * @return true si elle en possede une, false sinon
     */
    boolean hasArtifact();

    /**
     * Indique si une cellule possede un heliport
     * @return true si elle en possede une, false sinon
     */
    boolean isHeliport();

    /**
     * Indique si une cellule est submergee
     * @return true si elle est SUBMERGED, false sinon
     */
    boolean isSubmerged();

     /**
      * Indique si une cellule est inondee
      * @return true si elle est FLOODED, false sinon
      */
     boolean isFlooded();

     /**
      * Enleve la cle d'une case apres qu'elle soit prise
      */
     void updateKey();

     /**
      * Enleve l'artefact d'une case apres qu'il soit pris
      */
     void updateArtifact();

     /**
      * Permet d'inonder puis submerger la cellule
      */
     void flood();

     /**
      * Permet d'assecher une cellule inondee
      */
     void dryCell();

     /**
      * Affiche les coordonnees d'une cellule
      * @return les cordonnees sous la forme x,y
      */
     String toString();
}
