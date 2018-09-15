package view;

import controller.ControllerMalhaViaria;
import controller.MalhaViariaController;
import controller.Observador;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Avell
 */
public class TelaPrincipal extends JFrame implements Observador {

    private ControllerMalhaViaria controller = new MalhaViariaController();
    private JButton jbIniciarSimulacao;
    private Container container;
    private JPanel jpMenu;
    private JPanel jpMalhaViaria;
    private MalhaViariaTableModel tableModel;
    private JTable jtbMalhaViaria;
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

        jtbMalhaViaria = new JTable();

        jpMalhaViaria.add(jtbMalhaViaria);

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
                controller.criarMalhaViaria();
            }
        });
    }

    public static void main(String[] args) throws Exception {
        TelaPrincipal tp = new TelaPrincipal();
        tp.setVisible(true);
    }

    @Override
    public void notificaCriacaoDeMalha(int tamanhoX, int tamanhoY) {
        MalhaViariaCellRenderer malhaViariaCellRenderer = new MalhaViariaCellRenderer();
        jtbMalhaViaria.setDefaultRenderer(Object.class, malhaViariaCellRenderer);
        jtbMalhaViaria.setRowHeight(20);

        jtbMalhaViaria.setModel(tableModel);
        tableModel.setSize(tamanhoX, tamanhoY);

        TableColumnModel columnModel = jtbMalhaViaria.getColumnModel();
        for (int i = 0; i < jtbMalhaViaria.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(20);
        }
    }

}
