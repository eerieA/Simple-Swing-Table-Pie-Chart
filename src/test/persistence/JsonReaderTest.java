package persistence;

import model.ListOfStocks;
import model.Stock;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// CITATION: Code here uses lines from sample project JsonSerializationDemo, JsonReaderTest class
public class JsonReaderTest extends JsonTestSuper {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfStocks los = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyListOfStocks() {
        JsonReader reader = new JsonReader("./data/TestReaderEmptyListOfStocks.json");
        try {
            ListOfStocks los = reader.read();
            assertEquals(0, los.getNumOfStocks());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/TestReaderGeneralListOfStocks.json");
        try {
            ListOfStocks los = reader.read();
            List<Stock> stocks = los.getStocks();
            assertEquals(2, stocks.size());
            checkStock("test1", 3, 66, "4/2134", stocks.get(0));
            checkStock("test2", 6, 30, "6/1046", stocks.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
