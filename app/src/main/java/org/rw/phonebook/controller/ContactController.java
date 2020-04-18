package org.rw.phonebook.controller;

import javax.servlet.http.HttpSession;
import org.rw.phonebook.entity.Contact;
import org.rw.phonebook.service.ContactService;
import org.rw.phonebook.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ContactController {
    private static final String REDIRECT_HOME = "redirect:/home";

    @GetMapping(value = "/edit-contact")
    public String editContact(@RequestParam("cid") Integer cid, HttpSession session, Model model) {
        if ((cid != null) && isLoggedIn(session)) {
            model.addAttribute("contactInfo", ContactService.findContact(cid));
            return "edit-contact";
        } else {
            return REDIRECT_HOME;
        }
    }

    @PostMapping(value = "/addContact")
    public String addContact(@ModelAttribute("newContact") Contact newContact, HttpSession session) {
        Long adminId = (Long) session.getAttribute("adminId");
        Long userId = (Long) session.getAttribute("userId");
        Long uid = (Long) session.getAttribute("uid");
        if ((adminId != null) && (uid != null)) { // not necessary but okay
            newContact.setUser(UserService.findUser(uid));
            ContactService.createContact(newContact);
        } else if (adminId != null) {
            newContact.setUser(UserService.findUser(adminId));
            ContactService.createContact(newContact);
        } else if (userId != null) {
            newContact.setUser(UserService.findUser(userId));
            ContactService.createContact(newContact);
        }
        return REDIRECT_HOME;
    }

    @PostMapping(value = "/editContact")
    public String editContact(@RequestParam("cid") Integer cid, HttpSession session, @RequestParam("inputPhoneNumber") String phoneNumber, @RequestParam("inputFirstName") String firstName,
        @RequestParam("inputLastName") String lastName, @RequestParam("inputEmail") String email, @RequestParam("inputAddress") String address) {
        if ((cid != null) && isLoggedIn(session)) {
            ContactService.updateContact(cid, firstName, lastName, phoneNumber, email, address);
        }
        return REDIRECT_HOME;
    }

    @PostMapping(value = "/deleteContact")
    public String deleteContact(@RequestParam("cid") Integer cid, HttpSession session) {
        if ((cid != null) && isLoggedIn(session)) {
            ContactService.deleteContact(cid);
        }
        return REDIRECT_HOME;
    }

    private boolean isLoggedIn(HttpSession session) {
        Long adminId = (Long) session.getAttribute("adminId");
        Long userId = (Long) session.getAttribute("userId");
        return (adminId != null) || (userId != null);
    }
}
