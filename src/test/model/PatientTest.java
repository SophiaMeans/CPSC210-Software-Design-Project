package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Tests for Patient class
public class PatientTest {

    Patient testPatient;
    Caretaker sarahTest;
    Caretaker danTest;
    Caretaker kateTest;

    @BeforeEach
    public void setup() {
        testPatient = new Patient("test", 65);
        sarahTest = new Caretaker("Sarah", "CNA");
        danTest = new Caretaker("Dan", "CNA");
        kateTest = new Caretaker("Kate", "CNA");
    }

    @Test
    public void testPatient() {
        assertEquals(testPatient.getName(), "test");
        assertEquals(testPatient.getAge(), 65);
        assertEquals(0, testPatient.getCaretakers().size());
        assertEquals(0, testPatient.getConditions().size());
        assertEquals(0, testPatient.getMedications().size());
    }

    @Test
    public void testAddMedicine() {
        testPatient.addMedication("Levodopa");
        assertEquals(1, testPatient.getMedications().size());
        assertEquals("Levodopa", testPatient.getMedications().get(0));

        testPatient.addMedication("Tasmar");
        testPatient.addMedication("Neupro");
        testPatient.addMedication("Eldepryl");

        assertEquals(4, testPatient.getMedications().size());
    }

    @Test
    public void testRemoveMedication() {
        testPatient.addMedication("Levodopa");
        testPatient.addMedication("Tasmar");
        testPatient.addMedication("Neupro");
        testPatient.addMedication("Eldepryl");

        testPatient.removeMedication("Tasmar");
        assertEquals(3, testPatient.getMedications().size());
        assertEquals("Neupro", testPatient.getMedications().get(1));
        assertFalse(testPatient.getMedications().contains("Tasmar"));
    }

    @Test
    public void testAddCondition () {
        testPatient.addCondition("Alzheimer's");
        assertEquals(1, testPatient.getConditions().size());
        assertEquals("Alzheimer's", testPatient.getConditions().get(0));

        testPatient.addCondition("Parkinson's");
        testPatient.addCondition("Arthritis");
        assertEquals(3, testPatient.getConditions().size());
        assertEquals("Arthritis", testPatient.getConditions().get(2));
    }

    @Test
    public void testRemoveCondition () {
        testPatient.addCondition("Alzheimer's");
        testPatient.removeCondition("Alzheimer's");
        assertEquals(testPatient.getConditions().size(), 0);

        testPatient.addCondition("Parkinson's");
        testPatient.addCondition("Arthritis");
        testPatient.removeCondition("Parkinson's");
        assertEquals(1, testPatient.getConditions().size());
        assertEquals("Arthritis", testPatient.getConditions().get(0));
        testPatient.removeCondition("Arthritis");
        assertEquals(0, testPatient.getConditions().size());
    }

    @Test
    public void testAddCaretaker() {
        testPatient.addCaretaker(kateTest);
        assertEquals(1, testPatient.getCaretakers().size());

        testPatient.addCaretaker(danTest);
        testPatient.addCaretaker(sarahTest);
        assertEquals(3, testPatient.getCaretakers().size());
        assertEquals(sarahTest, testPatient.getCaretakers().get(2));
    }

    @Test
    public void testRemoveCaretaker() {
        testPatient.addCaretaker(kateTest);
        testPatient.removeCaretaker(kateTest);
        assertEquals(testPatient.getCaretakers().size(), 0);

        testPatient.addCaretaker(danTest);
        testPatient.addCaretaker(sarahTest);
        testPatient.removeCaretaker(danTest);
        assertEquals(testPatient.getCaretakers().size(), 1);
        assertEquals(testPatient.getCaretakers().get(0), sarahTest);

        testPatient.removeCaretaker(sarahTest);
        assertEquals(testPatient.getCaretakers().size(), 0);
    }

    @Test
    public void testRemoveCaretakerIndex() {
        testPatient.addCaretaker(kateTest);
        testPatient.removeCaretaker(0, "Kate");
        assertEquals(testPatient.getCaretakers().size(), 0);

        testPatient.addCaretaker(danTest);
        testPatient.addCaretaker(sarahTest);
        testPatient.removeCaretaker(1, "Sandy");
        assertEquals(testPatient.getCaretakers().size(), 1);
        assertEquals(testPatient.getCaretakers().get(0), danTest);

        testPatient.removeCaretaker(0, "Dan");
        assertEquals(testPatient.getCaretakers().size(), 0);
    }
}
