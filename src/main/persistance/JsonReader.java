package persistance;

import model.Caretaker;
import model.Patient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//represents a reader that reads a patient from JSON data stored in file
//adapted from JsonReader class in JsonSerializationDemo
public class JsonReader {
    private String source; //taken from JsonSerializationDemo

    //EFFECTS: constructs a reader to read from source file
    //taken from JsonSerializationDemo
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads patient from file and returns it
    //throws IOException if an error occurs reading data from file
    //adapted from JsonSerializationDemo JsonReader class, read()

    public Patient read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePatient(jsonObject);
    }

    //EFFECTS: reads source file as string and return it
    //taken from JsonSerializationDemo JsonReader class, readFile()
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        //I HAVE NO IDEA WHAT THIS TRY DOES
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    //EFFECTS: parses patient from JSON object and returns it
    //adapted from JsonSerializationDemo JsonReader class parseWorkRoom()
    private Patient parsePatient(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Integer age = jsonObject.getInt("age");
        Patient patient = new Patient(name, age);
        addConditions(patient, jsonObject);
        addMedications(patient, jsonObject);
        addCaretakers(patient, jsonObject);
        addSchedule(patient, jsonObject);
        return patient;
    }

    //EFFECTS: parses schedule from JSON object and adds it to patient
    //adapted from JsonSerializationDemo JsonReader class, addThingies() and addThingy()
    private void addSchedule(Patient patient, JSONObject jsonObject) {
        JSONArray weeklySchedule = jsonObject.getJSONArray("schedule");
        for (int i = 0; i < 28; i++) {
            int acc = 0;
            //JSONObject nextShift = (JSONObject) s;
            String shift = (String) weeklySchedule.get(i);//.getString("shift");
            patient.getSchedule().getWeeklySchedule().set(i, shift);
        }
    }

    //MODIFIES: patient
    //EFFECTS: parses caretakers from JSON object and adds them to patient
    //adapted from JsonSerializationDemo JsonReader class, addThingies()
    private void addCaretakers(Patient patient, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("caretakers");
        for (Object json : jsonArray) {
            JSONObject nextCaretaker = (JSONObject) json; //taken from JsonSerializationDemo
            addCaretaker(patient, nextCaretaker);
        }
    }

    //MODIFIES: patient
    //EFFECTS: parses medications as strings from JSON object and adds them to patient
    //adapted from JsonSerializationDemo JsonReader class, addThingies() and addThingy()
    private void addMedications(Patient patient, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("medications");
        for (Object json : jsonArray) {
            String med = (String) json;
            patient.addMedication(med);
        }
    }

    //MODIFIES: patient
    //EFFECTS: parses conditions as strings from JSON object and adds them to patient
    //adapted from JsonSerializationDemo JsonReader class, addThingies() and addThingy()
    private void addConditions(Patient patient, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("conditions");
        for (Object json : jsonArray) {
            String con = (String) json;
            patient.addCondition(con);
        }
    }

    //MODIFIES: patient
    //EFFECTS: parses caretaker from JSON object and adds it to patient
    //adapted from JsonSerializationDemo
    private void addCaretaker(Patient patient, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String cert = jsonObject.getString("certification");
        Caretaker caretaker = new Caretaker(name, cert);
        patient.addCaretaker(caretaker);
    }
}
