package model;

import org.json.JSONException;
import persistence.JsonReader;

import java.io.IOException;

public class StockDataSingleton {
    private static StockDataSingleton instance;

    private static final String JSON_PATH = "./data/ListOfStocks.json";
    private ListOfStocks currentList;

    private StockDataSingleton() {
        currentList = readFromFile(JSON_PATH);
    }

    public static StockDataSingleton getInstance() {
        if(instance == null) {
            instance = new StockDataSingleton();
        }
        System.out.println("A StockDataSingleton instance has been called.");

        return instance;
    }

    public void addStock(Stock stock) {
        currentList.addStock(stock);
    }

    public void updateSavedData() {
        currentList = readFromFile(JSON_PATH);
    }

    public void removeStock(int index) {
        currentList.deleteStock(index);
    }

    public ListOfStocks getCurrentLos() {
        return currentList;
    }


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
}
