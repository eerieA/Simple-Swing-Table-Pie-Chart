package ui;

import javax.swing.*;
import java.awt.*;

// This class manages split panel GUI component and its sub objects, their appearances,
// and their event listeners
public class SplitPanelHandler {
    private JFrame parentGUI;
    private JSplitPane splitPane;
    private JScrollPane leftSubPane;
    private JScrollPane rightSubPane;
    private JTable leftTable;

    // REQUIRES: parentGUI not null
    // EFFECTS: creates a MenuBarHandler affiliated to a parentGUI object,
    //          give this some appropriate sub objects, and set their appearances
    public SplitPanelHandler(JFrame parentGUI, int leftWidth) {
        this.parentGUI = parentGUI;

        leftTable = new JTable(new TableModel());
        leftSubPane = new JScrollPane(leftTable);
        rightSubPane = new JScrollPane();
        leftSubPane.setMinimumSize(new Dimension(leftWidth, 50));

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSubPane, rightSubPane);
    }

    // EFFECTS: get the SplitPane
    public JSplitPane getSplitPane() {
        return splitPane;
    }
}
