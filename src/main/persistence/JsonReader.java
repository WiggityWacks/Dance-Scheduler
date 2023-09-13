package persistence;

import model.DanceClass;
import model.DanceClassList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.DanceType;
import model.Dancer;
import org.json.*;



/***************************************************************************************
 *    Title: JsonSerializationDemo
 *    Author: CPSC210
 *    Date: 2023
 *    Code version: 1.0
 *    Availability: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 ***************************************************************************************/
// Represents a reader that reads dance class list from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads dance class list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public DanceClassList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDanceClassList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses dance schedule from JSON object and returns it
    private DanceClassList parseDanceClassList(JSONObject jsonObject) {
        DanceClassList dcl = new DanceClassList();
        addDanceClasses(dcl, jsonObject);
        return dcl;
    }

    // MODIFIES: dcl
    // EFFECTS: parses dance classes from JSON object and adds them to dance class list
    private void addDanceClasses(DanceClassList dcl, JSONObject jsonObject) {
        JSONArray jsonarray = jsonObject.getJSONArray("classes");
        for (Object json: jsonarray) {
            JSONObject nextDanceClass = (JSONObject) json;
            addDanceClass(dcl, nextDanceClass);
        }
    }

    // MODIFIES: dcl
    // EFFECTS: parses dance class from JSON object and adds it to dance class list
    private void addDanceClass(DanceClassList dcl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String weekday = jsonObject.getString("weekday");
        DanceType danceType = new DanceType(jsonObject.getString("dance type"));
        DanceClass danceClass = new DanceClass(name, weekday);
        JSONArray jsonarray = jsonObject.getJSONArray("dancers");
        for (Object json: jsonarray) {
            JSONObject nextDancer = (JSONObject) json;
            addDancer(danceClass, nextDancer);
        }
        danceClass.setDanceType(danceType);
        dcl.addClass(danceClass);
    }

    // MODIFIES: dcl
    // EFFECTS: parses list of dancers from JSON object and adds it to the DanceClass
    private void addDancer(DanceClass danceClass, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        Dancer dancer = new Dancer(name, age);
        danceClass.addDancer(dancer);
    }

}
