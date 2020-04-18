package org.rw.phonebook.controller;

import javax.servlet.http.HttpSession;
import org.rw.phonebook.entity.User;
import org.rw.phonebook.service.UserService;
import org.rw.phonebook.util.Utilities;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class SignupController {
    private static final String ERROR_MSG_ATTRIBUTE = "errorMsg";
    private static final String REDIRECT_ERROR = "redirect:/error";

    @GetMapping(value = "/signup")
    public String signup(Model model) {
        User u = new User();
        model.addAttribute("newUser", u);
        return "signup";
    }

    @PostMapping(value = "/checkSignup")
    public String checkSignup(@ModelAttribute("newUser") User newUser, @RequestParam("confirmPass") String confirmPass, HttpSession session) {
        if (UserService.userExists(newUser.getPhoneNumber())) {
            session.setAttribute(ERROR_MSG_ATTRIBUTE, "An account already exists with this phone number!");
            return REDIRECT_ERROR;
        }
        if (!Utilities.isPhoneNumber(newUser.getPhoneNumber())) {
            session.setAttribute(ERROR_MSG_ATTRIBUTE, "Please enter your correct phone number!");
            return REDIRECT_ERROR;
        }
        if (!Utilities.checkPasswords(newUser.getPassword(), confirmPass)) {
            session.setAttribute(ERROR_MSG_ATTRIBUTE, "Please make sure your password is at least 8 characters long.\nNo spaces are allowed.\nAnd check if the passwords match!");
            return REDIRECT_ERROR;
        }
        if (!Utilities.isValidEmail(newUser.getEmail())) {
            session.setAttribute(ERROR_MSG_ATTRIBUTE, "Please enter your correct email address!");
            return REDIRECT_ERROR;
        }
        if (!Utilities.validUserInfo(newUser.getFirstName(), newUser.getLastName(), newUser.getAddress())) {
            session.setAttribute(ERROR_MSG_ATTRIBUTE, "Please enter your real name and address information!");
            return REDIRECT_ERROR;
        }
        Utilities.fixUserInfo(newUser);
        UserService.createUser(newUser);
        session.setAttribute("newAccount", newUser.getPhoneNumber());
        return "redirect:/login";
    }
}
