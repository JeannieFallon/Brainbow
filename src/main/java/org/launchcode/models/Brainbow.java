package org.launchcode.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeannie on 5/1/17.
 */
@Entity
public class Brainbow {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany
    @JoinColumn(name = "brainbow_id")
    private List<Subject> subjects = new ArrayList<Subject>();

    //private List<Cheese> cheeses = new ArrayList<Cheese>();


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
