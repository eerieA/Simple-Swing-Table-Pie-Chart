package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.StockDataHandler;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// TODO: add more unit tests if have time
public class StockDataHandlerTest {
    private static final String TEST_SAVED_PATH = "./data/TestSDHSavedFile.json";
    //private static final String TEST_TMP_PATH = "./data/TestSDHTmpFile.json";

    private JsonReader savedFile;
    private Stock stock1, stock2;
    private StockDataHandler sdh;

    @BeforeEach
    public void setup() {
        sdh = new StockDataHandler();
        savedFile = new JsonReader(TEST_SAVED_PATH);
        stock1 = new Stock("test1", 1, 100, 2000, 1);
        stock2 = new Stock("test2", 2, 200, 2002, 2);
    }

    @Test
    public void testConstructor() {
        try {
            ListOfStocks savedList = savedFile.read();
            assertEquals(savedList.getStocks().size(), sdh.getCurrentList().getStocks().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testUpdateCurrentListAdd() {
        ListOfStocks los = new ListOfStocks();
        los.addStock(stock1);
        sdh.updateCurrentList(los, 1, stock1);

        assertEquals(1, sdh.getCurrentList().getStocks().size());
        assertTrue(EventLog.getInstance().iterator().next().getDescription().contains(" User added one stock: "));
        assertTrue(EventLog.getInstance().iterator().next().getDescription().contains("test1"));
    }

    @Test
    public void testUpdateCurrentListDelete() {
        ListOfStocks los = new ListOfStocks();
        los.addStock(stock1);
        los.addStock(stock2);
        los.deleteStock(1);

        sdh.updateCurrentList(los, 2, stock2);

        assertEquals(1, sdh.getCurrentList().getStocks().size());
        assertTrue(EventLog.getInstance().iterator().next().getDescription().contains(" User deleted one stock: "));
        assertTrue(EventLog.getInstance().iterator().next().getDescription().contains("test2"));
    }

}
