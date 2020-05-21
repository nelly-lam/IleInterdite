package Model;

import java.util.HashMap;
import java.util.Observable;

public class Island extends Observable {
    public HashMap<Location, Cell> board;
    public int width;
    public int height;

    public Island(int w, int h){
        this.width = w;
        this.height = h;
        this.board = new HashMap<Location, Cell>();

        //helicoptere 1chance sur width*height
//      //keys on va en avoir 4
        //artifact on va en avoir 4

        //nous remplissons la hashmap avec toutes les cellules du plateau
        for(int i = 0; i < this.width; i++){
            for(int j = 0; j < this.height; j++){
                Location l = new Location(i,j);
                Cell c =
                board.put(l, Cell);
            }
            //creer une cell pour chaque i
            //donner une Model.Location qui augmente en x et y en fonction de width et height
            //random les keys et artifacts
            //Pas key et artifact du meme element sur la meme case
        }
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