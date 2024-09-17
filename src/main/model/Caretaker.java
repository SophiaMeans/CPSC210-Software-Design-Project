package model;

import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a caretaker with a name and certification
public class Caretaker implements Writable {

    private String name;
    private String certification;

    //REQUIRES: age > 0
    //MODIFIES: this
    //EFFECTS: creates a new caretaker with the given name and certification.
    public Caretaker(String name, String certification) {
        this.name = name;
        this.certification = certification;
    }

    //EFFECTS: returns true if inputted string is a valid certification, if not returns false
    public boolean validCertification() {
        String cert = this.certification;
        if (cert.equals("cna") || cert.equals("lpn") || cert.equals("rn")) {
            return true;
        }
        return false;
    }

    public String getName() {
        return this.name;
    }

    public String getCertification() {
        return this.certification;
    }

    @Override
    //EFFECTS: converts caretaker object to JSON object
    //adapted from JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("certification", certification);
        return json;
    }
}
