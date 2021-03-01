package persistence;

import model.Material;
import model.SetOfMaterials;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            SetOfMaterials total = new SetOfMaterials();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTotal() {
        try {
            SetOfMaterials total = new SetOfMaterials();
            JsonWriter writer = new JsonWriter("./data/emptyTotalMaterialsUsed.json");
            writer.open();
            writer.write(total);
            writer.close();

            JsonReader reader = new JsonReader("./data/emptyTotalMaterialsUsed.json");
            total = reader.read();
            assertEquals(0, total.staminaNeededToFinishSet());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterTotal() {
        try {
            Material testMaterial1 = new Material("Bones", 100, 10, 100);
            Material testMaterial2 = new Material("Chains", 200, 20, 200);
            Material testMaterial3 = new Material("Stakes", 300, 20, 200);
            SetOfMaterials testset = new SetOfMaterials();
            testset.addMaterialToList(testMaterial3);
            testset.addMaterialToList(testMaterial2);
            testset.addMaterialToList(testMaterial1);
            SetOfMaterials total = new SetOfMaterials();
            total.addSetOfMaterials(testset);

            JsonWriter writer = new JsonWriter("./data/testWriterTotal.json");
            writer.open();
            writer.write(testset);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterTotal.json");
            total = reader.read();
            assertEquals("Stakes 300\n" +
                    "Chains 200\n" +
                    "Bones 100\n",
                    total.materialsInThisSet());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}