package org.launchcode.models;

/**
 * Created by jeannie on 5/1/17.
 */
public class Subject {

    private int id;
    private String name;
    private String color;  // make this into an enum

    public Subject() {

    }

    public Subject(int id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
