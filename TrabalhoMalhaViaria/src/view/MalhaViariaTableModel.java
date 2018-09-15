package view;

import controller.ControllerMalhaViaria;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Avell
 */
public class MalhaViariaTableModel extends DefaultTableModel {

    private JLabel[][] casas;
    private ControllerMalhaViaria controle;

    public void setContoller(ControllerMalhaViaria controle) {
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

    public void criarTabuleiro() {
        controle.criarMalhaViaria();
        fireTableRowsInserted(getRowCount(), getRowCount());
    }
}