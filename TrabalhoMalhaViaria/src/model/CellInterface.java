package model;

import controller.Observado;
import controller.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Avell
 */
public abstract class CellInterface implements Observado {

    private Coordinate coordinate;
    private boolean busy = false;
    private List<Observer> observers = new ArrayList<>();

    public abstract void advanceVehicle(Vehicle vehicle);

    public abstract void addNext(CellInterface cell);

    public abstract CellInterface next();

    public abstract boolean isFinalCell();

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        if (busy) {
            for (Observer o : observers) {
                o.notifiesBusyCell(this.coordinate.getX(), this.coordinate.getY());
            }
        } else {
            for (Observer o : observers) {
                o.notifiesFreeCell(this.coordinate.getX(), this.coordinate.getY());
            }
        }
        this.busy = busy;
    }

    public boolean isExitCell() {
        if (isFinalCell()) {
            RoadMesh malhaViaria = RoadMesh.getInstance();
            if (coordinate.getX() == 0
                    || coordinate.getX() == malhaViaria.getXSize() - 1
                    || coordinate.getY() == 0
                    || coordinate.getY() == malhaViaria.getYSize() - 1) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void addObservador(Observer o) {
        this.observers.add(o);
    }
    
    public List<Observer> getObservers() {
        return this.observers;
    }

}
