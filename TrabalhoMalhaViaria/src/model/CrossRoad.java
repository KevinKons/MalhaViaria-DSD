package model;

import controller.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Avell
 */
public abstract class CrossRoad extends AbstractCell {

    private List<AbstractCell> nextCells = new ArrayList<>();
    protected AbstractCell next = null;
    private Semaphore mutex = new Semaphore(1);

    public CrossRoad(Coordinate coordinate) {
        super.setCoordinate(coordinate);
    }

    @Override
    public void addNext(AbstractCell cell) {
        this.nextCells.add(cell);
    }

    @Override
    public AbstractCell next() {
        Random random = new Random();

        AbstractCell cell = null;
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
