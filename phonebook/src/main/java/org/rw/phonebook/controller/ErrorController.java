package org.rw.phonebook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ErrorController {
    public ErrorController() {
    }


    @RequestMapping(value = "/error")
    public String error(HttpSession session, ModelMap map) {
        map.addAttribute("errorMsg", session.getAttribute("errorMsg"));
        session.setAttribute("errorMsg", null);
        return "error";
    }
}
