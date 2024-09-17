package persistance;

import model.Patient;
import model.Schedule;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Tests for JsonReader class
//adapted from JsonSerializationDemo JsonReaderTest class
public class JsonReaderTest extends JsonTest {

    @Test
    //adapted from JsonSerializationDemo JsonReaderTest class testReaderNonExistentFile()
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/youThought.json");
        try {
            Patient p = reader.read();
            fail("IOException excepted");
        } catch (IOException e) {
            // all is well
        }
    }

    @Test
    //adapted from JsonSerializationDemo JsonReaderTest class, testReaderEmptyWorkroom()
    public void testReaderEmptyPatient() {
        JsonReader reader = new JsonReader("./data/TestReaderEmptyPatient.json");
        try {
            Patient p = reader.read();
            assertEquals("Ruthipoo", p.getName());
            assertEquals(19, p.getAge());
            assertEquals(0, p.getMedications().size());
            assertEquals(0, p.getConditions().size());
            assertEquals(0, p.getCaretakers().size());
            checkSchedule(p, 0, 5, "available");
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    //adapted from JsonSerializationDemo JsonReaderTest class, testReaderGeneralWorkroom
    public void testReaderGeneralPatient() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPatient.json");
        try {
            Patient p = reader.read();
            assertEquals("Nanditapoo", p.getName());
            assertEquals(20, p.getAge());
            assertEquals(1, p.getConditions().size());
            assertEquals(2, p.getMedications().size());
            assertEquals(1, p.getCaretakers().size());
            assertEquals("Carolyn", p.getCaretakers().get(0).getName());
            checkSchedule(p, 1, 17, "Carolyn");

            } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
