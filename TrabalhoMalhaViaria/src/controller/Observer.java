package controller;

import java.util.List;
import model.AbstractCell;

/**
 *
 * @author Avell
 */
public interface Observer {
    
    void notificaCriacaoDeMalha(int tamanhoX, int tamanhoY,
                                List<AbstractCell[]> vias, List<AbstractCell> crossRoads);
    void notifiesBusyCell(int x, int y);
    void notifiesFreeCell(int x, int y);
    void notifiesFreeCrossRoad(int x, int y);
    void notifiesVehicleLogOutMesh(int vehicleAmount);
    void notifiesVehicleLogInMesh(int vehicleAmount);
}
