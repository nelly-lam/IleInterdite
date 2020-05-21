package Model;

public class Location {
    private int x;
    private int y;

    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Retourne l'attribut x
     */
    public int getX(){
        return this.x;
    }

    /**
     * Retourne l'attribut y
     */
    public int getY(){
        return this.y;
    }

    /**
     * Change les attributs x et y
     */
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
}
