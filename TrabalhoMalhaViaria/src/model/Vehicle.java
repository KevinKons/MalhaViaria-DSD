package model;

import java.util.Random;

public class Vehicle implements Runnable {

    private AbstractCell cell;
    private Colors color;


    public Vehicle() {
        color = Colors.values()[new Random().nextInt(Colors.values().length)];
    }

    @Override
    public void run() {
        RoadMesh roadMesh = RoadMesh.getInstance();
        Random random = new Random();
        while(!cell.isExitCell()) {
            cell.advanceVehicle(this);
            System.out.println("Passei por campo " + cell.getCoordinate().toString());
            try {
                Thread.sleep(random.nextInt(200) + 500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        cell.setBusy(false, this.color);
        roadMesh.vehicleLogOutMesh();
    }

    public AbstractCell getCell() {
        return cell;
    }

    public void setCell(AbstractCell cell) {
        this.cell = cell;
    }

    public Colors getColor() {
        return color;
    }
}
