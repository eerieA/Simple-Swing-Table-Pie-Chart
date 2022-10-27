package persistence;

import model.ListOfStocks;
import model.Stock;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// CITATION: Code here uses lines from sample project JsonSerializationDemo, JsonWriterTest class
public class JsonWriterTest extends JsonTestSuper {

    @Test
    public void testWriterInvalidFile() {
        JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
        try {
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyListOfStocks() {
        try {
            ListOfStocks los = new ListOfStocks();
            JsonWriter writer = new JsonWriter("./data/TestWriterEmptyListOfStocks.json");
            writer.open();
            writer.write(los);
            writer.close();

            JsonReader reader = new JsonReader("./data/TestWriterEmptyListOfStocks.json");
            los = reader.read();
            assertEquals(0, los.getNumOfStocks());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralListOfStocks() {
        try {
            ListOfStocks los = new ListOfStocks();
            los.addStock(new Stock("test1", 4, 500, 2222, 9));
            los.addStock(new Stock("test2", 10, 3000, 1111, 12));
            JsonWriter writer = new JsonWriter("./data/TestWriterGeneralListOfStocks.json");
            writer.open();
            writer.write(los);
            writer.close();

            JsonReader reader = new JsonReader("./data/TestWriterGeneralListOfStocks.json");
            los = reader.read();
            List<Stock> stocks = los.getStocks();
            assertEquals(2, stocks.size());
            checkStock("test1", 4, 500, "9/2222", stocks.get(0));
            checkStock("test2", 10, 3000, "12/1111", stocks.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
