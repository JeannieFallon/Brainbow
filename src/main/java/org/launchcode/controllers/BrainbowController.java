package org.launchcode.controllers;

import org.launchcode.data.SubjectDao;
import org.launchcode.models.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * Created by jeannie on 4/29/17.
 */
@Controller
@RequestMapping(value = "brainbow")
public class BrainbowController {

    @Autowired
    private SubjectDao subjectDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        Iterable<Subject> subjects = subjectDao.findAll();

        /* Calculate which subject(s) user should work on next, based on lowest worktime */

        //declare list of subjects with lowest worktime
        //use ArrayList in case multiple subjects share same lowest worktime
        ArrayList<Subject> lowestTimeSubjects = new ArrayList<Subject>();

        //check for no subjects
        if(subjects != null) {

            //convert Iterable to List in order to access first subject directly
            ArrayList<Subject> subjectsList = new ArrayList<Subject>();
                for(Subject subject: subjects) {
                    subjectsList.add(subject);
                }

            //set lowest time to first subject as baseline
            int lowestTime = subjectsList.get(0).getTime();

            for(Subject subject : subjectsList) {

                int subjectTime = subject.getTime();

                //for subject with new lowest time, reset baseline and list
                if(subjectTime < lowestTime) {
                    lowestTime = subjectTime;
                    lowestTimeSubjects.clear();
                    lowestTimeSubjects.add(subject);

                //for subject with equal lowest time, add subject to list
                } else if(subjectTime == lowestTime) {
                    lowestTimeSubjects.add(subject);
                }
            }
        }

        model.addAttribute("title", "Home");
        //pass in Iterable of subjects to display in eventual Brainbow graphic
        model.addAttribute("subjects",subjects);
        //pass in List of subject(s) with lowest worktime to suggest next subject to user
        model.addAttribute("lowestTimeSubjects",lowestTimeSubjects);
        return "brainbow/index";
    }


    @RequestMapping(value = "log", method = RequestMethod.GET)
    public String log(Model model) {
        model.addAttribute("title","Log Worktime");
        model.addAttribute("subjects",subjectDao.findAll());
        return "brainbow/log";
    }


    @RequestMapping(value = "log", method = RequestMethod.POST)
    public String log(@RequestParam("time") int time, @RequestParam("subjectId") int subjectId) {

        Subject subjectToEdit = subjectDao.findOne(subjectId);

        //add time logged to previous time
        int newTime = time + subjectToEdit.getTime();
        subjectToEdit.setTime(newTime);

        subjectDao.save(subjectToEdit);

        return "redirect:";
    }


    @RequestMapping(value = "about")
    public String about(Model model) {
        model.addAttribute("title", "About");
        return "brainbow/about";
    }

    //TODO: add ability to reset time on all subjects to 0
}
