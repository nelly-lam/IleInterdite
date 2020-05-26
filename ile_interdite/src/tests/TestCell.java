package tests;
import model.Cell;
import model.Island;
import org.junit.jupiter.api.Test;

public class TestCell {
    @Test
    void hasKeyTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island, 4, 4, false);
        assert (!c.hasKey());
        /**Cell c2 = new Cell(island, 4, 4, false, Cell.Element.NONE);
        assert (!c2.hasKey());**/
    }

    @Test
    void updateKeyTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island, 4, 4, false);
        c.updateKey();
        assert (c.getKey() == Cell.Element.NONE);

        Cell c2 = new Cell(island, 4, 4, false);
        c2.updateKey();
        assert (c.getKey() == Cell.Element.NONE);
    }

    @Test
    void hasArtifactTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island, 4, 4, false);
        assert (!c.hasArtifact());
        Cell c2 = new Cell(island,4, 4, false);
        assert (!c2.hasArtifact());
    }

    @Test
    void updateArtifactTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island, 4, 4, false);
        c.updateArtifact();
        assert (c.getArtifact() == Cell.Element.NONE);

        Cell c2 = new Cell(island, 4, 4, false);
        c2.updateArtifact();
        assert (c2.getArtifact() == Cell.Element.NONE);
    }


    @Test
    void isHeliportTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island, 4, 4, true);
        assert (c.isHeliport());
        Cell c2 = new Cell(island, 4, 4, false);
        assert (!c2.isHeliport());
    }

    @Test
    void floodTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island, 4, 4, false);
        assert (c.getState() == Cell.State.NORMAL);
        c.flood();
        assert (c.getState() == Cell.State.FLOODED);
        c.flood();
        assert (c.getState() == Cell.State.SUBMERGED);
        c.flood();
        assert (c.getState() == Cell.State.SUBMERGED);
    }

    @Test
    void isSubmergedTest() {

        Island island = new Island(10, 10);
        Cell c = new Cell(island, 4, 4, false);
        Cell c2 = new Cell(island, 4, 4, false);
        c.flood();
        c.flood();
        assert(c.isSubmerged());
        assert(!c2.isSubmerged());
    }

    @Test
    void isFloodedTest(){
        Island island = new Island(10, 10);
        Cell c = new Cell(island, 4, 4, false);
        Cell c2 = new Cell(island, 4, 4, false);
        c.flood();
        assert(c.isFlooded());
        assert(!c2.isFlooded());
    }

    @Test
    void dewateringCellTest(){
        Island island = new Island(10, 10);
        Cell c = new Cell(island, 4, 4, false);
        Cell c2 = new Cell(island, 4, 4, false);
        c.flood();
        c2.flood();
        c2.flood();
        c.dryCell();
        assert(c.getState() == Cell.State.NORMAL);
        assert(c2.getState() != Cell.State.NORMAL);
    }
}
