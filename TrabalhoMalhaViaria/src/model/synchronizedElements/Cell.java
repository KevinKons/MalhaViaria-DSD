package model.synchronizedElements;

import model.CellInterface;
import model.Coordinate;
import model.Vehicle;

/**
 *
 * @author Avell
 */
public class Cell extends CellInterface {

    private CellInterface next;

    public Cell(Coordinate coordinate) {
        super.setCoordinate(coordinate);
    }

    @Override
    public void advanceVehicle(Vehicle vehicle) {
        if(this.next.isNotBusy()) {
            vehicle.setCell(this.next);
            this.next.setBusy(true);
            super.setBusy(false);
        }
    }

    @Override
    public void addNext(CellInterface cell) {
        this.next = cell;
    }

    @Override
    public CellInterface next() {
        return next;
    }
    
    @Override
    public boolean equals(Object obj) {
        Cell outro = (Cell) obj;
        if (this.next != null && outro.next() != null) {
            return super.getCoordinate().equals(outro.getCoordinate())
                    && this.next.getCoordinate().equals(outro.next().getCoordinate());
        } else {
            return super.getCoordinate().equals(outro.getCoordinate());
        }

    }

    @Override
    public boolean isFinalCell() {
        return false;
    }



}   
