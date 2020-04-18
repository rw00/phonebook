package org.rw.phonebook.controller;

import org.rw.phonebook.entity.Contact;
import org.rw.phonebook.service.ContactManager;
import org.rw.phonebook.service.UserManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ContactController {
    private static final String REDIRECT_HOME = "redirect:/home";

    @PostMapping(value = "/edit-contact")
    public String editContact(@RequestParam("cid") Integer cid, HttpSession session, Model model) {
        if ((cid != null) && isLoggedIn(session)) {
            model.addAttribute("contactInfo", ContactManager.findContact(cid));
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
            newContact.setUser(UserManager.findUser(uid));
            ContactManager.createContact(newContact);
        } else if (adminId != null) {
            newContact.setUser(UserManager.findUser(adminId));
            ContactManager.createContact(newContact);
        } else if (userId != null) {
            newContact.setUser(UserManager.findUser(userId));
            ContactManager.createContact(newContact);
        }
        return REDIRECT_HOME;
    }

    @PostMapping(value = "/editContact")
    public String editContact(@RequestParam("cid") Integer cid, HttpSession session, @RequestParam("inputPhoneNumber") String phoneNumber, @RequestParam("inputFirstName") String firstName, @RequestParam("inputLastName") String lastName, @RequestParam("inputEmail") String email, @RequestParam("inputAddress") String address) {
        if ((cid != null) && isLoggedIn(session)) {
            ContactManager.updateContact(cid, firstName, lastName, phoneNumber, email, address);
        }
        return REDIRECT_HOME;
    }

    @PostMapping(value = "/deleteContact")
    public String deleteContact(@RequestParam("cid") Integer cid, HttpSession session) {
        if ((cid != null) && isLoggedIn(session)) {
            ContactManager.deleteContact(cid);
        }
        return REDIRECT_HOME;
    }

    public boolean isLoggedIn(HttpSession session) {
        Long adminId = (Long) session.getAttribute("adminId");
        Long userId = (Long) session.getAttribute("userId");
        return (adminId != null) || (userId != null);
    }
}
