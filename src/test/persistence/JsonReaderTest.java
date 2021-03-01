package persistence;

import model.SetOfMaterials;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            SetOfMaterials set = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReader() {
        JsonReader reader = new JsonReader("./data/testWriterTotal.json");
        try {
            SetOfMaterials set = reader.read();
            assertEquals("Stakes 300\n" +
                    "Chains 200\n" +
                    "Bones 100\n",
                    set.materialsInThisSet());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

}