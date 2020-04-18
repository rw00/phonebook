package org.rw.phonebook.controller;

import javax.servlet.http.HttpSession;

import org.rw.phonebook.entity.Contact;
import org.rw.phonebook.service.ContactManager;
import org.rw.phonebook.service.UserManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ContactController {
    public ContactController() {
    }


    @RequestMapping(value = "/edit-contact", method = RequestMethod.POST)
    public String editContact(@RequestParam("cid") Integer cid, HttpSession session, Model model) {
        if ((cid != null) && isLoggedIn(session)) {
            model.addAttribute("contactInfo", ContactManager.findContact(cid));
            return "edit-contact";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping(value = "/addContact", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("newContact") Contact newContact, HttpSession session) {
        Integer adminId = (Integer) session.getAttribute("adminId");
        Integer userId = (Integer) session.getAttribute("userId");
        Integer uid = (Integer) session.getAttribute("uid");
        if ((adminId != null) && (uid != null)) { // not necessary but okay
            newContact.setUser(UserManager.findUser(uid));
            ContactManager.createContact(newContact);
        } else if ((adminId != null) && (uid == null)) {
            newContact.setUser(UserManager.findUser(adminId));
            ContactManager.createContact(newContact);
        } else if (userId != null) {
            newContact.setUser(UserManager.findUser(userId));
            ContactManager.createContact(newContact);
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/editContact", method = RequestMethod.POST)
    public String editContact(@RequestParam("cid") Integer cid, HttpSession session,
        @RequestParam("inputPhoneNumber") String phoneNumber,
        @RequestParam("inputFirstName") String firstName,
        @RequestParam("inputLastName") String lastName,
        @RequestParam("inputEmail") String email,
        @RequestParam("inputAddress") String address) {
        if ((cid != null) && isLoggedIn(session)) {
            ContactManager.updateContact(cid, firstName, lastName, phoneNumber, email, address);
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/deleteContact", method = RequestMethod.POST)
    public String deleteContact(@RequestParam("cid") Integer cid, HttpSession session) {
        if ((cid != null) && isLoggedIn(session)) {
            ContactManager.deleteContact(cid);
        }
        return "redirect:/home";
    }

    public boolean isLoggedIn(HttpSession session) {
        Integer adminId = (Integer) session.getAttribute("adminId");
        Integer userId = (Integer) session.getAttribute("userId");
        if ((adminId == null) && (userId == null)) {
            return false;
        } else {
            return true;
        }
    }
}
