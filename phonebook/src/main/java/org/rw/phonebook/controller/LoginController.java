package org.rw.phonebook.controller;

import javax.servlet.http.HttpSession;

import org.rw.phonebook.entity.User;
import org.rw.phonebook.service.UserManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {
    public LoginController() {
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpSession session) {
        Integer adminId = (Integer) session.getAttribute("adminId");
        if (adminId != null) {
            return "redirect:/admin/";
        } else {
            Integer userId = (Integer) session.getAttribute("userId");
            if (userId != null) {
                return "redirect:/user/";
            } else {
                return "login";
            }
        }
    }

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    public String checkLogin(@RequestParam("phoneNumber") String phoneNumber,
        @RequestParam("password") String password, HttpSession session) {
        User user = UserManager.findUserByPhoneNumberAndPassword(phoneNumber, password);
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
