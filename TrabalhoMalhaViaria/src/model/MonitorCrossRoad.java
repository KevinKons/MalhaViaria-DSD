package model;

public class MonitorCrossRoad extends CrossRoad {

    public MonitorCrossRoad(Coordinate coordinate) {
        super(coordinate);
    }

    @Override
    public synchronized void advanceVehicle(Vehicle vehicle) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        vehicle.setCell(next);
        next.setBusy(true, vehicle.getColor());
        this.setBusy(false, vehicle.getColor());
    }
}
