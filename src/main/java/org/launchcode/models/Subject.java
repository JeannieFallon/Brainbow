package org.launchcode.models;

/**
 * Created by jeannie on 5/1/17.
 */
public class Subject {

    private int id;
    private String name;
    private SubjectColor color;

    public Subject() {

    }

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
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

    public SubjectColor getColor() {
        return color;
    }

    public void setColor(SubjectColor color) {
        this.color = color;
    }
}
