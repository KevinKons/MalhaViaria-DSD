package controller;

import java.util.List;
import model.CellInterface;

/**
 *
 * @author Avell
 */
public interface Observer {
    
    void notificaCriacaoDeMalha(int tamanhoX, int tamanhoY, 
            List<CellInterface[]> vias, List<CellInterface> crossRoads);
    void notifiesBusyCell(int x, int y);
    void notifiesFreeCell(int x, int y);
    void notifiesFreeCrossRoad(int x, int y);
    void notifiesVehicleLogOutMesh(int vehicleAmount);
    void notifiesVehicleLogInMesh(int vehicleAmount);
}
