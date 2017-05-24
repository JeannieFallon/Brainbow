package org.launchcode.controllers;

import org.launchcode.data.SubjectDao;
import org.launchcode.models.Subject;
import org.launchcode.models.SubjectColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Created by jeannie on 5/1/17.
 */
@Controller
@RequestMapping(value = "brainbow/subject")
public class SubjectController {

    @Autowired
    private SubjectDao subjectDao;


    @RequestMapping(value = "")
    public String index(Model model) {
        Iterable<Subject> subjects = subjectDao.findAll();
        model.addAttribute("title", "Current Subjects");
        model.addAttribute("subjects",subjects);
        return "brainbow/subject/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Subject");
        model.addAttribute(new Subject());
        model.addAttribute("subjectColors", SubjectColor.values());
        return "brainbow/subject/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@ModelAttribute @Valid Subject subject, Errors errors, Model model) {

        if(errors.hasErrors()){
            model.addAttribute("title","Add Subject");
            model.addAttribute("subjectColors", SubjectColor.values());
            return "brainbow/subject/add";
        }

        subjectDao.save(subject);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String remove(Model model) {
        model.addAttribute("title", "Remove Subject");
        model.addAttribute("subjects", subjectDao.findAll());
        return "brainbow/subject/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String remove(@RequestParam int[] ids) {

        for(int id: ids) {
            subjectDao.delete(id);
        }

        return "redirect:";

    }
}
