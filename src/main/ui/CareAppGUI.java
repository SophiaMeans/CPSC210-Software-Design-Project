package ui;

import model.EventLog;
import model.Patient;
import persistance.JsonReader;
import persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

//Represents the graphical user interface for CareApp
//main source for all JSwing research: https://docs.oracle.com/javase/tutorial/uiswing/components/index.html
public class CareAppGUI extends JFrame {

    private static final String JSON_STORE = "./data/patient.json"; //from CareAppCUI
    private Patient user;
    private JsonWriter jsonWriter;  //taken from JsonSerializationDemo
    private JsonReader jsonReader;  //taken from JsonSerializationDemo

    private JFrame frame;
    private JTabbedPane tabbedPane;
    private ProfileDialog profileDialog;

    private JComponent homeTab;
    private JComponent scheduleTab; //= new ScheduleTab();
    private JComponent caretakersTab;
    private JComponent medicationsTab;  // = new MedicationsTab();
    private JComponent conditionsTab; // = new ConditionsTab();
    private JButton saveButton = new JButton("Save Profile");



    //MODIFIES: this
    //EFFECTS: creates main frame and popup window to process user input before loading main panel
    //based off TabbedPaneDemo.java and https://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html
    public CareAppGUI() throws FileNotFoundException {
        initialize();
        loadPatientProfile();

        frame = new JFrame("Caretaker Application");
        frame.addWindowListener(new CloseActionListener());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        profileDialog = new ProfileDialog(frame, user);
        profileDialog.setVisible(true);
        profileDialog.pack();
        if (profileDialog.getCreateNew()) {
            this.user = profileDialog.getUser();
        }
        createGUI();
    }

    //MODIFIES: this
    //EFFECTS: creates main panel with all subsequent components and places it in main frame
    //based off TabbedPaneDemo.java and https://docs.oracle.com/javase/tutorial/uiswing/components/tabbedpane.html
    //actionListener resource: https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html
    private void createGUI() {
        tabbedPane = new JTabbedPane();
        homeTab = new HomeTab(user);
        tabbedPane.addTab("Home", homeTab);
        homeTab.add(saveButton);
        saveButton.addActionListener(new CareAppGUI.SaveActionListener());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1); //taken from TabbedPaneDemo TabbedPaneDemo()
        tabbedPane.addTab("Schedule", scheduleTab);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);//taken from TabbedPaneDemo TabbedPaneDemo()
        caretakersTab = new CaretakersTab(user);
        tabbedPane.addTab("Caretakers", caretakersTab);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);//taken from TabbedPaneDemo TabbedPaneDemo()
        tabbedPane.addTab("Medications", medicationsTab);
        tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
        tabbedPane.addTab("Conditions", conditionsTab);
        tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);
        frame.add(tabbedPane);
    }

    //MODIFIES: this
    //EFFECTS: initializes user profile
    //taken from TellerApplication, from CareAppCUI
    private void initialize() {
        jsonWriter = new JsonWriter(JSON_STORE); //taken from JsonSerializationDemo
        jsonReader = new JsonReader(JSON_STORE); //taken from JsonSerializationDemo
    }

    //MODIFIES: this
    //EFFECTS: loads patient profile from JSON file
    //adapted from JsonSerializationDemo WorkRoomApp class, from CareAppCUI
    private void loadPatientProfile() {
        try {
            user = jsonReader.read();
            System.out.println("Loaded " + user.getName() + " from ./data/patient.json");
        } catch (IOException e) {
            System.out.println("Failed to read from file: ./data/patient.json");
        }
    }

    //MODIFIES: this
    //EFFECTS: saves patient profile as JSON file
    //adapted from JsonSerializationDemo WorkRoomApp class, from CareAppCUI
    public void savePatientProfile() {
        try {
            jsonWriter.open();
            jsonWriter.write(user);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Failed to write to file: ./data/patient.json");
        }
    }

    //MODIFIES: this
    //EFFECTS: runs CareApp
    //from Main class
    public static void main(String[] args) {
        try {
            new CareAppGUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }

    //Action listener to process user interaction with save button
    //actionListener resource: https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html
    public class SaveActionListener implements ActionListener {

        @Override
        //EFFECTS: calls to save patient profile when save button pressed
        public void actionPerformed(ActionEvent e) {
            savePatientProfile();
        }
    }

    //Action listener to print the even log when the user closes the window
    //WindowListener resource: https://docs.oracle.com/javase/tutorial/uiswing/events/windowlistener.html
    //https://docs.oracle.com/javase/8/docs/api/java/awt/event/WindowAdapter.html
    public class CloseActionListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            EventLog.getInstance().printLog(); //taken from Alarm System
            System.exit(0); //taken from https://stackoverflow.com/questions
            // /6084039/create-custom-operation-for-setdefaultcloseoperation
        }
    }
}
