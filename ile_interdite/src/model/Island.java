package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Island extends Observable {
    public Cell[][] board;
    public int width;
    public int height;
    public List<Player> players;
    public Player playerCourant;
    Random random = new Random();

    public Island(int w, int h) {
        this.width = w;
        this.height = h;
        this.board = new Cell[this.width][this.height];
        this.players = new ArrayList<>();
        this.addPlayer("toto");

        //helicoptere 1chance sur width*height
        //keys on va en avoir 4
        //artifact on va en avoir 4

        //nous remplissons la hashmap avec toutes les cellules du plateau
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++){
                this.board[i][j] = new Cell(this,i, j);
                //TODO ajouter les random pour les keys et artifacts
            }
            //creer une cell pour chaque i
            //donner une Model.Location qui augmente en x et y en fonction de width et height
            //random les keys et artifacts
            //Pas key et artifact du meme element sur la meme case
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

    public void dewatering(int x, int y) {
        Cell cell = this.board[x][y];
        if (cell.isFlooded()) {
            cell.dewateringCell();
        }
        else if (cell.isSubmerged()) {
            // TODO : exceptions pour l'impossibilité d'assécher
        }
        notifyObservers();
    }

    public void play() {
        //TODO
    }

    public void movePlayer(Player.Direction key) {
        this.playerCourant.move(key);
        notifyObservers();
    }
}