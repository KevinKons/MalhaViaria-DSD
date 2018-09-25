package controller;

/**
 *
 * @author Avell
 */
public interface RoadMeshInterfaceController extends Observed {

    int getQntLinhas();
    int getQntColunas();
    void criarMalhaViaria(int modeSelection, int vehicleMinAmount);
    String[] getRoadMeshOptions();
    void selecionaMalha(int selectedIndex);

    void stopSimulation();
}
