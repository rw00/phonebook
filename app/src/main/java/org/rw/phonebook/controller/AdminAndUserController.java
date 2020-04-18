package org.rw.phonebook.controller;

import javax.servlet.http.HttpSession;
import org.rw.phonebook.entity.Contact;
import org.rw.phonebook.entity.EmailSchedule;
import org.rw.phonebook.entity.User;
import org.rw.phonebook.repository.ContactRepository;
import org.rw.phonebook.service.ContactService;
import org.rw.phonebook.service.EmailScheduleService;
import org.rw.phonebook.service.UserService;
import org.rw.phonebook.util.EmailScheduler;
import org.rw.phonebook.util.Utilities;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin")
public class AdminAndUserController {
    private static final String ADMIN_ID_ATTRIBUTE = "adminId";
    private static final String REDIRECT_HOME = "redirect:/home";
    private static final String REDIRECT_ERROR = "redirect:/error";
    private static final String ERROR_MSG_ATTRIBUTE = "errorMsg";

    @RequestMapping(value = "/edit-user")
    public String editUser(HttpSession session, @RequestParam(value = "uid", required = false) Long uid, Model model) {
        if (isAdminLoggedIn(session)) {
            if (uid == null) {
                return REDIRECT_HOME;
            }
            model.addAttribute("userInfo", UserService.findUser(uid));
            model.addAttribute("userContacts", ContactRepository.getContacts(uid));
            model.addAttribute("newUserContact", new Contact());
            return "edit-user";
        } else {
            return REDIRECT_HOME;
        }
    }

    @PostMapping(value = "/updateUserInfo")
    public String updateUserInfo(HttpSession session, @RequestParam("uid") long uid, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
        @RequestParam("phoneNumber") String phoneNumber, @RequestParam("email") String email, @RequestParam("address") String address) {
        if (isAdminLoggedIn(session)) {
            session.setAttribute("uid", uid);
            User u = UserService.findUser(uid);
            u.setFirstName(firstName);
            u.setLastName(lastName);
            u.setPhoneNumber(phoneNumber);
            u.setEmail(email);
            u.setAddress(address);
            Utilities.fixUserInfo(u);
            UserService.updateUser(uid, u.getFirstName(), u.getLastName(), u.getPhoneNumber(), u.getEmail(), u.getPassword(), u.getAddress());
            return "redirect:/admin/edit-user";
        } else {
            return REDIRECT_HOME;
        }
    }

    // could be handled by ROOT/addContact
    // not tested tho
    @PostMapping(value = "/addUserContact")
    public String addUserContact(HttpSession session, @RequestParam("uid") Integer uid, @ModelAttribute("newUserContact") Contact newUserContact) {
        if (isAdminLoggedIn(session)) {
            session.setAttribute("uid", uid);
            newUserContact.setUser(UserService.findUser(uid));
            ContactService.createContact(newUserContact);
            return "redirect:/admin/edit-user";
        } else {
            return REDIRECT_HOME;
        }
    }

    @PostMapping(value = "/addUser")
    public String addUser(HttpSession session, @ModelAttribute("newUser") User newUser, @RequestParam("confirmPass") String confirmPass) {
        if (isAdminLoggedIn(session)) {
            if (UserService.userExists(newUser.getPhoneNumber())) {
                session.setAttribute(ERROR_MSG_ATTRIBUTE, "An account already exists with this phone number!");
                return REDIRECT_ERROR;
            }
            if (!Utilities.isPhoneNumber(newUser.getPhoneNumber())) {
                session.setAttribute(ERROR_MSG_ATTRIBUTE, "Please enter a valid phone number!");
                return REDIRECT_ERROR;
            }
            if (!Utilities.checkPasswords(newUser.getPassword(), confirmPass)) {
                session.setAttribute(ERROR_MSG_ATTRIBUTE, "Please make sure your password is at least 8 characters long.\nNo spaces are allowed.\nAnd check if the passwords match!");
                return REDIRECT_ERROR;
            }
            if (!Utilities.isValidEmail(newUser.getEmail())) {
                session.setAttribute(ERROR_MSG_ATTRIBUTE, "Please enter a valid email address!");
                return REDIRECT_ERROR;
            }
            Utilities.fixUserInfo(newUser);
            UserService.createUser(newUser);
        }
        return REDIRECT_HOME;
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(HttpSession session, @RequestParam("uid") Integer uid) {
        if (isAdminLoggedIn(session)) {
            User u = UserService.findUser(uid);
            UserService.deleteUserInfo(u);
            UserService.deleteUser(uid);
        }
        return REDIRECT_HOME;
    }

    @RequestMapping("/email-scheduling")
    public String emailScheduling(HttpSession session, @RequestParam("uid") Integer uid, Model model) {
        if (isAdminLoggedIn(session)) {
            long adminId = (Long) session.getAttribute(ADMIN_ID_ATTRIBUTE);
            User admin = UserService.findUser(adminId);
            User user = UserService.findUser(uid);
            model.addAttribute("schedule", EmailScheduleService.findSchedule(admin, user));
            return "email-scheduling";
        } else {
            return REDIRECT_HOME;
        }
    }

    @PostMapping(value = "/cancelSchedule")
    public String cancelSchedule(HttpSession session, @RequestParam("uid") Integer uid) {
        if (isAdminLoggedIn(session)) {
            long adminId = (Long) session.getAttribute(ADMIN_ID_ATTRIBUTE);
            User admin = UserService.findUser(adminId);
            User user = UserService.findUser(uid);
            EmailScheduleService.deleteSchedule(admin, user);
            EmailScheduler.cancelSchedule(adminId, uid);
        }
        return REDIRECT_HOME;
    }

    @PostMapping(value = "/setSchedule")
    public String setSchedule(HttpSession session, @RequestParam("uid") long uid, @RequestParam("syear") int syear, @RequestParam("smonth") int smonth, @RequestParam("sday") int sday, @RequestParam("eyear") int eyear,
        @RequestParam("emonth") int emonth, @RequestParam("eday") int eday, @RequestParam("stime") String stime, @RequestParam("etime") String etime, @RequestParam("hrs") int hrs, @RequestParam("mins") int mins,
        @RequestParam(value = "mon", required = false) Object mon, @RequestParam(value = "tue", required = false) Object tue, @RequestParam(value = "wed", required = false) Object wed,
        @RequestParam(value = "thu", required = false) Object thu, @RequestParam(value = "fri", required = false) Object fri, @RequestParam(value = "sat", required = false) Object sat,
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

            if ((stime.length() != 5) || (etime.length() != 5) || (stime.indexOf(':') != 2) || (etime.indexOf(':') != 2)) {
                session.setAttribute(ERROR_MSG_ATTRIBUTE, "Please enter time in the format HH:mm");
                return REDIRECT_ERROR;
            }
            int totalMin = (hrs * 60) + mins;

            long adminId = (Long) session.getAttribute(ADMIN_ID_ATTRIBUTE);
            User admin = UserService.findUser(adminId);
            User user = UserService.findUser(uid);
            if (EmailScheduleService.findSchedule(admin, user) != null) {
                try {
                    EmailScheduler.reschedule(sday, smonth, syear, stime, eday, emonth, eyear, etime, totalMin, selectedDays, adminId, uid);
                } catch (Exception e) {
                    session.setAttribute(ERROR_MSG_ATTRIBUTE, e.getMessage());
                    return REDIRECT_ERROR;
                }
            } else {
                try {
                    EmailScheduler.setSchedule(sday, smonth, syear, stime, eday, emonth, eyear, etime, totalMin, selectedDays, adminId, uid);
                    EmailScheduleService.createSchedule(new EmailSchedule(admin, user));
                } catch (Exception e) {
                    session.setAttribute(ERROR_MSG_ATTRIBUTE, e.getMessage());
                    return REDIRECT_ERROR;
                }
            }
        }
        return REDIRECT_HOME;
    }

    private boolean isAdminLoggedIn(HttpSession session) {
        Long adminId = (Long) session.getAttribute(ADMIN_ID_ATTRIBUTE);
        return adminId != null;
    }
}
