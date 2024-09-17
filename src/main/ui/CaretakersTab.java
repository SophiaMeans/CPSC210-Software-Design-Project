package ui;

import model.Caretaker;
import model.Patient;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Creates an interactive list panel to allow the user to add or remove caretakers, based off of ListDemo.java,
// components-ListDemoProject
//https://docs.oracle.com/javase/tutorial/uiswing/components/list.html
public class CaretakersTab extends JPanel implements ListSelectionListener {

    private JList list; //taken from ListDemo.java
    private DefaultListModel listCaretakers; //taken from ListDemo.java

    private static final String add = "Add";
    private static final String remove = "Remove";
    private JButton removeButton;
    private JTextField caretakerName;
    private JTextField caretakerCert;
    private JScrollPane scrollPane;
    private JPanel panel;
    private Patient user;

    //EFFECTS: constructs a new Caretakers Tab and places users list of caretakers in it
    //Based on ListDemo.java, components-ListDemoProject
    public CaretakersTab(Patient user) {
        super(new BorderLayout());
        this.user = user;
        createVisibleList();
        createPanel(createButtonsAndText());
    }

    //MODIFIES: this
    //EFFECTS: creates a default list consisting of each caretaker in users list of caretakers
    //based off ListDemo() in ListDemo.java components-ListDemoProject
    public void createListCaretakers() {
        listCaretakers = new DefaultListModel();
        for (Caretaker c : user.getCaretakers()) {
            listCaretakers.addElement(c.getName() + ", " + c.getCertification());
        }
    }

    //MODIFIES: this
    //EFFECTS: create the visible list and places it in a scroll pane
    //based off ListDemo() in ListDemo.java, components-ListDemoProject
    public void createVisibleList() {
        createListCaretakers();
        list = new JList(listCaretakers);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //taken from ListDemo()
        list.setSelectedIndex(0); //taken from ListDemo()
        list.addListSelectionListener(this); //taken from ListDemo()
        list.setVisibleRowCount(10);
        scrollPane = new JScrollPane(list); //taken from ListDemo()
    }

    //MODIFIES: this
    //EFFECTS: creates buttons and text fields at the bottom of Caretaker list
    //based off ListDemo() in ListDemo.java, components-ListDemoProject
    public JButton createButtonsAndText() {
        JButton addButton = new JButton(add);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(add); //taken from ListDemo()
        addButton.addActionListener(addListener);
        addButton.setEnabled(false); //taken from ListDemo()

        removeButton = new JButton(remove);
        removeButton.setActionCommand(remove); //taken from ListDemo()
        removeButton.addActionListener(new RemoveListener());

        caretakerName = new JTextField("name", 10);
        caretakerName.addActionListener(addListener);
        caretakerName.getDocument().addDocumentListener(addListener); //taken from ListDemo()
        caretakerCert = new JTextField("certification", 10);
        caretakerCert.addActionListener(addListener); //taken from ListDemo()

        return addButton;
    }

    //MODIFIES: this
    //EFFECTS: creates a panel and places the elements on it
    //based off ListDemo() in ListDemo.java, components-ListDemoProject
    public void createPanel(JButton addButton) {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS)); //taken from ListDemo.java,
                                                                    // components-ListDemoProject
        panel.add(removeButton);
        panel.add(Box.createHorizontalStrut(5)); //taken from ListDemo.java, components-ListDemoProject
        panel.add(new JSeparator(SwingConstants.VERTICAL)); //taken from ListDemo.java, components-ListDemoProject
        panel.add(Box.createHorizontalStrut(5)); //taken from ListDemo.java, components-ListDemoProject
        panel.add(caretakerName);
        panel.add(Box.createHorizontalStrut(5)); //taken from ListDemo.java, components-ListDemoProject
        panel.add(caretakerCert);
        panel.add(addButton);

        add(scrollPane, BorderLayout.CENTER); //taken from ListDemo.java, components-ListDemoProject
        add(panel, BorderLayout.PAGE_END); //taken from ListDemo.java, components-ListDemoProject

    }

    //class to process user interaction with the remove button. Based on FireListener in ListDemo.java,
    // components-ListDemoProject
    class RemoveListener implements ActionListener {

        @Override
        //EFFECTS: removes selected caretaker from users list of caretakers.
        //based off actionPerformed(ActionEvent e) in FireListener in ListDemo.java, components-ListDemoProject
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex(); //taken from ListDemo.java, components-ListDemoProject
            listCaretakers.remove(index); //taken from ListDemo.java, components-ListDemoProject
            removeCaretaker(index);

            if (listCaretakers.getSize() == 0) { //taken from ListDemo.java, components-ListDemoProject
                //if nobody in list disable remove button
                removeButton.setEnabled(false);
            } else {
                if (index == listCaretakers.getSize()) { //taken from ListDemo.java, components-ListDemoProject
                    index--;
                }
                list.setSelectedIndex(index); //taken from ListDemo.java, components-ListDemoProject
                list.ensureIndexIsVisible(index); //taken from ListDemo.java, components-ListDemoProject
            }
        }
    }

    //class to process user interaction with the add button. Based on HireListener in ListDemo.java,
    // components-ListDemoProject
    class AddListener implements ActionListener, DocumentListener {

        private JButton addButton; //taken from ListDemo.java components-ListDemoProject
        private boolean alreadyEnabled = false; //taken from ListDemo.java components-ListDemoProject

        //MODIFIES: this
        //EFFECTS: sets add Button
        //taken from HireListener() in HireListener in ListDemo.java, components-ListDemoProject
        public AddListener(JButton button) {
            this.addButton = button;
        }

        @Override
        //EFFECTS: adds the user inputted caretaker to the user profile and displays them in listCaretakers.
        //based off actionPerformed(ActionEvent e) in HireListener in ListDemo.java, components-ListDemoProject
        public void actionPerformed(ActionEvent e) {
            String name = caretakerName.getText();
            String cert = caretakerCert.getText();

            if (name.equals("") || alreadyInList(name, cert)) {
                caretakerName.requestFocusInWindow(); //taken from ListDemo.java
                caretakerName.selectAll(); //taken from ListDemo.java
            }

            listCaretakers.addElement(name + ", " + cert);
            addCaretaker(name, cert);
        }

        //EFFECTS: checks that the Caretaker to be added is not already in users caretakers
        //based off alreadyInList(String name) in HireListener in ListDemo.java, components-ListDemoProject
        protected boolean alreadyInList(String name, String cert) {
            return listCaretakers.contains(name + ", " + cert);
        }

        @Override
        //EFFECTS: enables add button
        //taken from insertUpdate(DocumentEvent e) and enableButton() in HireListener in ListDemo.java,
        //components-ListDemoProject
        public void insertUpdate(DocumentEvent e) {
            enableButton(); //taken from ListDemo.java
        }

        //EFFECTS: if addButton is not already enabled, enables it
        //taken from enableButton in HireListener in ListDemo.java, components-ListDemoProject
        private void enableButton() {
            if (!alreadyEnabled) { //taken from ListDemo.java
                addButton.setEnabled(true); //taken from ListDemo.java
            }
        }

        //EFFECTS: if there is nothing inputted into the text field disables add button
        //based off handleEmptyTextField(DocumentEvent e) in HireListener in ListDemo.java, components-ListDemoProject
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) { //taken from ListDemo.java
                addButton.setEnabled(false); //taken from ListDemo.java
                alreadyEnabled = false; //taken from ListDemo.java
                return true; //taken from ListDemo.java
            }
            return false; //taken from ListDemo.java
        }

        @Override
        //EFFECTS: resets add button through handleEmptyTextField
        //taken from removeUpdate(DocumentEvent e) in HireListener in ListDemo.java, components-ListDemoProject
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e); //taken from ListDemo.java, components-ListDemoProject
        }

        @Override
        //EFFECTS: activates the addButton if there is user input
        //taken from changedUpdate(DocumentEvent e) in HireListener in ListDemo.java, components-ListDemoProject
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) { //taken from ListDemo.java
                enableButton(); //taken from ListDemo.java
            }
        }
    }

    @Override
    //EFFECTS: if a row in the list is selected, enables the removeButton. If nothing selected disabled removeButton
    //taken from valueChanged(ListSelectionEvent e) in ListDemo.java, components-ListDemoProject
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) { //taken from ListDemo.java
            if (list.getSelectedIndex() == -1) {
                removeButton.setEnabled(false);//taken from ListDemo.java
            } else {
                removeButton.setEnabled(true);
            }
        }
    }

    //EFFECTS: updates user profile as caretakers are added
    public void addCaretaker(String name, String cert) {
        user.addCaretaker(new Caretaker(name, cert));
    }

    //MODIFIES: this
    //EFFECTS: removes inputted caretaker from users caretakers
    private void removeCaretaker(int index) {
        user.removeCaretaker(index, user.getCaretakers().get(index).getName());
    }
}
