package ui;

import model.ListOfStocks;

import javax.swing.table.AbstractTableModel;

// This class is the model for the table of stocks.
// CITATION: some lines of code is from the TableFTFEditDemo class in
// https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-TableFTFEditDemoProject.zip
public class StockTableModel extends AbstractTableModel {
    private static final String[] columnNames = {
            "Stock name",
            "Unit price $",
            "Invested total $",
            "Shares",
            "Month/Year of investment",
    };

    private ListOfStocks los;

    // EFFECTS: Instantiate a StockTableModel with data read from JSON file
    public StockTableModel() {
        StockDataHandler stockDataHandler = new StockDataHandler();
        this.los = stockDataHandler.readFromSavedFile();
    }

    public void updateTmpData() {
        StockDataHandler stockDataHandler = new StockDataHandler();
        this.los = stockDataHandler.readFromTmpFile();
    }

    /**
     * Returns the number of rows in the model. A
     * <code>JTable</code> uses this method to determine how many rows it
     * should display.  This method should be quick, as it
     * is called frequently during rendering.
     *
     * @return the number of rows in the model
     * @see #getColumnCount
     */
    @Override
    public int getRowCount() {
        return los.getStocks().size();
    }

    /**
     * Returns the number of columns in the model. A
     * <code>JTable</code> uses this method to determine how many columns it
     * should create and display by default.
     *
     * @return the number of columns in the model
     * @see #getRowCount
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    // REQUIRES: Time format in stocks is M/YYYY, especially the one and only "/"
    /**
     * Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param rowIndex    the row whose value is to be queried
     * @param columnIndex the column whose value is to be queried
     * @return the value Object at the specified cell
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return los.getStocks().get(rowIndex).getName();
            case 1:
                return los.getStocks().get(rowIndex).getUnitPrice();
            case 2:
                return los.getStocks().get(rowIndex).getInvestedAmount();
            case 3:
                return los.getStocks().get(rowIndex).getShares();
            case 4:
                return los.getStocks().get(rowIndex).getTime();
            default:
                return 0;
        }
    }

    // EFFECTS: [Class level method] Get the constant columnNames list
    public static String[] getStaticColumnNames() {
        return columnNames;
    }
}
