package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.StockDataHandler;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class StockDataHandlerTest {
    private static final String JSON_PATH = "./data/ListOfStocks.json";
    private static final String JSON_TMP_PATH = "./data/tmp.json";
    //private static final String JSON_TMP_PATH_FOR_TEST = "./data/TestSDHTmpFile.json";

    private JsonReader savedFile;
    private JsonReader tmpFile;
    private Stock stock1, stock2;
    private StockDataHandler sdh;

    @BeforeEach
    public void setup() {
        sdh = new StockDataHandler();
        savedFile = new JsonReader(JSON_PATH);
        tmpFile = new JsonReader(JSON_TMP_PATH);

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
    public void testReadFromSavedFile() {
        try {
            ListOfStocks savedList = savedFile.read();
            sdh.readFromTmpFile();

            assertEquals(savedList.getStocks().size(), sdh.getCurrentList().getStocks().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReadFromTmpFile() {
        try {
            ListOfStocks tmpList = tmpFile.read();
            ListOfStocks sdhTmpList = sdh.readFromTmpFile();

            assertEquals(tmpList.getStocks().size(), sdhTmpList.getStocks().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testWriteToTmpFile() {
        ListOfStocks tmpList = new ListOfStocks();
        tmpList.getStocks().add(stock1);
        tmpList.getStocks().add(stock2);

        sdh.updateCurrentList(tmpList, 1, stock2);
        sdh.writeToTmpFile();

        try {
            ListOfStocks tmpListToCompare = tmpFile.read();
            assertEquals(tmpListToCompare.getStocks().size(), sdh.getCurrentList().getStocks().size());
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
        //assertTrue(EventLog.getInstance().iterator().next().getDescription().contains("test1"));
    }

    @Test
    public void testUpdateCurrentListDelete() {
        ListOfStocks los = new ListOfStocks();
        los.addStock(stock1);
        los.addStock(stock2);
        los.deleteStock(1);

        sdh.updateCurrentList(los, 2, stock2);

        assertEquals(1, sdh.getCurrentList().getStocks().size());
    }

}
