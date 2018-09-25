package model;

import java.util.List;
import java.util.Random;

/**
 * @author Avell
 */
public class InsertVehicle implements Runnable {

    private List<AbstractCell> insertionCells;
    private boolean isExecuting = true;

    /**
     * @param insertionCells Are the cells where the vehicles first appear.
     */
    public InsertVehicle(List<AbstractCell> insertionCells) {
        this.insertionCells = insertionCells;
    }

    @Override
    public void run() {
        RoadMesh roadMesh = RoadMesh.getInstance();
        // To decide where the vehicles will enter
        Random random = new Random();
        // Insert the vehicles while the number of vehicles is lower then the quantity set by user.
        while (isExecuting) {
            System.out.println(roadMesh.getVehiclesAmount() <= roadMesh.getMinVehicleAmount());
            if (roadMesh.getVehiclesAmount() <= roadMesh.getMinVehicleAmount()) {
                Vehicle vehicle = new Vehicle();

                AbstractCell cell = null;
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
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    System.out.println("excecao");
                    ex.printStackTrace();
                }
            }
        }
    }

}
