package model;

import org.json.JSONArray;
import persistence.Writable;

import java.util.ArrayList;

// Represents a list of instantiated Stock objects
public class ListOfStocks implements Writable {
    private ArrayList<Stock> stocks;

    // EFFECTS: initiate a ListOfStocks with an empty ArrayList ready to store Stock objects.
    public ListOfStocks() {
        this.stocks = new ArrayList<>();
    }

    // REQUIRES: s is not null.
    // MODIFIES: this.
    // EFFECTS: add a stock to the list.
    public void addStock(Stock s) {
        stocks.add(s);
    }

    // MODIFIES: this.
    // EFFECTS: delete an investment item of the given stock name from the list.
    public void deleteStock(int id) {
        Stock removed = stocks.remove(id);
        EventLog.getInstance().logEvent(new Event("Removed one stock: " + removed.getName()));
        EventLog.getInstance().printEvents();
    }

    // EFFECTS: return all stocks currently in this list.
    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    // EFFECTS: return all stocks currently in this list.
    public int getNumOfStocks() {
        return stocks.size();
    }

    // EFFECTS: returns this as JSON array
    // CITATION: Some code in this method cites lines from sample project JsonSerializationDemo,
    //           Workroom.toJson() method
    @Override
    public JSONArray toJson() {
        JSONArray json = new JSONArray();
        json.put(0, "Currently recorded stocks:");
        json.put(1, stocksToJson());
        return json;
    }

    // EFFECTS: returns stocks in this list as a JSON array
    // CITATION: Some code in this method cites lines from sample project JsonSerializationDemo,
    //           Workroom.thingiesToJson() method
    protected JSONArray stocksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Stock s : stocks) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

}
