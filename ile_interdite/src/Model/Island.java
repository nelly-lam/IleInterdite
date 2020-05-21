package Model;

import java.util.HashMap;
import java.util.Observable;
import java.util.Random;

public class Island extends Observable {
    //public HashMap<Coord, Cell> board;
    public Cell[][] board;
    public int width;
    public int height;
    Random random = new Random();

    public Island(int w, int h){
        this.width = w;
        this.height = h;

        //this.board = new HashMap<Coord, Cell>();
        this.board = new Cell[this.width][this.height];
        //helicoptere 1chance sur width*height
        //keys on va en avoir 4
        //artifact on va en avoir 4

        //nous remplissons la hashmap avec toutes les cellules du plateau
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++){
                Coord l = new Coord(i,j);
                Cell c = new Cell(false, Cell.Element.None, Cell.Element.None);
                //board.put(l, c);
                this.board[i][j] = new Cell(this,i, j);
            }
            //creer une cell pour chaque i
            //donner une Model.Location qui augmente en x et y en fonction de width et height
            //random les keys et artifacts
            //Pas key et artifact du meme element sur la meme case
        }
    }

    public Cell getCellule(int x, int y) {
        return board[x][y];
    }

    public void risingWater() {
        int nbcell = 0;
        while (nbcell < 3) {
            //Cell cell = this.board.get(new Coord(random.nextInt(this.width), random.nextInt(this.height)));
            Cell cell = this.board[random.nextInt(this.width)][random.nextInt(this.height)];
            if(!cell.isSubmerged()) {
                // TODO : Un test lorsqu'il reste moins de 2 cases
                cell.flood();
                nbcell++;
            }
        }
        notifyObservers();
    }
}

/**
board{
        JPanel board
        Hashmap<location, cellule> //permet de garder les cellules
    int width
            int height

            constructor board
            creer une cellule pour chaque location (en comptant width height)
            random des keys et artifacts en faisant attention a ce que
            les keys et les artefacts du meme type ne soient pas sur la meme cellule
            dimensions

            getCellule(Model.Location) //return une cellule a partir d'une position
            void render() // appeller toutes les methodes render des cellules
            }
 **/