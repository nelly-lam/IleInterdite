package interfaces;

import model.Cell;
import model.Player;

public interface InterfaceIsland{

    /**
     * Ajoute un joueur a la liste des joueurs de Island et met a jour le joueur courant
     * @param name
     *         le nom du Player a ajouter, sous forme de chaine de caractere
     */
    void addPlayer(String name);

    /**
     * Retire un joueur de la liste des joueurs de Island et met a jour le joueur courant
     * @param p
     *      le Player a retirer
     */
    void removePlayer(Player p);

    /**
     * Inonde trois cellules au hasard, si la cellule est normale ou inondee elle devient submergee
     */
    void risingWater();

    /**
     * Deplace un joueur selon la direction (UP DOWN RIGHT LEFT) entree en parametre
     * @param key
     *      une Direction
     */
    void movePlayer(Player.Direction key);

    /**
     * Asseche une cellule choisie par le joueur
     * et met a jour la liste des actions speciales du joueur
     * @param x
     *       une abscisse int
     * @param y
     *      une ordonnee int
     */
    void sandBag(int x, int y);

    /**
     * Change la position d'un joueur selon des coordonnees choisies et le clic de la souris :
     *      seul si clic gauche
     *      avec les joueurs se trouvant sur sa cellule si clic droit
     * @param x
     *      un abscisse int
     * @param y
     *      une ordonnee int
     * @param leftClick
     *      un boolean qui informe sur le clic (droit ou gauche)
     */
    void teleportation(int x, int y, boolean leftClick);

    /**
     * Indique si l'echange de cle entre deux joueurs a ete effectue,
     * et met a jour le nombre d'action et la liste des cles des joueurs
     * @param p
     *      le Player avec qui l'echange a lieu
     * @param element
     *      la cle qui sera echangee
     * @return true si l'echange a bien eu lieu, false sinon
     */
    boolean giveKey(Player p, Cell.Element element);

    /**
     * Asseche une cellule selon des coordonnees donnees en parametre (choisi par un joueur),
     * et met a jour le nombre d'action restant du joueur courant
     * @param x
     *      une abscisse int
     * @param y
     *      une ordonnee int
     */
    void dry(int x, int y);

    /**
     * Effectue l'action de recherche de cle :
     *      soit le joueur recupere une cle si presente sur sa cellule
     *      soit inonde sa cellule
     *      soit obtient une action speciale
     * puis met a jour le nombre d'action restant du joueur
     */
    void searchKey();

    /**
     * Retire de la liste des artefacts de l'ile l'artefact qui se trouve sur la cellule du joueur
     * si il y en a bien un, et met a jour la cellule
     */
    void recoverArtifact();

    /**
     * Enumere les cas de defaite et de victoire d'une partie
     * Perd si
     *      l'heliport est submerge
     *      un des artefact est submerge
     *      un des joueurs est mort, voir isDead()
     * Gagne si
     *      tous les artefacts sont recuperes et les joueurs sur l'heliport
     */
    void stateGame();
}