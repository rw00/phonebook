package org.rw.phonebook.controller;

import javax.servlet.http.HttpSession;
import org.rw.phonebook.entity.Contact;
import org.rw.phonebook.entity.User;
import org.rw.phonebook.repository.ContactRepository;
import org.rw.phonebook.repository.UserRepository;
import org.rw.phonebook.service.UserService;
import org.rw.phonebook.util.Utilities;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final String ADMIN_ID_ATTRIBUTE = "adminId";
    private static final String REDIRECT_HOME = "redirect:/home";
    private static final String REDIRECT_ERROR = "redirect:/error";
    private static final String ERROR_MSG_ATTRIBUTE = "errorMsg";

    @RequestMapping("/")
    public String admin(HttpSession session, Model model) {
        if (isAdminLoggedIn(session)) {
            Long adminId = (Long) session.getAttribute(ADMIN_ID_ATTRIBUTE);
            model.addAttribute("adminInfo", UserService.findUser(adminId));
            model.addAttribute("admins", UserRepository.getAdmins(adminId));
            model.addAttribute("users", UserRepository.getUsers());
            model.addAttribute("newUser", new User());
            session.setAttribute("uid", null);
            return "admin";
        } else {
            return "redirect:/login";
        }
    }

    public boolean isAdminLoggedIn(HttpSession session) {
        Long adminId = (Long) session.getAttribute(ADMIN_ID_ATTRIBUTE);
        return adminId != null;
    }

    @RequestMapping("/profile")
    public String adminProfile(HttpSession session, Model model) {
        if (isAdminLoggedIn(session)) {
            long adminId = (Long) session.getAttribute(ADMIN_ID_ATTRIBUTE);
            model.addAttribute("adminInfo", UserRepository.getUserInfo(adminId));
            return "admin-profile";
        } else {
            return REDIRECT_HOME;
        }
    }

    @RequestMapping("/updateProfile")
    public String updateAdminProfile(HttpSession session, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("email") String email, @RequestParam("newPassword") String newPass,
        @RequestParam("confirmPass") String confirmPass, @RequestParam("currentPass") String currentPass) {
        if (isAdminLoggedIn(session)) {
            Long adminId = (Long) session.getAttribute(ADMIN_ID_ATTRIBUTE);
            if (!Utilities.checkCurrentPassword(currentPass, adminId)) {
                session.setAttribute(ERROR_MSG_ATTRIBUTE, "Please enter your correct current password to save changes!");
                return REDIRECT_ERROR;
            }
            if ((!newPass.isEmpty() || !confirmPass.isEmpty()) && !newPass.equals(confirmPass)) {
                session.setAttribute(ERROR_MSG_ATTRIBUTE, "The passwords you entered do not match!");
                return REDIRECT_ERROR;
            }
            if (!Utilities.isValidEmail(email)) {
                session.setAttribute(ERROR_MSG_ATTRIBUTE, "Please enter a valid email!");
                return REDIRECT_ERROR;
            }
            if (newPass.isEmpty()) {
                newPass = currentPass;
            }
            UserService.updateUser(adminId, "", "", phoneNumber.trim(), email.trim().toLowerCase(), newPass, "");
            return "redirect:/profile";
        } else {
            return REDIRECT_HOME;
        }
    }

    @GetMapping(value = "/contacts")
    public String adminContacts(HttpSession session, Model model) {
        if (isAdminLoggedIn(session)) {
            Long adminId = (Long) session.getAttribute(ADMIN_ID_ATTRIBUTE);
            model.addAttribute("adminContacts", ContactRepository.getContacts(adminId));
            model.addAttribute("newContact", new Contact());
            return "admin-contacts";
        } else {
            return REDIRECT_HOME;
        }
    }
}
