package org.launchcode.models;

import java.util.List;

/**
 * Created by jeannie on 5/1/17.
 */
public class Brainbow {

    private List<Subject> subjects;

    public Brainbow() {
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }
}
