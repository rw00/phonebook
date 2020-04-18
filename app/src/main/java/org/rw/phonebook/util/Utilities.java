package org.rw.phonebook.util;

import freemarker.template.Template;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import javax.persistence.Query;
import org.json.JSONObject;
import org.rw.phonebook.entity.User;
import org.rw.phonebook.repository.ContactRepository;
import org.rw.phonebook.service.EntityManagerProvider;
import org.rw.phonebook.service.UserService;


public class Utilities {
    static final int MIN_PASSWORD_LENGTH = 8;
    static final int MIN_PHONENUM_LENGTH = 8;
    static final int MIN_NAME_LENGTH = 2;
    static final int MIN_ADDRESS_LENGTH = 2;

    private Utilities() {
    }

    public static void sendUserContactsEmail(long userId, String adminEmail) throws Exception {
        Template template = TemplatingManager.getFreemarkerConfig().getTemplate("templates/email.ftl");
        Map<String, Object> templateContext = new HashMap<>();
        templateContext.put("userId", userId);
        String userEmail = (String) Utilities.getUserInfo(userId).get("email");
        templateContext.put("userEmail", userEmail);
        templateContext.put("userContacts", ContactRepository.getContactsList(userId));
        StringWriter writer = new StringWriter();
        template.process(templateContext, writer);
        try {
            Mailer.send(adminEmail, "Contacts List for " + userEmail, writer.toString());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the information of the User by his ID#
     *
     * @param userId , the ID# of the User
     *
     * @return JSONObject representation of the information of the User.
     */
    public static JSONObject getUserInfo(long userId) {
        Query q = EntityManagerProvider.createEntityManager().createQuery("SELECT u FROM User u WHERE u.userId = :userId");
        q.setParameter("userId", userId);
        @SuppressWarnings("rawtypes") List r = q.getResultList();
        if ((r == null) || r.isEmpty()) {
            return null;
        }
        User u = (User) q.getSingleResult();
        if (u != null) {
            JSONObject info = new JSONObject();
            info.put("userId", userId);
            info.put("phoneNumber", u.getPhoneNumber());
            info.put("password", u.getPassword());
            info.put("firstName", u.getFirstName());
            info.put("lastName", u.getLastName());
            info.put("email", u.getEmail());
            info.put("address", u.getAddress());
            return info;
        }
        return null;
    }

    public static void sendNotificationEmail(String adminEmail, long uid) throws Exception {
        Template template = TemplatingManager.getFreemarkerConfig().getTemplate("templates/noti.ftl");
        Map<String, Object> templateContext = new HashMap<>();
        templateContext.put("userId", uid);
        StringWriter writer = new StringWriter();
        template.process(templateContext, writer);
        try {
            Mailer.send(adminEmail, "Notification about User with ID#: " + uid, writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void fixUserInfo(User u) {
        u.setEmail(u.getEmail().trim().toLowerCase());
        u.setFirstName(fixName(u.getFirstName()));
        u.setLastName(fixName(u.getLastName()));
        u.setAddress(u.getAddress().trim());
        u.setPhoneNumber(u.getPhoneNumber().trim());
    }

    /**
     * Capitalizes the first letter and makes all other letters lower-case of the name of the User.
     *
     * @param name , the name either first or last.
     *
     * @return the fixed name String representation.
     */
    public static String fixName(String name) {
        if ((name == null) || name.trim().isEmpty()) {
            return "";
        }
        name = name.trim();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
    }

    /**
     * Checks whether the String str is a phone number.
     *
     * @param str , the String to be checked.
     *
     * @return <code>true</code> if String str is actually a phone number and <code>false</code> otherwise.
     */
    public static boolean isPhoneNumber(String str) {
        if (str == null) {
            return false;
        }
        str = str.trim();
        int len = str.length();
        if (len < MIN_PHONENUM_LENGTH) {
            return false;
        }
        for (int i = 0; i < len; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns boolean value if e-mail is valid or not by matching the argument email String with the final email regular expression.
     *
     * @param email , the email to be checked.
     *
     * @return <code>true</code> if the e-mail String is valid and <code>false</code> otherwise.
     */
    public static boolean isValidEmail(String email) {
        final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(EMAIL_REGEX);
    }

    public static boolean validUserInfo(String fname, String lname, String address) {
        if ((fname == null) || (lname == null) || (address == null)) {
            return false;
        }
        return (fname.length() >= MIN_NAME_LENGTH) && (lname.length() >= MIN_NAME_LENGTH) && (address.length() >= MIN_ADDRESS_LENGTH);
    }

    public static boolean checkPasswords(String password, String confirmPass) {
        return (password != null) && (password.length() >= MIN_PASSWORD_LENGTH) && !password.contains(" ") && password.equals(confirmPass);
    }

    public static boolean checkCurrentPassword(String password, long id) {
        String pass = UserService.findUser(id).getPassword();
        return password.equals(pass);
    }
}
