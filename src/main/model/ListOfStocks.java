package model;

import java.util.ArrayList;

// Represents a list of instantiated Stock objects
public class ListOfStocks {
    private ArrayList<Stock> stocks;

    // EFFECTS: initiate a ListOfStocks with an empty ArrayList ready to store Stock objects.
    public ListOfStocks() {
        this.stocks = new ArrayList<Stock>();
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
        stocks.remove(id);
    }

 /*   // MODIFIES: this.
    // EFFECTS: sort all currently stored stocks according to their name,
    //          in alphabetically descending order.
    //          (Stretch goal: add an input to let the user choose ascending or descending.)
    public void sortStocks(){
        //stub
    } */

    // EFFECTS: return all stocks currently in this list.
    public ArrayList<Stock> getStocks() {
        return stocks;
    }
}
