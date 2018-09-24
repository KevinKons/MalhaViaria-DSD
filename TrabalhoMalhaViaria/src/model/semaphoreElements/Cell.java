package model.semaphoreElements;

import model.CellInterface;
import model.Coordinate;
import model.Vehicle;

/**
 *
 * @author Avell
 */
public class Cell extends CellInterface {

    protected CellInterface next;

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
            if (super.getCoordinate().equals(outro.getCoordinate())
                    && this.next.getCoordinate().equals(outro.next().getCoordinate())) {
                return true;
            }
        } else {
            if (super.getCoordinate().equals(outro.getCoordinate())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isFinalCell() {
        return false;
    }



}   
