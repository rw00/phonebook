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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class UserController {
    private static final String USER_ID_ATTRIBUTE = "userId";
    private static final String REDIRECT_ERROR = "redirect:/error";
    private static final String ERROR_MSG_ATTRIBUTE = "errorMsg";

    @RequestMapping("/")
    public String user(HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute(USER_ID_ATTRIBUTE);
        if (userId == null) {
            return "redirect:/login";
        } else {
            User u = UserService.findUser(userId);
            model.addAttribute("userName", u.getFirstName() + " " + u.getLastName());
            model.addAttribute("userContacts", ContactRepository.getContacts(userId));
            model.addAttribute("newContact", new Contact());
            return "user";
        }
    }

    @RequestMapping("/profile")
    public String userProfile(HttpSession session, Model model) {
        if (isUserLoggedIn(session)) {
            Long userId = (Long) session.getAttribute(USER_ID_ATTRIBUTE);
            model.addAttribute("userInfo", UserRepository.getUserInfo(userId));
            return "profile";
        } else {
            return "redirect:/home";
        }
    }

    public boolean isUserLoggedIn(HttpSession session) {
        Long userId = (Long) session.getAttribute(USER_ID_ATTRIBUTE);
        return userId != null;
    }

    @RequestMapping("deleteAccount")
    public String deleteAccount(HttpSession session, @RequestParam("currentPass") String pass) {
        if (isUserLoggedIn(session)) {
            Long userId = (Long) session.getAttribute(USER_ID_ATTRIBUTE);
            String password = UserService.findUser(userId).getPassword();
            if (!password.equals(pass)) {
                session.setAttribute(ERROR_MSG_ATTRIBUTE, "Please enter your correct current password!");
                return REDIRECT_ERROR;
            }
            User u = UserService.findUser(userId);
            UserService.deleteUserInfo(u);
            UserService.deleteUser(userId);
            return "redirect:/logout";
        }
        return "redirect:/index";
    }

    @RequestMapping("/updateProfile")
    public String updateUserProfile(HttpSession session, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
        @RequestParam("email") String email, @RequestParam(value = "newPassword", required = false) String newPass, @RequestParam(value = "confirmPass", required = false) String confirmPass,
        @RequestParam("address") String address, @RequestParam("currentPass") String currentPass) {
        if (isUserLoggedIn(session)) {
            Long userId = (Long) session.getAttribute(USER_ID_ATTRIBUTE);
            String password = UserService.findUser(userId).getPassword();
            if (!password.equals(currentPass)) {
                session.setAttribute(ERROR_MSG_ATTRIBUTE, "Please enter your correct current password to save changes!");
                return REDIRECT_ERROR;
            }
            if ((!newPass.isEmpty() || !confirmPass.isEmpty()) && !Utilities.checkPasswords(newPass, confirmPass)) {
                session.setAttribute(ERROR_MSG_ATTRIBUTE, "Please make sure your password is at least 8 characters long.\nNo spaces are allowed.\nAnd check if the passwords match!");
                return REDIRECT_ERROR;
            }
            if (!Utilities.isValidEmail(email)) {
                session.setAttribute(ERROR_MSG_ATTRIBUTE, "Please enter a valid email!");
                return REDIRECT_ERROR;
            }
            if (!Utilities.isPhoneNumber(phoneNumber)) {
                session.setAttribute(ERROR_MSG_ATTRIBUTE, "Please enter a valid phone number!");
                return REDIRECT_ERROR;
            }
            if (!Utilities.validUserInfo(firstName, lastName, address)) {
                session.setAttribute(ERROR_MSG_ATTRIBUTE, "Please enter your real information!");
                return REDIRECT_ERROR;
            }
            if (newPass.isEmpty()) {
                newPass = currentPass;
            }
            UserService.updateUser(userId, Utilities.fixName(firstName), Utilities.fixName(lastName), phoneNumber.trim(), email.trim().toLowerCase(), newPass, address.trim());
            return "redirect:/profile";
        } else {
            return "redirect:/home";
        }
    }
}
