package model.threadstrategy;

import model.AbstractCell;
import model.Vehicle;

public class MonitorStrategy implements Strategy {

    public synchronized void advanceVehicle(Vehicle vehicle, AbstractCell next, AbstractCell currentCell) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        vehicle.setCell(next);
        next.setBusy(true);
        currentCell.setBusy(false);
    }
}
