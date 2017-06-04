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
    private Integer timeToLog; //make Integer to avoid default value 0 on log input

    //use List of full Subject objects in order to expand log analysis later
    @OneToMany
    @JoinColumn(name = "log_id")
    private List<Subject> subjects = new ArrayList<Subject>();

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

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    //convert List of Subjects to String of Subject names for display on History page
    //does this violate encapsulation? is this a data operation better performed elsewhere?
    public String convertSubjectsToString(List<Subject> subjects) {

        String subjectsString = new String();
        subjectsString += " | ";
        for(Subject subject : subjects) {
            subjectsString += subject.getName() + " | ";
        }

        //String subjectsString = StringUtils.collectionToDelimitedString(subjects, "|");
        return subjectsString;
    }
}
