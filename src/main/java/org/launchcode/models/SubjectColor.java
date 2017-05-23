package org.launchcode.models;

/**
 * Created by jeannie on 5/22/17.
 */
public enum  SubjectColor {

    RED ("Red"),
    ORANGE ("Orange"),
    YELLOW ("Yellow"),
    GREEN ("Green"),
    BLUE ("Blue");

    private final String color;

    SubjectColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
