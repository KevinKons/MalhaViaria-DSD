package model.threadstrategy;

import model.AbstractCell;
import model.Vehicle;

public interface Strategy {

    public void advanceVehicle(Vehicle vehicle, AbstractCell next, AbstractCell currentCell);

}
