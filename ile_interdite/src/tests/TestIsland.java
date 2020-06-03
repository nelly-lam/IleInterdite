package tests;

import exceptions.ExceptionNbEvents;
import model.Cell;
import model.Island;
import model.Player;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

public class TestIsland {
    @Test
    void addPlayer() {
        Island i = new Island(10, 10);
        assert (i.getPlayers().isEmpty());
        i.addPlayer("test");
        assert (i.getCurrentPlayer().getName().equals("test"));
        assert (i.getPlayers().size() == 1);
        i.addPlayer("test2");
        assert (i.getPlayers().get(1).getName().equals("test2"));
        assert (i.getPlayers().size() == 2);
    }

    @Test
    void risingWater() {
        Island island = new Island(5, 5);
        island.addPlayer("test");
        island.risingWater();
        int compteur = 0;
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                if (island.getCell(i, j).getState() == Cell.State.FLOODED) {
                    compteur++;
                }
            }
        }
        assert (compteur == 3);
    }

    @Test
    void movePlayer() {
        Island island = new Island(5, 5);
        island.addPlayer("tutu");
        island.movePlayer(Player.Direction.UP);
        assert (island.getCurrentPlayer().getAbs() == island.getHeliport().getAbs());
        assert (island.getCurrentPlayer().getOrd() == island.getHeliport().getOrd() - 1);
        island.movePlayer(Player.Direction.RIGHT);
        assert (island.getCurrentPlayer().getAbs() == island.getHeliport().getAbs() + 1);
        assert (island.getCurrentPlayer().getOrd() == island.getHeliport().getOrd() - 1);
        island.movePlayer(Player.Direction.DOWN);
        assert (island.getCurrentPlayer().getAbs() == island.getHeliport().getAbs() + 1);
        assert (island.getCurrentPlayer().getOrd() == island.getHeliport().getOrd());
        island.getCurrentPlayer().restoreNbEvents();
        island.movePlayer(Player.Direction.LEFT);
        assert (island.getCurrentPlayer().getAbs() == island.getHeliport().getAbs());
        assert (island.getCurrentPlayer().getOrd() == island.getHeliport().getOrd());
    }

    @Test
    void sandBag() {
        Island island = new Island(5, 5);
        island.addPlayer("toto");
        island.getCurrentPlayer().addActions(Player.SpecialAction.SAND);
        island.risingWater();
        ArrayList<Cell> cellsFlooded = new ArrayList<>();
        for (int i = 0; i < island.getWidth(); i++) {
            for (int j = 0; j < island.getHeight(); j++) {
                if (island.getCell(i, j).getState() == Cell.State.FLOODED) {
                    cellsFlooded.add(island.getCell(i, j));
                }
            }
        }
        island.sandBag(cellsFlooded.get(0).getAbs(), cellsFlooded.get(0).getOrd());
        assert (cellsFlooded.get(0).getState() == Cell.State.NORMAL);
        assert (!island.getCurrentPlayer().getSpecialEvent());
    }


    @Test
    void teleportation() {
        Island island = new Island(5, 5);
        island.addPlayer("tata");
        island.getCurrentPlayer().addActions(Player.SpecialAction.TELEPORTATION);
        island.teleportation(2, 2, true);
        assert (island.getCurrentPlayer().getAbs() == 2);
        assert (island.getCurrentPlayer().getOrd() == 2);
        assert (!island.getCurrentPlayer().getSpecialEvent());

        Island island2 = new Island(5, 5);
        island2.addPlayer("toto");
        island2.addPlayer("tutu");
        assert(island2.getCurrentPlayer().isOnSameCell(island2.getPlayers().get(1)));
        island2.getCurrentPlayer().addActions(Player.SpecialAction.TELEPORTATION);
        island2.teleportation(1, 2, false);
        assert (island2.getCurrentPlayer().getAbs() == 1);
        assert (island2.getCurrentPlayer().getOrd() == 2);
        assert (island2.getPlayers().size() == 2);
        System.out.println(island2.getPlayers().get(1).getAbs());
        System.out.println(island2.getHeliport().getAbs());
        assert (island2.getPlayers().get(1).getAbs() == island2.getHeliport().getAbs());
        assert (island2.getPlayers().get(1).getOrd() == island2.getHeliport().getOrd());
    }


    @Test
    void giveKey() {
        Island i = new Island(10, 10);
        i.addPlayer("test");
        i.addPlayer("test2");
        i.getPlayers().get(0).addKey(Cell.Element.AIR);
        i.giveKey(i.getPlayers().get(1), Cell.Element.AIR);

        assert (i.getPlayers().get(1).hasKey(Cell.Element.AIR));
        assert (!i.getPlayers().get(0).hasKey((Cell.Element.AIR)));
        assert (i.getPlayers().get(0).getNbEvents() == 2);

        try {
            i.getPlayers().get(0).addEvents();
            i.getPlayers().get(0).addEvents();
            i.giveKey(i.getPlayers().get(1), Cell.Element.AIR);
        } catch (ExceptionNbEvents exceptionNbEvents) {
            fail("Vous n'avez pas assez d'actions pour échanger les clés'");
        }
    }

    @Test
    void dry() {
        Island i = new Island(10, 10);
        i.addPlayer("test");
        i.getCell(0, 0).flood();
        assert (i.getCell(0, 0).isFlooded());
        i.dry(0, 0);
        assert (!i.getCell(0, 0).isFlooded());
    }

    @Test
    void searchKey() {
        Island island = new Island(5, 5);
        island.addPlayer("tata");
        Player p = island.getPlayers().get(0);

        //teste le cas ou le joueur n'as plus de coups
        try{
            p.addEvents();
            p.addEvents();
            p.addEvents();
            island.searchKey();
        } catch(ExceptionNbEvents exceptionNbEvents) {
            fail("Vous n'avez pas assez d'actions pour chercher une clé");
        }

        p.restoreNbEvents();

        //teste le cas ou la case possede une cle
        Cell c = island.getCell(p.getAbs(), p.getOrd());
        c.setKey(Cell.Element.AIR);
        island.searchKey();
        assert(p.hasKey(Cell.Element.AIR));
        assert(!c.hasKey());
    }

    @Test
    void recoverArtifact() {
        Island island = new Island(5, 5);
        island.addPlayer("tata");
        assert(island.getArtifacts().size() == 4);
        island.getCurrentPlayer().teleportPlayer(island.getArtifacts().get(0).getAbs(),
                                                island.getArtifacts().get(0).getOrd());

        for (int i = 0; i < 5; i++) {
            island.getCurrentPlayer().addKey(island.getArtifacts().get(0).getArtifact());
        }
        island.recoverArtifact();
        assert(island.getArtifacts().size() == 3);
    }

    @Test
    void removePlayer(){
        Island island = new Island(5, 5);
        island.addPlayer("taratata");
        island.addPlayer("trotro");
        island.addPlayer("trotinette");
        island.removePlayer(island.getPlayers().get(1));
        assert(island.getPlayers().size() == 2);
        assert(island.getPlayers().get(0).getNext().getName().equals("trotinette"));
    }

}