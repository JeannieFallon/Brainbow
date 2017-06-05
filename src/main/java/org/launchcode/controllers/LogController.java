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
 * Created by jeannie on 6/4/17.
 */
@Controller
@RequestMapping(value = "brainbow/log")
public class LogController {


    @Autowired
    SubjectDao subjectDao;

    @Autowired
    LogDao logDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String log(Model model) {
        model.addAttribute("title","Log Worktime");
        model.addAttribute("subjects",subjectDao.findAll());
        model.addAttribute(new Log());
        return "brainbow/log";
    }

    @RequestMapping(value = "", method = RequestMethod.POST, params={"ids"})
    public String log(@ModelAttribute @Valid Log log, @RequestParam("ids") int[] ids,
                      Errors errors, Model model) {

        if(errors.hasErrors()) {
            model.addAttribute("title","Log Worktime");
            model.addAttribute("subjects",subjectDao.findAll());
            return "brainbow/log";
        }

        //TODO: replace List of Subjects with List of Subject names only
        //List to set subjectNames field for Log
        List<String> subjectNames = new ArrayList<String>();

        //divide time logged by number of selected subjects
        int dividedTime = log.getTimeToLog() / ids.length;

        for(int id : ids) {
            Subject subject = subjectDao.findOne(id);

            //add Subject name to subjectNames for Log
            subjectNames.add(subject.getName());

            //add time entered to previous time
            int newTime = dividedTime + subject.getTime();
            subject.setTime(newTime);
            subjectDao.save(subject);
        }

        log.setSubjectNames(subjectNames);
        logDao.save(log);

        return "redirect:";
    }

    //Overload Log POST method to handle no checkboxes selected
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String log(@ModelAttribute @Valid Log log, Errors errors, Model model) {
        model.addAttribute("title","Log Worktime");
        model.addAttribute("subjects",subjectDao.findAll());
        model.addAttribute("noCheckboxError", "You must select at least one subject.");
        return "brainbow/log";
    }

}
