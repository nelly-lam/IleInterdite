package Model;

public class Player {
    protected static int idPlayer = 0;
    private Coord location;
    private Cell.Element key[];
    private Cell.Element artifact[];
    private boolean isDead;

    public Player(Coord l){
        this.location = l;
        this.isDead = false;
    }

    public void initPlayer(){
    }

    public int getIdJ() {
        return this.idPlayer;
    }

}
