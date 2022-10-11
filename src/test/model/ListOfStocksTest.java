package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListOfStocksTest {
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
        assertEquals(los1.getStocks().size(), 3);
        assertEquals(los1.getStocks().get(0).getName(), "GOGL");
        assertEquals(los1.getStocks().get(1).getName(), "APPL");
        assertEquals(los1.getStocks().get(2).getName(), "IMUX");
    }

    @Test
    public void testDeleteStock() {
        los1.addStock(st1);
        los1.addStock(st2);
        los1.addStock(st3);
        assertEquals(los1.getStocks().size(), 3);
        los1.deleteStock(0);
        assertEquals(los1.getStocks().get(0).getName(), "APPL");
        assertEquals(los1.getStocks().get(1).getName(), "IMUX");
    }
}