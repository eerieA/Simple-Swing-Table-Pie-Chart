package persistence;

import model.ListOfStocks;
import model.Stock;
import org.json.JSONArray;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads ListOfStocks from JSON data stored in file
// CITATION: Some code in this class cites lines from sample project JsonSerializationDemo, JsonReader class
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ListOfStocks from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfStocks read() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        return parseWorkRoom(jsonArray);
    }

    // EFFECTS: reads source file as string and returns it
    // CITATION: This method cites sample project JsonSerializationDemo, JsonReader.readFile() method
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ListOfStocks from JSON array and returns it
    private ListOfStocks parseWorkRoom(JSONArray jsonArray) {
        ListOfStocks los = new ListOfStocks();
        addStocks(los, jsonArray);
        return los;
    }

    // MODIFIES: los
    // EFFECTS: parses stocks from JSON array and adds them to workroom
    private void addStocks(ListOfStocks los, JSONArray ja) {
        JSONArray jsonArray = ja.getJSONArray(1);
        for (Object json : jsonArray) {
            JSONArray nextStock = (JSONArray) json;
            addAStock(los, nextStock);
        }
    }

    // MODIFIES: los
    // EFFECTS: parses a stock from JSON array and adds it to list of stocks
    private void addAStock(ListOfStocks los, JSONArray jsonArray) {
        String n = jsonArray.getString(0);
        int up = jsonArray.getInt(1);
        int ia = jsonArray.getInt(2);
        int year = jsonArray.getInt(3);
        int month = jsonArray.getInt(4);
        Stock stock = new Stock(n, up, ia, year, month);
        los.addStock(stock);
    }
}
