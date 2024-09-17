package persistance;

import model.Caretaker;
import model.Patient;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Tests for JsonWriter class
//adapted from JsonSerializationDemo JsonWriterTest class
public class JsonWriterTest extends JsonTest {

    @Test
    //adapted from JsonSerializationDemo JsonWriterTest class, testWriterInvalidFile()
    public void testWriterInvalidFile() {
        try {
            Patient p = new Patient("Meg", 19);
            JsonWriter writer = new JsonWriter("./data/notG\00dVeryBad.json");
            writer.open();
            fail("IOException was expected");
        } catch (FileNotFoundException e) {
            // not bad very good!;
        }
    }

    @Test
    //adapted from JsonSerializationDemo JsonWriterTest class, testWriterEmptyPatient()
    public void testWriterEmptyPatient() {
        try {
            Patient p = new Patient("James", 20);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPatient.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPatient.json");
            p = reader.read();
            assertEquals("James", p.getName());
            assertEquals(20, p.getAge());
            assertEquals(0, p.getMedications().size());
            assertEquals(0, p.getConditions().size());
            assertEquals(0, p.getCaretakers().size());
            checkSchedule(p, 4, 7, "available");
        } catch (IOException e) {
            fail("Not good very bad!");
        }
    }

    @Test
    //adapted from JsonSerializationDemo JsonWriterTest class, testWriterGeneralPatient()
    public void testWriterGeneralPatient() {
        try {
            Patient p = new Patient("Nanditapoo", 20);
            p.addMedication("Fireball");
            p.addMedication("Flan");
            p.addCondition("Awesome");
            p.addCaretaker(new Caretaker("Carolyn", "cna"));
            p.getSchedule().assignShift("monday", "afternoon", "Carolyn");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPatient.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPatient.json");
            p = reader.read();
            assertEquals("Nanditapoo", p.getName());
            assertEquals(20, p.getAge());
            assertEquals(1, p.getConditions().size());
            assertEquals(2, p.getMedications().size());
            assertEquals(1, p.getCaretakers().size());
            assertEquals("Carolyn", p.getCaretakers().get(0).getName());
            checkSchedule(p, 1, 6, "Carolyn");

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
