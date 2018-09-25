package model;

public class Vehicle implements Runnable {

    private int speed;
    private CellInterface cell;

    public Vehicle(int velocity) {
        this.speed = velocity;
    }
    
    @Override
    public void run() {
        RoadMesh roadMesh = RoadMesh.getInstance();
        while(!cell.isExitCell()) {
            cell.advanceVehicle(this);
            try {
                Thread.sleep(speed);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        cell.setBusy(false);
        roadMesh.vehicleLogOutMesh();
    }

    public CellInterface getCell() {
        return cell;
    }

    public void setCell(CellInterface cell) {
        this.cell = cell;
    }
        
}
