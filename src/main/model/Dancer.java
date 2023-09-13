package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a dancer with a name and age
public class Dancer implements Writable {
    private String name;
    private int age;

    // REQUIRES: age >= 0
    // EFFECTS: creates a dancer with name, age
    public Dancer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("age", age);
        return json;
    }


    /*
    // REQUIRES: name is a string with non-zero length
    // MODIFIES: this
    // EFFECTS: sets the name of the dancer to the given name
    public void setName(String name) {
        // stub
    }

    // REQUIRES: age >=0
    // MODIFIES: this
    // EFFECTS: sets the age of the dancer to the given name
    public void setAge(int age) {
        // stub
    }

    public ArrayList getClassList() {
        return null;
    }
     */

}
