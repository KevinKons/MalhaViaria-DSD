package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Avell
 */
public class InsertVehicle implements Runnable {

    private List<CellInterface> insertionCells = new ArrayList<>();
    private int vehicleInsertionSpeed;
    private final int vehicleSpeed;

    public InsertVehicle(List<CellInterface> insertionCells, int vehicleInsertionSpeed, int vehicleSpeed) {
        this.insertionCells = insertionCells;
        this.vehicleInsertionSpeed = vehicleInsertionSpeed;
        this.vehicleSpeed = vehicleSpeed;
    }

    @Override
    public void run() {
        RoadMesh roadMesh = RoadMesh.getInstance();
        Random random = new Random();
        while (roadMesh.getVehiclesAmount() < roadMesh.getMaxVehicleAmount()) {
            Vehicle vehicle = new Vehicle(vehicleSpeed);

            CellInterface cell = null;
            while (cell == null) {
                int randomPosition = random.nextInt(insertionCells.size());
                if (!insertionCells.get(randomPosition).isBusy()) {
                    cell = insertionCells.get(randomPosition);
                }
            }
            vehicle.setCell(cell);
            cell.setBusy(true);

            Thread veiculoThread = new Thread(vehicle);
            veiculoThread.start();
            roadMesh.vehicleLogInMesh();
            try {
                Thread.sleep(vehicleInsertionSpeed);
            } catch (InterruptedException ex) {
                System.out.println("excecao");
                ex.printStackTrace();
            }
        }
    }

}
