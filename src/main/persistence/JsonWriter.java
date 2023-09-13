package persistence;

import model.DanceClassList;
import org.json.JSONObject;

import java.io.*;

/***************************************************************************************
 *    Title: JsonSerializationDemo
 *    Author: CPSC210
 *    Date: 2023
 *    Code version: 1.0
 *    Availability: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 ***************************************************************************************/
// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private PrintWriter writer;
    private String destination;
    private static final int TAB = 4;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of dance schedule to file
    public void write(DanceClassList dcl) {
        JSONObject json = dcl.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}

