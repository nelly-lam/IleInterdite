package Model;

public class Player {
    protected static int idPlayer = 0;
    private Location location;
    private Cell.Element key[];
    private Cell.Element artifact[];
    private boolean isDead;

    public Player(Location l){
        this.location = l;
        this.isDead = false;
    }

    public void initPlayer(){
    }

    public int getIdJ() {
        return this.idPlayer;
    }

}
