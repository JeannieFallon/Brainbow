package org.launchcode.controllers;

import org.launchcode.data.LogDao;
import org.launchcode.data.SubjectDao;
import org.launchcode.models.Log;
import org.launchcode.models.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeannie on 4/29/17.
 */

@Controller
@RequestMapping(value = "brainbow")
public class BrainbowController {

    @Autowired
    private SubjectDao subjectDao;

    @Autowired
    private LogDao logDao;

    @RequestMapping(value = "")
    public String index(Model model) {

        Iterable<Subject> subjects = subjectDao.findAll();

        //convert Iterable to List
        //tally total time using same loop
        ArrayList<Subject> subjectsList = new ArrayList<Subject>();
        double totalTime = 0;
        for(Subject subject: subjects) {
            subjectsList.add(subject);
            totalTime += subject.getTime();
        }

        /* Calculate each subject's percentage of total time */

        //check for division by zero
        if(totalTime > 0) {
            for(Subject subject: subjectsList) {
                int subjectTime = subject.getTime();
                subject.setTimePercentage((subjectTime/totalTime) * 100);
                subjectDao.save(subject);
            }
        }

        /* Find which subject(s) user should work on next, based on lowest worktime */

        //use ArrayList in case multiple subjects share same lowest worktime
        ArrayList<Subject> lowestTimeSubjects = new ArrayList<Subject>();

        //check for no subjects
        if(subjectsList.size() > 0) {

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

        //TEST
        model.addAttribute("totalTime", totalTime);

        model.addAttribute("title", "Home");
        //pass in List of all subjects to display in eventual Brainbow graphic
        model.addAttribute("subjects",subjectsList);
        //pass in List of subject(s) with lowest worktime to suggest next subject to user
        model.addAttribute("lowestTimeSubjects",lowestTimeSubjects);
        return "brainbow/index";
    }


    @RequestMapping(value = "log", method = RequestMethod.GET)
    public String log(Model model) {
        model.addAttribute("title","Log Worktime");
        model.addAttribute("subjects",subjectDao.findAll());
        model.addAttribute(new Log());
        return "brainbow/log";
    }

    @RequestMapping(value = "log", method = RequestMethod.POST)
    public String log(@ModelAttribute @Valid Log log, @RequestParam("ids") int[] ids,
                      Errors errors, Model model) {

        if(errors.hasErrors()) {
            model.addAttribute("title","Log Worktime");
            model.addAttribute("subjects",subjectDao.findAll());
            return "brainbow/log";
        }

        //initialize List for selected subjects

        List<Subject> subjects = new ArrayList<Subject>();

        //divide time logged among selected subjects
        int dividedTime = log.getTimeToLog() / ids.length;

        for(int id : ids) {
            Subject subject = subjectDao.findOne(id);
            //add subject to List of subjects to log
            subjects.add(subject);
            //add time entered to previous time
            int newTime = dividedTime + subject.getTime();
            subject.setTime(newTime);
            subjectDao.save(subject);
        }

        log.setSubjects(subjects);
        logDao.save(log);

        return "redirect:";
    }

    //TODO: Overload log POST method to handle no subjects selected


    @RequestMapping(value = "reset", method = RequestMethod.GET)
    public String reset(Model model) {
        model.addAttribute("title","Reset");
        //pass in subjects for template conditional
        model.addAttribute("subjects",subjectDao.findAll());
        return "brainbow/reset";
    }

    @RequestMapping(value = "reset", method = RequestMethod.POST)
    public String reset() {

        Iterable<Subject> subjects = subjectDao.findAll();

        //set time in each subject to 0
        for(Subject subject : subjects) {
            subject.setTime(0);
            subject.setTimePercentage(0);
            subjectDao.save(subject);
        }

        return "redirect:";
    }


    @RequestMapping(value = "about")
    public String about(Model model) {
        model.addAttribute("title", "About");
        return "brainbow/about";
    }

}
