package ui;

import model.*;
import persistence.StockDataHandler;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;

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
        StockDataHandler stockDataHandler = new StockDataHandler();
        stockDataHandler.clearTmpFile();
        this.los = stockDataHandler.getCurrentList();
        leftTable = new JTable(new StockTableModel(this.los));
        leftTable.getSelectionModel().addListSelectionListener(new RowListener());
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
        StockDataHandler stockDataHandler = new StockDataHandler();
        //this.los = stockDataHandler.readFromTmpFile();
        this.los = StockDataSingleton.getInstance().getCurrentLos(); //TODO: this is new singleton entry

        updateDataForTableAndPie(this.rightWidth, this.los);

    }

    // EFFECTS: get previously saved stocks data and pass it to relevant components
    public void updateSavedData() {
        StockDataHandler stockDataHandler = new StockDataHandler();
        this.los = stockDataHandler.readFromSavedFile();

        updateDataForTableAndPie(this.rightWidth, this.los);

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

    public ListOfStocks getCurrentList() {
        return this.los;
    }

    // REQUIRES: pieWidth > 0; los not null
    // EFFECTS: pass given stocks data to relevant components, regenerate them, and add them to corresponding
    //          panels
    private void updateDataForTableAndPie(int pieWidth, ListOfStocks los) {
        StockTableModel stockTableModel = new StockTableModel(los);
        getLeftTable().setModel(stockTableModel);
        setStockTableHeaders();

        PieChart pie = new PieChart(pieWidth, los);
        getRightSubPane().getContentPane().removeAll();
        getRightSubPane().add(pie);
        getRightSubPane().updateUI();
    }

    // REQUIRES: row >= 0 and row <= size of current los - 1
    // EFFECTS: create a popup menu when user right-click a table row, and provide menu item to delete one item
    private JPopupMenu createRowPopup(int row) {
        JPopupMenu popup = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Delete");

        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("Row: " + row);
                removeFromTempData(row);
            }
        });
        popup.add(menuItem);

        return popup;
    }

    // REQUIRES: index >= 0 and index <= size of current los - 1
    // EFFECTS: remove the stock at the given index from current temp los, and update the temp file
    private void removeFromTempData(int index) {
        StockDataHandler stockDataHandler = new StockDataHandler();

        Stock removed = this.los.getStocks().get(index);
        this.los.deleteStock(index);

        updateDataForTableAndPie(this.rightWidth, this.los);

        //stockDataHandler.updateCurrentList(this.los, 2, removed);
        //stockDataHandler.writeToTmpFile();
        StockDataSingleton.getInstance().removeStock(index); // TODO: this is new singleton entry
    }

    // This class is a helper class to create a ListSelectionListener that enables row interaction events in the table
    // CITATION: some lines in this class is taken from TableSelectionDemo sample project
    // https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-TableSelectionDemoProject.zip
    private class RowListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            // This conditional is to skip events like the values are being changed
            // if there is no such check, the following code will execute even when loading data
            if (event.getValueIsAdjusting()) {
                return;
            }

            MouseListener popupListener = new PopupListener(createRowPopup(leftTable.getSelectedRow()));
            leftTable.addMouseListener(popupListener);
        }
    }

    // This class is a helper class to create a MouseAdapter that enables a popup menu at the position of a row
    // CITATION: some lines in this class is taken from TableSelectionDemo sample project
    // https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-TableSelectionDemoProject.zip
    private class PopupListener extends MouseAdapter {
        JPopupMenu popup;

        PopupListener(JPopupMenu popupMenu) {
            popup = popupMenu;
        }

        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(),
                        e.getX(), e.getY());
            }
        }
    }
}
