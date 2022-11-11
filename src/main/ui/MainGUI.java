package ui;

import javax.swing.*;

// This class assembles the main JFrame and all sub objects, manage them, and provide main entry for
// the entire program
public class MainGUI extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final float LEFTWIDTHRATIO = 0.35f;


    // EFFECTS: creates the main JFrame window affiliated with some children objects,
    //          and assembles them together
    public MainGUI() {
        super("Main Window");

        MenuBarHandler menuBarHandler = new MenuBarHandler(this);
        SplitPanelHandler splitPanelHandler = new SplitPanelHandler(this, Math.round(WIDTH * LEFTWIDTHRATIO));

        //Set some parameters of the window.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH,HEIGHT);

        //Set components of the window
        this.setJMenuBar(menuBarHandler.createMenuBar());
        this.getContentPane().add(splitPanelHandler.getSplitPane());

        //Display the window.
        this.setVisible(true);
    }

    // EFFECTS: provide main entry for the entire program by creating the main GUI
    public static void main(String[] args) {
        new MainGUI();
    }
}
