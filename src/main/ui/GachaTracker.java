//package ui;
//
//
//import model.Material;
//import model.SetOfMaterials;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Scanner;
//
////Gacha tracker application
//public class GachaTracker {
//    private static final String JSON_STORE = "./data/total.json";
//    private Material mat;
//    private SetOfMaterials set;
//    private SetOfMaterials total;
//    private Scanner userInput;
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//
//
//    // EFFECTS: Runs the gacha tracker application
//    public GachaTracker() {
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//        runGachaTracker();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user input
//    private void runGachaTracker() {
//        boolean keepGoing = true;
//        String command = null;
//
//        init();
//
//        while (keepGoing) {
//            displayMenu();
//            command = userInput.next();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processCommand(command);
//            }
//        }
//
//        System.out.println("\nGoodbye!");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user command
//    private void processCommand(String command) {
//        if (command.equals("m")) {
//            createNewMaterial();
//        } else if (command.equals("n")) {
//            createNewSet();
//        } else if (command.equals("t")) {
//            printTotalMaterials();
//        } else if (command.equals("s")) {
//            saveTotalMaterials();
//        } else if (command.equals("l")) {
//            loadTotalMaterials();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: Creates a new material and prints the time and stamina needed to finish obtaining the material
//    private void createNewMaterial() {
//        Scanner a = new Scanner(System.in);
//        Scanner b = new Scanner(System.in);
//        Scanner c = new Scanner(System.in);
//        Scanner d = new Scanner(System.in);
//
//        System.out.println("Enter the name of the material: ");
//        String name = a.nextLine();
//
//        System.out.println("Enter the quantity of the material you need: ");
//        int amount = b.nextInt();
//
//        System.out.println("Enter the average stamina per drop of the material: ");
//        int avgdrop = c.nextInt();
//
//        System.out.println("Enter the time it takes to get one on average: ");
//        int time = d.nextInt();
//
//        mat = new Material(name, amount, avgdrop, time);
//        System.out.println(name + "\nNeed: " + amount + "\nAverage Stamina Per Drop: "
//                + avgdrop + "\nAverage Time Per Run: " + time);
//        System.out.println("\nStamina needed to complete this material: " + mat.totalStaminaNeeded()
//                + "\nTime needed to complete this material: " + mat.totalTimeNeeded() + "s");
//    }
//
//    // REQUIRES: name must be a string, quantity, average drop and time must be an int > 0
//    // EFFECTS: create a new set of materials and can choose to add it to the total. Also shows the total stamina and
//    //          time needed to finish the set
//    private void createNewSet() {
//        set = new SetOfMaterials();
//        System.out.println("How many different materials do you want to add to the set: ");
//        int amount = userInput.nextInt();
//        for (int x = 0; x < amount; x++) {
//            Scanner a = new Scanner(System.in);
//            Scanner b = new Scanner(System.in);
//            Scanner c = new Scanner(System.in);
//            Scanner d = new Scanner(System.in);
//            System.out.println("Enter the name of the material: ");
//            String name = a.nextLine();
//            System.out.println("Enter the quantity of the material you need: ");
//            int quantity = b.nextInt();
//            System.out.println("Enter the average stamina per drop of the material: ");
//            int avgdrop = c.nextInt();
//            System.out.println("Enter the time it takes to get one on average: ");
//            int time = d.nextInt();
//            mat = new Material(name, quantity, avgdrop, time);
//            set.addMaterialToList(mat);
//        }
//        System.out.println(set.materialsInThisSet());
//        System.out.println("\nStamina needed to complete this set of materials: " + set.staminaNeededToFinishSet()
//                         + "\nTime needed to complete this set of materials: " + set.timeNeededToFinishSet() + "s");
//        addToTotal(set);
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: adds a set of materials to the total
//    private void addToTotal(SetOfMaterials set) {
//        Scanner z = new Scanner(System.in);
//
//        System.out.println("Add this set to the list of total materials? y/n: ");
//        String answer = z.nextLine();
//        if (answer.equals("y")) {
//            set.addSetOfMaterials(total);
//            System.out.println("Succesfully added!");
//        }
//
//    }
//
//    //EFFECTS: prints the total materials used
//    private void printTotalMaterials() {
//        System.out.println(total.materialsInThisSet());
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initializes Scanner and TotalMaterialsUsed
//    private void init() {
//        total = new SetOfMaterials();
//        userInput = new Scanner(System.in);
//    }
//
//    // EFFECTS: displays menu of options to user
//    private void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\tm -> Create a new material");
//        System.out.println("\tn -> Create a new set of materials");
//        System.out.println("\tt -> Vew the list of total materials used");
//        System.out.println("\ts -> Save the current list of total materialss");
//        System.out.println("\tl -> Load the current list of total materials");
//        System.out.println("\tq -> quit");
//    }
//
//    //EFFECTS: loads the total materials used
//    private void saveTotalMaterials() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(total);
//            jsonWriter.close();
//            System.out.println("Saved the total materials used");
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write file");
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads total materials used from file
//    private void loadTotalMaterials() {
//        try {
//            total = jsonReader.read();
//            System.out.println("Loaded total materials used");
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//}
