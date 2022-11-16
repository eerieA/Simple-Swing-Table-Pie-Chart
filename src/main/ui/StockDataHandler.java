package ui;

import model.ListOfStocks;
import model.Stock;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

// This class is for handling stocks data from various sources, and providing methods for shared accessing
public class StockDataHandler {
    private static final String JSON_PATH = "./data/ListOfStocks.json";
    private static final String JSON_TMP_PATH = "./data/tmp.json";

    private ListOfStocks currentList;

    // EFFECTS: Instantiate a StockDataHandler with previously saved list of stocks
    public StockDataHandler() {
        this.currentList = readFromSavedFile();
    }

    // EFFECTS: Read info of stocks from previously saved JSON file and return it as a ListOfStocks
    public ListOfStocks readFromSavedFile() {
        return readFromFile(JSON_PATH);
    }

    // EFFECTS: Read info of stocks from temporarily saved JSON file and return it as a ListOfStocks
    public void writeToSavedFile(ListOfStocks newLos) {
        writToFile(JSON_PATH, newLos);
    }

    // EFFECTS: Read info of stocks from temporarily saved JSON file and return it as a ListOfStocks
    public ListOfStocks readFromTmpFile() {
        return readFromFile(JSON_TMP_PATH);
    }

    // EFFECTS: Read info of stocks from temporarily saved JSON file and return it as a ListOfStocks
    public void writeToTmpFile() {
        writToFile(JSON_TMP_PATH, currentList);
    }

    public void clearTmpFile() {
        writToFile(JSON_TMP_PATH, new ListOfStocks());
    }

    // MODIFIES: this
    // EFFECTS: add a stock to the list of stocks
    public void addStockToCurrentList(Stock stock) {
        if (stock != null) {
            this.currentList.addStock(stock);
        }
    }

    public void setCurrentList(ListOfStocks los) {
        this.currentList = los;
    }

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
