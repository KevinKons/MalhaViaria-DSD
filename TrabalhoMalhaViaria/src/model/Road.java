package model;

public class Road {
 
    private CellInterface[] cells;
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
        this.cells = new CellInterface[size];
    }

    public CellInterface[] getCells() {
        return cells;
    }
}
