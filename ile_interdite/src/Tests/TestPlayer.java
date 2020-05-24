package Tests;

import Model.Cell;
import Model.Island;
import Model.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestPlayer {

    @Test
    void getNameTest() {
        Island island = new Island(20,20);
        Player p = new Player(island, "Joueur", 10, 10);
        assert (p.getName().equals("Joueur"));
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

        assert(up1 == a1.get(0));
        /**assert(down1 == a1.get(1));
        assert(right1 == a1.get(2));
        assert(left1 == a1.get(3));

        Player p2 = new Player(island, "Joueur2", 0, 0);
        ArrayList<Cell> a2 = p.nearbyCells();
        assert (a2.size() == 2);
        Cell down2 = new Cell(island, 0, 1);
        Cell right2 = new Cell(island, 1, 0);
        assert(down2 == a2.get(0));
        assert(right2 == a2.get(1));

        Player p3 = new Player(island, "Joueur3", 0, 5);
        ArrayList<Cell> a3 = p.nearbyCells();
        assert(a3.size() == 2);
        Cell up3 = new Cell(island, 0, 4);
        Cell right3= new Cell(island, 1, 5);
        assert(up3 == a2.get(0));
        assert(right3 == a2.get(1));

        Player p4 = new Player(island, "Joueur4", 5, 0);
        ArrayList<Cell> a4 = p.nearbyCells();
        assert(a4.size() == 2);
        Cell down4 = new Cell(island, 5, 1);
        Cell left4 = new Cell(island, 4, 0);
        assert(down4 == a4.get(0));
        assert(left4 == a4.get(1));

        Player p5 = new Player(island, "Joueur5", 5, 5);
        ArrayList<Cell> a5 = p.nearbyCells();
        assert(a5.size() == 2);
        Cell up5 = new Cell(island, 5, 4);
        Cell left5 = new Cell(island, 4, 5);
        assert(up5 == a5.get(0));
        assert(left5 == a5.get(1));**/

    }

    @Test
    void dieTest() {
        Island island = new Island(20,20);
        Player p = new Player(island, "Joueur", 1, 2);
        island.board[p.getAbs()][p.getOrd()].state = Cell.State.Submerged;
        p.die();
        assert (p.getIsDead());
    }
}
