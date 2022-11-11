package ui;

import javax.swing.table.AbstractTableModel;

// This class is the model for the table of stocks.
// CITATION: some lines of code is from the TableFTFEditDemo class in
// https://docs.oracle.com/javase/tutorial/uiswing/examples/zipfiles/components-TableFTFEditDemoProject.zip
public class TableModel extends AbstractTableModel {
    private String[] columnNames = {"Stock name",
            "unit price $",
            "invested total $",
            "shares",
            "month",
            "year of investment",
    };
    private Object[][] data = {
            {"test1", 10, 4900, 490, 2, 2087},
            {"test2", 20, 500, 25, 12, 9998}
    };

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
        return data.length;
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
        return data[rowIndex][columnIndex];
    }
}
