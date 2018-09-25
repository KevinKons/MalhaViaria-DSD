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
    private int minVehicleAmount;
    private List<CrossRoad> crossRoads = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();
    private List<Vehicle> vehicles = new ArrayList<>();

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

    public int getMinVehicleAmount() {
        return minVehicleAmount;
    }
    
    public void setMinVehicleAmount(int maxVehicleAmount) {
        this.minVehicleAmount = maxVehicleAmount;
    }


    @Override
    public void addObserver(Observer o) {
        this.observers.add(o);
    }


    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }

    public void setCrossRoads(List<CrossRoad> crossRoads) {
        this.crossRoads = crossRoads;
    }

    public void addVehicle(Vehicle vehicle) {
        this.vehicles.add(vehicle);

        for(Observer o : observers) {
            o.notifiesVehicleLogInMesh(vehicles.size());
        }
    }

    public void removeVehicle(Vehicle vehicle) {
        this.vehicles.remove(vehicle);

        for(Observer o : observers) {
            o.notifiesVehicleLogOutMesh(vehicles.size());
        }
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void cleanVehicles() {
        for(Vehicle v : vehicles) {
        }
    }
}
