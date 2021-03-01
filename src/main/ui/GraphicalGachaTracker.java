package ui;


import model.Material;
import exceptions.OutOfBoundsException;
import model.SetOfMaterials;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

//Graphical Gacha Tracker application

public class GraphicalGachaTracker extends JFrame
        implements ActionListener, DocumentListener {
    private static final String JSON_STORE = "./data/GraphicalTotal.json";
    protected static final String NEW_MATERIAL = "Add Material";
    protected static final String DELETE_MATERIAL = "Delete material";
    protected static final String SAVE_MATERIAL = "Save materials";
    protected static final String LOAD_MATERIAL = "Load materials";
    private String soundName = "videoplayback.wav";
    private JButton deleteButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton addButton;
    private Material mat;
    private SetOfMaterials total;
    private JTable jt;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private JTextField textName;
    private JTextField textQuantity;
    private JTextField textStamina;
    private JTextField textTime;
    private DefaultTableModel model;
    private JFrame mainFrame;

    // EFFECTS: Runs the gacha tracker application and initializes jsonreader and writer
    public GraphicalGachaTracker() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runGachaTracker();
    }

    // EFFECTS: Initialize Jframe, Jtable and adds buttons and table
    public void runGachaTracker() {
        mainFrame = new JFrame("Gacha Tracker");
        jt = new JTable();
        total = new SetOfMaterials();
        createButtons();
        createTable();

        mainFrame.setSize(800, 500);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);
    }


    // EFFECTS: Creates Jtable
    private void createTable() {
        Object[] columns = {"Name","Quantity","Stamina","Time"};
        model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        jt.setModel(model);

        textName = new JTextField();
        textQuantity = new JTextField();
        textStamina = new JTextField();
        textTime = new JTextField();

        addLabels();

        textName.setBounds(300, 25, 100, 25);
        textQuantity.setBounds(300, 75, 100, 25);
        textStamina.setBounds(300, 125, 100, 25);
        textTime.setBounds(300, 175, 100, 25);

        JScrollPane pane = new JScrollPane(jt);
        pane.setBounds(10, 10, 250, 350);

        mainFrame.setLayout(null);

        mainFrame.add(pane);
        mainFrame.add(textName);
        mainFrame.add(textQuantity);
        mainFrame.add(textStamina);
        mainFrame.add(textTime);

        createTableMouseEvent(jt);
    }

    // EFFECTS: Creates table mouse events
    private void createTableMouseEvent(JTable jt) {
        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = jt.getSelectedRow();

                textName.setText(model.getValueAt(i, 0).toString());
                textQuantity.setText(model.getValueAt(i, 1).toString());
                textStamina.setText(model.getValueAt(i, 2).toString());
                textTime.setText(model.getValueAt(i, 3).toString());
            }
        });
    }


    // EFFECTS: Adds labels
    private void addLabels() {
        JLabel nameLabel = new JLabel("Name");
        JLabel staminaLabel = new JLabel("Stamina");
        JLabel quantityLabel = new JLabel("Quantity");
        JLabel timeLabel = new JLabel("Time");

        nameLabel.setBounds(300, 0, 100, 25);
        staminaLabel.setBounds(300, 50, 100, 25);
        quantityLabel.setBounds(300, 100, 100, 25);
        timeLabel.setBounds(300, 150, 100, 25);

        mainFrame.add(nameLabel);
        mainFrame.add(staminaLabel);
        mainFrame.add(quantityLabel);
        mainFrame.add(timeLabel);

    }

    // EFFECTS: Adds buttons
    private void createButtons() {
        addMaterialButton();
        addDeleteMaterialButton();
        addSaveMaterialButton();
        addLoadMaterialsButton();
    }

    // EFFECTS: Adds add material buttons
    private void addMaterialButton() {
        addButton = new JButton("Add Material");
        addButton.setActionCommand(NEW_MATERIAL);
        addButton.addActionListener(this::actionPerformed);
        addButton.setBounds(25, 400, 140, 40);
        mainFrame.add(addButton);
        Object[] row = new Object[4];
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name = textName.getText();
                Integer amount = Integer.parseInt(textQuantity.getText());
                Integer stamina = Integer.parseInt(textStamina.getText());
                Integer time = Integer.parseInt(textTime.getText());

                mat = new Material(name, amount, stamina, time);
                total.addMaterialToList(mat);

                row[0] = textName.getText();
                row[1] = textQuantity.getText();
                row[2] = textStamina.getText();
                row[3] = textTime.getText();

                model.addRow(row);

                createButtonNoise();

            }
        });
    }

    // Creates the on button sound effect sound taken from https://www.youtube.com/watch?v=EV0BdRtLXB4
    private void createButtonNoise() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(
                    "data/" + soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
            unsupportedAudioFileException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // EFFECT: Adds the delete material button
    private void addDeleteMaterialButton() {
        deleteButton = new JButton(DELETE_MATERIAL);
        deleteButton.setBounds(225, 400, 140, 40);
        deleteButton.addActionListener(this::actionPerformed);
        mainFrame.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int i = jt.getSelectedRow();
                if (i >= 0) {
                    model.removeRow(i);
                } else {
                    System.out.println("Delete Error");
                }
                try {
                    total.removeMaterial(i);
                } catch (OutOfBoundsException outOfBoundsException) {
                    outOfBoundsException.printStackTrace();
                }

                createButtonNoise();
            }
        });
    }

    // EFFECTS: Adds the save material button
    private void addSaveMaterialButton() {
        saveButton = new JButton(SAVE_MATERIAL);
        saveButton.setBounds(425, 400, 140, 40);
        saveButton.addActionListener(this::actionPerformed);
        mainFrame.add(saveButton);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveTotalMaterials();
                createButtonNoise();
            }
        });
    }

    // EFFECTS: Adds the load material button
    private void addLoadMaterialsButton() {
        loadButton = new JButton(LOAD_MATERIAL);
        loadButton.setBounds(625, 400, 140, 40);
        loadButton.addActionListener(this::actionPerformed);
        mainFrame.add(loadButton);
        Object[] row = new Object[4];
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    total = jsonReader.read();
                    model.setRowCount(0);
                    addToJlist(row);
                    System.out.println("Loaded total materials used");
                } catch (IOException a) {
                    System.out.println("Unable to read from file: " + JSON_STORE);
                }
                createButtonNoise();
            }
        });
    }

    // EFFECTS: Add material to JList
    private void addToJlist(Object[] row) {
        for (Material mat: total.convertToArrayList()) {
            row[0] = mat.getMaterialName();
            row[1] = mat.getNumberOfMaterials();
            row[2] = mat.getStaminaPerDrop();
            row[3] = mat.getTimePerDrop();
            model.addRow(row);
        }
    }

    // EFFECTS: Saves the total materials used
    private void saveTotalMaterials() {
        try {
            jsonWriter.open();
            jsonWriter.write(total);
            jsonWriter.close();
            System.out.println("Saved the total materials used");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write file");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    public class AddMaterialActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}