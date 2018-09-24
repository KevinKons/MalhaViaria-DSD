package model.synchronizedElements;

import controller.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public CrossRoad(Coordinate coordinate) {
        super.setCoordinate(coordinate);
    }

    @Override
    public synchronized void advanceVehicle(Vehicle vehicle) {
        try {
            Thread.sleep(500);
            vehicle.setCell(next);
            next.setBusy(true);
            this.setBusy(false);
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
            if (nextCells.get(randomPosition).isNotBusy()) {
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
