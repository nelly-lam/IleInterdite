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
    private final Cell.Element[] elements = {Cell.Element.FIRE, Cell.Element.WATER, Cell.Element.EARTH, Cell.Element.AIR};
    private final Player.SpecialAction[] actions = {Player.SpecialAction.SAND, Player.SpecialAction.TELEPORTATION};
    private ArrayList<Cell> cellsSafe;
    private boolean ingineerEvent;
    public static final Random RANDOM = new Random();

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.ingineerEvent = false;
        this.board = new Cell[this.width][this.height];
        this.players = new ArrayList<>();
        this.artifacts = new ArrayList<>();
        this.cellsSafe = new ArrayList<>();

        int heliportX = RANDOM.nextInt(this.width / 2) + this.width / 4;
        int heliportY = RANDOM.nextInt(this.height / 2) + this.height / 4;

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.board[i][j] = new Cell(i, j, heliportX == i && heliportY == j);
                this.cellsSafe.add(this.board[i][j]);
            }
        }
        this.heliport = this.board[heliportX][heliportY];

        int nbKey = (int) (this.width * this.height * 0.25);

        for (int i = 0; i < 4; i++) {
            boolean isArtefactPlaced = false;
            while (!isArtefactPlaced) {
                Cell cell = this.board[RANDOM.nextInt(this.width)][RANDOM.nextInt(this.height)];
                if (((cell.getAbs() < (this.width / 4)) || (cell.getAbs() > ((this.width / 4) * 3))) && ((cell.getOrd() < (this.height / 4)) || (cell.getOrd() > ((this.height / 4) * 3))) && !cell.isHeliport() && !cell.hasArtifact()) {
                    cell.setArtifact(elements[i]);
                    this.artifacts.add(cell);
                    isArtefactPlaced = true;
                }
            }

            int j = 0;
            while (j < nbKey / 4) {
                Cell cell = this.board[RANDOM.nextInt(this.width)][RANDOM.nextInt(this.height)];
                if (!cell.hasKey() && !cell.isHeliport() && !cell.hasArtifact()) {
                    cell.setKey(elements[i]);
                    j++;
                }
            }
        }
    }

    public int getHeight() { return this.height; }
    public int getWidth() { return this.width; }
    public ArrayList<Cell> getArtifacts() { return this.artifacts; }
    public Cell getCell(int x, int y) { return this.board[x][y]; }
    public Player getCurrentPlayer() { return this.currentPlayer; }
    public ArrayList<Player> getPlayers() { return this.players; }
    public Cell getHeliport() { return this.heliport; }

    public void addPlayer(String name, Player.Role role) {
        Player p = new Player(this, name, role, this.heliport.getAbs(), this.heliport.getOrd());
        if (players.isEmpty()) {
            p.setNext(p);
            this.currentPlayer = p;
        } else {
            this.players.get(this.players.size() - 1).setNext(p);
            p.setNext(this.players.get(0));
        }
        this.players.add(p);
    }

    public void removePlayer(Player p) {
        if (this.currentPlayer == p) {
            this.players.get(this.players.size()-1).setNext(p.getNext());
        } else {
            this.players.get(this.players.indexOf(p)-1).setNext(p.getNext());
        }
        this.players.remove(p);
        if (!this.players.isEmpty()) {
            this.currentPlayer = this.players.get(0);
        }
    }

    public void risingWater() {
        int nbcell = 0;
        while (nbcell < 3) {
            try {
                int indice = RANDOM.nextInt(this.cellsSafe.size());
                Cell cell = this.cellsSafe.get(indice);
                if (!cell.isSubmerged()) {
                    cell.flood();
                    nbcell++;
                } else {
                    this.cellsSafe.remove(cell);
                }
            } catch (IllegalArgumentException e) {
                this.stateGame();
            }
        }

        double nb = Math.random();
        if (nb < 0.2) {
            int indice = RANDOM.nextInt(4);
            this.currentPlayer.addKey(elements[indice]);
        }

        this.currentPlayer.restoreNbEvents();
        this.currentPlayer.restoreSpecialEvent();
        this.stateGame();
        this.currentPlayer = this.currentPlayer.getNext();
        this.ingineerEvent = false;
        this.notifyObservers();
    }

    public void movePlayer(Player.Direction key) {
        this.currentPlayer.move(key);
        this.notifyObservers();
    }

    public void sandBag(int x, int y) {
        if (this.currentPlayer.hasAction(Player.SpecialAction.SAND)) {
            try {
                Cell cell = this.getCell(x, y);
                if (cell.dryCell()) {
                    this.currentPlayer.useSpecialEvent();
                    this.currentPlayer.updateAction(Player.SpecialAction.SAND);
                    ViewGame.updateDisplay("Assèchement effectué");
                }
            } catch (ExceptionSpecialEvent exceptionSpecialEvent){
                ViewGame.updateDisplay("Vous ne pouvez plus utiliser d'actions spéciales pour ce tour");
            }
        } else {
            ViewGame.updateDisplay("Vous ne possédez pas cette action");
        }
        this.notifyObservers();
    }

    public void teleportation(int x, int y, boolean leftClick) {
        if (this.currentPlayer.hasAction(Player.SpecialAction.TELEPORTATION)) {
            try {
                this.currentPlayer.useSpecialEvent();
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
            } catch (ExceptionSpecialEvent exceptionSpecialEvent) {
                ViewGame.updateDisplay("Vous ne pouvez plus utiliser d'actions spéciales pour ce tour");
            }
        } else {
            ViewGame.updateDisplay("Vous ne possédez pas cette action");
        }
        this.notifyObservers();
    }

    public boolean giveKey(Player p, Cell.Element element) {
        if (this.currentPlayer.hasKey(element)) {
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
        }
        return false;
    }

    public void dry(int x, int y) {
        try {
            Cell cell = this.getCell(x, y);
            if (cell.dryCell()) {
                if (this.currentPlayer.getRole() != Player.Role.INGENIEUR || !this.ingineerEvent) {
                    this.currentPlayer.addEvents();
                    this.ingineerEvent = true;
                } else if (this.currentPlayer.getRole() == Player.Role.INGENIEUR && this.ingineerEvent) {
                    this.ingineerEvent = false;
                }
            }
            notifyObservers();
        } catch (ExceptionNbEvents exceptionNbEvents) {
            ViewGame.updateDisplay("Vous n'avez pas assez d'actions pour assécher cette case");
        }
    }

    public void searchKey() {
        try {
            this.currentPlayer.addEvents();
            Cell cell = this.board[this.currentPlayer.getAbs()][this.currentPlayer.getOrd()];
            if (cell.hasKey()) {
                this.currentPlayer.addKey(cell.getKey());
                ViewGame.updateDisplay("Vous avez récupéré une clé " + cell.getKey() + " !");
                cell.updateKey();
            } else {
                double nb = Math.random();
                if (nb < 0.6) {
                    cell.flood();
                }
            }
            double nb = Math.random();
            if (nb < 0.3) {
                int hint = RANDOM.nextInt(2);
                this.currentPlayer.addActions(actions[hint]);
                ViewGame.updateDisplay("Vous avez récupéré une action spéciale !");
            }
        } catch (ExceptionNbEvents exceptionNbEvents) {
            ViewGame.updateDisplay("Vous n'avez pas assez d'actions pour chercher une clé");
        }
        notifyObservers();
    }

    public void recoverArtifact() {
        try {
            Cell cell = this.board[this.currentPlayer.getAbs()][this.currentPlayer.getOrd()];
            if (cell.hasArtifact()) {
                if (this.currentPlayer.recoverArtifactPlayer(cell)) {
                    this.artifacts.remove(cell);
                    ViewGame.updateDisplay("Vous avez récupéré un artefact !");
                }
            } else {
                ViewGame.updateDisplay("Il n'y a pas d'artefacts sur cette case");
            }
        } catch (ExceptionNbEvents exceptionNbEvents) {
            ViewGame.updateDisplay("Vous n'avez pas assez d'actions pour ramassez l'artefact");
        }
        notifyObservers();
    }

    public void stateGame() {
        boolean won = true;
        boolean lost = false;
        if (this.heliport.isSubmerged() || this.currentPlayer.isDead()) {
            lost = true;
        }
        for (Cell artifact : this.artifacts) {
            if (artifact.isSubmerged()) {
                lost = true;
            }
        }
        for (Player p : this.players) {
            if ((p.getOrd() != this.heliport.getOrd()) && (p.getAbs() != this.heliport.getAbs())) {
                won = false;
            }
        }
        if (won && this.artifacts.isEmpty()) {
            new ViewEndGame(true, this);
        } else if (lost) {
            new ViewEndGame(false, this);
        }
    }
}