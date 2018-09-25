package model.roadmeshbuilder.products;

import controller.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.AbstractCell;
import model.Coordinate;
import model.Vehicle;
import model.threadstrategy.Strategy;

/**
 *
 * @author Avell
 */
public class CrossRoad extends AbstractCell {

    private List<AbstractCell> nextCells = new ArrayList<>();
    private AbstractCell next = null;
    private Strategy strategy;

    public CrossRoad(Coordinate coordinate) {
        super.setCoordinate(coordinate);
    }

    @Override
    public void advanceVehicle(Vehicle vehicle) {
        strategy.advanceVehicle(vehicle, next, this);
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

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
