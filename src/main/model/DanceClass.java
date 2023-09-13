package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


// Represents a dance class with class name, dance type, a weekday, and a list of enrolled dancers
public class DanceClass implements Writable {
    private String name;
    private String weekday;
    private DanceType danceType;
    private List<Dancer> dancers;


    // REQUIRES: className has non-zero length
    // EFFECTS: name is set to dance class name,
    //          with an empty dance type and list of dancers, and sets weekday to date
    public DanceClass(String name, String weekday) {
        this.name = name;
        this.weekday = weekday;
        dancers = new ArrayList<>();
        EventLog.getInstance().logEvent(new Event("Dance class " + name + " on " + weekday + " has been created."));
    }



    // MODIFIES: this
    // EFFECTS: adds dancer to list of dancers enrolled
    public void addDancer(Dancer dancer) {
        dancers.add(dancer);
        EventLog.getInstance().logEvent(new Event(dancer.getName() + " has been added to " + name + "."));
    }

    public List<Dancer> getDancers() {
        return dancers;
    }

    public void setClassDate(String newWeekDay) {
        this.weekday = newWeekDay;
        EventLog.getInstance().logEvent(new Event(name + " has been set to " + newWeekDay + "."));
    }

    public String getClassDate() {
        return weekday;
    }

    public void setClassName(String newClassName) {
        name = newClassName;
        EventLog.getInstance().logEvent(new Event(name + " has been changed to " + newClassName + "."));
    }

    public String getClassName() {
        return name;
    }

    public void setDanceType(DanceType danceType) {
        this.danceType = danceType;
        EventLog.getInstance().logEvent(new Event(name + "'s dance type has been set to " + danceType.getName() + "."));
    }

    public DanceType getDanceType() {
        return danceType;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("weekday", weekday);
        json.put("dancers", dancersToJson());
        json.put("dance type", danceType.getName());
        return json;
    }

    /***************************************************************************************
     *    Title: JsonSerializationDemo
     *    Author: CPSC210
     *    Date: 2023
     *    Code version: 1.0
     *    Availability: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
     ***************************************************************************************/
    // EFFECTS: returns dancers in this class as a JSON array
    private JSONArray dancersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Dancer d: dancers) {
            jsonArray.put(d.toJson());
        }

        return jsonArray;
    }
}
