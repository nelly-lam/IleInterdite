package Tests;
import Model.Cell;
import Model.Island;
import org.junit.jupiter.api.Test;

public class TestCell {
    @Test
    void hasKeyTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island,false, Cell.Element.Air, Cell.Element.None);
        assert (c.hasKey());
        Cell c2 = new Cell(island,false, Cell.Element.None, Cell.Element.None);
        assert (!c2.hasKey());
    }

    @Test
    void updateKeyTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island,false, Cell.Element.None, Cell.Element.Air);
        c.updateKey();
        assert (c.getKey() == Cell.Element.None);

        Cell c2 = new Cell(island,false, Cell.Element.None, Cell.Element.None);
        c2.updateKey();
        assert (c.getKey() == Cell.Element.None);
    }

    @Test
    void hasArtifactTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island,false, Cell.Element.None, Cell.Element.Air);
        assert (c.hasArtifact());
        Cell c2 = new Cell(island,false, Cell.Element.None, Cell.Element.None);
        assert (!c2.hasArtifact());
    }

    @Test
    void updateArtifactTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island,false, Cell.Element.None, Cell.Element.Air);
        c.updateArtifact();
        assert (c.getArtifact() == Cell.Element.None);

        Cell c2 = new Cell(island,false, Cell.Element.None, Cell.Element.None);
        c2.updateArtifact();
        assert (c2.getArtifact() == Cell.Element.None);
    }


    @Test
    void isHelicopterTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island,true, Cell.Element.None, Cell.Element.None);
        assert (c.isHelicopter());
        Cell c2 = new Cell(island,false, Cell.Element.None, Cell.Element.None);
        assert (!c2.isHelicopter());
    }

    @Test
    void floodTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island,false, Cell.Element.None, Cell.Element.None);
        assert (c.state == Cell.State.Normal);
        c.flood();
        assert (c.state == Cell.State.Flooded);
        c.flood();
        assert (c.state == Cell.State.Submerged);
        c.flood();
        assert (c.state == Cell.State.Submerged);
    }

    @Test
    void isSubmergedTest() {
        Island island = new Island(10, 10);
        Cell c = new Cell(island,false, Cell.Element.None, Cell.Element.None);
        Cell c2 = new Cell(island,false, Cell.Element.None, Cell.Element.None);
        c.flood();
        c.flood();
        assert(c.isSubmerged());
        assert(!c2.isSubmerged());
    }

    @Test
    void isFloodedTest(){
        Island island = new Island(10, 10);
        Cell c = new Cell(island,false, Cell.Element.None, Cell.Element.None);
        Cell c2 = new Cell(island,false, Cell.Element.None, Cell.Element.None);
        c.flood();
        assert(c.isFlooded());
        assert(!c2.isFlooded());
    }

    @Test
    void dewateringCellTest(){
        Island island = new Island(10, 10);
        Cell c = new Cell(island,false, Cell.Element.None, Cell.Element.None);
        Cell c2 = new Cell(island,false, Cell.Element.None, Cell.Element.None);
        c.flood();
        c2.flood();
        c2.flood();
        c.dewateringCell();
        assert(c.state == Cell.State.Normal);
        assert(c2.state != Cell.State.Normal);
    }
}
