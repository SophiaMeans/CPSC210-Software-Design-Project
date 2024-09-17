package persistance;

import model.Event;
import model.EventLog;
import model.Patient;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// represents a writer that writes a Json representation of a patient to file
//adapted from JsonWriter class in JsonSerializationDemo
public class JsonWriter {
    private PrintWriter writer;  //taken from JsonSerializationDemo
    private String destination; //taken from JsonSerializationDemo

    // EFFECTS: constructs writer to write to destination file
    //taken from JsonSerializationDemo
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    //taken from JsonSerializationDemo
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of a patient to file
    //adapted from JsonSerializationDemo
    public void write(Patient p) {
        JSONObject json = p.toJson();
        saveToFile(json.toString());
        EventLog.getInstance().logEvent(new Event("Saved " + p.getName() + "'s profile"));
    }

    //MODIFIES: this
    //EFFECTS: closes writer
    //taken from JsonSerializationDemo
    public void close() {
        writer.close();
    }

    //MODIFIES: this
    //EFFECTS: writes string to file
    //taken from JsonSerializationDemo
    private void saveToFile(String json) {
        writer.print(json);
    }

}
