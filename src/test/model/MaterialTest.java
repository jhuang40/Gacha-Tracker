package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.JsonWriter;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class MaterialTest {
    private Material testMaterial;

    @BeforeEach
    void runBefore() {
        testMaterial = new Material("Bones", 100, 10, 100);
    }

    @Test
    void testMaterialNeeded() {
        assertEquals("Bones 100", testMaterial.materialNeeded());
    }
  
    @Test
    void testMaterialName() {
        assertEquals("Bones", testMaterial.getMaterialName());
    }
}