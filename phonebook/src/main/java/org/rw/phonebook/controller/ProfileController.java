package org.rw.phonebook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ProfileController {
    public ProfileController() {
    }


    @RequestMapping("/profile")
    public String profile(HttpSession session) {
        Integer adminId = (Integer) session.getAttribute("adminId");
        Integer userId = (Integer) session.getAttribute("userId");
        if (adminId != null) {
            return "redirect:/admin/profile";
        } else if (userId != null) {
            return "redirect:/user/profile";
        } else {
            return "login";
        }
    }
}
