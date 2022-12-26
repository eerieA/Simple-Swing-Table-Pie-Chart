package ui;

import model.EventLog;
import model.ListOfStocks;
import model.Stock;
import model.StockDataSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new MainCloseListener());
        this.setSize(WIDTH,HEIGHT);

        //Set components of the window
        this.setJMenuBar(menuBarHandler.createMenuBar());
        this.getContentPane().add(mainPanelHandler.getSplitPane());

        //Display the window.
        this.setLocation(centerPosition.width, centerPosition.height);
        this.setVisible(true);
        this.setResizable(false);
    }

    // EFFECTS: create AddItemPopup so that user can add one new stock item
    public void createAddItemPopup() {
        int width = WIDTH / 2;
        int height = HEIGHT / 2;
        Dimension position = new Dimension((this.scrSize.width - width) / 2,
                (this.scrSize.height - height) / 2);

        AddOneItemPopup addOneItemPopup = new AddOneItemPopup(this, width, height, position);
    }

    public ListOfStocks getTmpDataFromTable() {
        return mainPanelHandler.getCurrentList();
    }

    // EFFECTS: pass along request to update temp stock data to mainPanelHandler
    public void updateTmpData() {
        mainPanelHandler.updateTmpData();
    }

    // EFFECTS: pass along request to load saved stock data to mainPanelHandler
    public void updateSavedData() {
        mainPanelHandler.updateSavedData();
    }

    // EFFECTS: provide main entry for the entire program by creating the main GUI
    public static void main(String[] args) {
        new MainGUI();

        //System.out.println(StockDataSingleton.getInstance().getCurrentLos().getNumOfStocks());

        //Stock tmp = new Stock("singletontest", 3, 4500, 2341, 6);
        //StockDataSingleton.getInstance().addStock(tmp);
        //System.out.println(StockDataSingleton.getInstance().getCurrentLos().getNumOfStocks());
    }

    protected static void onExit() {
        System.out.println("Closing..." + "\n");
        System.out.println("Logged events: ");
        EventLog.getInstance().iterator().forEachRemaining(event ->
                System.out.println(event.getDescription()));
        System.exit(0);
    }

    private static class MainCloseListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            MainGUI.onExit();
        }
    }
}
