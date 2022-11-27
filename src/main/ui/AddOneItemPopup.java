package ui;

import model.ListOfStocks;
import model.Stock;
import persistence.StockDataHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// This class manages the creation and organization of an "add new item" pop-up like JFrame
public class AddOneItemPopup extends JFrame {
    private static final int RESERVEDHEIGHT = 60; //Height reserved for button and frames, recommend >= 60
    private static final int BUTTONHEIGHT = 20; //Height reserved for button and frames, recommend >= 20

    private MainGUI parentGUI;
    private Dimension dimension;
    private StockDataHandler stockDataHandler;

    // EFFECTS: instantiate a AddOneItemPopup, set its parent GUI, size, location, default behaviours
    //          and necessary children components
    public AddOneItemPopup(MainGUI parentGUI, int width, int height, Dimension position) {
        super("Add a new stock item");

        this.parentGUI = parentGUI;
        this.stockDataHandler = new StockDataHandler();

        this.dimension = new Dimension(width, height);
        this.setSize(dimension);
        this.setLocation(position.width, position.height);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        createSplitPane(this.dimension.height);

        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Set up panels, text fields, etc. for this window
    private void createSplitPane(int parentHeight) {
        AddOneItemPopup parent = this;
        Dimension upperPaneSize = new Dimension(0, parentHeight - RESERVEDHEIGHT - BUTTONHEIGHT);
        ArrayList<JTextField> txtFields;

        JPanel upperPane = new JPanel();
        JButton button = new JButton("Add");
        txtFields = setupTextFields();
        addTextFieldsToPane(upperPane, txtFields);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOneStockFromFields(txtFields);
                parent.dispose();
            }

        });
        upperPane.setMinimumSize(upperPaneSize);
        upperPane.setVisible(true);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, upperPane, button);
        splitPane.setVisible(true);

        this.add(splitPane);
    }

    // EFFECTS: add all necessary JTextField input fields into an array in certain order, and return the array
    //          these are for one stock
    private ArrayList<JTextField> setupTextFields() {
        ArrayList<JTextField> txtFields = new ArrayList<>();

        JTextField txtFieldName = new JTextField("Input stock name");
        JTextField txtFieldUnitPrice = new JTextField("Input unit price (>0)");
        JTextField txtFieldInvestAmount = new JTextField("Input invested amount (>0)");
        JTextField txtFieldMonth = new JTextField("Input month [1-12]");
        JTextField txtFieldYear = new JTextField("Input year [1900-9999]");

        txtFields.add(txtFieldName);
        txtFields.add(txtFieldUnitPrice);
        txtFields.add(txtFieldInvestAmount);
        txtFields.add(txtFieldMonth);
        txtFields.add(txtFieldYear);

        return txtFields;
    }

    // MODIFIES: the given JPanel
    // EFFECTS: add every JTextField in given array to the given JPanel in certain order
    private void addTextFieldsToPane(JPanel pane, ArrayList<JTextField> txtFields) {
        if (txtFields.size() == 0) {
            System.out.println("txtFields sent to addTextFieldsToPane() is empty.");
        } else {
            for (JTextField field: txtFields) {
                pane.add(field);
            }
            pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        }
    }

    // EFFECTS: read strings from every JTextField in the given array, and return an ArrayList<String>
    //          storing the strings in certain order
    private ArrayList<String> collectTextFields(ArrayList<JTextField> fields) {
        ArrayList<String> strings = new ArrayList<>();

        for (JTextField field: fields) {
            strings.add(field.getText());
        }

        return strings;
    }

    // MODIFIES: this; the current MainPanelHandler (under parentGUI)
    // EFFECTS: collect inputted strings from the text fields and convert them to one stock object,
    //          add the new stock item to a temp file,
    //          and request relevant components to update data display.
    private void addOneStockFromFields(ArrayList<JTextField> txtFields) {
        ArrayList<String> strings = collectTextFields(txtFields);
        Stock stock = convertToStock(strings);

        ListOfStocks los = parentGUI.getTmpDataFromTable();
        los.addStock(stock);

        stockDataHandler.updateCurrentList(los, 1, stock);
        stockDataHandler.writeToTmpFile();
        parentGUI.updateTmpData();
    }

    // EFFECTS: convert array of strings to one stock object
    private Stock convertToStock(ArrayList<String> collectedStrings) {
        Stock stock = new Stock("null", -1, -1, -1, -1);

        if (!collectedStrings.get(0).equals("")) {
            stock.setName(collectedStrings.get(0));
        }

        stock.setUnitPrice(safeParseIntegerForStock(collectedStrings.get(1)));
        stock.setInvestedAmount(safeParseIntegerForStock(collectedStrings.get(2)));
        stock.setTime(safeParseIntegerForStock(collectedStrings.get(3)),
                safeParseIntegerForStock(collectedStrings.get(4)));

        return stock;
    }

    // EFFECTS: Try to parse strings for stock related numbers into integers;
    //          if succeeds, return the integer, and if not, return -1;
    //          catch NumberFormatException.
    private int safeParseIntegerForStock(String s) {
        int result = -1;

        try {
            result = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            //System.out.println("Illegal input in AddOneItemPopup :: convertToStock()");
        }

        return result;
    }
}
