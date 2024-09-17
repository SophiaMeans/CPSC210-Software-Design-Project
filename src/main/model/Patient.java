package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.List;

// Represent a patient with a name, age, list of conditions, list of medications, list of caretakers, and a weekly
// schedule
public class Patient implements Writable {

    private String name;
    private int age;
    private List<String> conditions;
    private List<String> medications;
    private List<Caretaker> caretakers;
    private Schedule schedule;

    // REQUIRES: age > 0
    // EFFECTS: constructs a patient with the given name, age, empty conditions, empty medications,
    // and empty caretakers.
    public Patient(String name, int age) {
        this.name = name;
        this.age = age;
        this.conditions = new ArrayList<>();
        this.medications = new ArrayList<>();
        this.caretakers = new ArrayList<>();
        this.schedule = new Schedule();
        EventLog.getInstance().logEvent(new Event(name + "'s profile was created"));
    }

    // MODIFIES: this
    // EFFECTS: adds the given medication to the patients list of medications.
    public void addMedication(String newMed) {
        this.medications.add(newMed);
    }

    //REQUIRES: the given medication is present in the patients medications
    //MODIFIES: this
    //EFFECTS: removes the given medication f   rom the patients medications
    public void removeMedication(String oldMed) {
        this.medications.remove(oldMed);
    }

    //MODIFIES: this
    //EFFECTS: adds the given condition to the patients conditions
    public void addCondition(String newCondition) {
        this.conditions.add(newCondition);
    }

    //REQUIRES: the given condition is present in the patients conditions
    //MODIFIES: this
    //EFFECTS: removes the given condition from the patients conditions
    public void removeCondition(String oldCondition) {
        this.conditions.remove(oldCondition);
    }

    //MODIFIES: this
    //EFFECTS: adds the given caretaker to the patients caretakers
    public void addCaretaker(Caretaker newCaretaker) {
        //could take 2 strings as parameters and create new caretaker in this function
        this.caretakers.add(newCaretaker);
        EventLog.getInstance().logEvent(new Event("Added " + newCaretaker.getName() + " to caretakers"));
    }

    //REQUIRES: the given caretaker is present in the patients caretakers
    //MODIFIES: this
    //EFFECTS: removes the given caretaker from the patients caretakers
    public void removeCaretaker(Caretaker oldCaretaker) {
        this.caretakers.remove(oldCaretaker);
        EventLog.getInstance().logEvent(new Event("removed " + oldCaretaker.getName() + " from caretakers"));
    }

    //MODIFIES: this
    //EFFECTS: removes caretaker at inputted index.
    public void removeCaretaker(int oldCaretaker, String name) {
        this.caretakers.remove(oldCaretaker);
        EventLog.getInstance().logEvent(new Event("removed " + name + " from caretakers"));
    }

    //EFFECTS: returns list of medications
    public List<String> getMedications() {
        return this.medications;
    }

    //EFFECTS: returns list of conditions
    public List<String> getConditions() {
        return this.conditions;
    }

    //EFFECTS: returns list of caretakers
    public List<Caretaker> getCaretakers() {
        return this.caretakers;
    }

    //EFFECTS: returns name of patient
    public String getName() {
        return this.name;
    }

    //EFFECTS: returns age of patient
    public int getAge() {
        return this.age;
    }

    //EFFECTS: returns schedule for a patient
    public Schedule getSchedule() {
        return this.schedule;
    }

    @Override
    //EFFECTS: converts Patient object to JSON object
    //adapted from JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("age", age);
        json.put("conditions", conditionsToJson());
        json.put("medications", medicationsToJson());
        json.put("caretakers", caretakersToJson());
        json.put("schedule", scheduleToJson());
        return json;
    }

    //EFFECTS: returns patients weekly schedule as JSON Array
    //adapted from JsonSerializationDemo, workroom class, thingiesToJson()
    private JSONArray scheduleToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Object s: schedule.getWeeklySchedule()) {
            jsonArray.put(s);
        }
        return jsonArray;
    }

    //EFFECTS: returns conditions in the patient as JSON array
    //adapted from JsonSerializationDemo, workroom class, thingiesToJson()
    private JSONArray conditionsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String c : conditions) {
            jsonArray.put(c);
        }
        return jsonArray;
    }

    //EFFECTS: returns conditions in the patient as JSON array
    //adapted from JsonSerializationDemo, workroom class, thingiesToJson()
    private JSONArray medicationsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String m : medications) {
            jsonArray.put(m);
        }
        return jsonArray;
    }


    //EFFECTS: returns conditions in the patient as JSON array
    //adapted from JsonSerializationDemo, workroom class, thingiesToJson()
    public JSONArray caretakersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Caretaker c : caretakers) {
            jsonArray.put(c.toJson());
        }
        return jsonArray;
    }
}
