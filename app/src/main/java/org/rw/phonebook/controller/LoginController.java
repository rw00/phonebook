package org.rw.phonebook.controller;

import javax.servlet.http.HttpSession;
import org.rw.phonebook.entity.User;
import org.rw.phonebook.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {
    @GetMapping(value = "/login")
    public String login(HttpSession session) {
        Long adminId = (Long) session.getAttribute("adminId");
        if (adminId != null) {
            return "redirect:/admin/";
        } else {
            Long userId = (Long) session.getAttribute("userId");
            if (userId != null) {
                return "redirect:/user/";
            } else {
                return "login";
            }
        }
    }

    @PostMapping(value = "/checkLogin")
    public String checkLogin(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("password") String password, HttpSession session) {
        User user = UserService.findUserByPhoneNumberAndPassword(phoneNumber, password);
        if (user != null) {
            if (user.isAdmin()) {
                session.setAttribute("adminId", user.getUserId());
                return "redirect:/admin/";
            } else {
                session.setAttribute("userId", user.getUserId());
                return "redirect:/user/";
            }
        } else {
            session.setAttribute("errorMsg", "Please enter your correct phone number and password!\nIf you don't have account, please sign up for free!");
            return "redirect:/error";
        }
    }
}
