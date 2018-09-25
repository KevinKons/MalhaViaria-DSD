package view;

import controller.RoadMeshController;
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
import controller.Observer;
import javax.swing.JRadioButton;
import model.CellInterface;
import controller.RoadMeshInterfaceController;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import model.RoadMesh;

/**
 *
 * @author Avell
 */
public class MainFrame extends JFrame implements Observer {

    private RoadMeshInterfaceController controller = new RoadMeshController();
    private JButton btStartSimulation;
    private Container container;
    private JPanel jpMenu;
    private JPanel jpRoadMesh;
    private JPanel jpModeSelection;
    private JPanel jpVehicleSettings;
    private JPanel jpInformation;
    private RoadMeshTableModel tableModel;
    private JTable tbRoadMesh;
    private JList jlRoadMeshSelector;
    private JScrollPane jpScrollPane;
    private JRadioButton rbtSemaphoreMode;
    private JRadioButton rbtSynchronizedMode;
    private ButtonGroup buttonGroup;
    private JTextField tfVehicleMaxAmount;
    private JTextField tfVehicleSpeed;
    private JTextField tfVehicleInsertionSpeed;
    private JLabel lbVehicleMaxAmount;
    private JLabel lbVehicleSpeed;
    private JLabel lbInsertionVehicleSpeed;
    private JLabel lbVehicleCurrentAmount;

    public MainFrame() {
        super();
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        setTitle("Simulador Malha Viária");

        initComponents();
        initListeners();
    }


    public static void main(String[] args) {
        MainFrame tp = new MainFrame();
        tp.setVisible(true);
    }

    private void initComponents() {
        controller.addObserver(this);

        container = this.getContentPane();
        container.setLayout(new BorderLayout());

        jpMenu = new JPanel();
        jpMenu.setBorder(new TitledBorder("Menu"));
        jpMenu.setLayout(new FlowLayout());

        btStartSimulation = new JButton("Start Simulation");
        btStartSimulation.setEnabled(false);

        jlRoadMeshSelector = new JList(controller.getRoadMeshOptions());
        jlRoadMeshSelector.setToolTipText("Select a Road Mesh");
        jlRoadMeshSelector.setVisibleRowCount(controller.getRoadMeshOptions().length);
        jlRoadMeshSelector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        jpScrollPane = new JScrollPane(jlRoadMeshSelector);
        jpScrollPane.setPreferredSize(new Dimension(135, 80));
        jpScrollPane.setBorder(new TitledBorder("Road Mesh Selection"));

        rbtSemaphoreMode = new JRadioButton("Semaphore Mode", true);
        rbtSynchronizedMode = new JRadioButton("Synchronized Mode");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(rbtSemaphoreMode);
        buttonGroup.add(rbtSynchronizedMode);

        jpModeSelection = new JPanel(new GridLayout(2, 1));
        jpModeSelection.setBorder(new TitledBorder("Mode Selection"));
        jpModeSelection.add(rbtSemaphoreMode);
        jpModeSelection.add(rbtSynchronizedMode);

        tfVehicleMaxAmount = new JTextField("20");
        tfVehicleSpeed = new JTextField("500");
        tfVehicleInsertionSpeed = new JTextField("1000");

        lbVehicleMaxAmount = new JLabel("Vehicle max amount");
        lbInsertionVehicleSpeed = new JLabel("Vehicle insertion speed");
        lbVehicleSpeed = new JLabel("Vehicle speed");

        jpVehicleSettings = new JPanel(new GridLayout(3, 2));
        jpVehicleSettings.setBorder(new TitledBorder("Vehicle Settings"));
        jpVehicleSettings.add(lbVehicleMaxAmount);
        jpVehicleSettings.add(tfVehicleMaxAmount);
        jpVehicleSettings.add(lbInsertionVehicleSpeed);
        jpVehicleSettings.add(tfVehicleInsertionSpeed);
        jpVehicleSettings.add(lbVehicleSpeed);
        jpVehicleSettings.add(tfVehicleSpeed);

        jpMenu.add(jpScrollPane);
        jpMenu.add(jpModeSelection);
        jpMenu.add(jpVehicleSettings);
        jpMenu.add(btStartSimulation);

        lbVehicleCurrentAmount = new JLabel("Vehicles: ");
        jpInformation = new JPanel(new FlowLayout());
        jpInformation.setBorder(new TitledBorder("Information"));
        jpInformation.add(lbVehicleCurrentAmount);

        jpRoadMesh = new JPanel();
        jpRoadMesh.setBorder(new TitledBorder("Road Mesh"));
        jpRoadMesh.setLayout(new GridLayout(1, 2));

        tableModel = new RoadMeshTableModel();
        tableModel.setContoller(controller);

        tbRoadMesh = new JTable();

        jpRoadMesh.add(tbRoadMesh);

        container.add(BorderLayout.CENTER, jpRoadMesh);
        container.add(BorderLayout.NORTH, jpMenu);
        container.add(BorderLayout.EAST, jpInformation);
    }

    private void initListeners() {
        jlRoadMeshSelector.addListSelectionListener(
                e -> {
                    controller.selecionaMalha(jlRoadMeshSelector.getSelectedIndex());
                    btStartSimulation.setEnabled(true);
                }
        );
        
        Observer mainFrame = this;
        btStartSimulation.addActionListener(e -> {
            int modeSelection = 0;
            if (rbtSynchronizedMode.isSelected()) {
                modeSelection = 1;
            }
            int vehicleInsertionSpeed = Integer.parseInt(tfVehicleInsertionSpeed.getText());
            int vehicleSpeed = Integer.parseInt(tfVehicleSpeed.getText());
            int vehicleMaxAmount = Integer.parseInt(tfVehicleMaxAmount.getText());
            tableModel.criarTabuleiro(modeSelection, vehicleMaxAmount, vehicleSpeed, vehicleInsertionSpeed);
            RoadMesh.getInstance().addObserver(mainFrame);
        });
    }

    @Override
    public void notificaCriacaoDeMalha(int tamanhoX, int tamanhoY, List<CellInterface[]> road, List<CellInterface> crossRoads) {
        MalhaViariaCellRenderer malhaViariaCellRenderer = new MalhaViariaCellRenderer();
        tbRoadMesh.setDefaultRenderer(Object.class, malhaViariaCellRenderer);
        tbRoadMesh.setRowHeight(20);

        tbRoadMesh.setModel(tableModel);
        tableModel.setSize(tamanhoX, tamanhoY);

        setRoads(road);
        setCrossRoads(crossRoads);

        TableColumnModel columnModel = tbRoadMesh.getColumnModel();
        for (int i = 0; i < tbRoadMesh.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(20);
        }

    }

    private void setRoads(List<CellInterface[]> roads) {
        for (CellInterface[] road : roads) {
            for (CellInterface cell : road) {
                cell.addObserver(this);

                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setBackground(Color.GRAY);

                tbRoadMesh.add(label);
                tableModel.setValueAt(label, cell.getCoordinate().getY(), cell.getCoordinate().getX());

                tbRoadMesh.repaint();
            }
        }
    }

    @Override
    public void notifiesBusyCell(int x, int y) {
        JLabel cell = (JLabel) tableModel.getValueAt(y, x);
        cell.setBackground(Color.red);
        tbRoadMesh.repaint();
    }

    @Override
    public void notifiesFreeCell(int x, int y) {
        JLabel cell = (JLabel) tableModel.getValueAt(y, x);
        cell.setBackground(Color.GRAY);
        tbRoadMesh.repaint();
    }

    private void setCrossRoads(List<CellInterface> crossRoads) {
        for (CellInterface crossRoad : crossRoads) {
            crossRoad.addObserver(this);

            JLabel label = new JLabel();
            label.setOpaque(true);
            label.setBackground(Color.YELLOW);

            tbRoadMesh.add(label);
            tableModel.setValueAt(label, crossRoad.getCoordinate().getY(), crossRoad.getCoordinate().getX());

            tbRoadMesh.repaint();
        }
    }

    @Override
    public void notifiesFreeCrossRoad(int x, int y) {
        JLabel cell = (JLabel) tableModel.getValueAt(y, x);
        cell.setBackground(Color.YELLOW);
        tbRoadMesh.repaint();
    }

    @Override
    public void notifiesVehicleLogOutMesh(int vehicleAmount) {
        lbVehicleCurrentAmount.setText("Vehicles count: " + vehicleAmount);
    }

    @Override
    public void notifiesVehicleLogInMesh(int vehicleAmount) {
        lbVehicleCurrentAmount.setText("Vehicles count: " + vehicleAmount);
    }

}
