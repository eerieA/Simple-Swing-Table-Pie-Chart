package model;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListOfStocksTest extends StockModelTestSuper {
    private ListOfStocks los1 = new ListOfStocks();
    private Stock st1;
    private Stock st2;
    private Stock st3;

    @BeforeEach
    public void setup() {
        this.st1 = new Stock("GOGL", 1, 1, 1900, 1);
        this.st2 = new Stock("APPL", 11, 1000, 2202, 4);
        this.st3 = new Stock("IMUX", 300, 2500000, 9999, 12);
    }

    @Test
    public void testConstructor() {
        assertTrue(los1.getStocks().isEmpty());
    }

    @Test
    public void testAddStock() {
        los1.addStock(st1);
        los1.addStock(st2);
        los1.addStock(st3);
        assertEquals(los1.getNumOfStocks(), 3);
        assertEquals(los1.getStocks().get(0).getName(), "GOGL");
        assertEquals(los1.getStocks().get(1).getName(), "APPL");
        assertEquals(los1.getStocks().get(2).getName(), "IMUX");
    }

    @Test
    public void testDeleteStock() {
        los1.addStock(st1);
        los1.addStock(st2);
        los1.addStock(st3);
        assertEquals(los1.getNumOfStocks(), 3);
        los1.deleteStock(0);
        assertEquals(los1.getStocks().get(0).getName(), "APPL");
        assertEquals(los1.getStocks().get(1).getName(), "IMUX");
    }

    @Test
    public void testToJson() {
        JSONArray expected = new JSONArray();
        los1.addStock(st1);
        expected.put(0, "Currently recorded stocks:");
        expected.put(1, los1.stocksToJson());

        assertEquals(expected.getString(0), los1.toJson().getString(0));
        assertTrue(isIdenticalJsonStocksArray(expected.getJSONArray(1), los1.stocksToJson()));
    }

    @Test
    public void testIsIdenticalJsonStocksArray () {
        ListOfStocks expected = new ListOfStocks();
        expected.addStock(st1);
        expected.addStock(st2);
        los1.addStock(st1);
        los1.addStock(st2);

        assertTrue(isIdenticalJsonStocksArray(expected.stocksToJson(), los1.stocksToJson()));
    }

    // EFFECTS: compare all members of 2 ListOFStock objects; return true if they are all equal
    // This is a helper function for tests
    private Boolean isIdenticalJsonStocksArray(JSONArray jsa1, JSONArray jsa2) {
        for (int i = jsa1.length() - 1; i > 1; i--) {
            if (!isIdenticalJsonStocks(jsa1.getJSONArray(i), jsa2.getJSONArray(i))) {
                return false;
            }
        }
        return true;
    }
}