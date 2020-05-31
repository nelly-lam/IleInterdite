package model;

import exceptions.ExceptionNbHits;
import views.ViewEndGame;
import views.ViewGame;

import java.util.ArrayList;
import java.util.Random;

public class Island extends Observable {
    public Cell[][] board;
    public int width;
    public int height;
    public ArrayList<Player> players;
    public Player currentPlayer;
    private final Cell heliport;
    private ArrayList<Cell> artifacts;
    private final static Cell.Element[] ELEMENTS = {Cell.Element.FIRE, Cell.Element.WATER, Cell.Element.EARTH, Cell.Element.AIR};
    private int nbCellSafe;
    Random random = new Random();

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new Cell[this.width][this.height];
        this.players = new ArrayList<>();
        this.artifacts = new ArrayList<>();
        this.nbCellSafe = this.height*this.width*2;

        int heliportX = random.nextInt(this.width/2)+this.width/4;
        int heliportY = random.nextInt(this.height/2)+this.height/4;

        for(int i = 0; i < this.width; i++) {
            for(int j = 0; j < this.height; j++) {
                this.board[i][j] = new Cell(i, j, heliportX == i && heliportY == j);
            }
        }
        this.heliport = this.board[heliportX][heliportY];

        int nbKey = (int) (this.width*this.height*0.25);

        for(int i = 0; i < 4; i++) {
            boolean isArtefactPlaced = false;
            while(!isArtefactPlaced) {
                Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
                if(cell.getAbs() < this.width/4 || cell.getAbs() > (this.width/4)*3 && cell.getOrd() < this.height/4 || cell.getOrd() > (this.height/4)*3 && !cell.isHeliport() && !cell.hasArtifact()) {
                    cell.setArtifact(ELEMENTS[i]);
                    this.artifacts.add(cell);
                    isArtefactPlaced = true;
                }
            }

            int j = 0;
            while(j < nbKey/4) {
                Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
                if(!cell.hasKey() && !cell.isHeliport() && !cell.hasArtifact()) {
                    cell.setKey(ELEMENTS[i]);
                    j++;
                }
            }
        }
    }

    public Cell getCell(int x, int y) {
        return board[x][y];
    }

    public void addPlayer(String name) {
        if(players.isEmpty()) {
            Player p = new Player(this, name, this.heliport.getAbs(), this.heliport.getOrd());
            this.players.add(p);
            this.currentPlayer = p;
        } else {
            Player p = new Player(this, name, this.players.get(0), this.heliport.getAbs(), this.heliport.getOrd());
            this.players.get(this.players.size()-1).setNext(p);
            this.players.add(p);
        }
    }

    public void risingWater() {
        if(this.nbCellSafe > 2) {
            int nbcell = 0;
            while(nbcell < 3) {
                Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
                if(!cell.isSubmerged()) {
                    cell.flood();
                    nbcell++;
                    this.nbCellSafe--;
                }
            }
        }
        else {
            for(int i = 0; i < this.width; i++) {
                for(int j = 0; j < this.height; j++) {
                    if(!this.board[i][j].isSubmerged()) {
                        this.board[i][j].flood();
                        this.nbCellSafe--;
                    }
                    if(this.board[i][j].getState() == Cell.State.FLOODED) {
                        j--;
                    }
                    if(this.nbCellSafe == 0) { break; }
                }
            }
        }

        double nb = Math.random();
        if(nb < 0.2) {
            int hint = random.nextInt(4);
            this.currentPlayer.addKey(ELEMENTS[hint]);
        }

        this.currentPlayer.restoreNbHits();
        this.currentPlayer = this.currentPlayer.getNext();
        notifyObservers();
        this.stateGame();
    }

    public void dry(int x, int y) {
        try {
            Cell cell = this.board[x][y];
            if(cell.isFlooded()) {
                this.currentPlayer.addHits();
                cell.dryCell();
                this.nbCellSafe++;
            }
            else if(cell.isSubmerged()) {
                ViewGame.updateDisplay("Cette case est submergée, vous ne pouvez pas l'assécher");
            }
            notifyObservers();
        } catch(ExceptionNbHits exceptionNbHits) {
            ViewGame.updateDisplay("Vous n'avez pas assez de coups pour assécher cette case");
        }
    }

    public void movePlayer(Player.Direction key) {
        this.currentPlayer.move(key);
        notifyObservers();
    }

    public void searchKey() {
        try {
            this.currentPlayer.addHits();
            Cell cell = this.board[this.currentPlayer.getAbs()][this.currentPlayer.getOrd()];
            if(cell.hasKey()) {
                this.currentPlayer.addKey(cell.getKey());
                cell.updateKey();
            } else {
                double nb = Math.random();
                if(nb < 0.65) {
                    cell.flood();
                }
            }
        } catch(ExceptionNbHits exceptionNbHits) {
            ViewGame.updateDisplay("Vous n'avez pas assez de coups pour chercher une clé");
        }
        notifyObservers();
    }

    public void recoverArtifact() {
        try {
            Cell cell = this.board[this.currentPlayer.getAbs()][this.currentPlayer.getOrd()];
            if(cell.hasArtifact()) {
                if(this.currentPlayer.nbKeyElement(cell.getArtifact()) >= 1) {
                    this.currentPlayer.addHits();
                    this.currentPlayer.addArtifact(cell.getArtifact());
                    for(int i = 0; i < 1; i++) {
                        this.currentPlayer.updateKey(cell.getArtifact());
                    }
                    this.artifacts.remove(cell);
                    cell.updateArtifact();
                } else {
                    ViewGame.updateDisplay("Il vous manque " + (4 - this.currentPlayer.nbKeyElement(cell.getArtifact())) +" clés pour récupérer cet artefact");
                }
            } else {
                ViewGame.updateDisplay("Il n'y a pas d'artefacts sur cette case");
            }
        } catch(ExceptionNbHits exceptionNbHits) {
            ViewGame.updateDisplay("Vous n'avez pas assez de coups pour ramassez l'artefact");
        }
        notifyObservers();
    }

    public void stateGame() {
        if(this.heliport.isSubmerged()) {
            ViewEndGame.display(false);
        }
        for(Cell artifact : this.artifacts) {
            if(artifact.isSubmerged()) {
                ViewEndGame.display(false);
            }
        }
        boolean win = true;
        for(Player p : this.players) {
            if(p.isDead()) {
                ViewEndGame.display(false);
            }
            if(p.getOrd() != this.heliport.getOrd() && p.getAbs() != this.heliport.getOrd()) {
                win = false;
            }
        }
        if(win && this.artifacts.isEmpty()) {
            ViewEndGame.display(true);
        }
    }
}

    //for Actions Spéciales : Sac de sable OUPS
    /**
     * méthode dryRandomly() : liste les Cell FLOODED, en prend une au hasard et l'assèche
     */
    /*
    public void dryRandomly(){
        try {
            ArrayList<Cell> listFloodedCells = new ArrayList<Cell>();
            for (int i = 0; i < this.width; i++){
                for (int j = 0; j < this.height; j++){
                    if (this.board[i][j].getState() == Cell.State.FLOODED){
                        listFloodedCells.add(this.board[i][j]);
                    }
                }
            }
            Random rand = new Random();
            int rand1 = rand.nextInt(listFloodedCells.size()-1);
            Cell cell = listFloodedCells.get(rand1);
            cell.dryCell();
            this.playerCourant.addHits();
            notifyObservers();
        } catch (ExceptionNbHits exceptionNbHits) {
            System.out.println("Vous ne pouvez pas assécher");
            //exceptionNbHits.printStackTrace();
        }
    }
    */

    //for Actions Spéciales EN CHANTIER évènement recherche clés
    /*public void searchKey2(int x, int y){
        try {
            Cell cell = this.board[this.currentPlayer.getAbs()][this.currentPlayer.getOrd()];
            if (cell.hasKey()) {  //Récup la clé si il y en a une
                this.currentPlayer.addKey(cell.getKey());
                System.out.println("clé obtenue :" + cell.getKey());
                cell.updateKey();
                this.currentPlayer.addHits();
            } else {              //Sinon inonde la Cell sur laquelle le PlayerCourant se situe
                double rand = Math.random();
                if (rand < 0.33) {
                    double nb = Math.random();
                    if (nb < 0.65) {
                        cell.flood();
                        this.currentPlayer.addHits();
                    }            //Ou rien
                } else if (rand >= 0.33 && rand < 0.66) { //Ou assèche une Cell au hasard
                    Cell cell2 = this.board[x][y];
                    if (cell2.getState() == Cell.State.FLOODED){ this.dry(x, y); }
                } else {  //Ou se téléporte sur une Cell de son choix
                    //System.out.println("You can't travel yet");
                    this.currentPlayer.teleportPlayer(x,y);
                }
            }
        } catch (ExceptionNbHits exceptionNbHits) {
            System.out.println("Vous ne pouvez pas fouiller la zone");
            //exceptionNbHits.printStackTrace();
        }
        notifyObservers();
    }*/