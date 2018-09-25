package model;

import controller.Observer;
import java.util.ArrayList;
import java.util.List;
import controller.Observed;
import model.roadmeshbuilder.products.CrossRoad;

public class RoadMesh implements Observed {

    private int XSize;
    private int YSize;
    private List<Road> roads = new ArrayList<>();
    private int vehicleAmount = 0;
    private int maxVehicleAmount;
    private List<CrossRoad> crossRoads = new ArrayList<>();
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

    void vehicleLogOutMesh() {
        vehicleAmount--;
        for(Observer o : observers) {
            o.notifiesVehicleLogOutMesh(vehicleAmount);
        }
    }

    public int getVehiclesAmount() {
        return vehicleAmount;
    }

    public CrossRoad searchCrossRoad(Coordinate coordinate) {
        for(CrossRoad crossRoad : crossRoads) {
            if(crossRoad.getCoordinate().equals(coordinate))
                return crossRoad;
        }
        return null;
    }
    
    public void addCrossRoad(CrossRoad crossRoad) {
        this.crossRoads.add(crossRoad);
    }
    
    public List<CrossRoad> getCrossRoads() {
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
