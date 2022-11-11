package ui;

import javax.swing.*;
import java.awt.event.*;

// This class manages menu bar GUI component and its sub items, their appearances,
// and their event listeners
public class MenuBarHandler implements ActionListener, ItemListener {
    private JFrame parentGUI;
    private JMenuBar menuBar;

    // REQUIRES: parentGUI not null
    // EFFECTS: creates a MenuBarHandler affiliated to a parentGUI object,
    //          and give this a Jmenubar object
    public MenuBarHandler(JFrame parentGUI) {
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

        //a group of JMenuItems under main menu
        JMenuItem menuItem1 = new JMenuItem("Exit", KeyEvent.VK_T);
        menuItem1.getAccessibleContext().setAccessibleDescription(
                "This should exit the program");
        menuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Close window");
                parentGUI.dispose();
            }
        });
        mainMenu.add(menuItem1);

        return menuBar;
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        ;
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
        ;
    }
}
