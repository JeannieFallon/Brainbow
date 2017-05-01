package org.launchcode.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jeannie on 5/1/17.
 */
@Controller
@RequestMapping(value = "subject")
public class SubjectController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Current Subjects");
        return "subject/index";
    }

    @RequestMapping(value = "add")
    public String add(Model model) {
        model.addAttribute("title", "Add Subject");
        return "subject/add";
    }

    @RequestMapping(value = "remove")
    public String remove(Model model) {
        model.addAttribute("title", "Remove Subject");
        return "subject/remove";
    }
}
