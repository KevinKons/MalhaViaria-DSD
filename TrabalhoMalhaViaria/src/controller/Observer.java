package controller;

import java.util.List;
import model.AbstractCell;
import model.Colors;

/**
 *
 * @author Avell
 */
public interface Observer {
    
    void notifiesRoadMeshCreation(int tamanhoX, int tamanhoY,
                                  List<AbstractCell[]> vias, List<AbstractCell> crossRoads);
    void notifiesBusyCell(int x, int y, String color);
    void notifiesFreeCell(int x, int y);
    void notifiesFreeCrossRoad(int x, int y);
    void notifiesVehicleLogOutMesh(int vehicleAmount);
    void notifiesVehicleLogInMesh(int vehicleAmount);
    void notifiesEndOfSimulation();
}
