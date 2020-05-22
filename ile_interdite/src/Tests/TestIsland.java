package Tests;

import Model.Cell;
import Model.Island;
import org.junit.jupiter.api.Test;

public class TestIsland {

    @Test
    void getCellTest() {
        Island island = new Island(20, 20);
        Cell c = new Cell(false, Cell.Element.Air, Cell.Element.None);
        island.board[0][0] = c;
        assert (island.getCell(0, 0) == c);
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

}
