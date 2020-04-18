package org.rw.phonebook.controller;

import javax.servlet.http.HttpSession;

import org.rw.phonebook.entity.Contact;
import org.rw.phonebook.entity.User;
import org.rw.phonebook.functions.MainFunctions;
import org.rw.phonebook.repository.ContactRepository;
import org.rw.phonebook.repository.UserRepository;
import org.rw.phonebook.service.UserManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class UserController {
    public UserController() {
    }


    @RequestMapping("/")
    public String user(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        } else {
            User u = UserManager.findUser(userId);
            model.addAttribute("userName", u.getFirstName() + " " + u.getLastName());
            model.addAttribute("userContacts", ContactRepository.getContacts(userId));
            model.addAttribute("newContact", new Contact());
            return "user";
        }
    }

    @RequestMapping("/profile")
    public String userProfile(HttpSession session, Model model) {
        if (isUserLoggedIn(session)) {
            Integer userId = (Integer) session.getAttribute("userId");
            model.addAttribute("userInfo", UserRepository.getUserInfo(userId));
            return "profile";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping("deleteAccount")
    public String deleteAccount(HttpSession session,
        @RequestParam("currentPass") String pass) {
        if (isUserLoggedIn(session)) {
            Integer userId = (Integer) session.getAttribute("userId");
            String password = UserManager.findUser(userId).getPassword();
            if (!password.equals(pass)) {
                session.setAttribute("errorMsg", "Please enter your correct current password!");
                return "redirect:/error";
            }
            User u = UserManager.findUser(userId);
            UserManager.deleteUserInfo(u);
            UserManager.deleteUser(userId);
            return "redirect:/logout";
        }
        return "redirect:/index";
    }

    @RequestMapping("/updateProfile")
    public String updateUserProfile(HttpSession session,
        @RequestParam("phoneNumber") String phoneNumber,
        @RequestParam("firstName") String firstName,
        @RequestParam("lastName") String lastName,
        @RequestParam("email") String email,
        @RequestParam(value = "newPassword", required = false) String newPass,
        @RequestParam(value = "confirmPass", required = false) String confirmPass,
        @RequestParam("address") String address,
        @RequestParam("currentPass") String currentPass) {
        if (isUserLoggedIn(session)) {
            Integer userId = (Integer) session.getAttribute("userId");
            String password = UserManager.findUser(userId).getPassword();
            if (!password.equals(currentPass)) {
                session.setAttribute("errorMsg", "Please enter your correct current password to save changes!");
                return "redirect:/error";
            }
            if (!newPass.isEmpty() || !confirmPass.isEmpty()) {
                if (!MainFunctions.checkPasswords(newPass, confirmPass)) {
                    session.setAttribute("errorMsg", "Please make sure your password is at least 8 characters long.\nNo spaces are allowed.\nAnd check if the passwords match!");
                    return "redirect:/error";
                }
            }
            if (!MainFunctions.isValidEmail(email)) {
                session.setAttribute("errorMsg", "Please enter a valid email!");
                return "redirect:/error";
            }
            if (!MainFunctions.isPhoneNumber(phoneNumber)) {
                session.setAttribute("errorMsg", "Please enter a valid phone number!");
                return "redirect:/error";
            }
            if (!MainFunctions.validUserInfo(firstName, lastName, address)) {
                session.setAttribute("errorMsg", "Please enter your real information!");
                return "redirect:/error";
            }
            if (newPass.isEmpty()) {
                newPass = currentPass;
            }
            UserManager.updateUser(userId, MainFunctions.fixName(firstName), MainFunctions.fixName(lastName), phoneNumber.trim(), email.trim().toLowerCase(), newPass, address.trim());
            return "redirect:/profile";
        } else {
            return "redirect:/home";
        }
    }

    public boolean isUserLoggedIn(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            return false;
        } else {
            return true;
        }
    }
}
