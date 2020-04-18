package org.rw.phonebook.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
    @RequestMapping("/home")
    public String home(HttpSession session) {
        Long adminId = (Long) session.getAttribute("adminId");
        if (adminId != null) {
            return "redirect:/admin/";
        } else {
            Long userId = (Long) session.getAttribute("userId");
            if (userId != null) {
                return "redirect:/user/";
            } else {
                return "redirect:/login";
            }
        }
    }
}
