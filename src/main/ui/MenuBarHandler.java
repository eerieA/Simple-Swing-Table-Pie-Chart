package ui;

import model.ListOfStocks;
import model.StockDataSingleton;
import persistence.StockDataHandler;

import javax.swing.*;
import java.awt.event.*;

// This class manages menu bar GUI component and its sub items, their appearances,
// and their event listeners
public class MenuBarHandler implements ActionListener, ItemListener {
    private MainGUI parentGUI;
    private JMenuBar menuBar;

    // REQUIRES: parentGUI not null
    // EFFECTS: creates a MenuBarHandler affiliated to a parentGUI object,
    //          and give this a Jmenubar object
    public MenuBarHandler(MainGUI parentGUI) {
        this.parentGUI = parentGUI;
        this.menuBar = createMenuBar();
    }

    // MODIFIES: this
    // EFFECTS: creates a JMenubar with necessary menu items
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu mainMenu;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the main menu.
        mainMenu = new JMenu("Main menu");
        mainMenu.getAccessibleContext().setAccessibleDescription(
                "The main menu in this GUI");
        menuBar.add(mainMenu);

        //A group of JMenuItems under main menu
        //This item 1
        JMenuItem menuItem1 = createAddNewStockMenuItem();
        mainMenu.add(menuItem1);

        //This is item 2
        JMenuItem menuItem2 = createSaveFileMenuItem();
        mainMenu.add(menuItem2);

        //This is item 3
        JMenuItem menuItem3 = createLoadFileMenuItem();
        mainMenu.add(menuItem3);

        //This is item 4
        JMenuItem menuItem4 = createExitMenuItem();
        mainMenu.add(menuItem4);

        return menuBar;
    }

    // EFFECTS: create th menu item for adding one new item
    private JMenuItem createAddNewStockMenuItem() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentGUI.createAddItemPopup();
            }
        };
        return createMenuItem("Add new stock", "Click this to add new stock item", listener);
    }

    // EFFECTS: create the menu item for saving current temp list to file
    //TODO: pop up a panel or sth notifying the user about successfully saved to hard drive
    private JMenuItem createSaveFileMenuItem() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StockDataHandler stockDataHandler = new StockDataHandler();
                ListOfStocks los = StockDataSingleton.getInstance().getCurrentLos(); // this is singleton entry
                stockDataHandler.writeToSavedFile(los);
            }
        };

        return createMenuItem("Save to file", "Click this to save current data to file", listener);
    }

    // EFFECTS: create the menu item for loading previously saved list from file
    private JMenuItem createLoadFileMenuItem() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StockDataSingleton.getInstance().updateSavedData(); // this is singleton entry
                parentGUI.updateSavedData();
            }
        };
        return createMenuItem("Load last saved", "Click this to load last saved stock item", listener);
    }

    // EFFECTS: create the menu item for exiting the program entirely
    private JMenuItem createExitMenuItem() {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainGUI.onExit();
            }
        };

        return createMenuItem("Exit", "Click this to exit the program", listener);
    }

    // EFFECTS: create a menu item with given text, accessibility text and action listener
    private JMenuItem createMenuItem(String text, String accText, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(text);

        menuItem.getAccessibleContext().setAccessibleDescription(accText);
        menuItem.addActionListener(listener);

        return menuItem;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * Invoked when an item has been selected or deselected by the user.
     * The code written for this method performs the operations
     * that need to occur when an item is selected (or deselected).
     *
     * @param e the event to be processed
     */
    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
