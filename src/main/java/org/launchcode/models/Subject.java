package org.launchcode.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by jeannie on 5/1/17.
 */
@Entity
public class Subject {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 35, message = "Subject should be a general area of study.")
    private String name;

    private int time;                   //time will be measured in minutes
    private double timePercentage;      //will store relation of subject's time to total time
    private SubjectColor color;

    public Subject() {

    }

    public Subject(String name) {
        this.name = name;
        this.time = 0;
        this.timePercentage = 0;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getTimePercentage() {
        return timePercentage;
    }

    public void setTimePercentage(double timePercentage) {
        this.timePercentage = timePercentage;
    }

    public SubjectColor getColor() {
        return color;
    }

    public void setColor(SubjectColor color) {
        this.color = color;
    }
}
