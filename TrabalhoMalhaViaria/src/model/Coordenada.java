package model;

public class Coordenada {

    private int x;
    private int y;

    public Coordenada(int x, int y) {
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
        Coordenada outro = (Coordenada) obj;
        if(this.x == outro.getX() && this.y == outro.getY()) 
            return true;
        
        return false;
    }
}
