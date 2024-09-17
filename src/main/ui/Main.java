package ui;

import java.io.FileNotFoundException;

public class Main {
    //adapted from JsonSerializationDemo Main class
    public static void main(String[] args) {
        try {
            new CareAppCUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
