package ui;

import javax.swing.*;
import java.awt.*;

// This class manages split panel GUI component and its sub objects, their appearances, and their
// event listeners
public class MainPanelHandler {
    private MainGUI parentGUI;
    private JSplitPane splitPane;
    private JScrollPane leftSubPane;
    private JInternalFrame rightSubPane;
    private JTable leftTable;

    // REQUIRES: parentGUI not null
    // EFFECTS: creates a MenuBarHandler affiliated to a parentGUI object,
    //          give this some appropriate sub objects, and set their appearances
    public MainPanelHandler(MainGUI parentGUI, int leftWidth, int rightWidth) {
        this.parentGUI = parentGUI;

        // Set data for the table in the left panel
        leftTable = new JTable(new StockTableModel());
        setStockTableHeaders();

        PieChart pie1 = new PieChart(rightWidth);

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

    // EFFECTS: get the SplitPane component of this
    public JSplitPane getSplitPane() {
        return splitPane;
    }

    public JTable getLeftTable() {
        return leftTable;
    }
}
