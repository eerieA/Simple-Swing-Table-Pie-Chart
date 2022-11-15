package ui;

import javax.swing.*;
import java.awt.*;

// This class assembles the main JFrame and all sub objects, manage them, and provide main entry for
// the entire program
public class MainGUI extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public static final float LEFTPANELMINRATIO = 0.55f;

    private Dimension scrSize;
    private MainPanelHandler mainPanelHandler;

    // EFFECTS: creates the main JFrame window affiliated with some children objects,
    //          and assembles them together
    public MainGUI() {
        super("Main Window");

        this.scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension centerPosition = new Dimension((scrSize.width - WIDTH) / 2,
                (scrSize.height - HEIGHT) / 2);
        MenuBarHandler menuBarHandler = new MenuBarHandler(this);
        this.mainPanelHandler = new MainPanelHandler(this,
                Math.round(WIDTH * LEFTPANELMINRATIO), Math.round(WIDTH * (1 - LEFTPANELMINRATIO)));

        //Set some parameters of the window.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH,HEIGHT);

        //Set components of the window
        this.setJMenuBar(menuBarHandler.createMenuBar());
        this.getContentPane().add(mainPanelHandler.getSplitPane());

        //Display the window.
        this.setLocation(centerPosition.width, centerPosition.height);
        this.setVisible(true);
        this.setResizable(false);
    }

    public void createAddItemPopup() {
        int width = WIDTH / 2;
        int height = HEIGHT / 2;
        Dimension position = new Dimension((this.scrSize.width - width) / 2,
                (this.scrSize.height - height) / 2);

        AddOneItemPopup addOneItemPopup = new AddOneItemPopup(this, width, height, position);
    }

    public void updateTableData() {
        StockTableModel stockTableModel = new StockTableModel();
        stockTableModel.updateTmpData();
        mainPanelHandler.getLeftTable().setModel(stockTableModel);
        mainPanelHandler.setStockTableHeaders();
    }

    // EFFECTS: provide main entry for the entire program by creating the main GUI
    public static void main(String[] args) {
        new MainGUI();
    }
}
