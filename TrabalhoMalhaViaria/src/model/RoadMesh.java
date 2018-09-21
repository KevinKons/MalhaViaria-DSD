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
    private int maxVehicleAmount;
    private List<CellInterface> crossRoads = new ArrayList<>();
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

    public void vehicleLogInMesh() {
        vehicleAmount++;
        for(Observer o : observers) {
            o.notifiesVehicleLogInMesh(vehicleAmount);
        }
    }

    public void vehicleLogOutMesh() {
        vehicleAmount--;
        for(Observer o : observers) {
            o.notifiesVehicleLogOutMesh(vehicleAmount);
        }
    }

    public int getVehiclesAmount() {
        return vehicleAmount;
    }

    public CellInterface searchCrossRoad(Coordinate coordinate) {
        for(CellInterface crossRoad : crossRoads) {
            if(crossRoad.getCoordinate().equals(coordinate))
                return crossRoad;
        }
        return null;
    }
    
    public void addCrossRoad(CellInterface crossRoad) {
        this.crossRoads.add(crossRoad);
    }
    
    public List<CellInterface> getCrossRoads() {
        return this.crossRoads;
    }

    public int getMaxVehicleAmount() {
        return maxVehicleAmount;
    }
    
    public void setMaxVehicleAmount(int maxVehicleAmount) {
        this.maxVehicleAmount = maxVehicleAmount;
    }


    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }

}
