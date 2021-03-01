package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Material;
import model.SetOfMaterials;
import org.json.*;

// Represents a reader that reads a total set of materials from JSON data stored in file
// Structure of this class is based off of https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads total materials from file and returns it;
    // throws IOException if an error occurs reading data from file
    public SetOfMaterials read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTotalMaterial(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses TotalMaterialsUsed from JSON object and returns it
    private SetOfMaterials parseTotalMaterial(JSONObject jsonObject) {
        SetOfMaterials total = new SetOfMaterials();
        addMaterialsToJson(total, jsonObject);
        return total;
    }

    // MODIFIES: total
    // EFFECTS: parses Set of Materials from JSON object and adds them to total materials
    private void addMaterialsToJson(SetOfMaterials total, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Storage");
        for (Object json : jsonArray) {
            SetOfMaterials set = new SetOfMaterials();
            JSONObject nextMaterial = (JSONObject) json;
            addMaterial(set, nextMaterial);
            set.addSetOfMaterials(total);
        }

    }

    // MODIFIES: total
    // EFFECTS: parses material from JSON object and adds it to Set of Material
    private void addMaterial(SetOfMaterials set, JSONObject jsonObject) {
        int quantity = jsonObject.getInt("quantity");
        int stamina = jsonObject.getInt("stamina");
        String name = jsonObject.getString("name");
        int time = jsonObject.getInt("time");
        Material mat = new Material(name,quantity,stamina,time);
        set.addMaterialToList(mat);
    }
}
