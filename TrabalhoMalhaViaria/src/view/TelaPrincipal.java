package view;

import controller.ControllerMalhaViaria;
import controller.MalhaViariaController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;
import model.semaphoreElements.Cell;
import controller.Observer;
import model.CellInterface;

/**
 *
 * @author Avell
 */
public class TelaPrincipal extends JFrame implements Observer {

    private ControllerMalhaViaria controller = new MalhaViariaController();
    private JButton jbIniciarSimulacao;
    private Container container;
    private JPanel jpMenu;
    private JPanel jpMalhaViaria;
    private MalhaViariaTableModel tableModel;
    private JTable jtbRoadMesh;
    private JList jlSeletorMalhaViaria;
    private JScrollPane jpScrollPane;

    public TelaPrincipal() {
        super();
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setTitle("Simulador Malha Viária");

        initComponents();
        initListeners();
    }

    private void initComponents() {
        controller.addObservador(this);

        container = this.getContentPane();
        container.setLayout(new BorderLayout());

        jpMenu = new JPanel();
        jpMenu.setBorder(new TitledBorder("Menu"));
        jpMenu.setLayout(new FlowLayout());

        jbIniciarSimulacao = new JButton("Iniciar Simulação");
        jbIniciarSimulacao.setEnabled(false);

        jlSeletorMalhaViaria = new JList(controller.getOpcoesDeMalhas());
        jlSeletorMalhaViaria.setBorder(new TitledBorder("Seleção de malha"));
        jlSeletorMalhaViaria.setToolTipText("Selecione uma malha");
        jlSeletorMalhaViaria.setVisibleRowCount(controller.getOpcoesDeMalhas().length);
        jlSeletorMalhaViaria.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jpScrollPane = new JScrollPane(jlSeletorMalhaViaria);
        jpScrollPane.setPreferredSize(new Dimension(135, 80));

        jpMenu.add(jpScrollPane);
        jpMenu.add(jbIniciarSimulacao);

        jpMalhaViaria = new JPanel();
        jpMalhaViaria.setBorder(new TitledBorder("Malha Viária"));
        jpMalhaViaria.setLayout(new FlowLayout());

        tableModel = new MalhaViariaTableModel();
        tableModel.setContoller(controller);

        jtbRoadMesh = new JTable();

        jpMalhaViaria.add(jtbRoadMesh);

        container.add(BorderLayout.CENTER, jpMalhaViaria);
        container.add(BorderLayout.NORTH, jpMenu);
    }

    private void initListeners() {
        jlSeletorMalhaViaria.addListSelectionListener(
                new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                controller.selecionaMalha(jlSeletorMalhaViaria.getSelectedIndex());
                jbIniciarSimulacao.setEnabled(true);
            }
        }
        );

        jbIniciarSimulacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.criarTabuleiro();
            }
        });
    }

    public static void main(String[] args) throws Exception {
        TelaPrincipal tp = new TelaPrincipal();
        tp.setVisible(true);
    }

    @Override
    public void notificaCriacaoDeMalha(int tamanhoX, int tamanhoY, List<CellInterface[]> road, List<CellInterface> crossRoads) {
        MalhaViariaCellRenderer malhaViariaCellRenderer = new MalhaViariaCellRenderer();
        jtbRoadMesh.setDefaultRenderer(Object.class, malhaViariaCellRenderer);
        jtbRoadMesh.setRowHeight(20);

        jtbRoadMesh.setModel(tableModel);
        tableModel.setSize(tamanhoX, tamanhoY);

        setRoads(road);
        setCrossRoads(crossRoads);

        TableColumnModel columnModel = jtbRoadMesh.getColumnModel();
        for (int i = 0; i < jtbRoadMesh.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(20);
        }

    }

    private void setRoads(List<CellInterface[]> roads) {
        for (CellInterface[] road : roads) {
            for (CellInterface cell : road) {
                cell.addObservador(this);

                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setBackground(Color.GRAY);

                jtbRoadMesh.add(label);
                tableModel.setValueAt(label, cell.getCoordinate().getY(), cell.getCoordinate().getX());

                jtbRoadMesh.repaint();
            }
        }
    }

    @Override
    public void notifiesBusyCell(int x, int y) {
        JLabel cell = (JLabel) tableModel.getValueAt(y, x);
        cell.setBackground(Color.red);
        jtbRoadMesh.repaint();
    }

    @Override
    public void notifiesFreeCell(int x, int y) {
        JLabel cell = (JLabel) tableModel.getValueAt(y, x);
        cell.setBackground(Color.GRAY);
        jtbRoadMesh.repaint();
    }

    private void setCrossRoads(List<CellInterface> crossRoads) {
        for (CellInterface crossRoad : crossRoads) {
            crossRoad.addObservador(this);

            JLabel label = new JLabel();
            label.setOpaque(true);
            label.setBackground(Color.YELLOW);

            jtbRoadMesh.add(label);
            tableModel.setValueAt(label, crossRoad.getCoordinate().getY(), crossRoad.getCoordinate().getX());

            jtbRoadMesh.repaint();
        }
    }

    @Override
    public void notifiesFreeCrossRoad(int x, int y) {
        JLabel cell = (JLabel) tableModel.getValueAt(y, x);
        cell.setBackground(Color.YELLOW);
        jtbRoadMesh.repaint();
    }

}
