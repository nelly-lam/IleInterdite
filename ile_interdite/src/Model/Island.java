package Model;

import java.util.ArrayList;
import java.util.Random;

public class Island extends Observable {
    public Cell[][] board;
    public int width;
    public int height;
    public ArrayList<Player> players;
    public static Player playerCourant;
    Random random = new Random();

    public Island(int w, int h){
        this.width = w;
        this.height = h;
        this.board = new Cell[this.width][this.height];
        this.players = new ArrayList<Player>();
        this.players.add(new Player(this, "toto", 18, 10));
        this.players.add(new Player(this, "titi", 10, 10));

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

    public void risingWater() {
        int nbcell = 0;
        while (nbcell < 3) {
            Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
            if(!cell.isSubmerged()) {
                // TODO : Un test lorsqu'il reste moins de 2 cases
                cell.flood();
                nbcell++;
            }
        }
        notifyObservers();
    }

    public void play() {
        for(Player player : this.players) {
            player.move(key);
        }
    }

    public void movePlayer(Player player, Player.Direction key) {
        for(Player p : this.players) {
            if(p == player) {
                player.move(key);
            }
        }
        notifyObservers();
    }
}