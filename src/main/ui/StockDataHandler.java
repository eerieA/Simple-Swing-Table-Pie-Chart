package ui;

import model.ListOfStocks;
import org.json.JSONException;
import persistence.JsonReader;

import java.io.IOException;

// This class is for handling stocks data from various sources, and providing methods for shared accessing
public class StockDataHandler {
    private static final String JSON_PATH = "./data/ListOfStocks.json";

    private ListOfStocks los;

    // EFFECTS: Instantiate a StockDataHandler with an empty list of stocks
    public StockDataHandler() {
        this.los = new ListOfStocks();
    }

    // EFFECTS: Read info of stocks from designated JSON file and return it as a ListOfStocks
    public ListOfStocks readFromFile() {
        JsonReader reader = new JsonReader(JSON_PATH);
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
}
