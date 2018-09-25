package model;

import controller.Observer;
import java.util.ArrayList;
import java.util.List;
import controller.Observed;

public class RoadMesh implements Observed {

    private int XSize;
    private int YSize;
    private List<Road> roads = new ArrayList<>();
    private int vehicleAmount = 0;
    private int minVehicleAmount;
    private List<AbstractCell> crossRoads = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    private static RoadMesh instance;

    public static RoadMesh getInstance() {
        if (instance == null) {
            instance = new RoadMesh();
        }

        return instance;
    }

    private RoadMesh() { }

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

    void vehicleLogInMesh() {
        vehicleAmount++;
        for(Observer o : observers) {
            o.notifiesVehicleLogInMesh(vehicleAmount);
        }
    }

    void vehicleLogOutMesh() {
        vehicleAmount--;
        for(Observer o : observers) {
            o.notifiesVehicleLogOutMesh(vehicleAmount);
        }
    }

    int getVehiclesAmount() {
        return vehicleAmount;
    }

    public AbstractCell searchCrossRoad(Coordinate coordinate) {
        for(AbstractCell crossRoad : crossRoads) {
            if(crossRoad.getCoordinate().equals(coordinate))
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

    int getMinVehicleAmount() {
        return minVehicleAmount;
    }
    
    public void setMinVehicleAmount(int minVehicleAmount) {
        this.minVehicleAmount = minVehicleAmount;
    }


    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

}
