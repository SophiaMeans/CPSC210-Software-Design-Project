package persistance;

import model.Patient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    public void checkSchedule(Patient p, int takenShift, int randomCheck, String name) {
        assertEquals(name, p.getSchedule().getWeeklySchedule().get(takenShift));
        assertEquals("available", p.getSchedule().getWeeklySchedule().get(randomCheck));
    }
}
