package model;

// Represents the types of dances a dance class can offer (ex. hip hop, ballet, etc.)
public class DanceType {
    private String name;

    public DanceType(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}