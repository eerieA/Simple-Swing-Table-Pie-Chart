package persistence;

import model.Event;
import model.EventLog;
import model.ListOfStocks;
import model.Stock;
import org.json.JSONException;

import java.io.IOException;
import java.util.Calendar;

// This class is for handling stocks data from persistence files, and providing methods for shared accessing
public class StockDataHandler {
    private static final String JSON_PATH = "./data/ListOfStocks.json";
    private static final String JSON_TMP_PATH = "./data/tmp.json";

    private ListOfStocks currentList;

    // EFFECTS: Instantiate a StockDataHandler with previously saved list of stocks
    public StockDataHandler() {
        EventLog.getInstance().logEvent(new Event("System loading initial list..."));
        this.currentList = readFromSavedFile();
    }

    // EFFECTS: Read info of stocks from previously saved JSON file and return it as a ListOfStocks
    public ListOfStocks readFromSavedFile() {
        EventLog.getInstance().logEvent(new Event("System reading saved list.."));
        return readFromFile(JSON_PATH);
    }

    // EFFECTS: Write given info of stocks to previously saved JSON file and return it as a ListOfStocks
    public void writeToSavedFile(ListOfStocks newLos) {
        writToFile(JSON_PATH, newLos);
    }

    /*
    // EFFECTS: Read info of stocks from temporarily saved JSON file and return it as a ListOfStocks
    public ListOfStocks readFromTmpFile() {
        EventLog.getInstance().logEvent(new Event("System reading temp list.."));
        return readFromFile(JSON_TMP_PATH);
    }

    // EFFECTS: Write info of stocks to temporarily saved JSON file and return it as a ListOfStocks
    public void writeToTmpFile() {
        writToFile(JSON_TMP_PATH, currentList);
    }

    // EFFECTS: clear the temp file by writing an empty ListOfStocks into it
    public void clearTmpFile() {
        writToFile(JSON_TMP_PATH, new ListOfStocks());
    }*/

    /*
    // REQUIRES: stock not null
    // MODIFIES: this
    // EFFECTS: add or delete a stock to the list of stocks
    public void updateCurrentList(ListOfStocks los, int op, Stock stock) {
        // op = 1 means add a stock; op = 2 means delete a stock
        this.currentList = los;

        switch (op) {
            case 1:
                EventLog.getInstance().logEvent(new Event(Calendar.getInstance().getTimeInMillis()
                        + " Since user requested adding stock: " + stock.getName()));
                break;
            default:
                EventLog.getInstance().logEvent(new Event(Calendar.getInstance().getTimeInMillis()
                        + " Since user requested deleting stock: " + stock.getName()));
                break;
        }
    }*/

    // EFFECTS: return the current temporary list of stocks
    public ListOfStocks getCurrentList() {
        return currentList;
    }

    // EFFECTS: Read info of stocks from designated JSON file and return it as a ListOfStocks
    private ListOfStocks readFromFile(String file) {
        JsonReader reader = new JsonReader(file);
        ListOfStocks los = new ListOfStocks();

        try {
            los = reader.read();
        } catch (IOException e) {
            System.out.println("IOException: Couldn't read from file.");
        } catch (JSONException e) {
            System.out.println("JSONException: No data read from file.");
        }

        return los;
    }

    // REQUIRES: los not empty
    // MODIFIES: file
    // EFFECTS: Write info of stocks to designated JSON file
    private void writToFile(String file, ListOfStocks los) {
        JsonWriter writer = new JsonWriter(file);

        try {
            writer.open();
            writer.write(los);
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException: Couldn't write to file.");
        }
    }
}
