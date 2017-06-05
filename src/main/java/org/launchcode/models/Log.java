package org.launchcode.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeannie on 5/31/17.
 */
@Entity
public class Log {

    @Id
    @GeneratedValue
    private int id;

    //TODO: set a minimum of one to prevent zero & negative numbers inputs
    @NotNull
    @Max(500)
    private Integer timeToLog; //make Integer to avoid default value 0 on log input

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> subjectNames = new ArrayList<String>();

    public Log() {

    }

    public int getId() {
        return id;
    }

    public Integer getTimeToLog() {
        return timeToLog;
    }

    public void setTimeToLog(int timeToLog) {
        this.timeToLog = timeToLog;
    }

    public List<String> getSubjectNames() {
        return subjectNames;
    }

    public void setSubjectNames(List<String> subjectNames) {
        this.subjectNames = subjectNames;
    }
}
