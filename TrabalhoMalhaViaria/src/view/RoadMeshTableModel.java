package view;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import controller.RoadMeshInterfaceController;

/**
 *
 * @author Avell
 */
public class RoadMeshTableModel extends DefaultTableModel {

    private JLabel[][] casas;
    private RoadMeshInterfaceController controle;

    public void setContoller(RoadMeshInterfaceController controle) {
        this.controle = controle;
    }
    
    public void setSize(int x, int y) {
        casas = new JLabel[x][y];
    }

    @Override
    public int getRowCount() {
        if (controle != null) {
            return controle.getQntLinhas();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        if (controle != null) {
            return controle.getQntColunas();
        }
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return casas[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        casas[rowIndex][columnIndex] = (JLabel) aValue;
        fireTableStructureChanged();
    }

    public void criarTabuleiro(int modeSelection, int vehicleMaxAmount, int vehicleSpeed, int vehicleInsertionSpeed) {
        controle.criarMalhaViaria(modeSelection, vehicleMaxAmount, vehicleSpeed, vehicleInsertionSpeed);
        fireTableRowsInserted(getRowCount(), getRowCount());
    }
}