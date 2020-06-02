package model;

import exceptions.ExceptionNbHits;
import views.ViewEndGame;
import views.ViewGame;

import java.util.ArrayList;
import java.util.Random;

public class Island extends Observable {
    private final Cell[][] board;
    private final int width;
    private final int height;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private final Cell heliport;
    private ArrayList<Cell> artifacts;
    private final static Cell.Element[] ELEMENTS = {Cell.Element.FIRE, Cell.Element.WATER, Cell.Element.EARTH, Cell.Element.AIR};
    private final static Player.SpecialAction[] ACTIONS = {Player.SpecialAction.SAND, Player.SpecialAction.TELEPORTATION};
    private int nbCellSafe;
    public static Random random = new Random();

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

    public int getHeight() { return this.height; }
    public int getWidth() { return this.width; }
    public Cell getCell(int x, int y) { return this.board[x][y]; }
    public Player getCurrentPlayer() { return this.currentPlayer; }
    public ArrayList<Player> getPlayers() { return this.players; }

    public void addPlayer(String name) {
        Player p = new Player(this, name, this.heliport.getAbs(), this.heliport.getOrd());
        if(players.isEmpty()) {
            this.currentPlayer = p;
        } else {
            this.players.get(this.players.size()-1).setNext(p);
        }
        this.players.add(p);
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

        this.currentPlayer.restoreNbEvents();
        this.currentPlayer = this.currentPlayer.getNext();
        notifyObservers();
        this.stateGame();
    }

    public void movePlayer(Player.Direction key) {
        this.currentPlayer.move(key);
        notifyObservers();
    }

    public void dry(int x, int y) {
        try {
            Cell cell = this.board[x][y];
            if(cell.isFlooded()) {
                this.currentPlayer.addEvents();
                cell.dryCell();
                this.nbCellSafe++;
            } else if(cell.isSubmerged()) {
                ViewGame.updateDisplay("Cette case est submergée, vous ne pouvez pas l'assécher");
            } else if(cell.isNormal()) {
                ViewGame.updateDisplay("Cette case est déjà sèche, vous ne pouvez pas l'assécher");
            }
            notifyObservers();
        } catch(ExceptionNbHits exceptionNbHits) {
            ViewGame.updateDisplay("Vous n'avez pas assez d'actions pour assécher cette case");
        }
    }

    public void teleportation(Player p, int x, int y) {
        Cell cell = this.getCell(x, y);
        if(cell.getState() != Cell.State.SUBMERGED){
            try {
                this.currentPlayer.addEvents();
                p.teleportPlayer(x, y);
            } catch(ExceptionNbHits exceptionNbHits) {
                ViewGame.updateDisplay("Vous n'avez pas assez de coups pour vous téléporter");
            }
        } else {
            ViewGame.updateDisplay("Cette case n'est pas safe");
        }
    }

    public void searchKey() {
        try {
            this.currentPlayer.addEvents();
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
            double nb = Math.random();
            if(nb < 1) {
                int hint = random.nextInt(2);
                this.currentPlayer.addActions(ACTIONS[hint]);
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
                    this.currentPlayer.addEvents();
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

    public void giveKey(Player p, Cell.Element element) {
        try {
            this.currentPlayer.addEvents();
            p.addKey(element);
            this.currentPlayer.updateKey(element);
            ViewGame.updateDisplay("La clé a été transféré");
        } catch (ExceptionNbHits exceptionNbHits) {
            ViewGame.updateDisplay("Vous n'avez pas assez de coup");
        }
        this.notifyObservers();
    }

    public void stateGame() {
        boolean win = true;
        boolean loose = false;
        if(this.heliport.isSubmerged()) {
            loose = true;
        }
        for(Cell artifact : this.artifacts) {
            if(artifact.isSubmerged()) {
                loose = true;
            }
        }
        for(Player p : this.players) {
            if(p.isDead()) {
                loose = true;
            }
            if(p.getOrd() != this.heliport.getOrd() && p.getAbs() != this.heliport.getOrd()) {
                win = false;
            }
        }
        if(win && this.artifacts.isEmpty()) {
            ViewEndGame endGame = new ViewEndGame(true, this);
        } else if(loose) {
            ViewEndGame endGame = new ViewEndGame(false, this);
        }
    }
}