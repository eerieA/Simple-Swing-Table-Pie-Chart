package ui;

import model.ListOfStocks;

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
    // EFFECTS: creates a Jmenubar with menu items
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
        JMenuItem menuItem2 = createExitMenuItem();
        mainMenu.add(menuItem2);

        return menuBar;
    }

    private JMenuItem createAddNewStockMenuItem() {
        JMenuItem menuItem = new JMenuItem("Add new stock", KeyEvent.VK_A);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Click this to add new stock item");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parentGUI.createAddItemPopup();
            }
        });
        return menuItem;
    }

    private JMenuItem createExitMenuItem() {
        JMenuItem menuItem = new JMenuItem("Exit", KeyEvent.VK_E);
        menuItem.getAccessibleContext().setAccessibleDescription(
                "Click this to exit the program");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Close window");
                parentGUI.dispose();
            }
        });
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
