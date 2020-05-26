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
    private Cell heliport;
    Random random = new Random();

    public Island(int w, int h) {
        this.width = w;
        this.height = h;
        this.board = new Cell[this.width][this.height];
        this.players = new ArrayList<>();

        int heliportX = random.nextInt(this.width/2)+this.width/4;
        int heliportY = random.nextInt(this.height/2)+this.height/4;

        for(int i = 0; i < this.width; i++) {
            for(int j = 0; j < this.height; j++) {
                this.board[i][j] = new Cell(this, i, j, heliportX == i && heliportY == j);
            }
        }
        this.heliport = this.board[heliportX][heliportY];

        // TODO pas hyper convaincu par ce code
        Cell.Element[] keys = new Cell.Element[4];
        keys[0] = Cell.Element.FIRE;
        keys[1] = Cell.Element.AIR;
        keys[2] = Cell.Element.WATER;
        keys[3] = Cell.Element.EARTH;

        for(int i = 0; i < 4; i++) {
            int j = 0;
            while (j < 15){
                Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
                if (!cell.hasKey() && !cell.isHeliport()) {
                    cell.setKey(keys[i]);
                    j++;
                }
            }
            boolean artefact = false;
            while(!artefact) {
                Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
                if(cell.getAbs() < this.width/4 || cell.getAbs() > (this.width/4)*3 && cell.getOrd() < this.height/4 || cell.getOrd() > (this.height/4)*3 && !cell.hasKey() && !cell.isHeliport()) {
                    cell.setArtifact(keys[i]);
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
        int nbcell = 0;
        // TODO REVOIR L'EXCEPTION
        try{
            while (nbcell < 3) {
                Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
                if (!cell.isSubmerged()) {
                    cell.flood();
                    nbcell++;
                }
            }
        } catch (Exception e) {
            //If there's an error at runtime, flood everything
            for(int i = 0; i<this.width; i++) {
                for(int j = 0; j < this.height; j++) {
                    if (!this.board[i][j].isSubmerged()) {
                        this.board[i][j].flood();
                    }
                }
            }
        }
        notifyObservers();
        this.playerCourant.restoreNbHits();
        this.playerCourant = this.playerCourant.getNext();
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
            // TODO afficher un message au joueur
            exceptionNbHits.printStackTrace();
        }
    }

    public void play() {
        //TODO
    }

    public void movePlayer(Player.Direction key) {
        this.playerCourant.move(key);
        notifyObservers();
    }
}