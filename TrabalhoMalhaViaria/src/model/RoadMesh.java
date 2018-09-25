package model;

import controller.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import controller.Observed;

public class RoadMesh implements Observed {

    private int XSize;
    private int YSize;
    private List<Road> roads;
    private int vehicleAmount = 0;
    private int minVehicleAmount;
    private List<AbstractCell> crossRoads;
    private List<Observer> observers;

    private static RoadMesh instance;

    public static RoadMesh getInstance() {
        if (instance == null) {
            instance = new RoadMesh();
        }

        return instance;
    }

    private RoadMesh() {
    }

    public void setXSize(int tamanhoX) {
        this.XSize = tamanhoX;
    }

    public void setYSize(int tamanhoY) {
        this.YSize = tamanhoY;
    }

    public void addRoad(Road via) {
        roads.add(via);
    }

    public List<Road> getRoads() {
        return roads;
    }

    public int getXSize() {
        return XSize;
    }

    public int getYSize() {
        return YSize;
    }

    public void vehicleLogInMesh() {
        vehicleAmount++;
        for (Observer o : observers) {
            o.notifiesVehicleLogInMesh(vehicleAmount);
        }
    }

    public void vehicleLogOutMesh() {
        Semaphore mutex = new Semaphore(1);
        try {
            mutex.acquire();
            vehicleAmount--;
            System.out.println(observers == null);
            for (Observer o : observers) {
                o.notifiesVehicleLogOutMesh(vehicleAmount);
            }
            if (vehicleAmount == 0) {
                for (Observer o : observers) {
                    o.notifiesEndOfSimulation();
                }
            }
            mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getVehiclesAmount() {
        return vehicleAmount;
    }

    public AbstractCell searchCrossRoad(Coordinate coordinate) {
        for (AbstractCell crossRoad : crossRoads) {
            if (crossRoad.getCoordinate().equals(coordinate))
                return crossRoad;
        }
        return null;
    }

    public void addCrossRoad(AbstractCell crossRoad) {
        this.crossRoads.add(crossRoad);
    }

    public List<AbstractCell> getCrossRoads() {
        return this.crossRoads;
    }

    public int getMinVehicleAmount() {
        return minVehicleAmount;
    }

    public void setMinVehicleAmount(int minVehicleAmount) {
        this.minVehicleAmount = minVehicleAmount;
    }


    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }

    public void setCrossRoads(List<AbstractCell> crossRoads) {
        this.crossRoads = crossRoads;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public void init() {
        observers = new ArrayList<>();
        crossRoads = new ArrayList<>();
        roads = new ArrayList<>();
    }
}
