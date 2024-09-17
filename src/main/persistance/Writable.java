package persistance;

import org.json.JSONObject;

//taken from Writable interface in Json Serialization Demo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
