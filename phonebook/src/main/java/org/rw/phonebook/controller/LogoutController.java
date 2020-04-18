package org.rw.phonebook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LogoutController {
    public LogoutController() {
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("adminId", null);
        session.setAttribute("userId", null);
        session.setAttribute("uid", null);
        session.invalidate();
        return "redirect:/login";
    }
}
