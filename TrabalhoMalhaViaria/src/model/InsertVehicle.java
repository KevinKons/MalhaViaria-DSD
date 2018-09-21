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
    private int maxVehicleAmount;

    public InsertVehicle(List<CellInterface> insertionCells, int maxVehicleAmount) {
        this.insertionCells = insertionCells;
        this.maxVehicleAmount = maxVehicleAmount;
    }

    @Override
    public void run() {
        RoadMesh roadMesh = RoadMesh.getInstance();
        Random random = new Random();
        while (roadMesh.getVehiclesAmount() <= maxVehicleAmount) {
            Vehicle vehicle = new Vehicle(500);

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
            System.out.println(roadMesh.getVehiclesAmount());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("excecao");
                ex.printStackTrace();
            }
        }
    }

}
