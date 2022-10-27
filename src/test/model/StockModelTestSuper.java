package model;

import org.json.JSONArray;

public class StockModelTestSuper {
    // REQUIRES: 2 inputs are all of JSONArray type, and values are all the correct type
    // EFFECTS: compare all fields of 2 Json object converted from Stock; return true if they all equal
    // This is a helper function for tests
    protected Boolean isIdenticalJsonStocks(JSONArray js1, JSONArray js2) {
        if (js1.length() != js2.length()) {
            return false;
        } else {
            Boolean compare = (js1.getString(0).equals(js2.getString(0))) &&
                    (js1.getInt(1) == js2.getInt(1)) &&
                    (js1.getInt(2) == js2.getInt(2)) &&
                    (js1.getInt(3) == (js2.getInt(3))) &&
                    (js1.getInt(4) == (js2.getInt(4)));
            return compare;
        }
    }
}
