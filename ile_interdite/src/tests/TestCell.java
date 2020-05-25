package tests;
import model.Cell;
import model.Island;
import org.junit.jupiter.api.Test;

public class TestCell {
    @Test
    void hasKeyTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island, 4, 4, false, Cell.Element.AIR, Cell.Element.NONE);
        assert (c.hasKey());
        Cell c2 = new Cell(island, 4, 4, false, Cell.Element.NONE, Cell.Element.NONE);
        assert (!c2.hasKey());
    }

    @Test
    void updateKeyTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island, 4, 4, false, Cell.Element.NONE, Cell.Element.AIR);
        c.updateKey();
        assert (c.getKey() == Cell.Element.NONE);

        Cell c2 = new Cell(island, 4, 4, false, Cell.Element.NONE, Cell.Element.NONE);
        c2.updateKey();
        assert (c.getKey() == Cell.Element.NONE);
    }

    @Test
    void hasArtifactTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island, 4, 4, false, Cell.Element.NONE, Cell.Element.AIR);
        assert (c.hasArtifact());
        Cell c2 = new Cell(island,4, 4, false, Cell.Element.NONE, Cell.Element.NONE);
        assert (!c2.hasArtifact());
    }

    @Test
    void updateArtifactTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island, 4, 4, false, Cell.Element.NONE, Cell.Element.AIR);
        c.updateArtifact();
        assert (c.getArtifact() == Cell.Element.NONE);

        Cell c2 = new Cell(island, 4, 4, false, Cell.Element.NONE, Cell.Element.NONE);
        c2.updateArtifact();
        assert (c2.getArtifact() == Cell.Element.NONE);
    }


    @Test
    void isHelicopterTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island, 4, 4, true, Cell.Element.NONE, Cell.Element.NONE);
        assert (c.isHelicopter());
        Cell c2 = new Cell(island, 4, 4, false, Cell.Element.NONE, Cell.Element.NONE);
        assert (!c2.isHelicopter());
    }

    @Test
    void floodTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island, 4, 4, false, Cell.Element.NONE, Cell.Element.NONE);
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
        Cell c = new Cell(island, 4, 4, false, Cell.Element.NONE, Cell.Element.NONE);
        Cell c2 = new Cell(island, 4, 4, false, Cell.Element.NONE, Cell.Element.NONE);
        c.flood();
        c.flood();
        assert(c.isSubmerged());
        assert(!c2.isSubmerged());
    }

    @Test
    void isFloodedTest(){
        Island island = new Island(10, 10);
        Cell c = new Cell(island, 4, 4, false, Cell.Element.NONE, Cell.Element.NONE);
        Cell c2 = new Cell(island, 4, 4, false, Cell.Element.NONE, Cell.Element.NONE);
        c.flood();
        assert(c.isFlooded());
        assert(!c2.isFlooded());
    }

    @Test
    void dewateringCellTest(){
        Island island = new Island(10, 10);
        Cell c = new Cell(island, 4, 4, false, Cell.Element.NONE, Cell.Element.NONE);
        Cell c2 = new Cell(island, 4, 4, false, Cell.Element.NONE, Cell.Element.NONE);
        c.flood();
        c2.flood();
        c2.flood();
        c.dewateringCell();
        assert(c.getState() == Cell.State.NORMAL);
        assert(c2.getState() != Cell.State.NORMAL);
    }
}
