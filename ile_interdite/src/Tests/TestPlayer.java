package Tests;

import Model.Cell;
import Model.Island;
import Model.Player;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class TestPlayer {

    @Test
    void getNameTest() {
        Island island = new Island(20,20);
        Player p = new Player(island, "Joueur", 10, 10);
        assert (p.getName().equals("Joueur"));
    }

    @Test
    void getNextTest(){
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 4, 3);
        Player p2 = new Player(island, "tutu", p, 4, 3);
        Player p3 = new Player(island, "tata", p2, 4, 3);
        assert(p.getNext().getName() == "titi");
        assert(p2.getNext().getName() == "titi");
        assert(p3.getNext().getName() == "tutu");
    }

    @Test
    void getNbHits(){
        //TODO
    }

    @Test
    void getAbsTest() {
        Island island = new Island(20,20);
        Player p = new Player(island, "Joueur", 1, 2);
        assert (p.getAbs() == 1);
    }

    @Test
    void getOrdTest() {
        Island island = new Island(20,20);
        Player p = new Player(island, "Joueur", 1, 2);
        assert (p.getOrd() == 2);
    }

    @Test
    void getIsDeadTest(){
        Island island = new Island(5,5);
        Player p = new Player(island, "Joueur", 4, 3);
        assert(p.getIsDead() == false);
    }

    @Test
    void getKeyArrayTest(){
        Island island = new Island(5,5);
        Player p = new Player(island, "Joueur", 4, 3);
        p.addKey(Cell.Element.Earth);
        p.addKey(Cell.Element.Water);
        assert(p.getKeyArray().size() == 2);
        assert(p.getKeyArray().get(0) == Cell.Element.Earth);
        assert(p.getKeyArray().get(1) == Cell.Element.Water);
    }

    @Test
    void getArtifactArray(){
        Island island = new Island(5,5);
        Player p = new Player(island, "Joueur", 4, 3);
        p.addArtifact(Cell.Element.Water);
        p.addArtifact(Cell.Element.Fire);
        p.addArtifact(Cell.Element.Fire);
        assert(p.getArtifactArray().size() == 3);
        assert(p.getArtifactArray().get(0) == Cell.Element.Water);
        assert(p.getArtifactArray().get(1) == Cell.Element.Fire);
        assert(p.getArtifactArray().get(2) == Cell.Element.Fire);
    }

    @Test
    void setNextTest(){
        Island island = new Island(5,5);
        Player p = new Player(island, "Joueur", 4, 3);
        Player p2 = new Player(island, "Joueur2", 3, 4);
        p.setNext(p2);
        p2.setNext(p);
        assert(p.getNext().getName() == "Joueur2");
        assert(p2.getNext().getName() == "Joueur");
    }

    @Test
    void addHitsTest(){
        //TODO
    }


    @Test
    void restoreNbHitsTest(){
        //TODO
    }

    @Test
    void addKeyTest() {
        Island island = new Island(20,20);
        Player p = new Player(island, "Joueur", 1, 2);
        p.addKey(Cell.Element.Fire);
        ArrayList<Cell.Element> e = p.getKeyArray();
        boolean hasFireElement = false;
        for(int i = 0; i < e.size() ; i++){
            if(e.get(i) == Cell.Element.Fire) {
                hasFireElement = true;
            }
        }
        assert (hasFireElement);
    }

    @Test
    void addArtifactTest() {
        Island island = new Island(20,20);
        Player p = new Player(island, "Joueur", 1, 2);
        p.addArtifact(Cell.Element.Fire);
        ArrayList<Cell.Element> e = p.getArtifactArray();
        boolean hasFireElement = false;
        for(int i = 0; i < e.size() ; i++){
            if(e.get(i) == Cell.Element.Fire) {
                hasFireElement = true;
            }
        }
        assert (hasFireElement);
    }

    @Test
    void nearbyCellsTest() {
        //up down right left

        Island island = new Island(6,6);

        Player p = new Player(island, "Joueur", 3, 3);
        ArrayList<Cell> a1 = p.nearbyCells();
        assert(a1.size() == 4);
        Cell up1 = new Cell(island, 3, 2);
        Cell down1 = new Cell(island, 3, 4);
        Cell right1 = new Cell(island, 4, 3);
        Cell left1 = new Cell(island, 2, 3);
        System.out.println(a1.get(0).toString());
        System.out.println(a1.get(1).toString());
        System.out.println(a1.get(2).toString());
        System.out.println(a1.get(3).toString());

        assertEquals(up1, a1.get(0));
        assertEquals(down1, a1.get(1));
        assertEquals(right1, a1.get(2));
        assertEquals(left1, a1.get(3));

        Player p2 = new Player(island, "Joueur2", 0, 0);
        ArrayList<Cell> a2 = p2.nearbyCells();
        assert (a2.size() == 2);
        Cell down2 = new Cell(island, 0, 1);
        Cell right2 = new Cell(island, 1, 0);
        System.out.println(a2.get(0).toString());
        System.out.println(a2.get(1).toString());
        assertEquals(down2, a2.get(0));
        assertEquals(right2, a2.get(1));

        Player p3 = new Player(island, "Joueur3", 0, 5);
        ArrayList<Cell> a3 = p3.nearbyCells();
        assert(a3.size() == 2);
        Cell up3 = new Cell(island, 0, 4);
        Cell right3= new Cell(island, 1, 5);
        System.out.println(a3.get(0).toString());
        System.out.println(a3.get(1).toString());
        assertEquals(up3, a3.get(0));
        assertEquals(right3, a3.get(1));

        Player p4 = new Player(island, "Joueur4", 5, 0);
        ArrayList<Cell> a4 = p4.nearbyCells();
        assert(a4.size() == 2);
        Cell down4 = new Cell(island, 5, 1);
        Cell left4 = new Cell(island, 4, 0);
        assertEquals(down4, a4.get(0));
        assertEquals(left4, a4.get(1));

        Player p5 = new Player(island, "Joueur5", 5, 5);
        ArrayList<Cell> a5 = p5.nearbyCells();
        assert(a5.size() == 2);
        Cell up5 = new Cell(island, 5, 4);
        Cell left5 = new Cell(island, 4, 5);
        assertEquals(up5, a5.get(0));
        assertEquals(left5, a5.get(1));

    }

    @Test
    void dieTest() {
        //Cell du player submergée
        Island island = new Island(5,5);
        Player p = new Player(island, "Joueur", 1, 2);
        island.board[p.getAbs()][p.getOrd()].state = Cell.State.Submerged;
        p.die();
        assert (p.getIsDead());

        //Cells autour du player submergées
        ArrayList<Cell> cap = p.nearbyCells();
        int compteur = 0;
        for (int i = 0; i < cap.size(); i++){
            cap.get(i).flood();
            cap.get(i).flood();
            if(cap.get(i).state == Cell.State.Submerged){
                compteur++;
            }
        }
        System.out.println(cap.get(0).state);
        System.out.println(cap.size());
        System.out.println(compteur);
        assert(compteur == 4);

        //Toutes les Cells submergées
        Island island2 = new Island(5,5);
        int compteur2 = 0;
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                island2.getCell(i,j).flood();
                island2.getCell(i,j).flood();
                if (island2.board[i][j].state == Cell.State.Submerged){
                    compteur2++;
                }
            }
        }
        System.out.println(island2.getCell(0,4).state);
        System.out.println(compteur2);
        assert(compteur2 == island2.width * island2.height);
    }
}
