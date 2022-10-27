package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents an interface of objects whose information can be written into a data file in JSON format
// CITATION: Some code in this class cites lines from sample project JsonSerializationDemo, Writable class
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONArray toJson();
}
