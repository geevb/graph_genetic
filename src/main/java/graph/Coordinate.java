
package graph;

public class Coordinate {
    
    protected int posX;
    protected int posY;
    protected String name;
    
    public Coordinate(int x, int y, String name) {
        this.posX = x;
        this.posY = y;
        this.name = name;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public String getName() {
        return name;
    }
 
}
