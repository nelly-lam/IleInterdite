package Tests;
import Model.Cell;
import org.junit.jupiter.api.Test;

public class TestCell {
    @Test
    void hasKeyTest() {
        Cell c = new Cell(false, Cell.Element.Air, Cell.Element.None);
        assert (c.hasKey());
        Cell c2 = new Cell(false, Cell.Element.None, Cell.Element.None);
        assert (!c2.hasKey());
    }

    @Test
    void hasArtifactTest() {
        Cell c = new Cell(false, Cell.Element.None, Cell.Element.Air);
        assert (c.hasArtifact());
        Cell c2 = new Cell(false, Cell.Element.None, Cell.Element.None);
        assert (!c2.hasArtifact());
    }

    @Test
    void isHelicopterTest() {
        Cell c = new Cell(true, Cell.Element.None, Cell.Element.None);
        assert (c.isHelicopter());
        Cell c2 = new Cell(false, Cell.Element.None, Cell.Element.None);
        assert (!c2.isHelicopter());
    }

    @Test
    void updateKeyTest() {
        Cell c = new Cell(false, Cell.Element.None, Cell.Element.Air);
        c.updateKey();
        assert (c.getKey() == Cell.Element.None);

        Cell c2 = new Cell(false, Cell.Element.None, Cell.Element.None);
        c2.updateKey();
        assert (c.getKey() == Cell.Element.None);
    }

    @Test
    void updateArtifactTest() {
        Cell c = new Cell(false, Cell.Element.None, Cell.Element.Air);
        c.updateArtifact();
        assert (c.getArtifact() == Cell.Element.None);

        Cell c2 = new Cell(false, Cell.Element.None, Cell.Element.None);
        c2.updateArtifact();
        assert (c2.getArtifact() == Cell.Element.None);
    }

    @Test
    void floodTest() {
        Cell c = new Cell(false, Cell.Element.None, Cell.Element.None);
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
        Cell c = new Cell(false, Cell.Element.None, Cell.Element.None);
        Cell c2 = new Cell(false, Cell.Element.None, Cell.Element.None);
        c.flood();
        c.flood();
        assert(c.isSubmerged());
        assert(!c2.isSubmerged());
    }
}
