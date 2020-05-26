package model;

import exceptions.ExceptionNbHits;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Island extends Observable {
    public Cell[][] board;
    public int width;
    public int height;
    public ArrayList<Player> players;
    public Player playerCourant;
    Random random = new Random();

    public Island(int w, int h) {
        this.width = w;
        this.height = h;
        this.board = new Cell[this.width][this.height];
        this.players = new ArrayList<>();
        this.addPlayer("toto");
        this.addPlayer("titi");

        int heliportX = random.nextInt(this.width/2)+10;
        int heliportY = random.nextInt(this.height/2)+10;

        //nous remplissons la hashmap avec toutes les cellules du plateau
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++){
                if(heliportX == i && heliportY == j) {
                    this.board[i][j] = new Cell(this, i, j, true, Cell.Element.NONE);
                }
                this.board[i][j] = new Cell(this, i, j, false, Cell.Element.NONE);
            }

        }
        Cell.Element[] keys = new Cell.Element[4];
        keys[0] = Cell.Element.FIRE;
        keys[1] = Cell.Element.AIR;
        keys[2] = Cell.Element.WATER;
        keys[3] = Cell.Element.EARTH;

        //TODO optimiser
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 15; j++) {
                Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
                if(cell.getKey() == Cell.Element.NONE) {
                    cell.setKey(keys[i]);
                }
            }

            Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
            if(cell.getKey() == Cell.Element.NONE) {
                cell.setArtifact(keys[i]);
            }
        }
    }

    public Cell getCell(int x, int y) {
        return board[x][y];
    }

    public void addPlayer(String name) {
        if(players.size() == 0) {
            Player p = new Player(this, name, 18, 10);
            this.players.add(p);
            this.playerCourant = p;
        }
        else {
            Player p = new Player(this, name, this.players.get(0), 18, 10);
            this.players.get(this.players.size()-1).setNext(p);
            this.players.add(p);
        }
    }

    public void risingWater() {
        int nbcell = 0;
        while (nbcell < 3) {
            Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
            if (!cell.isSubmerged()) {
                try{
                    cell.flood();
                    nbcell++;
                }
                catch (Exception e) {
                    //If there's an error at runtime, flood everything
                    for(int i = 0; i<this.width; i++){
                        for(int j = 0; j < this.height; j++){
                            if (!this.board[i][j].isSubmerged()) {
                                this.board[i][j].flood();
                            }
                        }
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
                cell.dewateringCell();
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