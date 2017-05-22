package org.launchcode.controllers;

import org.launchcode.models.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jeannie on 5/1/17.
 */
@Controller
@RequestMapping(value = "brainbow/subject")
public class SubjectController {


    @RequestMapping(value = "")
    public String index(Model model) {
        Subject subject = new Subject(0, "Java", "green");
        model.addAttribute("title", "Current Subjects");
        model.addAttribute("name", subject.getName());
        model.addAttribute("color", subject.getColor());
        return "brainbow/subject/index";
    }

    @RequestMapping(value = "add")
    public String add(Model model) {
        model.addAttribute("title", "Add Subject");
        return "brainbow/subject/add";
    }

    @RequestMapping(value = "remove")
    public String remove(Model model) {
        model.addAttribute("title", "Remove Subject");
        return "brainbow/subject/remove";
    }
}
