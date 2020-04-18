package org.rw.phonebook.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ErrorController {
    private static final String ERROR_MSG_ATTRIBUTE = "errorMsg";

    @RequestMapping(value = "/error")
    public String error(HttpSession session, ModelMap map) {
        map.addAttribute(ERROR_MSG_ATTRIBUTE, session.getAttribute(ERROR_MSG_ATTRIBUTE));
        session.setAttribute(ERROR_MSG_ATTRIBUTE, null);
        return "error";
    }
}
