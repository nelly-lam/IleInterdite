package Tests;

import Model.Cell;
import Model.Island;
import org.junit.jupiter.api.Test;

public class TestIsland {

    @Test
    void getCellTest() {
        Island island = new Island(20, 20);
        Cell c = new Cell(island,false, Cell.Element.Air, Cell.Element.None);
        island.board[0][0] = c;
        assert (island.getCell(0, 0) == c);
    }

    @Test
    void addPlayerTest(){
        Island island = new Island(5,5);
        island.addPlayer("toto");
        assert(island.players.size() == 1);
        assert(island.playerCourant.getName() == "toto");

        Island island2 = new Island(5,5);
        island2.addPlayer("titi");
        island2.addPlayer("tata");
        island2.addPlayer("tutu");
        //assert(island2.players.size() == 3);
        //assert(island2.players.get(2).getName() == "tutu");
        //assert(island2.playerCourant.getName() == "titi");
    }

    @Test
    void risingWaterTest() {
        Island board = new Island(10, 10);
        board.risingWater();
        int compteur = 0;
        for (int i = 0; i < board.width; i++) {
            for (int j = 0; j < board.height; j++) {
                if (board.getCell(i, j).state == Cell.State.Flooded) {
                    compteur++;
                }
            }
        }
        assert (compteur == 3);
    }

    @Test
    void dewateringTest(){
        Island island = new Island(5,5);
        island.getCell(1,2).state = Cell.State.Flooded;
        island.dewatering(1, 2);
        assert(island.getCell(1,2).state == Cell.State.Normal);
        //island.getCell(2,2).state = Cell.State.Submerged;
        //island.dewatering(2, 2);
        //assert(island.getCell(2,2).state != Cell.State.Normal);
    }

    @Test
    void playTest(){
        //TODO
    }

    @Test
    void movePlayerTest(){
        //TODO
    }

}
