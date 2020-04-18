package org.rw.phonebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {
    @RequestMapping("/profile")
    public String profile(HttpSession session) {
        Long adminId = (Long) session.getAttribute("adminId");
        Long userId = (Long) session.getAttribute("userId");
        if (adminId != null) {
            return "redirect:/admin/profile";
        } else if (userId != null) {
            return "redirect:/user/profile";
        } else {
            return "login";
        }
    }
}
