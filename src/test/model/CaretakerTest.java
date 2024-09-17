package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test for Caretaker class
class CaretakerTest {

    Caretaker sarahTest;
    Caretaker danTest;
    Caretaker harrietTest;
    Caretaker megTest;

    @BeforeEach
    public void setup() {
        sarahTest = new Caretaker("Sarah", "cna");
        danTest = new Caretaker("Dan", "lpn");
        megTest = new Caretaker("Meg", "rn");
        harrietTest = new Caretaker("Harriet", "none");
    }

    @Test
    public void testValidCertification () {
        assertTrue(sarahTest.validCertification());
        assertTrue(danTest.validCertification());
        assertFalse(harrietTest.validCertification());
        assertTrue(megTest.validCertification());
    }

    @Test
    public void testGetCertification() {
        assertEquals("cna", sarahTest.getCertification());
        assertEquals("lpn", danTest.getCertification());
        assertEquals("rn", megTest.getCertification());
        assertEquals("none", harrietTest.getCertification());
    }
}