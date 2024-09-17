package model;

import org.json.JSONObject;

import java.util.ArrayList;

//Represents the schedule of caretakers as a list of the caretakers names with each index specifying a shift time on a
//day of the week.
public class Schedule {

    private ArrayList<String> weeklySchedule;

    //adapted from HairSalon4Solution HairSalon() function
    public Schedule() {
        weeklySchedule = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            //Each day of the week has 4 available shifts with i 0-3 dedicated to Monday i 4-7 dedicated to Tuesday and
            //so on.
            weeklySchedule.add(i, "available");
        }
    }

    //MODIFIES: this
    //EFFECTS: places inputted caretaker at specified shift index
    public void assignShift(String day, String time, String caretaker) {
        int index = convertToShift(day, time);
        weeklySchedule.set(index, caretaker);
    }

    //MODIFIES: this
    //EFFECTS: replaces content of inputted index with "available" making the shift available.
    public void removeShift(String day, String time) {
        int index = convertToShift(day, time);
        weeklySchedule.set(index, "available");
    }

    public int convertToShift(String day, String time) {
        if (day.equals("monday")) {
            return convertMonday(time);
        } else if (day.equals("tuesday")) {
            return convertTuesday(time);
        } else if (day.equals("wednesday")) {
            return convertWednesday(time);
        } else if (day.equals("thursday")) {
            return convertThursday(time);
        } else if (day.equals("friday")) {
            return convertFriday(time);
        } else if (day.equals("saturday")) {
            return convertSaturday(time);
        }
        return convertSunday(time);
    }

    private int convertMonday(String time) {
        if (time.equals("morning")) {
            return 0;
        } else if (time.equals("afternoon")) {
            return 1;
        } else if (time.equals("evening")) {
            return 2;
        } else {
            return 3;
        }
    }

    private int convertTuesday(String time) {
        if (time.equals("morning")) {
            return 4;
        } else if (time.equals("afternoon")) {
            return 5;
        } else if (time.equals("evening")) {
            return 6;
        } else {
            return 7;
        }
    }

    private int convertWednesday(String time) {
        if (time.equals("morning")) {
            return 8;
        } else if (time.equals("afternoon")) {
            return 9;
        } else if (time.equals("evening")) {
            return 10;
        } else {
            return 11;
        }
    }

    private int convertThursday(String time) {
        if (time.equals("morning")) {
            return 12;
        } else if (time.equals("afternoon")) {
            return 13;
        } else if (time.equals("evening")) {
            return 14;
        } else {
            return 15;
        }
    }

    private int convertFriday(String time) {
        if (time.equals("morning")) {
            return 16;
        } else if (time.equals("afternoon")) {
            return 17;
        } else if (time.equals("evening")) {
            return 18;
        } else {
            return 19;
        }
    }

    private int convertSaturday(String time) {
        if (time.equals("morning")) {
            return 20;
        } else if (time.equals("afternoon")) {
            return 21;
        } else if (time.equals("evening")) {
            return 22;
        } else {
            return 23;
        }
    }

    private int convertSunday(String time) {
        if (time.equals("morning")) {
            return 24;
        } else if (time.equals("afternoon")) {
            return 25;
        } else if (time.equals("evening")) {
            return 26;
        } else {
            return 27;
        }
    }

    //REQUIRES: index to be <=28
    //EFFECTS: returns a string of the time of the shift at the given index
    public String getShiftTimeString(int index) {
        //List<String> mornings = new ArrayList<>(); I could also do this, but I would need a say to initialize all the
        //values at once
        if (index == 0 || index == 4 || index == 8 || index == 12 || index == 16 || index == 20 || index == 24) {
            return "morning from 9am to 12pm";
        } else if (index == 1 || index == 5 || index == 9 || index == 13 || index == 17 || index == 21 || index == 25) {
            return "afternoon from 12pm to 3pm";
        } else if (index == 2 || index == 6 || index == 10 || index == 14 || index == 18 || index == 22
                || index == 26) {
            return "evening from 3pm to 10pm";
        } else {
            return "night from 10pm to 9am";
        }
    }

    //REQUIRES: index to be <= 27
    //EFFECTS: returns a string of the day of the shift at the given index
    public String getShiftDayString(int index) {
        if (index <= 3) {
            return "Monday";
        } else if (index <= 7) {
            return "Tuesday";
        } else if (index <= 11) {
            return "Wednesday";
        } else if (index <= 15) {
            return "Thursday";
        } else if (index <= 19) {
            return "Friday";
        } else if (index <= 23) {
            return "Saturday";
        } else {
            return "Sunday";
        }
    }

    //EFFECTS: returns the weekly schedule
    public ArrayList getWeeklySchedule() {
        return this.weeklySchedule;
    }

}
