package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.*;
import model.roadmeshfactory.RoadMeshFactory;

public class RoadMeshController implements RoadMeshInterfaceController {

    private List<Observer> observadores = new ArrayList<>();
    private RoadMesh roadMesh;
    private final String[] opcoesDeMalha = {"malha1", "malha2", "malha3", "malha4"};
    private int malhaSelecionada;

    @Override
    public void addObserver(Observer o) {
        observadores.add(o);
    }

    @Override
    public int getQntLinhas() {
        return roadMesh.getYSize();
    }

    @Override
    public int getQntColunas() {
        return roadMesh.getXSize();
    }


    /**
     * Create the road mesh
      * @param modeSelection represents either is mutex or synchronized
     * @param vehicleMaxAmount represents the max quantity of vehicles
     * @param vehicleSpeed is the vehicle speed on the road mesh
     * @param vehicleInsertionSpeed is the delay of vehicle insertion
     */
    @Override
    public void criarMalhaViaria(int modeSelection, int vehicleMaxAmount, int vehicleSpeed, int vehicleInsertionSpeed) {
        try {
            roadMesh = RoadMeshFactory.getInstance().buildRoadMesh(opcoesDeMalha[malhaSelecionada]);
            roadMesh.setMaxVehicleAmount(vehicleMaxAmount);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        List<CellInterface[]> roads = new ArrayList<>();
        for (Road road : roadMesh.getRoads()) {
            CellInterface[] returnRoads = new CellInterface[road.getSize()];
            int i = 0;
            for (CellInterface cell : road.getCells()) {
                returnRoads[i] = cell;
                i++;
            }
            roads.add(returnRoads);
        }
        for (Observer o : observadores) {
            o.notificaCriacaoDeMalha(roadMesh.getXSize(), roadMesh.getYSize(), roads, roadMesh.getCrossRoads());
        }
        
        startSimulation(vehicleInsertionSpeed, vehicleSpeed);
    }

    @Override
    public String[] getRoadMeshOptions() {
        return opcoesDeMalha;
    }

    @Override
    public void selecionaMalha(int indexMalhaSelecionada) {
        this.malhaSelecionada = indexMalhaSelecionada;
    }

    /**
     * Start put vehicles on the road mesh
     */
    private void startSimulation(int vehicleInsertionSpeed, int vehicleSpeed) {
        InsertVehicle insertVehicle = new InsertVehicle(findInsertionCells(), vehicleInsertionSpeed, vehicleSpeed);
        Thread insertVehicleThread = new Thread(insertVehicle);
        insertVehicleThread.start();

//        CellInterface cell = roadMesh.searchCrossRoad(new Coordinate(7, 6));
//        cell.setBusy(true);
        //Try if the vehicle stop on the crossroad.
//        CellInterface cell = roadMesh.getRoads().get(1).getCells()[0];
//        cell.setBusy(true);
//        CellInterface cell1 = roadMesh.getRoads().get(13).getCells()[0];
//        cell1.setBusy(true);
    }

    private List<CellInterface> findInsertionCells() {
        List<CellInterface> insertionCells = new ArrayList<>();

        for(CellInterface c : roadMesh.getRoads().get(1).getCells()) {
            System.out.println(c.getCoordinate().toString());
        }

        for (Road road : roadMesh.getRoads()) {
            if (road.getCells()[0].getCoordinate().getX() == 0
                    || road.getCells()[0].getCoordinate().getX() == roadMesh.getXSize() - 1
                    || road.getCells()[0].getCoordinate().getY() == 0
                    || road.getCells()[0].getCoordinate().getY() == roadMesh.getYSize() - 1) {
                insertionCells.add(road.getCells()[0]);
            }
        }
        return insertionCells;
    }


}
