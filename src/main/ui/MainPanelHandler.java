package ui;

import model.ListOfStocks;

import javax.swing.*;
import java.awt.*;

// This class manages split panel GUI component and its sub objects, their appearances, and their
// event listeners
public class MainPanelHandler {
    private int rightWidth;

    private MainGUI parentGUI;
    private JSplitPane splitPane;
    private JScrollPane leftSubPane;
    private JInternalFrame rightSubPane;
    private JTable leftTable;

    private ListOfStocks los;

    // REQUIRES: parentGUI not null
    // EFFECTS: creates a MenuBarHandler affiliated to a parentGUI object,
    //          give this some appropriate sub objects, and set their appearances
    public MainPanelHandler(MainGUI parentGUI, int leftWidth, int rightWidth) {
        this.parentGUI = parentGUI;
        this.rightWidth = rightWidth;

        // Set data for the table in the left panel
        this.los = (new StockDataHandler()).getCurrentList();
        leftTable = new JTable(new StockTableModel());
        setStockTableHeaders();

        PieChart pie1 = new PieChart(rightWidth, this.los);

        // Set sub panels and their contents
        leftSubPane = new JScrollPane(leftTable);
        rightSubPane = new JInternalFrame();
        leftSubPane.setMinimumSize(new Dimension(leftWidth, 0));
        rightSubPane.setMinimumSize(new Dimension(rightWidth, 0));
        rightSubPane.add(pie1);
        rightSubPane.setVisible(true);

        // Set main panel
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSubPane, rightSubPane);

    }

    // REQUIRES: prefW <= maxW
    // EFFECTS: create a GroupLayout for one JComponent
    private GroupLayout createCroupLayoutOne(int prefW, int maxW, JComponent item) {
        GroupLayout layout = new GroupLayout(rightSubPane.getContentPane());
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(item, 0, prefW, maxW)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(item, 0, prefW, maxW)
                                .addContainerGap())
        );
        return layout;
    }

    // REQUIRES: StockTableModel.getStaticColumnNames() not empty
    // EFFECTS: get and reset column names of the stock table using a loop
    public void setStockTableHeaders() {
        int i = 0;
        String[] columnNames = StockTableModel.getStaticColumnNames();

        // When i less than or equal to column count - 1 (because column count starts from 1), replace
        // column name with corresponding string in columnNames[]
        while (i <= (this.leftTable.getColumnCount() - 1)) {
            leftTable.getTableHeader().getColumnModel().getColumn(i).setHeaderValue(
                    columnNames[i]);
            i++;
        }
    }

    // EFFECTS: get updated temp stocks data and pass it to relevant components
    public void updateTmpData() {
        StockTableModel stockTableModel = new StockTableModel();
        stockTableModel.updateTmpData();
        getLeftTable().setModel(stockTableModel);
        setStockTableHeaders();

        StockDataHandler stockDataHandler = new StockDataHandler();
        this.los = stockDataHandler.readFromTmpFile();
        PieChart pie = new PieChart(this.rightWidth, this.los);
        getRightSubPane().getContentPane().removeAll();
        getRightSubPane().add(pie);
        getRightSubPane().updateUI();
    }

    // EFFECTS: get previously saved stocks data and pass it to relevant components
    //TODO: extract a parent method
    public void updateSavedData() {
        StockTableModel stockTableModel = new StockTableModel();
        getLeftTable().setModel(stockTableModel);
        setStockTableHeaders();

        this.los = stockTableModel.getCurrentLos();
        PieChart pie = new PieChart(this.rightWidth, this.los);
        getRightSubPane().getContentPane().removeAll();
        getRightSubPane().add(pie);
        getRightSubPane().updateUI();
    }

    public ListOfStocks getCurrentLos() {
        return los;
    }

    // EFFECTS: get the SplitPane component of this
    public JSplitPane getSplitPane() {
        return splitPane;
    }

    // EFFECTS: get the leftTable component of this
    public JTable getLeftTable() {
        return leftTable;
    }

    // EFFECTS: get the rightSubPane component of this
    public JInternalFrame getRightSubPane() {
        return rightSubPane;
    }

    private void updateData() {
        ;
    }
}
