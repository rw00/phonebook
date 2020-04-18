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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
public class AdminController {
    public AdminController() {
    }


    @RequestMapping("/")
    public String admin(HttpSession session, Model model) {
        if (isAdminLoggedIn(session)) {
            Integer adminId = (Integer) session.getAttribute("adminId");
            model.addAttribute("adminInfo", UserManager.findUser(adminId));
            model.addAttribute("admins", UserRepository.getAdmins(adminId));
            model.addAttribute("users", UserRepository.getUsers());
            model.addAttribute("newUser", new User());
            session.setAttribute("uid", null);
            System.err.println(UserRepository.getAdmins(adminId));
            return "admin";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping("/profile")
    public String adminProfile(HttpSession session, Model model) {
        if (isAdminLoggedIn(session)) {
            Integer adminId = (Integer) session.getAttribute("adminId");
            model.addAttribute("adminInfo", UserRepository.getUserInfo(adminId));
            return "admin-profile";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping("/updateProfile")
    public String updateAdminProfile(HttpSession session,
        @RequestParam("phoneNumber") String phoneNumber,
        @RequestParam("email") String email,
        @RequestParam("newPassword") String newPass,
        @RequestParam("confirmPass") String confirmPass,
        @RequestParam("currentPass") String currentPass) {
        if (isAdminLoggedIn(session)) {
            Integer adminId = (Integer) session.getAttribute("adminId");
            if (!MainFunctions.checkCurrentPassword(currentPass, adminId)) {
                session.setAttribute("errorMsg", "Please enter your correct current password to save changes!");
                return "redirect:/error";
            }
            if (!newPass.isEmpty() || !confirmPass.isEmpty()) {
                if (!newPass.equals(confirmPass)) {
                    session.setAttribute("errorMsg", "The passwords you entered do not match!");
                    return "redirect:/error";
                }
            }
            if (!MainFunctions.isValidEmail(email)) {
                session.setAttribute("errorMsg", "Please enter a valid email!");
                return "redirect:/error";
            }
            if (newPass.isEmpty()) {
                newPass = currentPass;
            }
            UserManager.updateUser(adminId, "", "", phoneNumber.trim(), email.trim().toLowerCase(), newPass, "");
            return "redirect:/profile";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public String adminContacts(HttpSession session, Model model) {
        if (isAdminLoggedIn(session)) {
            Integer adminId = (Integer) session.getAttribute("adminId");
            model.addAttribute("adminContacts", ContactRepository.getContacts(adminId));
            model.addAttribute("newContact", new Contact());
            return "admin-contacts";
        } else {
            return "redirect:/home";
        }
    }

    public boolean isAdminLoggedIn(HttpSession session) {
        Integer adminId = (Integer) session.getAttribute("adminId");
        if (adminId == null) {
            return false;
        } else {
            return true;
        }
    }
}
