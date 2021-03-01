package model;

import org.json.JSONObject;

//Represents a Material with a name, a quantity, time per drop and stamina per drop
public class Material {

    private String name;
    private int quantityneeded;
    private int timeperdrop;
    private int staminaperdrop;

    // EFFECTS: Creates a new material with a name, number of needed material, stamina per drop, time per drop
    //         measured in seconds
    public Material(String materialname, int quantity, int stamina, int seconds) {
        this.name = materialname;
        this.quantityneeded = quantity;
        this.staminaperdrop = stamina;
        this.timeperdrop = seconds;
    }

    // EFFECTS: return the total stamina needed to obtain the number of materials needed
    public int totalStaminaNeeded() {
        return getNumberOfMaterials() * getStaminaPerDrop();
    }

    // EFFECTS: return the total time needed to obtain the number of materials needed
    public int totalTimeNeeded() {
        return getNumberOfMaterials() * getTimePerDrop();
    }

    public int getNumberOfMaterials() {
        return quantityneeded;
    }

    public int getTimePerDrop() {
        return timeperdrop;
    }

    public int getStaminaPerDrop() {
        return staminaperdrop;
    }

    // EFFECTS: Outputs the materials name and amount needed of the material
    public String materialNeeded() {
        String numberofmaterialsstring;
        numberofmaterialsstring = Integer.toString(quantityneeded);
        return name + " " + numberofmaterialsstring;
    }

    // EFFECTS: return the name of the material
    public String getMaterialName() {
        return name;
    }

    // EFFECTS: Turns a material into a json
    public JSONObject materialToJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("quantity", quantityneeded);
        json.put("time", timeperdrop);
        json.put("stamina", staminaperdrop);
        return json;
    }


}
