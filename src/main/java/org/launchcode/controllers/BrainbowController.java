package org.launchcode.controllers;

import org.launchcode.data.BrainbowDao;
import org.launchcode.data.SubjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jeannie on 4/29/17.
 */
@Controller
@RequestMapping(value = "brainbow")
public class BrainbowController {

    @Autowired
    private BrainbowDao brainbowDao;

    @Autowired
    private SubjectDao subjectDao;


    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Home");
        //pass in an iterable of subjects to display on eventual Brainbow graphic
        model.addAttribute("subjects",subjectDao.findAll());
        return "brainbow/index";
    }






    @RequestMapping(value = "about")
    public String about(Model model) {
        model.addAttribute("title", "About");
        return "brainbow/about";
    }
}
