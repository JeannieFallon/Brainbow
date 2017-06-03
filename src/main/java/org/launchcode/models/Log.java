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

    @NotNull
    @Max(500)
    private int timeToLog;

    @OneToMany
    @JoinColumn(name = "log_id")
    private List<Subject> subjects = new ArrayList<Subject>();

    public Log() {

    }

    public int getId() {
        return id;
    }

    public int getTimeToLog() {
        return timeToLog;
    }

    public void setTimeToLog(int timeToLog) {
        this.timeToLog = timeToLog;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
