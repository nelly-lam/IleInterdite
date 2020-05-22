package Tests;

import Model.Cell;
import Model.Island;
import Model.Player;
import org.junit.jupiter.api.Test;

public class TestPlayer {

    @Test
    void getNameTest() {
        Island island = new Island(20,20);
        Player p = new Player(island, "Joueur", 10, 10);
        assert (p.getName() == "Joueur");
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
        //TODO add getKey
        for(int i = 0; i < p. )
    }

    @Test
    void addArtifactTest() {
        Island island = new Island(20,20);
        Player p = new Player(island, "Joueur", 1, 2);
        p.addArtifact(Cell.Element.Fire);
        //TODO add getArtifact
        for(int i = 0; i < p.)
    }

    @Test
    void nearbyCellsTest() {

    }

    @Test
    void die() {
        Island island = new Island(20,20);
        Player p = new Player(island, "Joueur", 1, 2);
        p.die();
        //TODO add getIsDead
        assert (p.isDead);

    }
}
