package model;

public class Coordinate {

    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + " , " + y;
    }
    
    @Override
    public boolean equals(Object obj) {
        Coordinate outro = (Coordinate) obj;
        if(this.x == outro.getX() && this.y == outro.getY()) 
            return true;
        
        return false;
    }
}
