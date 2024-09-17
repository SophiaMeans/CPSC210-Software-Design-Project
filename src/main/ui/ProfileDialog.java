package ui;

import model.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

//EFFECTS: Creates dialog window to gather login information or load previous profile.
//based on components-DialogDemoProject from
//https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html#DialogDemo
public class ProfileDialog extends JDialog {

    private JTextField nameField;
    private JTextField ageField;
    private JOptionPane optionPane;
    private Patient user;
    private boolean createNew = false;

    //MODIFIES: this
    //EFFECTS: creates a new profile dialog window for user to input a new profile or load their old profile
    // Based on CustomDialog(Frame aframe, String aWord, Dialog parent) in CustomDialog.java in
    // components-DialogDemoProject
    public ProfileDialog(Frame frame, Patient user) {
        super(frame, true);

        JButton reloadButton;
        JButton createButton;

        reloadButton = new JButton("reload profile");
        reloadButton.addActionListener(new ReloadActionListener());
        createButton = new JButton("create new profile");
        createButton.addActionListener(new CreateActionListener());
        nameField = new JTextField("name", 20);
        nameField.setBounds(10, 10, 10, 10);
        ageField = new JTextField("age",10);
        ageField.setBounds(10, 10, 10, 10);

        String message = "Reload your profile or enter your name and age to create a new profile. \n";
        Object[] content = {message, nameField, ageField};
        Object[] buttons = {reloadButton, createButton};
        optionPane = new JOptionPane(content,
                JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION, null, buttons);

        setContentPane(optionPane);
    }

    //ActionListener to process user interaction with create profile button
    //referenced Window disposal at: https://docs.oracle.com/javase/7/docs/api/java/awt/Window.html
    public class CreateActionListener implements ActionListener {

        @Override
        //EFFECTS: creates a new user profile if inputs are valid, if not returns error message, then disposes dialog
        public void actionPerformed(ActionEvent e) {
            String nameText = nameField.getText();
            String ageText = ageField.getText();
            Integer age;
            try {
                age = Integer.parseInt(ageText);
            } catch (NumberFormatException ex) {
                showMessageDialog(optionPane, "please enter a valid age");
                return;
            }

            user = new Patient(nameText, age);
            createNew = true;
            dispose(); //found in Oracle documentation, Window Class
        }
    }

    //ActionListener to process user interaction with reload profile button
    //referenced Window disposal at: https://docs.oracle.com/javase/7/docs/api/java/awt/Window.html
    public class ReloadActionListener implements ActionListener {

        @Override
        //EFFECTS: disposes ProfileDialog since reload profile is default in CareAppGUI
        public void actionPerformed(ActionEvent e) {
            dispose(); //found in Oracle documentation, WindowClass
        }
    }

    public Patient getUser() {
        return user;
    }

    public boolean getCreateNew() {
        return createNew;
    }
}
