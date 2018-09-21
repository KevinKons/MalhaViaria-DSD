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

    public void notifiesBusyCell(int x, int y);

    public void notifiesFreeCell(int x, int y);

    public void notifiesFreeCrossRoad(int x, int y);
}
