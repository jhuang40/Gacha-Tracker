package model;

import exceptions.OutOfBoundsException;
import org.json.JSONArray;

import java.util.ArrayList;

// Represents a Set of Materials

public class SetOfMaterials {
    private ArrayList<Material> materialset;

    // Creates an empty list of materials
    public SetOfMaterials() {
        materialset = new ArrayList<>();
    }

    // REQUIRES: The item being added cannot be already in the set
    // MODIFIES: This
    // EFFECTS: Adds materials to list
    public void addMaterialToList(Material m) {
        materialset.add(m);
    }

    // Returns the time needed in seconds to finish the task
    public int timeNeededToFinishSet() {
        int totaltime;
        totaltime = 0;
        for (Material m: materialset) {
            totaltime += m.totalTimeNeeded();
        }
        return totaltime;
    }

    // Returns the stamina needed to finish the set
    public int staminaNeededToFinishSet() {
        int totalstamina;
        totalstamina = 0;
        for (Material m: materialset) {
            totalstamina += m.totalStaminaNeeded();
        }
        return totalstamina;
    }

    // EFFECTS: Returns the total materials in this set in a string format with the material name and the quantity
    //         of each one
    // CITATION: https://www.tutorialspoint.com/How-to-create-a-string-from-a-Java-ArrayList
    public String materialsInThisSet() {
        ArrayList<String> temp;
        temp = new ArrayList<>();
        for (Material mat: materialset) {
            temp.add(mat.materialNeeded());
        }

        StringBuffer sb = new StringBuffer();
        for (String s: temp) {
            sb.append(s);
            sb.append("\n");
        }

        return sb.toString();
    }

    // EFFECTS: returns setofmaterials as a JSON array
    public JSONArray setOfMaterialsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Material mat : materialset) {
            jsonArray.put(mat.materialToJson());
        }

        return jsonArray;
    }

    // EFFECTS: Adds a set of materials to a set of materials
    // MODIFIES: this
    public void addSetOfMaterials(SetOfMaterials set) {
        for (Material mat : materialset) {
            set.addMaterialToList(mat);
        }
    }

    // EFFECTS: Converts SetOfMaterials into an ArrayList<Material
    public ArrayList<Material> convertToArrayList() {
        ArrayList<Material> output = new ArrayList<Material>();
        for (Material mat: materialset) {
            output.add(mat);
        }
        return output;
    }

    // EFFECTS: Remove material at indec i and throws OutOfBoundsException if the index is greater than the size of the
    //          array
    public void removeMaterial(int i) throws OutOfBoundsException {
        if (i > materialset.size()) {
            throw new OutOfBoundsException("Index is out of bounds");
        } else {
            materialset.remove(i);
        }
    }

}
