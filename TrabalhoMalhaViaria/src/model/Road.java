package model;

public class Road {
 
    private AbstractCell[] cells;
    private int size;
    private GeographicalOrientation geographicalOrientation;

    public GeographicalOrientation getGeographicalOrientation() {
        return geographicalOrientation;
    }

    public void setGeographicalOrientation(GeographicalOrientation orientacaoGeofrafica) {
        this.geographicalOrientation = orientacaoGeofrafica;
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
