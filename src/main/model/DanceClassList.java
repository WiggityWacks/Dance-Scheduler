package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents the studio with booking schedule for a regular week
public class DanceClassList implements Writable {
    private List<DanceClass> classes;

    // EFFECTS: creates a blank weekly studio schedule
    public DanceClassList() {
        classes = new ArrayList<>();
    }

    // EFFECTS: returns weekly schedule (full list of classes)
    public List<DanceClass> getWeeklySchedule() {
        return classes;
    }

    public List<String> scheduleToString(List<DanceClass> classSchedule) {
        List<String> classNames = new ArrayList<>();
        for (DanceClass danceClass: classSchedule) {
            classNames.add(danceClass.getClassName());
        }
        return classNames;
    }

    // EFFECTS: returns schedule for a single day
    public List<DanceClass> getDailySchedule(String day) {
        List<DanceClass> filteredClasses = new ArrayList<>();
        for (DanceClass dc: classes) {
            if (dc.getClassDate().equals(day)) {
                filteredClasses.add(dc);
            }
        }

        return filteredClasses;
    }

    // EFFECTS: returns a list of all classes of a certain dance type
    public List<DanceClass> getDanceTypeList(DanceType danceType) {
        List<DanceClass> filteredClasses = new ArrayList<>();
        for (DanceClass dc: classes) {
            if (dc.getDanceType() == danceType) {
                filteredClasses.add(dc);
            }
        }
        return filteredClasses;
    }

    // MODIFIES: this
    // EFFECTS: adds class to list.
    public void addClass(DanceClass newClass) {
        classes.add(newClass);
        EventLog.getInstance().logEvent(new Event(newClass.getClassName() + " has been added to the schedule."));
    }

    // MODIFIES: this
    // EFFECTS: removes class from list
    public void removeClass(int i) {
        EventLog.getInstance().logEvent(new Event(classes.get(i).getClassName() + " has been successfully removed."));
        classes.remove(i);
    }

    public DanceClass getClass(int i) {
        return classes.get(i);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("classes", classesToJson());
        return json;
    }


    /***************************************************************************************
     *    Title: JsonSerializationDemo
     *    Author: CPSC210
     *    Date: 2023
     *    Code version: 1.0
     *    Availability: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
     ***************************************************************************************/
    // EFFECTS: returns classes in the schedule as a JSON array
    private JSONArray classesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (DanceClass dc: classes) {
            jsonArray.put(dc.toJson());
        }

        return jsonArray;
    }
}
