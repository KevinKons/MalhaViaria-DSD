package controller;

import model.CellInterface;
import model.Coordinate;

/**
 *
 * @author Avell
 */
public interface RoadMeshInterfaceController extends Observed {

    int getQntLinhas();
    int getQntColunas();
    void criarMalhaViaria(int modeSelection, int vehicleMaxAmount, int vehicleSpeed, int vehicleInsertionSpeed);
    String[] getRoadMeshOptions();
    void selecionaMalha(int selectedIndex);
    
}
