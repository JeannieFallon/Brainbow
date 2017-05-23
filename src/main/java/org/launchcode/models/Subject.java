package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jeannie on 5/1/17.
 */
@Entity
public class Subject {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private SubjectColor color;

    public Subject() {

    }

    public Subject(String name) {
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
