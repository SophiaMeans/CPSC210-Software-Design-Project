package ui;

import model.Patient;

import javax.swing.*;

//represents a home tab in CareApp
public class HomeTab extends JPanel {

    private boolean saveProfile = false;

    //EFFECTS: creates Home screen contents and imports image
    //based off TabbedPaneDemo makeTextPanel()
    //ImageIcon from https://docs.oracle.com/javase/tutorial/uiswing/components/icon.html
    public HomeTab(Patient user) {
        ImageIcon sourceImage = new ImageIcon("images/koala.png");
        JLabel welcome = new JLabel("Welcome " + user.getName());
        JLabel image = new JLabel(sourceImage);
        this.add(welcome);
        this.add(image);
    }
}
