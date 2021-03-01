package model;

import exceptions.OutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SetOfMaterialsTest {
    private Material testMaterial1;
    private Material testMaterial2;
    private Material testMaterial3;
    private SetOfMaterials testset;

    @BeforeEach
    void runBefore() {
        testMaterial1 = new Material("Bones", 100, 10, 100);
        testMaterial2 = new Material("Chains", 200, 20, 200);
        testMaterial3 = new Material("Stakes", 300, 20, 200);
        testset = new SetOfMaterials();
    }

    @Test
    void testMaterialsInThisSet() {
        testset.addMaterialToList(testMaterial2);
        testset.addMaterialToList(testMaterial3);

        assertEquals("Chains 200\nStakes 300\n",testset.materialsInThisSet());
    }

    @Test
    void testMaterialInThisSetLong() {
        testset.addMaterialToList(testMaterial2);
        testset.addMaterialToList(testMaterial1);
        testset.addMaterialToList(testMaterial3);

        assertEquals("Chains 200\nBones 100\nStakes 300\n",testset.materialsInThisSet());
    }

    @Test
    void testTimeNeededToFinishSet() {
        testset.addMaterialToList(testMaterial1);
        assertEquals(10000,testset.timeNeededToFinishSet());
    }

    @Test
    void testTimeNeededToFinishSetMoreThanOne() {
        testset.addMaterialToList(testMaterial2);
        testset.addMaterialToList(testMaterial1);
        testset.addMaterialToList(testMaterial3);
        assertEquals(110000,testset.timeNeededToFinishSet());
    }

    @Test
    void testStaminaNeededToFinishSet() {
        testset.addMaterialToList(testMaterial1);
        assertEquals(1000,testset.staminaNeededToFinishSet());
    }

    @Test
    void testStaminaNeededToFinishSetMoreThanOne() {
        testset.addMaterialToList(testMaterial2);
        testset.addMaterialToList(testMaterial1);
        testset.addMaterialToList(testMaterial3);
        assertEquals(11000,testset.staminaNeededToFinishSet());
    }

    @Test
    void testAddSetOfMaterials() {
        SetOfMaterials testset1 = new SetOfMaterials();
        SetOfMaterials output = new SetOfMaterials();
        testset1.addMaterialToList(testMaterial2);
        testset1.addMaterialToList(testMaterial1);
        testset.addMaterialToList(testMaterial2);
        testset.addMaterialToList(testMaterial1);
        testset.addMaterialToList(testMaterial3);
        testset.addSetOfMaterials(testset1);
        assertEquals("Chains 200\n" +
                "Bones 100\n" +
                "Chains 200\n" +
                "Bones 100\n" +
                "Stakes 300\n",
                testset1.materialsInThisSet());
    }

    @Test
    void testAddMaterials() {
        SetOfMaterials testset1 = new SetOfMaterials();
        testset1.addMaterialToList(testMaterial2);
        testset1.addMaterialToList(testMaterial2);
        assertEquals("Chains 200\n" +
                "Chains 200\n",testset1.materialsInThisSet());
    }

    @Test
    void testRemoveMaterial() {
        SetOfMaterials testset1 = new SetOfMaterials();
        testset1.addMaterialToList(testMaterial2);
        testset1.addMaterialToList(testMaterial2);
        try {
            testset1.removeMaterial(1);
        } catch (OutOfBoundsException e) {
            fail("Exception not expected");
        }
        assertEquals("Chains 200\n", testset1.materialsInThisSet());
    }

    @Test
    void testConvertToArrayList() {
        SetOfMaterials testset1 = new SetOfMaterials();
        testset1.addMaterialToList(testMaterial2);
        testset1.addMaterialToList(testMaterial2);
        ArrayList<Material> testArray = testset1.convertToArrayList();
        ArrayList<Material> testArrayCorrect = new ArrayList<>();
        testArrayCorrect.add(testMaterial2);
        testArrayCorrect.add(testMaterial2);
        assertEquals(testArrayCorrect,testArray);

    }

    @Test
    void testThrowExceptionGreaterThanIndex() {
        SetOfMaterials testset1 = new SetOfMaterials();
        testset1.addMaterialToList(testMaterial2);
        testset1.addMaterialToList(testMaterial2);
        try {
            testset1.removeMaterial(3);
        } catch (OutOfBoundsException e) {
        }
    }

}