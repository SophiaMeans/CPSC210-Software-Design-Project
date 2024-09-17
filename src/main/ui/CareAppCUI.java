package ui;

import model.Caretaker;
import model.Patient;
import model.Schedule;
import persistance.JsonReader;
import persistance.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CareAppCUI {

    private static final String JSON_STORE = "./data/patient.json";
    private Patient userProfile;
    private Scanner input; //taken from Teller application
    private JsonWriter jsonWriter;  //taken from JsonSerializationDemo
    private JsonReader jsonReader;  //taken from JsonSerializationDemo

    //EFFECTS: runs the care application
    //adapted from the TellerApp method in the teller application
    public CareAppCUI() throws FileNotFoundException {
        runCareApp();
    }

    //MODIFIES: this
    //EFFECTS: processes the user input to create user profile
    private void runCareApp() {
        String command;  //taken from teller application
        initialize(); //taken from teller application

        System.out.println("l -> load your profile");
        System.out.println("n -> create an new profile");

        command = input.next();
        if (command.equals("l")) {
            loadPatientProfile();
            returnToHome();
        } else if (command.equals("n")) {
            newProfile();
            returnToHome();
        }
    }

    //MODIFIES: this
    //EFFECTS: processes the user input and directs it to the correct function
    //adapted from the teller application processCommand method
    private void processHomeCommand(String command) {
        if (command.equals("1")) {
            displayMedPage();
            processMedCommand();
        } else if (command.equals("2")) {
            displayConPage();
            processConCommand();
        } else if (command.equals("3")) {
            displayCarPage();
            processCarCommand();
        } else if (command.equals("4")) {
            displaySchedulePage();
            processScheduleCommand();
        } else if (command.equals("q")) {
            quitAndSave();
        } else {
            System.out.println("Your selection is not valid");
        }
    }

    //EFFECTS: displays home page of options to the user
    //adapted from the teller application displayMenu method
    private void displayHome() {
        System.out.println("Please select an option");
        System.out.println("1 -> My Medications");
        System.out.println("2 -> My Conditions");
        System.out.println("3 -> My Caretakers");
        System.out.println("4 -> Weekly Schedule");
        System.out.println("q -> Quit");
    }

    //EFFECTS: displays page of medication actions to user
    private void displayMedPage() {
        System.out.println("Please select an option");
        System.out.println("1 -> View medications");
        System.out.println("2 -> Add medication");
        System.out.println("3 -> Remove medication");
        System.out.println("q -> Quit");
    }

    //EFFECTS: displays page of condition actions to user
    private void displayConPage() {
        System.out.println("Please select an option");
        System.out.println("1 -> View conditions");
        System.out.println("2 -> Add condition");
        System.out.println("3 -> Remove condition");
        System.out.println("q -> Quit");
    }

    //EFFECTS: displays page of caretaker actions to user
    private void displayCarPage() {
        System.out.println("Please select an option");
        System.out.println("1 -> View caretakers");
        System.out.println("2 -> Add caretaker");
        System.out.println("3 -> Remove caretaker");
        System.out.println("q -> Quit");
    }

    //EFFECTS: displays page of schedule actions to user
    private void displaySchedulePage() {
        System.out.println("Please select an option");
        System.out.println("1 -> Assign a shift");
        System.out.println("2 -> Un-assign a shift");
        System.out.println("3 -> View weekly schedule");
        System.out.println("q -> Quit");
    }

    //MODIFIES: this
    //EFFECTS: processes user input from medication page
    private void processMedCommand() {
        String medCommand = input.next();

        switch (medCommand) {
            case "1":
                System.out.println(userProfile.getMedications());
                returnToHome();
                break;
            case "2":
                addMedication();
                break;
            case "3":
                removeMedication();
                break;
            case "q":
                quitAndSave();
                break;
            default:
                System.out.println("Your selection was not valid");
                displayMedPage();
                processMedCommand();
                break;
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user input from conditions page
    private void processConCommand() {
        String conCommand = input.next();

        switch (conCommand) {
            case "1":
                System.out.println(userProfile.getConditions());
                returnToHome();
                break;
            case "2":
                addCondition();
                break;
            case "3":
                removeCondition();
                break;
            case "q":
                quitAndSave();
                break;
            default:
                System.out.println("Your selection was not valid");
                displayConPage();
                processConCommand();
                break;
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user input from caretaker page
    private void processCarCommand() {
        String carCommand = input.next();

        switch (carCommand) {
            case "1":
                viewCaretakers();
                returnToHome();
                break;
            case "2":
                addCaretaker();
                break;
            case "3":
                removeCaretaker();
                break;
            case "q":
                quitAndSave();
                break;
            default:
                System.out.println("Your selection was not valid");
                displayCarPage();
                processCarCommand();
                break;
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user input from schedule page
    private void processScheduleCommand() {
        String scheduleCommand = input.next();

        switch (scheduleCommand) {
            case "1":
                assignShift();
                break;
            case "2":
                removeShift();
                break;
            case "3":
                viewSchedule();
                break;
            default:
                System.out.println("Your selection was not valid");
                displayMedPage();
                processMedCommand();
                break;
        }
    }

    //MODIFIES: this
    //EFFECTS: removes inputted medication from users list of medications
    private void removeMedication() {
        System.out.println("Enter the name of the medication you would like to remove");
        String toRemove = input.next();

        if (userProfile.getMedications().contains(toRemove)) {
            userProfile.removeMedication(toRemove);
            System.out.println(toRemove + " has been removed from your Medications");
            returnToHome();
        } else {
            System.out.println("Your are not currently taking this medication");
            returnToHome();
        }
    }

    //MODIFIES: this
    //EFFECTS: adds inputted medication to users medications
    private void addMedication() {
        System.out.println("Enter the name of the medication you would like to add");
        String toAdd = input.next();

        if (userProfile.getMedications().contains(toAdd)) {
            System.out.println("You are already taking this medication");
            returnToHome();
        } else {
            userProfile.addMedication(toAdd);
            System.out.println(toAdd + " has been added to your list of medications");
            returnToHome();
        }
    }

    //EFFECTS: displays a list of caretaker names and their corresponding certifications to the user
    private void viewCaretakers() {
        for (Caretaker c : userProfile.getCaretakers()) {
            System.out.println(c.getName() + ", " + c.getCertification());
        }
    }

    //MODIFIES: this
    //EFFECTS: removes inputted caretaker from users caretakers
    private void removeCaretaker() {
        System.out.println("Enter the name of the caretaker you would like to remove");
        String toRemove = input.next();

        for (Caretaker c : userProfile.getCaretakers()) {
            if (toRemove.equals(c.getName())) {
                System.out.println(toRemove + " has been removed from your Caretakers");
                returnToHome();
            }
        }
        System.out.println("You do not currently have a Caretaker named" + toRemove);
        returnToHome();
    }

    //MODIFIES: this
    //EFFECTS: adds inputted caretaker to users caretakers
    private void addCaretaker() {
        System.out.println("Enter the name of the Caretaker you would like to add");
        String name = input.next();
        System.out.println("Enter the certification of " + name);
        String cert = input.next();

        userProfile.addCaretaker(new Caretaker(name, cert));
        System.out.println(name + " has been added to your Caretakers");
        System.out.println("Would you like to add another Caretaker?");
        String repeat = input.next();
        if (repeat.equals("yes")) {
            addCaretaker();
        }
        returnToHome();
    }

    //MODIFIES: this
    //EFFECTS: removes inputted condition from users conditions
    private void removeCondition() {
        System.out.println("Enter the name of the condition you would like to remove");
        String toRemove = input.next();

        if (userProfile.getConditions().contains(toRemove)) {
            userProfile.removeCondition(toRemove);
            System.out.println("The condition " + toRemove + " has been removed from your profile");
            returnToHome();
        } else {
            System.out.println("You do not currently have this condition in your profile");
            returnToHome();
        }
    }

    //MODIFIES: this
    //EFFECTS: adds inputted condition to users conditions
    private void addCondition() {
        System.out.println("Enter the name of the condition you would like to add");
        String toAdd = input.next();

        if (userProfile.getConditions().contains(toAdd)) {
            System.out.println("You already have this condition in your profile");
            returnToHome();
        } else {
            userProfile.addCondition(toAdd);
            System.out.println("The condition" + toAdd + " has been added to your profile");
            returnToHome();
        }
    }

    //EFFECTS: prints a readable list of shifts in the weekly schedule and who they are assigned to
    private void viewSchedule() {
        for (int i = 0; i < 28; i++) {
            Schedule s = userProfile.getSchedule();
            if (s.getShiftTimeString(i).equals("morning from 9am to 12pm")) {
                //INSERT NEW LINE HERE FOR SPACING
                System.out.println(s.getShiftDayString(i));
                System.out.println(s.getShiftTimeString(i) + ": " + s.getWeeklySchedule().get(i));
            } else {
                System.out.println(s.getShiftTimeString(i) + ": " + s.getWeeklySchedule().get(i));
            }
            returnToHome();
        }
    }

    //MODIFIES: this
    //EFFECTS: assigns the specified caretaker to the specified shift only if caretaker is in users list of caretakers
    //and user inputted day and time are valid.
    private void assignShift() {
        System.out.println("Enter the name of the caretaker you would like to assign");
        String name = input.next();
        System.out.println("Enter the day of the shift");
        String day = input.next();

        if (checkValidDay(day)) {
            System.out.println("Enter either morning, afternoon, evening, or night to assign the shift time");
            String time = input.next();
            if (checkValidTime(time)) {
                userProfile.getSchedule().assignShift(day, time, name);
                System.out.println(name + " has been assigned to the " + time + " shift on " + day);
                returnToHome();
            } else {
                System.out.println("Your input was not a valid shift time");
                displaySchedulePage();
                processScheduleCommand();
            }
        } else {
            System.out.println("Your input was not a valid day of the week");
            displaySchedulePage();
            processScheduleCommand();
        }
    }

    private void removeShift() {
        String day;
        String time;

        System.out.println("Enter the day of the shift");
        day = input.next();
        System.out.println("Enter either morning, afternoon, evening, or night to un-assign the shift time");
        time = input.next();

        userProfile.getSchedule().removeShift(day, time);
        System.out.println("The " + time + " shift on " + day + " is now available");
        returnToHome();
    }

    //EFFECTS: checks if the inputted day is a valid day of the week
    private boolean checkValidDay(String day) {
        return day.equals("monday") || day.equals("tuesday") || day.equals("wednesday") || day.equals("thursday")
                || day.equals("friday") || day.equals("saturday") || day.equals("sunday");
    }

    //EFFECTS: checks if the inputted time is a valid shift time: morning, afternoon, evening, or night
    private boolean checkValidTime(String time) {
        return time.equals("morning") || time.equals("afternoon") || time.equals("evening") || time.equals("night");
    }

    //MODIFIES: this
    //EFFECTS: initializes user profile
    //taken from TellerApplication
    private void initialize() {
        input = new Scanner(System.in); //taken from Teller application
        input.useDelimiter("\n"); //taken from Teller application
        jsonWriter = new JsonWriter(JSON_STORE); //taken from JsonSerializationDemo
        jsonReader = new JsonReader(JSON_STORE); //taken from JsonSerializationDemo
    }

    //EFFECTS: saves patient profile and quits application
    private void quitAndSave() {
        savePatientProfile();
        System.out.println("Goodbye!");
    }

    private void newProfile() {
        String name;
        int age;

        System.out.println("Please enter your name");
        name = input.next();
        System.out.println("Please enter your age");
        age = Integer.parseInt(input.next());  //<- AM I ALLOWED TO USE THIS FUNCTION!?

        userProfile = new Patient(name, age);

    }

    //MODIFIES: this
    //EFFECTS: returns user to homepage
    private void returnToHome() {
        String command;
        displayHome();
        command = input.next();
        processHomeCommand(command);
    }

    //EFFECTS: returns user to schedule page
    private void returnToSchedule() {
        displaySchedulePage();
        processScheduleCommand();
    }

    //MODIFIES: this
    //EFFECTS: saves patient profile as JSON file
    //adapted from JsonSerializationDemo WorkRoomApp class
    private void savePatientProfile() {
        try {
            jsonWriter.open();
            jsonWriter.write(userProfile);
            jsonWriter.close();
            System.out.println("Saved " + userProfile.getName() + "'s profile to ./data/patient.json");
        } catch (FileNotFoundException e) {
            System.out.println("Failed to write to file: ./data/patient.json");
        }
    }

    //MODIFIES: this
    //EFFECTS: loads patient profile from JSON file
    //adapted from JsonSerializationDemo WorkRoomApp class
    private void loadPatientProfile() {
        try {
            userProfile = jsonReader.read();
            System.out.println("Loaded " + userProfile.getName() + " from ./data/patient.json");
        } catch (IOException e) {
            System.out.println("Failed to read from file: ./data/patient.json");
        }
    }
}
