package model;

import java.util.ArrayList;
import java.util.List;

public class RoadMesh {

    private int tamanhoX;
    private int tamanhoY;
    private List<Road> vias = new ArrayList<>();
    private int qntVeiculos = 0;
    private List<CellInterface> crossRoads = new ArrayList<>();

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
        this.tamanhoX = tamanhoX;
    }

    public void setYSize(int tamanhoY) {
        this.tamanhoY = tamanhoY;
    }

    public void addVia(Road via) {
        vias.add(via);
    }

    public List<Road> getRoads() {
        return vias;
    }

    public int getXSize() {
        return tamanhoX;
    }

    public int getYSize() {
        return tamanhoY;
    }

    public void vehicleLogInMesh() {
        qntVeiculos++;
    }

    public void veiculoSaiuDaMalha() {
        qntVeiculos--;
    }

    public int getVehiclesAmount() {
        return qntVeiculos;
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

}
