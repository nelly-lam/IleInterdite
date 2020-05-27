package model;

import exceptions.ExceptionNbHits;

import java.util.ArrayList;
import java.util.Random;

public class Island extends Observable {
    public Cell[][] board;
    public int width;
    public int height;
    public ArrayList<Player> players;
    public Player playerCourant;
    private final Cell heliport;
    private final ArrayList<Cell> artifacts;
    private final static Cell.Element[] ELEMENTS = {Cell.Element.FIRE, Cell.Element.WATER, Cell.Element.EARTH, Cell.Element.AIR};
    private int nbCellSafe;
    Random random = new Random();

    public Island(int w, int h) {
        this.width = w;
        this.height = h;
        this.board = new Cell[this.width][this.height];
        this.players = new ArrayList<>();
        this.artifacts = new ArrayList<>();
        this.nbCellSafe = this.height*this.width*2;

        int heliportX = random.nextInt(this.width/2)+this.width/4;
        int heliportY = random.nextInt(this.height/2)+this.height/4;

        for(int i = 0; i < this.width; i++) {
            for(int j = 0; j < this.height; j++) {
                this.board[i][j] = new Cell(this, i, j, heliportX == i && heliportY == j);
            }
        }
        this.heliport = this.board[heliportX][heliportY];

        int nbKey = (int) (this.width*this.height*0.15);

        for(int i = 0; i < 4; i++) {
            int j = 0;
            while (j < nbKey/4){
                Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
                if (!cell.hasKey() && !cell.isHeliport()) {
                    cell.setKey(ELEMENTS[i]);
                    j++;
                }
            }
            boolean artefact = false;
            while(!artefact) {
                Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
                if(cell.getAbs() < this.width/4 || cell.getAbs() > (this.width/4)*3 && cell.getOrd() < this.height/4 || cell.getOrd() > (this.height/4)*3 && !cell.hasKey() && !cell.isHeliport() && !cell.hasArtifact()) {
                    cell.setArtifact(ELEMENTS[i]);
                    this.artifacts.add(cell);
                    artefact = true;
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
            this.playerCourant = p;
        }
        else {
            Player p = new Player(this, name, this.players.get(0), this.heliport.getAbs(), this.heliport.getOrd());
            this.players.get(this.players.size()-1).setNext(p);
            this.players.add(p);
        }
    }

    public void risingWater() {
        if (this.nbCellSafe > 2) {
            int nbcell = 0;
            while (nbcell < 3) {
                Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
                if (!cell.isSubmerged()) {
                    cell.flood();
                    nbcell++;
                    this.nbCellSafe--;
                }
            }
        }
        else {
            for(int i = 0; i<this.width; i++) {
                for(int j = 0; j < this.height; j++) {
                    if (!this.board[i][j].isSubmerged()) {
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
        if(nb < 1) {
            int hint = random.nextInt(4);
            this.playerCourant.addKey(ELEMENTS[hint]);
        }

        this.playerCourant.restoreNbHits();
        this.playerCourant = this.playerCourant.getNext();
        System.out.println(this.players.size());
        notifyObservers();
        this.stateGame();
    }

    public void dry(int x, int y) {
        try {
            this.playerCourant.addHits();
            Cell cell = this.board[x][y];
            if (cell.isFlooded()) {
                cell.dryCell();
            }
            else if (cell.isSubmerged()) {
                // TODO : exception pour l'impossibilité d'assécher
            }
            notifyObservers();
        } catch (ExceptionNbHits exceptionNbHits) {
            System.out.println("Vous ne pouvez pas assécher");
            //exceptionNbHits.printStackTrace();
        }
    }

    public void movePlayer(Player.Direction key) {
        this.playerCourant.move(key);
        notifyObservers();
    }

    public void searchKey() {
        try {
            this.playerCourant.addHits();
            Cell cell = this.board[this.playerCourant.getAbs()][this.playerCourant.getOrd()];
            if (cell.hasKey()) {
                this.playerCourant.addKey(cell.getKey());
                cell.updateKey();
            } else {
                double nb = Math.random();
                if (nb < 0.65) {
                    cell.flood();
                }
            }
        } catch (ExceptionNbHits exceptionNbHits) {
            System.out.println("Vous ne pouvez pas fouiller la zone");
            //exceptionNbHits.printStackTrace();
        }
        notifyObservers();
    }

    public void recoverArtifact () {
        try {
            Cell cell = this.board[this.playerCourant.getAbs()][this.playerCourant.getOrd()];
            if (cell.hasArtifact() && this.playerCourant.nbKeyElement(cell.getArtifact()) >= 4) {
                this.playerCourant.addHits();
                this.playerCourant.addArtifact(cell.getArtifact());
                // TODO : revoir cette partie
                try {
                    for (int i = 0; i < 4; i++) {
                        this.playerCourant.updateKey(cell.getArtifact());
                    }
                } catch (Exception notEnoughKeys) {
                    System.out.println("Erreur lors de la suppression des Cles dans recoverArtifact!");
                }
                cell.updateArtifact();
            }
        } catch (ExceptionNbHits exceptionNbHits) {
            System.out.println("Vous ne pouvez pas ramasser d'artefact");
            //exceptionNbHits.printStackTrace();
        }
    }

    public void stateGame() {
        boolean win = true;
        for (Player p : this.players) {
            if (p.isDead()) {
                System.out.println("Vous avez perdu !");
            }
            else if(p.getOrd() != this.heliport.getOrd() && p.getAbs() != this.heliport.getOrd()) {
                win = false;
            }
        }
        if(this.heliport.isSubmerged()) {
            System.out.println("Vous avez perdu !");
        }
        for (Cell artifact : this.artifacts) {
            if (artifact.isSubmerged()) {
                System.out.println("Vous avez perdu !");
            }
        }
        if(win && this.artifacts.isEmpty()) {
            System.out.println("Vous avez gagné !");
        }
    }
}