package org.rw.phonebook.controller;

import javax.servlet.http.HttpSession;

import org.rw.phonebook.entity.Contact;
import org.rw.phonebook.entity.Email_Schedule;
import org.rw.phonebook.entity.User;
import org.rw.phonebook.functions.EmailScheduler;
import org.rw.phonebook.functions.MainFunctions;
import org.rw.phonebook.repository.ContactRepository;
import org.rw.phonebook.service.ContactManager;
import org.rw.phonebook.service.EmailScheduleManager;
import org.rw.phonebook.service.UserManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
public class AdminAndUserController {
    public AdminAndUserController() {
    }


    @RequestMapping(value = "/edit-user")
    // , method = RequestMethod.POST)
    public String editUser(HttpSession session,
        @RequestParam(value = "uid", required = false) Integer uid, Model model) {
        if (isAdminLoggedIn(session)) {
            if (uid == null) {
                uid = (Integer) session.getAttribute("uid");
                if (uid == null) {
                    return "redirect:/home";
                }
            }
            model.addAttribute("userInfo", UserManager.findUser(uid));
            model.addAttribute("userContacts", ContactRepository.getContacts(uid));
            model.addAttribute("newUserContact", new Contact());
            return "edit-user";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public String updateUserInfo(HttpSession session,
        @RequestParam("uid") int uid,
        @RequestParam("firstName") String firstName,
        @RequestParam("lastName") String lastName,
        @RequestParam("phoneNumber") String phoneNumber,
        @RequestParam("email") String email,
        @RequestParam("address") String address) {
        if (isAdminLoggedIn(session)) {
            session.setAttribute("uid", uid);
            User u = UserManager.findUser(uid);
            u.setFirstName(firstName);
            u.setLastName(lastName);
            u.setPhoneNumber(phoneNumber);
            u.setEmail(email);
            u.setAddress(address);
            MainFunctions.fixUserInfo(u);
            UserManager.updateUser(uid, u.getFirstName(), u.getLastName(), u.getPhoneNumber(), u.getEmail(), u.getPassword(), u.getAddress());
            return "redirect:/admin/edit-user";
        } else {
            return "redirect:/home";
        }
    }

    // could be handled by ROOT/addContact
    // not tested tho
    @RequestMapping(value = "/addUserContact", method = RequestMethod.POST)
    public String addUserContact(HttpSession session,
        @RequestParam("uid") Integer uid,
        @ModelAttribute("newUserContact") Contact newUserContact) {
        if (isAdminLoggedIn(session)) {
            session.setAttribute("uid", uid);
            newUserContact.setUser(UserManager.findUser(uid));
            ContactManager.createContact(newUserContact);
            return "redirect:/admin/edit-user";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(HttpSession session,
        @ModelAttribute("newUser") User newUser,
        @RequestParam("confirmPass") String confirmPass) {
        if (isAdminLoggedIn(session)) {
            if (UserManager.userExists(newUser.getPhoneNumber())) {
                session.setAttribute("errorMsg", "An account already exists with this phone number!");
                return "redirect:/error";
            }
            if (!MainFunctions.isPhoneNumber(newUser.getPhoneNumber())) {
                session.setAttribute("errorMsg", "Please enter a valid phone number!");
                return "redirect:/error";
            }
            if (!MainFunctions.checkPasswords(newUser.getPassword(), confirmPass)) {
                session.setAttribute("errorMsg", "Please make sure your password is at least 8 characters long.\nNo spaces are allowed.\nAnd check if the passwords match!");
                return "redirect:/error";
            }
            if (!MainFunctions.isValidEmail(newUser.getEmail())) {
                session.setAttribute("errorMsg", "Please enter a valid email address!");
                return "redirect:/error";
            }
            MainFunctions.fixUserInfo(newUser);
            UserManager.createUser(newUser);
        }
        return "redirect:/home";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(HttpSession session,
        @RequestParam("uid") Integer uid) {
        if (isAdminLoggedIn(session)) {
            User u = UserManager.findUser(uid);
            UserManager.deleteUserInfo(u);
            UserManager.deleteUser(uid);
        }
        return "redirect:/home";
    }

    @RequestMapping("/email-scheduling")
    public String emailScheduling(HttpSession session,
        @RequestParam("uid") Integer uid, Model model) {
        if (isAdminLoggedIn(session)) {
            int adminId = (Integer) session.getAttribute("adminId");
            User admin = UserManager.findUser(adminId);
            User user = UserManager.findUser(uid);
            model.addAttribute("schedule", EmailScheduleManager.findSchedule(admin, user));
            return "email-scheduling";
        } else {
            return "redirect:/home";
        }
    }

    @RequestMapping(value = "/cancelSchedule", method = RequestMethod.POST)
    public String cancelSchedule(HttpSession session,
        @RequestParam("uid") Integer uid) {
        if (isAdminLoggedIn(session)) {
            int adminId = (Integer) session.getAttribute("adminId");
            User admin = UserManager.findUser(adminId);
            User user = UserManager.findUser(uid);
            EmailScheduleManager.deleteSchedule(admin, user);
            EmailScheduler.cancelSchedule(adminId, uid);
        }
        return "redirect:/home";
    }

    @RequestMapping(value = "/setSchedule", method = RequestMethod.POST)
    public String setSchedule(HttpSession session,
        @RequestParam("uid") int uid,
        @RequestParam("syear") int syear,
        @RequestParam("smonth") int smonth,
        @RequestParam("sday") int sday,
        @RequestParam("eyear") int eyear,
        @RequestParam("emonth") int emonth,
        @RequestParam("eday") int eday,
        @RequestParam("stime") String stime,
        @RequestParam("etime") String etime,
        @RequestParam("hrs") int hrs,
        @RequestParam("mins") int mins,
        @RequestParam(value = "mon", required = false) Object mon,
        @RequestParam(value = "tue", required = false) Object tue,
        @RequestParam(value = "wed", required = false) Object wed,
        @RequestParam(value = "thu", required = false) Object thu,
        @RequestParam(value = "fri", required = false) Object fri,
        @RequestParam(value = "sat", required = false) Object sat,
        @RequestParam(value = "sun", required = false) Object sun) {
        if (isAdminLoggedIn(session)) {
            String selectedDays = "";
            if (sun != null) {
                selectedDays += "1";
            }
            if (mon != null) {
                selectedDays += "2";
            }
            if (tue != null) {
                selectedDays += "3";
            }
            if (wed != null) {
                selectedDays += "4";
            }
            if (thu != null) {
                selectedDays += "5";
            }
            if (fri != null) {
                selectedDays += "6";
            }
            if (sat != null) {
                selectedDays += "7";
            }
            if (selectedDays.isEmpty()) {
                selectedDays = "1234567";
            }

            if ((stime.length() != 5) || (etime.length() != 5) || (stime.indexOf(":") != 2) || (etime.indexOf(":") != 2)) {
                session.setAttribute("errorMsg", "Please enter time in the format HH:mm");
                return "redirect:/error";
            }
            int totalMin = (hrs * 60) + mins;

            int adminId = (Integer) session.getAttribute("adminId");
            User admin = UserManager.findUser(adminId);
            User user = UserManager.findUser(uid);
            if (EmailScheduleManager.findSchedule(admin, user) != null) {
                try {
                    EmailScheduler.reschedule(sday, smonth, syear, stime, eday, emonth, eyear, etime, totalMin, selectedDays, adminId, uid);
                } catch (Exception e) {
                    session.setAttribute("errorMsg", e.getMessage());
                    return "redirect:/error";
                }
            } else {
                try {
                    EmailScheduler.setSchedule(sday, smonth, syear, stime, eday, emonth, eyear, etime, totalMin, selectedDays, adminId, uid);
                    EmailScheduleManager.createSchedule(new Email_Schedule(admin, user));
                } catch (Exception e) {
                    session.setAttribute("errorMsg", e.getMessage());
                    return "redirect:/error";
                }
            }
        }
        return "redirect:/home";
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
