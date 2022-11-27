package model;

import org.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StocksTest extends StockModelTestSuper {
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
        assertEquals(st2.getName(), "APPL");
        assertEquals(st2.getUnitPrice(), 11);
        assertEquals(st2.getInvestedAmount(), 1000);
        assertEquals(st2.getTime(), "4/2202");
        assertEquals(st2.getShares(), 91);
    }

    @Test
    public void testUpdateComplete() {
        // Complete case: every field is updated
        // st1 = new Stock("GOGL", 1, 1, 1900, 1)
        Stock st1_exptd1 = new Stock("GOGL2", 2, 4, 1901, 2);
        assertEquals(st1.update("GOGL2", 2, 4, 1901, 2), 1);
        assertTrue(isIdenticalStocks(st1, st1_exptd1));
    }

    @Test
    public void testUpdateInComplete1() {
        // Incomplete case: only month is not updated
        // st1 = new Stock("GOGL", 1, 1, 1900, 1);
        Stock st1_exptd2 = new Stock("GOGL2", 2, 4, 1901, 1);
        assertEquals(st1.update("GOGL2", 2, 4, 1901, -1), 0);
        assertTrue(isIdenticalStocks(st1, st1_exptd2));
    }

    @Test
    public void testUpdateInComplete2() {
        // Incomplete case: unit price and month are not updated
        // st1 = new Stock("GOGL", 1, 1, 1900, 1);
        Stock st1_exptd2 = new Stock("GOGL2", 1, 4, 1901, 1);
        assertEquals(st1.update("GOGL2", -1, 4, 1901, -1), 0);
        assertTrue(isIdenticalStocks(st1, st1_exptd2));
    }

    @Test
    public void testUpdateInComplete3() {
        // Incomplete case: name, unit price and month are not updated
        // st1 = new Stock("GOGL", 1, 1, 1900, 1);
        Stock st1_exptd2 = new Stock("GOGL", 1, 4, 1901, 1);
        assertEquals(st1.update("", -1, 4, 1901, -1), 0);
        assertTrue(isIdenticalStocks(st1, st1_exptd2));
    }

    @Test
    public void testUpdateFail() {
        // Lower boundary of year, month
        Stock st1_exptd1 = new Stock("GOGL", 1, 1, 1900, 1);
        assertEquals(st1.update("", -1, -1, 1899, 0), 0);
        assertTrue(isIdenticalStocks(st1, st1_exptd1));
        // Upper boundary of year, month
        Stock st3_exptd1 = new Stock("IMUX", 300, 2500000, 9999, 12);
        assertEquals(st3.update("", -1, -1, 10000, 13), 0);
        assertTrue(isIdenticalStocks(st3, st3_exptd1));
    }

    @Test
    public void testSetTime() {
        //this.st1 = new Stock("GOGL", 1, 1, 1900, 1); //month = 1, year = 1900
        // Year and month both in range
        st1.setTime(3, 2011);
        assertEquals(st1.getTime(), "3/2011"); //month = 3, year = 2011
        // Year in range, month not in range
        st1.setTime(0, 3011);
        assertEquals(st1.getTime(), "3/3011"); //month = 3, year = 3011
        // Year not in range, month in range
        st1.setTime(11, 10000);
        assertEquals(st1.getTime(), "11/3011"); //month = 11, year = 3011
        // Year not in range, month not in range
        st1.setTime(20, 10000);
        assertEquals(st1.getTime(), "11/3011"); //month = 11, year = 3011
    }

    @Test
    public void testToJson() {
        JSONArray expected = new JSONArray();
        expected.put(0, "GOGL");
        expected.put(1, 1);
        expected.put(2, 1);
        expected.put(3, 1900);
        expected.put(4, 1);

        assertEquals(expected.getClass(), st1.toJson().getClass());
        assertTrue(isIdenticalJsonStocks(st1.toJson(), expected));
    }

    @Test
    public void testIsIdenticalJSONStocks() {
        JSONArray js1 = new JSONArray();
        js1.put(0, "GOGL");
        js1.put(1, 1);
        js1.put(2, 1);
        js1.put(3, 1900);
        js1.put(4, 1);
        JSONArray js2 = new JSONArray();
        js2.put(0, "GOGL");
        js2.put(1, 1);
        js2.put(2, 1);
        js2.put(3, 1900);
        js2.put(4, 1);
        JSONArray js3 = new JSONArray();
        js3.put(0, "GOGL");
        js3.put(1, 1);
        js3.put(2, 1);
        js3.put(3, 1900);
        js3.put(4, 2);
        JSONArray js4 = new JSONArray();
        js4.put(0, "GOGL");

        assertTrue(isIdenticalJsonStocks(js1, js2));
        assertFalse(isIdenticalJsonStocks(js1, js3));
        assertFalse(isIdenticalJsonStocks(js1, js4));
    }

    // EFFECTS: compare all fields of 2 Stock object; return true if they all equal
    // This is a helper function for tests
    private boolean isIdenticalStocks(Stock st1, Stock st2) {
        boolean compare;
        compare = (st1.getName() == st2.getName()) &&
                (st1.getUnitPrice() == st2.getUnitPrice()) &&
                (st1.getInvestedAmount() == st2.getInvestedAmount()) &&
                (st1.getTime().equals(st2.getTime()));

        return compare;
    }

}