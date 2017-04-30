package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jeannie on 4/29/17.
 */
@Controller
@RequestMapping(value = "brainbow")
public class BrainbowController {

    @RequestMapping(value = "")
    public String index() {
        return "brainbow/index";
    }

    @RequestMapping(value = "about")
    public String about() {
        return "brainbow/about";
    }
}
