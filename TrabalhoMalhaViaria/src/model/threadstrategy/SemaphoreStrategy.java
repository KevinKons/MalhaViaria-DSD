package model.threadstrategy;

import model.AbstractCell;
import model.Vehicle;

import java.util.concurrent.Semaphore;

public class SemaphoreStrategy implements Strategy {

    private Semaphore mutex = new Semaphore(1);

    public void advanceVehicle(Vehicle vehicle, AbstractCell next, AbstractCell currentCell) {
        try {
            mutex.acquire();
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            vehicle.setCell(next);
            next.setBusy(true);
            currentCell.setBusy(false);
            mutex.release();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
