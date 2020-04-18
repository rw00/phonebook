package org.rw.phonebook.controller;

import javax.servlet.http.HttpSession;

import org.rw.phonebook.entity.User;
import org.rw.phonebook.functions.MainFunctions;
import org.rw.phonebook.service.UserManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SignupController {
    public SignupController() {
    }


    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        User u = new User();
        model.addAttribute("newUser", u);
        return "signup";
    }

    @RequestMapping(value = "/checkSignup", method = RequestMethod.POST)
    public String checkSignup(@ModelAttribute("newUser") User newUser,
        @RequestParam("confirmPass") String confirmPass, HttpSession session) {
        if (UserManager.userExists(newUser.getPhoneNumber())) {
            session.setAttribute("errorMsg", "An account already exists with this phone number!");
            return "redirect:/error";
        }
        if (!MainFunctions.isPhoneNumber(newUser.getPhoneNumber())) {
            session.setAttribute("errorMsg", "Please enter your correct phone number!");
            return "redirect:/error";
        }
        if (!MainFunctions.checkPasswords(newUser.getPassword(), confirmPass)) {
            session.setAttribute("errorMsg", "Please make sure your password is at least 8 characters long.\nNo spaces are allowed.\nAnd check if the passwords match!");
            return "redirect:/error";
        }
        if (!MainFunctions.isValidEmail(newUser.getEmail())) {
            session.setAttribute("errorMsg", "Please enter your correct email address!");
            return "redirect:/error";
        }
        if (!MainFunctions.validUserInfo(newUser.getFirstName(), newUser.getLastName(), newUser.getAddress())) {
            session.setAttribute("errorMsg", "Please enter your real name and address information!");
            return "redirect:/error";
        }
        MainFunctions.fixUserInfo(newUser);
        UserManager.createUser(newUser);
        session.setAttribute("newAccount", newUser.getPhoneNumber());
        return "redirect:/login";
    }
}
