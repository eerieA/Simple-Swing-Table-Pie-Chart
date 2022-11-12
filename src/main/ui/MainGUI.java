package ui;

import javax.swing.*;
import java.awt.*;

// This class assembles the main JFrame and all sub objects, manage them, and provide main entry for
// the entire program
public class MainGUI extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;
    public static final float LEFTPANELMINRATIO = 0.55f;


    // EFFECTS: creates the main JFrame window affiliated with some children objects,
    //          and assembles them together
    public MainGUI() {
        super("Main Window");

        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
        MenuBarHandler menuBarHandler = new MenuBarHandler(this);
        SplitPanelHandler splitPanelHandler = new SplitPanelHandler(this,
                Math.round(WIDTH * LEFTPANELMINRATIO), Math.round(WIDTH * (1 - LEFTPANELMINRATIO)));

        //Set some parameters of the window.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH,HEIGHT);

        //Set components of the window
        this.setJMenuBar(menuBarHandler.createMenuBar());
        this.getContentPane().add(splitPanelHandler.getSplitPane());

        //Display the window.
        this.setLocation((scrSize.width - WIDTH) / 2,(scrSize.height - HEIGHT) / 2);
        this.setVisible(true);
        this.setResizable(false);
    }

    // EFFECTS: provide main entry for the entire program by creating the main GUI
    public static void main(String[] args) {
        new MainGUI();
    }
}
