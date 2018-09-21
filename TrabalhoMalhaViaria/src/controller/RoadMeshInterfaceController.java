package controller;

import model.CellInterface;
import model.Coordinate;

/**
 *
 * @author Avell
 */
public interface RoadMeshInterfaceController extends Observed {

    public int getQntLinhas();
    public int getQntColunas();
    public void criarMalhaViaria(int modeSelection, int vehicleMaxAmount, int vehicleSpeed, int vehicleInsertionSpeed);
    public String[] getRoadMeshOptions();

    public void selecionaMalha(int selectedIndex);
    
}
