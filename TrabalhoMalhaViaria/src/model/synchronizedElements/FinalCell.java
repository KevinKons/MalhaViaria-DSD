package model.synchronizedElements;

import model.semaphoreElements.*;
import model.CellInterface;
import model.Coordinate;
import model.Vehicle;

/**
 *
 * @author Avell
 */
public class FinalCell extends CellInterface {

    private CellInterface next;

    public FinalCell(Coordinate coordinate) {
        super.setCoordinate(coordinate);
    }

    @Override
    public void advanceVehicle(Vehicle vehicle) {
        if (!next.isBusy()) { //Se cruzamento está vazio
            if (!next.next().isBusy()) { //se campo após o cruzamento está vazio
                vehicle.setCell(this.next);
                this.next.setBusy(true);
                super.setBusy(false);
                
                this.next.advanceVehicle(vehicle);
            }
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
        if (super.getCoordinate().equals(outro.getCoordinate())) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isFinalCell() {
        return true;
    }
    
    

}
