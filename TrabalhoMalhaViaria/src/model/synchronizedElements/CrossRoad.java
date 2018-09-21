package model.synchronizedElements;

import controller.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import model.CellInterface;
import model.Coordinate;
import model.Vehicle;

/**
 *
 * @author Avell
 */
public class CrossRoad extends CellInterface {

    private List<CellInterface> nextCells = new ArrayList<>();
    private CellInterface next = null;
    private Semaphore mutex = new Semaphore(1);
    
    public CrossRoad(Coordinate coordinate) {
        super.setCoordinate(coordinate);
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

    @Override
    public void addNext(CellInterface cell) {
        this.nextCells.add(cell);
    }

    @Override
    public CellInterface next() {
        Random random = new Random();

        CellInterface cell = null;
        while (cell == null) {
            int randomPosition = random.nextInt(nextCells.size());
            if (!nextCells.get(randomPosition).isBusy()) {
                cell = nextCells.get(randomPosition);
            }
        }
        
        next = cell;
        return cell;
    }

    @Override
    public boolean isFinalCell() {
        return false;
    }
    
    @Override
    public void setBusy(boolean busy) {
        super.setBusy(busy);
        if (busy) {
            for (Observer o : super.getObservers()) {
                o.notifiesBusyCell(super.getCoordinate().getX(), super.getCoordinate().getY());
            }
        } else {
            for (Observer o : super.getObservers()) {
                o.notifiesFreeCrossRoad(super.getCoordinate().getX(), super.getCoordinate().getY());
            }
        }
    }

}
