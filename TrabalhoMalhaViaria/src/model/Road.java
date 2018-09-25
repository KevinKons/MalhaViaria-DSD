package model;

public class Road {
 
    private AbstractCell[] cells;
    private int size;
    private GeographicalOrientation orientacaoGeografica;

    public GeographicalOrientation getGeographicalOrientation() {
        return orientacaoGeografica;
    }

    public void setGeographicalOrientation(GeographicalOrientation orientacaoGeofrafica) {
        this.orientacaoGeografica = orientacaoGeofrafica;
    }
    
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        this.cells = new AbstractCell[size];
    }

    public AbstractCell[] getCells() {
        return cells;
    }
}
