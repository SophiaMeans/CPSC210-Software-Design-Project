package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScheduleTest {

    Schedule testSchedule;

    @BeforeEach
    public void setup() {
        testSchedule = new Schedule();
    }

    @Test
    public void testSchedule() {
        assertEquals(28, testSchedule.getWeeklySchedule().size());
        assertEquals("available", testSchedule.getWeeklySchedule().get(0));
        assertEquals("available", testSchedule.getWeeklySchedule().get(13));
    }

    @Test
    public void testAssignShift() {
        testSchedule.assignShift("monday", "evening", "Sandy");
        testSchedule.assignShift("tuesday", "morning", "Ruby");
        testSchedule.assignShift("wednesday", "night", "Dan");
        testSchedule.assignShift("thursday", "afternoon", "Harriet");
        testSchedule.assignShift("friday", "morning", "James");

        assertEquals("Sandy", testSchedule.getWeeklySchedule().get(2));
        assertEquals("Ruby", testSchedule.getWeeklySchedule().get(4));
        assertEquals("Dan", testSchedule.getWeeklySchedule().get(11));
        assertEquals("Harriet", testSchedule.getWeeklySchedule().get(13));
        assertEquals("James", testSchedule.getWeeklySchedule().get(16));
    }

    @Test
    public void testRemoveShiftNoneAssigned() {
        testSchedule.removeShift("monday", "night");
        testSchedule.removeShift("wednesday", "afternoon");

        assertEquals("available", testSchedule.getWeeklySchedule().get(3));
        assertEquals("available", testSchedule.getWeeklySchedule().get(9));
    }

    @Test
    public void testRemoveShiftAlreadyAssigned() {
        testSchedule.assignShift("monday", "evening", "Sandy");
        testSchedule.assignShift("tuesday", "morning", "Ruby");
        testSchedule.assignShift("wednesday", "night", "Dan");
        testSchedule.removeShift("monday", "evening");
        testSchedule.removeShift("tuesday", "morning");
        testSchedule.removeShift("wednesday", "night");

        assertEquals("available", testSchedule.getWeeklySchedule().get(2));
        assertEquals("available", testSchedule.getWeeklySchedule().get(4));
        assertEquals("available", testSchedule.getWeeklySchedule().get(11));
    }

    @Test
    //convertMonday(), convertTuesday(), convertWednesday(), convertThursday(), and convertFriday() are implicitly
    //tested in this function
    public void testConvertToShiftMondayThroughWednesday() {
        Integer testM1 = testSchedule.convertToShift("monday", "morning");
        Integer testM2 = testSchedule.convertToShift("monday", "afternoon");
        Integer testM3 = testSchedule.convertToShift("monday", "evening");
        Integer testM4 = testSchedule.convertToShift("monday", "night");
        Integer testT1 = testSchedule.convertToShift("tuesday", "morning");
        Integer testT2 = testSchedule.convertToShift("tuesday", "afternoon");
        Integer testT3 = testSchedule.convertToShift("tuesday", "evening");
        Integer testT4 = testSchedule.convertToShift("tuesday", "night");
        Integer testW1 = testSchedule.convertToShift("wednesday", "morning");
        Integer testW2 = testSchedule.convertToShift("wednesday", "afternoon");
        Integer testW3 = testSchedule.convertToShift("wednesday", "evening");
        Integer testW4 = testSchedule.convertToShift("wednesday", "night");

        assertEquals(0, testM1);
        assertEquals(1, testM2);
        assertEquals(2, testM3);
        assertEquals(3, testM4);
        assertEquals(4, testT1);
        assertEquals(5, testT2);
        assertEquals(6, testT3);
        assertEquals(7, testT4);
        assertEquals(8, testW1);
        assertEquals(9, testW2);
        assertEquals(10, testW3);
        assertEquals(11, testW4);
    }

    @Test
    public void testConvertToShiftThursdayToSunday() {
        Integer testT1 = testSchedule.convertToShift("thursday", "morning");
        Integer testT2 = testSchedule.convertToShift("thursday", "afternoon");
        Integer testT3 = testSchedule.convertToShift("thursday", "evening");
        Integer testT4 = testSchedule.convertToShift("thursday", "night");
        Integer testF1 = testSchedule.convertToShift("friday", "morning");
        Integer testF2 = testSchedule.convertToShift("friday", "afternoon");
        Integer testF3 = testSchedule.convertToShift("friday", "evening");
        Integer testF4 = testSchedule.convertToShift("friday", "night");
        Integer testSa1 = testSchedule.convertToShift("saturday", "morning");
        Integer testSa2 = testSchedule.convertToShift("saturday", "afternoon");
        Integer testSa3 = testSchedule.convertToShift("saturday", "evening");
        Integer testSa4 = testSchedule.convertToShift("saturday", "night");
        Integer testSu1 = testSchedule.convertToShift("sunday", "morning");
        Integer testSu2 = testSchedule.convertToShift("sunday", "afternoon");
        Integer testSu3 = testSchedule.convertToShift("sunday", "evening");
        Integer testSu4 = testSchedule.convertToShift("sunday", "night");

        assertEquals(12, testT1);
        assertEquals(13, testT2);
        assertEquals(14, testT3);
        assertEquals(15, testT4);
        assertEquals(16, testF1);
        assertEquals(17, testF2);
        assertEquals(18, testF3);
        assertEquals(19, testF4);
        assertEquals(20, testSa1);
        assertEquals(21, testSa2);
        assertEquals(22, testSa3);
        assertEquals(23, testSa4);
        assertEquals(24, testSu1);
        assertEquals(25, testSu2);
        assertEquals(26, testSu3);
        assertEquals(27, testSu4);
    }

    @Test
    public void testGetShiftTimeString() {
        assertEquals("morning from 9am to 12pm", testSchedule.getShiftTimeString(0));
        assertEquals("morning from 9am to 12pm", testSchedule.getShiftTimeString(4));
        assertEquals("morning from 9am to 12pm", testSchedule.getShiftTimeString(8));
        assertEquals("morning from 9am to 12pm", testSchedule.getShiftTimeString(12));
        assertEquals("morning from 9am to 12pm", testSchedule.getShiftTimeString(16));
        assertEquals("morning from 9am to 12pm", testSchedule.getShiftTimeString(20));
        assertEquals("morning from 9am to 12pm", testSchedule.getShiftTimeString(24));
        assertEquals("afternoon from 12pm to 3pm", testSchedule.getShiftTimeString(1));
        assertEquals("afternoon from 12pm to 3pm", testSchedule.getShiftTimeString(5));
        assertEquals("afternoon from 12pm to 3pm", testSchedule.getShiftTimeString(9));
        assertEquals("afternoon from 12pm to 3pm", testSchedule.getShiftTimeString(13));
        assertEquals("afternoon from 12pm to 3pm", testSchedule.getShiftTimeString(17));
        assertEquals("afternoon from 12pm to 3pm", testSchedule.getShiftTimeString(21));
        assertEquals("afternoon from 12pm to 3pm", testSchedule.getShiftTimeString(25));
        assertEquals("evening from 3pm to 10pm", testSchedule.getShiftTimeString(2));
        assertEquals("evening from 3pm to 10pm", testSchedule.getShiftTimeString(6));
        assertEquals("evening from 3pm to 10pm", testSchedule.getShiftTimeString(10));
        assertEquals("evening from 3pm to 10pm", testSchedule.getShiftTimeString(14));
        assertEquals("evening from 3pm to 10pm", testSchedule.getShiftTimeString(18));
        assertEquals("evening from 3pm to 10pm", testSchedule.getShiftTimeString(22));
        assertEquals("evening from 3pm to 10pm", testSchedule.getShiftTimeString(26));
        assertEquals("night from 10pm to 9am", testSchedule.getShiftTimeString(3));
    }

    @Test
    public void testGetShiftDayString() {
        assertEquals("Monday", testSchedule.getShiftDayString(3));
        assertEquals("Monday", testSchedule.getShiftDayString(1));
        assertEquals("Tuesday", testSchedule.getShiftDayString(7));
        assertEquals("Tuesday", testSchedule.getShiftDayString(4));
        assertEquals("Wednesday", testSchedule.getShiftDayString(11));
        assertEquals("Wednesday", testSchedule.getShiftDayString(9));
        assertEquals("Thursday", testSchedule.getShiftDayString(15));
        assertEquals("Thursday", testSchedule.getShiftDayString(13));
        assertEquals("Friday", testSchedule.getShiftDayString(19));
        assertEquals("Friday", testSchedule.getShiftDayString(18));
        assertEquals("Saturday", testSchedule.getShiftDayString(23));
        assertEquals("Saturday", testSchedule.getShiftDayString(21));
        assertEquals("Sunday", testSchedule.getShiftDayString(27));


    }
}
