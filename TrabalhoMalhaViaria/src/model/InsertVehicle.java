package model;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Avell
 */
public class InsertVehicle implements Runnable {

    private List<CellInterface> insertionCells;
    private int vehicleInsertionDelay;
    private final int vehicleSpeed;

    /**
     *
     * @param insertionCells Are the cells where the vehicles first appear.
     * @param vehicleInsertionDelay
     * @param vehicleSpeed
     */
    public InsertVehicle(List<CellInterface> insertionCells, int vehicleInsertionDelay, int vehicleSpeed) {
        this.insertionCells = insertionCells;
        this.vehicleInsertionDelay = vehicleInsertionDelay;
        this.vehicleSpeed = vehicleSpeed;
    }

    @Override
    public void run() {
        RoadMesh roadMesh = RoadMesh.getInstance();
        // To decide where the vehicles will enter
        Random random = new Random();
        // Insert the vehicles while the number of vehicles is lower then the quantity set by user.
        while (roadMesh.getVehiclesAmount() < roadMesh.getMaxVehicleAmount()) {
            Vehicle vehicle = new Vehicle(vehicleSpeed);

            CellInterface cell = null;
            while (cell == null) {
                int randomPosition = random.nextInt(insertionCells.size());
                if (insertionCells.get(randomPosition).isNotBusy()) {
                    cell = insertionCells.get(randomPosition);
                }
            }
            vehicle.setCell(cell);
            cell.setBusy(true);

            Thread veiculoThread = new Thread(vehicle);
            veiculoThread.start();
            roadMesh.vehicleLogInMesh();
            try {
                Thread.sleep(vehicleInsertionDelay);
            } catch (InterruptedException ex) {
                System.out.println("excecao");
                ex.printStackTrace();
            }
        }
    }

}
