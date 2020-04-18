package org.rw.phonebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
    private static final String GREETING = "Welcome to <span class='title'>RW!</span> Contacts App!";
    private static final String DISCLAIMER = "The Best Free Contacts App On The Web!";


    public IndexController() {
    }


    @RequestMapping("/")
    public String index0(Model model) {
        model.addAttribute("greeting", GREETING);
        model.addAttribute("disclaimer", DISCLAIMER);

        return "index";
    }

    @RequestMapping("/index")
    public String index(Model model) {
        return index0(model);
    }
}
