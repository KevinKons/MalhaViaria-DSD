package model;

import java.util.Random;

public class Vehicle implements Runnable {

    private AbstractCell cell;

    @Override
    public void run() {
        RoadMesh roadMesh = RoadMesh.getInstance();
        Random random = new Random();
        while(!cell.isExitCell()) {
            cell.advanceVehicle(this);
            try {
                Thread.sleep(random.nextInt(200 + 400));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        cell.setBusy(false);
        roadMesh.removeVehicle(this);
    }

    public AbstractCell getCell() {
        return cell;
    }

    public void setCell(AbstractCell cell) {
        this.cell = cell;
    }
        
}
