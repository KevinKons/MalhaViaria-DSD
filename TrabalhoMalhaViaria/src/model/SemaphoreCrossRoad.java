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
            System.out.println("passei pelo cruzamento " + super.getCoordinate().toString());
            vehicle.setCell(next);
            next.setBusy(true, vehicle.getColor());
            this.setBusy(false, vehicle.getColor());
            mutex.release();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
