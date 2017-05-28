package org.launchcode.controllers;

import org.launchcode.data.SubjectDao;
import org.launchcode.models.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("title", "Home");
        //pass in an iterable of subjects to display on eventual Brainbow graphic
        model.addAttribute("subjects",subjectDao.findAll());
        return "brainbow/index";
    }


    @RequestMapping(value = "log", method = RequestMethod.GET)
    public String log(Model model) {
        model.addAttribute("title","Log Worktime");
        model.addAttribute("subjects",subjectDao.findAll());
        return "brainbow/log";
    }


    @RequestMapping(value = "log", method = RequestMethod.POST)
    public String log(@RequestParam("time") int time, @ModelAttribute int subjectId) {

        Subject subjectToEdit = subjectDao.findOne(subjectId);
        int timeToLog = time + subjectToEdit.getTime();

        subjectToEdit.setTime(timeToLog);
        subjectDao.save(subjectToEdit);

        return "redirect:";
    }


    @RequestMapping(value = "about")
    public String about(Model model) {
        model.addAttribute("title", "About");
        return "brainbow/about";
    }
}
