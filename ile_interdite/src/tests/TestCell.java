package tests;
import model.Cell;
import model.Island;
import org.junit.jupiter.api.Test;

public class TestCell {
    /**@Test
    void hasKeyTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island, 4, 4, false);
        assert (!c.hasKey());
        Cell c2 = new Cell(island, 4, 4, false, Cell.Element.NONE);
        assert (!c2.hasKey());
    }**/

    /**@Test
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
    }**/



    @Test
    void setKey() {
        Cell c = new Cell(0, 0, false);
        c.setKey(Cell.Element.AIR);
        assert(c.getKey() == Cell.Element.AIR);
        c.setKey(Cell.Element.NONE);
        assert(c.getKey() == Cell.Element.NONE);
    }

    @Test
    void setArtifact() {
        Cell c = new Cell(0, 0, false);
        c.setArtifact(Cell.Element.AIR);
        assert(c.getArtifact() == Cell.Element.AIR);
        c.setArtifact(Cell.Element.NONE);
        assert(c.getArtifact() == Cell.Element.NONE);
    }

    @Test
    void hasKey() {
        Cell c = new Cell(1, 1, false);
        assert(!c.hasKey());
        Cell c1 = new Cell(1, 1, false);
        c1.setKey(Cell.Element.AIR);
        assert(c1.hasKey());
    }

    @Test
    void hasArtifact() {
        Cell c = new Cell(1, 1, false);
        assert(!c.hasArtifact());
        Cell c1 = new Cell(1, 1, false);
        c1.setArtifact(Cell.Element.AIR);
        assert(!c1.hasKey());
    }

    @Test
    void isHeliport() {
        Cell c = new Cell(0, 0, false);
        assert(!c.isHeliport());
        Cell c2 = new Cell(0, 0, true);
        assert(c2.isHeliport());
    }

    @Test
    void flood() {
        Cell c = new Cell(0, 0, false);
        assert(!c.isFlooded());
        Cell c1 = new Cell(0, 0, false);
        c1.flood();
        assert(c1.isFlooded());
        Cell c3 = new Cell(0, 0, false);
        c3.flood();
        c3.flood();
        assert(!c3.isFlooded());
    }

    @Test
    void isSubmerged() {
        Cell c = new Cell(0, 0, false);
        assert(!c.isSubmerged());
        Cell c1 = new Cell(0, 0, false);
        c1.flood();
        assert(!c1.isSubmerged());
        Cell c3 = new Cell(0, 0, false);
        c3.flood();
        c3.flood();
        assert(c3.isSubmerged());
    }

    @Test
    void isFlooded() {
        
    }

    @Test
    void updateKey() {
        Cell c = new Cell(0, 0, false);
        c.setKey(Cell.Element.EARTH);
        c.updateKey();
        assert(!c.hasKey());
    }

    @Test
    void updateArtifact() {
        Cell c = new Cell(0, 0, false);
        c.setArtifact(Cell.Element.EARTH);
        c.updateArtifact();
        assert(!c.hasArtifact());
    }

    @Test
    void dryCell() {
        Cell c = new Cell(4, 4, false);
        Cell c2 = new Cell(4, 4, false);
        c.flood();
        c2.flood();
        c2.flood();
        c.dryCell();
        assert(c.getState() == Cell.State.NORMAL);
        assert(c2.getState() != Cell.State.NORMAL);
    }
}
