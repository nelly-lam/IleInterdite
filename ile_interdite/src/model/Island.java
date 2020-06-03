package model;

import exceptions.ExceptionNbEvents;
import exceptions.ExceptionSpecialEvent;
import interfaces.InterfaceIsland;
import views.ViewEndGame;
import views.ViewGame;

import java.util.ArrayList;
import java.util.Random;

public class Island extends Observable implements InterfaceIsland {
    private final Cell[][] board;
    private final int width;
    private final int height;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private final Cell heliport;
    private ArrayList<Cell> artifacts;
    private final static Cell.Element[] ELEMENTS = {Cell.Element.FIRE, Cell.Element.WATER, Cell.Element.EARTH, Cell.Element.AIR};
    private final static Player.SpecialAction[] ACTIONS = {Player.SpecialAction.SAND, Player.SpecialAction.TELEPORTATION};
    private ArrayList<Cell> nbCellSafe;
    public static Random random = new Random();

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.board = new Cell[this.width][this.height];
        this.players = new ArrayList<>();
        this.artifacts = new ArrayList<>();
        this.nbCellSafe = new ArrayList<>();

        int heliportX = random.nextInt(this.width / 2) + this.width / 4;
        int heliportY = random.nextInt(this.height / 2) + this.height / 4;

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.board[i][j] = new Cell(i, j, heliportX == i && heliportY == j);
                this.nbCellSafe.add(this.board[i][j]);
            }
        }
        this.heliport = this.board[heliportX][heliportY];

        int nbKey = (int) (this.width * this.height * 0.25);

        for (int i = 0; i < 4; i++) {
            boolean isArtefactPlaced = false;
            while (!isArtefactPlaced) {
                Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
                if (cell.getAbs() < this.width / 4 || cell.getAbs() > (this.width / 4) * 3 && cell.getOrd() < this.height / 4 || cell.getOrd() > (this.height / 4) * 3 && !cell.isHeliport() && !cell.hasArtifact()) {
                    cell.setArtifact(ELEMENTS[i]);
                    this.artifacts.add(cell);
                    isArtefactPlaced = true;
                }
            }

            int j = 0;
            while (j < nbKey / 4) {
                Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
                if (!cell.hasKey() && !cell.isHeliport() && !cell.hasArtifact()) {
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
        if (players.isEmpty()) {
            p.setNext(p);
            this.currentPlayer = p;
        } else {
            this.players.get(this.players.size() - 1).setNext(p);
            p.setNext(this.players.get(0));
        }
        this.players.add(p);
    }

    public void risingWater() {
        int nbcell = 0;
        while (nbcell < 3) {
            try {
                int indice = random.nextInt(this.nbCellSafe.size());
                Cell cell = this.nbCellSafe.get(indice);
                if (!cell.isSubmerged()) {
                    cell.flood();
                    nbcell++;
                } else {
                    this.nbCellSafe.remove(cell);
                }
            } catch (IllegalArgumentException e) {
                this.stateGame();
            }
        }

        double nb = Math.random();
        if (nb < 0.2) {
            int indice = random.nextInt(4);
            this.currentPlayer.addKey(ELEMENTS[indice]);
        }

        this.currentPlayer.restoreNbEvents();
        this.currentPlayer = this.currentPlayer.getNext();
        this.notifyObservers();
        this.stateGame();
    }

    public void movePlayer(Player.Direction key) {
        this.currentPlayer.move(key);
        this.notifyObservers();
    }

    public void sandBag(int x, int y) {
        if (this.currentPlayer.hasAction(Player.SpecialAction.SAND)) {
            try {
                this.currentPlayer.useSpecialEvent();
                this.dry(x, y);
                this.currentPlayer.updateAction(Player.SpecialAction.SAND);
                ViewGame.updateDisplay("Assèchement effectué");
            } catch(ExceptionSpecialEvent exceptionSpecialEvent){
                ViewGame.updateDisplay("Vous ne pouvez plus utiliser d'actions spéciales pour ce tour");
            }
        } else {
            ViewGame.updateDisplay("Vous ne possédez pas cette action");
        }
        this.notifyObservers();
    }

    public void teleportation(int x, int y, boolean leftClick) {
        if (this.currentPlayer.hasAction(Player.SpecialAction.TELEPORTATION)) {
            if (leftClick) {
                this.currentPlayer.teleportPlayer(x, y);
            } else {
                for (Player p : this.players) {
                    if (this.currentPlayer.isOnSameCell(p)) {
                        p.teleportPlayer(x, y);
                    }
                }
            }
            this.currentPlayer.updateAction(Player.SpecialAction.TELEPORTATION);
            ViewGame.updateDisplay("Téléportation effectuée");
        } else {
            ViewGame.updateDisplay("Vous ne possédez pas cette action");
        }
        this.notifyObservers();
    }

    public boolean giveKey(Player p, Cell.Element element) {
        if(this.currentPlayer.hasKey(element)) {
            try {
                this.currentPlayer.addEvents();
                p.addKey(element);
                this.currentPlayer.updateKey(element);
                ViewGame.updateDisplay("La clé a été transféré");
                this.notifyObservers();
                return true;
            } catch (ExceptionNbEvents exceptionNbEvents) {
                ViewGame.updateDisplay("Vous n'avez pas assez d'actions pour échanger les clés'");
            }
        } else {
            ViewGame.updateDisplay("Vous n'avez pas de clés à donner");
        }
        return false;
    }

    public void dry(int x, int y) {
        try {
            Cell cell = this.getCell(x, y);
            if(cell.dryCell()) {
                this.currentPlayer.addEvents();
            }
            notifyObservers();
        } catch(ExceptionNbEvents exceptionNbEvents) {
            ViewGame.updateDisplay("Vous n'avez pas assez d'actions pour assécher cette case");
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
            if(nb < 0.15) {
                int hint = random.nextInt(2);
                this.currentPlayer.addActions(ACTIONS[hint]);
            }
        } catch(ExceptionNbEvents exceptionNbEvents) {
            ViewGame.updateDisplay("Vous n'avez pas assez d'actions pour chercher une clé");
        }
        notifyObservers();
    }

    public void recoverArtifact() {
        try {
            Cell cell = this.board[this.currentPlayer.getAbs()][this.currentPlayer.getOrd()];
            if(cell.hasArtifact()) {
                if(this.currentPlayer.recoverArtifactPlayer(cell)) {
                    this.artifacts.remove(cell);
                }
            } else {
                ViewGame.updateDisplay("Il n'y a pas d'artefacts sur cette case");
            }
        } catch(ExceptionNbEvents exceptionNbEvents) {
            ViewGame.updateDisplay("Vous n'avez pas assez d'actions' pour ramassez l'artefact");
        }
        notifyObservers();
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
            new ViewEndGame(true, this);
        } else if(loose) {
            new ViewEndGame(false, this);
        }
    }
}