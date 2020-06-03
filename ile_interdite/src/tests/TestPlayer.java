package tests;

import exceptions.ExceptionNbEvents;
import exceptions.ExceptionSpecialEvent;
import model.Cell;
import model.Island;
import model.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestPlayer {

    @Test
    void getName() {
        Island island = new Island(20,20);
        Player p = new Player(island, "Joueur", 10, 10);
        assert (p.getName().equals("Joueur"));
    }

    @Test
    void getNext() {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 4, 3);
        Player p2 = new Player(island, "tutu", 4, 3);
        Player p3 = new Player(island, "tata", 4, 3);
        p.setNext(p2);
        p2.setNext(p3);
        assert(p.getNext().getName().equals("tutu"));
        assert(p2.getNext().getName().equals("tata"));
    }

    @Test
    void getColor() {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 4, 3);
        assert(p.getColor() != null);
    }

    @Test
    void getAbs() {
        Island island = new Island(20,20);
        Player p = new Player(island, "Joueur", 1, 2);
        assert (p.getAbs() == 1);
    }

    @Test
    void getOrd() {
        Island island = new Island(20,20);
        Player p = new Player(island, "Joueur", 1, 2);
        assert (p.getOrd() == 2);
    }

    @Test
    void getNbEvents() {
        Island island = new Island(5,5);
        Player p = new Player(island, "Joueur", 4, 3);
        assert(p.getNbEvents() == 3);
    }

    @Test
    void setNext() {
        Island island = new Island(5,5);
        Player p = new Player(island, "Joueur", 4, 3);
        Player p2 = new Player(island, "Joueur2", 3, 4);
        p.setNext(p2);
        p2.setNext(p);
        assert(p.getNext().getName().equals("Joueur2"));
        assert(p2.getNext().getName().equals("Joueur"));
    }

    @Test
    void updateKey() {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 3, 3);
        p.addKey(Cell.Element.AIR);
        p.addKey(Cell.Element.AIR);
        p.addKey(Cell.Element.WATER);
        p.addKey(Cell.Element.AIR);
        p.updateKey(Cell.Element.AIR);
        p.updateKey(Cell.Element.WATER);
        assert(p.nbKeyElement(Cell.Element.AIR) == 2);
        assert(p.nbKeyElement(Cell.Element.WATER) == 0);
    }

    @Test
    void updateAction() {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 3, 3);
        p.addActions(Player.SpecialAction.SAND);
        p.addActions(Player.SpecialAction.SAND);
        p.addActions(Player.SpecialAction.SAND);
        p.addActions(Player.SpecialAction.TELEPORTATION);
        p.updateAction(Player.SpecialAction.SAND);
        p.updateAction(Player.SpecialAction.TELEPORTATION);
        assert(p.nbSpecialAction(Player.SpecialAction.TELEPORTATION) == 0);
        assert(p.nbSpecialAction(Player.SpecialAction.SAND) == 2);
    }

    @Test
    void hasKey() {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 3, 3);
        p.addKey(Cell.Element.AIR);
        assert(p.hasKey(Cell.Element.AIR));
        assert(!p.hasKey(Cell.Element.FIRE));
    }

    @Test
    void hasAction() {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 3, 3);
        p.addActions(Player.SpecialAction.SAND);
        assert(p.hasAction(Player.SpecialAction.SAND));
        assert(!p.hasAction(Player.SpecialAction.TELEPORTATION));
    }

    @Test
    void hasArtifact() {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 3, 3);
        p.addArtifact(Cell.Element.AIR);
        assert(p.hasArtifact(Cell.Element.AIR));
        assert(!p.hasArtifact(Cell.Element.FIRE));
    }

    @Test
    void addKey() {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 3, 3);
        p.addKey(Cell.Element.AIR);
        assert(p.hasKey(Cell.Element.AIR));
        assert(!p.hasKey(Cell.Element.FIRE));
    }

    @Test
    void addArtifact() {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 3, 3);
        p.addArtifact(Cell.Element.AIR);
        assert(p.hasArtifact(Cell.Element.AIR));
        assert(!p.hasArtifact(Cell.Element.FIRE));
    }

    @Test
    void addActions() {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 3, 3);
        p.addActions(Player.SpecialAction.SAND);
        assert(p.hasAction(Player.SpecialAction.SAND));
        assert(!p.hasAction(Player.SpecialAction.TELEPORTATION));
    }

    @Test
    void isOnSameCell() {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 3, 3);
        Player p2 = new Player(island, "titi", 3, 3);
        assert(p.isOnSameCell(p2));
    }

    @Test
    void move() {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 3, 3);
        p.move(Player.Direction.DOWN);
        assert(p.getAbs() == 3);
        assert(p.getOrd() == 4);
        p.move(Player.Direction.LEFT);
        assert(p.getAbs() == 2);
        assert(p.getOrd() == 4);
        p.move(Player.Direction.UP);
        assert(p.getAbs() == 2);
        assert(p.getOrd() == 3);
        p.restoreNbEvents();
        p.move(Player.Direction.RIGHT);
        assert(p.getAbs() == 3);
        assert(p.getOrd() == 3);
        Player p2 = new Player(island, "titi", 2, 3);
        p2.move(Player.Direction.RIGHT);
        assert(p2.getAbs() == 3);
        assert(p2.getOrd() == 3);
    }

    @Test
    void teleportPlayer() {

    }

    @Test
    void recoverArtifactPlayer() throws ExceptionNbEvents {
        Island island = new Island(5,5);
        ArrayList<Cell> arti = island.getArtifacts();

        Player p = new Player(island, "titi", arti.get(0).getAbs(), arti.get(0).getAbs());
        p.addKey(Cell.Element.WATER);
        p.addKey(Cell.Element.WATER);
        p.addKey(Cell.Element.WATER);
        p.addKey(Cell.Element.WATER);
        p.addKey(Cell.Element.AIR);
        p.addKey(Cell.Element.AIR);
        p.addKey(Cell.Element.AIR);
        p.addKey(Cell.Element.AIR);
        p.addKey(Cell.Element.FIRE);
        p.addKey(Cell.Element.FIRE);
        p.addKey(Cell.Element.FIRE);
        p.addKey(Cell.Element.FIRE);
        p.addKey(Cell.Element.EARTH);
        p.addKey(Cell.Element.EARTH);
        p.addKey(Cell.Element.EARTH);
        p.addKey(Cell.Element.EARTH);
        p.recoverArtifactPlayer(arti.get(0));
    }

    @Test
    void addEvents() throws ExceptionNbEvents {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 3, 3);
        p.addEvents();
        assert(p.getNbEvents() == 2);

    }

    @Test
    void useSpecialEvent() throws ExceptionSpecialEvent {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 3, 3);
        p.addActions(Player.SpecialAction.SAND);
        p.useSpecialEvent();
        assert(!p.getSpecialEvent());
    }

    @Test
    void restoreNbEvents() {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 3, 3);
        p.move(Player.Direction.DOWN);
        p.move(Player.Direction.LEFT);
        assert(p.getNbEvents() == 1);
        p.move(Player.Direction.UP);
        p.restoreNbEvents();
        assert(p.getNbEvents() == 3);
        p.move(Player.Direction.RIGHT);
        assert(p.getNbEvents() == 2);

    }

    @Test
    void restoreSpecialEvent() {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 3, 3);
        p.restoreSpecialEvent();
        assert(!p.getSpecialEvent());
    }

    @Test
    void isDead() {
        Island island = new Island(5,5);
        Player p2 = new Player(island, "titi", 1, 1);
        island.getCell(1,1).flood();
        island.getCell(1,1).flood();
        assert(p2.isDead());
        Player p = new Player(island, "titi", 3, 3);
        island.getCell(3,2).flood();
        island.getCell(3,4).flood();
        island.getCell(2,3).flood();
        island.getCell(4,3).flood();
        island.getCell(3,2).flood();
        island.getCell(3,4).flood();
        island.getCell(2,3).flood();
        island.getCell(4,3).flood();
        assert(p.isDead());
    }

    @Test
    void nbKeyElement() {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 3, 3);
        p.addKey(Cell.Element.AIR);
        p.addKey(Cell.Element.AIR);
        p.addKey(Cell.Element.WATER);
        p.addKey(Cell.Element.AIR);
        assert(p.nbKeyElement(Cell.Element.AIR) == 3);
        assert(p.nbKeyElement(Cell.Element.WATER) == 1);
        assert(p.nbKeyElement(Cell.Element.EARTH) == 0);
    }

    @Test
    void nbSpecialAction() {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 3, 3);
        p.addActions(Player.SpecialAction.SAND);
        assert(p.nbSpecialAction(Player.SpecialAction.SAND) == 1);
        assert(p.nbSpecialAction(Player.SpecialAction.TELEPORTATION) == 0);
    }

    @Test
    void playersOnSameCell() {
        Island island = new Island(5,5);
        Player p = new Player(island, "titi", 3, 3);
        Player p2 = new Player(island, "tata", 3, 3);
        Player p3 = new Player(island, "tutu", 3, 3);
        island.addPlayer("titi");
        island.addPlayer("tata");
        island.addPlayer("tutu");
        ArrayList<Player> players = new ArrayList<Player>();
        for (Player p4 : island.getPlayers().get(0).playersOnSameCell()) {
            players.add(p4);
        }
        assert(p.isOnSameCell(p2)); //ok
        assert(players.size() == 2);
        assert(players.contains(p2));
    }

}
