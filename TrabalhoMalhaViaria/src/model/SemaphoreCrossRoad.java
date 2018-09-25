package model;

import java.util.concurrent.Semaphore;

public class SemaphoreCrossRoad extends CrossRoad {

    private Semaphore mutex = new Semaphore(1);

    public SemaphoreCrossRoad(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public void advanceVehicle(Vehicle vehicle) {
        try {
            mutex.acquire();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            vehicle.setCell(next);
            next.setBusy(true);
            this.setBusy(false);
            mutex.release();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
