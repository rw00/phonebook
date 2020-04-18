package org.rw.phonebook.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.json.JSONArray;
import org.json.JSONObject;
import org.rw.phonebook.entity.Contact;
import org.rw.phonebook.entity.User;
import org.rw.phonebook.repository.ContactRepository;
import org.rw.phonebook.service.EntityManagerProvider;
import org.rw.phonebook.service.UserManager;

import javax.mail.MessagingException;
import javax.persistence.Query;
import java.io.StringWriter;
import java.util.List;

@SuppressWarnings("unchecked")
public class Utilities {
    static final int MIN_PASSWORD_LENGTH = 8;
    static final int MAX_PASSWORD_LENGTH = 30;
    static final int MIN_PHONENUM_LENGTH = 8;
    static final int MIN_NAME_LENGTH = 2;
    static final int MIN_ADDRESS_LENGTH = 2;

    private Utilities() {
    }

    public static void sendUserContactsEmail(long userId, String adminEmail) {
        VelocityEngine velocityEngine = VelocityManager.getVelocityEngine();
        Template template = velocityEngine.getTemplate("templates/email.vm");
        VelocityContext context = new VelocityContext();
        context.put("userId", userId);
        String userEmail = (String) Utilities.getUserInfo(userId).get("email");
        context.put("userEmail", userEmail);
        context.put("userContacts", ContactRepository.getContactsList(userId));
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        try {
            GMailer.send(adminEmail, "Contacts List for " + userEmail, writer.toString());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendNotificationEmail(String adminEmail, long uid) {
        VelocityEngine velocityEngine = VelocityManager.getVelocityEngine();
        Template template = velocityEngine.getTemplate("templates/noti.vm");
        VelocityContext context = new VelocityContext();
        context.put("userId", uid);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        try {
            GMailer.send(adminEmail, "Notification about User with ID#: " + uid, writer.toString());
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

    public static String fixDate(int d) {
        String date = d + "";
        if (date.length() < 2) {
            date = "0" + date;
        }
        return date;
    }

    /**
     * Returns boolean value if e-mail is valid or not by matching the argument email String with the final email
     * regular expression.
     *
     * @param email , the email to be checked.
     * @return <code>true</code> if the e-mail String is valid and <code>false</code> otherwise.
     */
    public static boolean isValidEmail(String email) {
        final String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(EMAIL_REGEX);
    }

    public static String toTitleCase(String text) {
        if ((text == null) || text.isEmpty()) {
            return "";
        }
        StringBuilder r = new StringBuilder(Character.toUpperCase(text.charAt(0)) + "");
        for (int i = 1; i < text.length(); i++) {
            if (r.charAt(i - 1) == ' ') {
                r.append(Character.toUpperCase(text.charAt(i)));
            } else {
                r.append(Character.toLowerCase(text.charAt(i)));
            }
        }
        return r.toString();
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
        String pass = UserManager.findUser(id).getPassword();
        return password.equals(pass);
    }

    /**
     * Retrieves the information and returns JSONObject representation of the contact based on their contactID#.
     *
     * @param contactId , the contactID#
     * @return JSONObject containing the information of the contact.
     */
    public static JSONObject getContactInfo(long contactId) {
        Query q = EntityManagerProvider.createEntityManager().createQuery("SELECT c FROM Contact c WHERE c.contactId = :contactId");
        q.setParameter("contactId", contactId);
        Contact c = (Contact) q.getSingleResult();
        if (c != null) {
            JSONObject info = new JSONObject();
            info.put("contactId", contactId);
            info.put("phoneNumber", c.getPhoneNumber());
            info.put("firstName", c.getFirstName());
            info.put("lastName", c.getLastName());
            info.put("email", c.getEmail());
            info.put("address", c.getAddress());
            return info;
        }
        return null;
    }

    /**
     * Returns the information of the User by his ID#
     *
     * @param userId , the ID# of the User
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

    /**
     * Returns all the contacts and their information in a JSONArray object.
     *
     * @param userId , the User ID# to get all his contacts list.
     * @return JSONArray made of JSONObjects containing the information about the contacts.
     */
    public static JSONArray getContacts(long userId) {
        Query q = EntityManagerProvider.createEntityManager().createQuery("SELECT c FROM Contact c WHERE c.user = :user");
        q.setParameter("user", org.rw.phonebook.service.UserManager.findUser(userId));
        List<Contact> result = q.getResultList();
        JSONArray list = new JSONArray();
        for (Contact c : result) {
            long contactId = c.getContactId();
            JSONObject details = new JSONObject();
            details.put("contactId", contactId);
            details.put("phoneNumber", c.getPhoneNumber());
            details.put("firstName", c.getFirstName());
            details.put("lastName", c.getLastName());
            details.put("email", c.getEmail());
            details.put("address", c.getAddress());
            list.put(details);
        }
        return list;
    }

    /**
     * Returns String representation of information about the contacts for this User
     *
     * @param userId , the User ID# to get his contacts details.
     * @return String representation formatted properly in the form of an e-mail addressing the Admin.
     */
    public static String getContactsDetails(long userId) {
        String contactsDetails = "<!DOCTYPE html><html><p>Dear Admin,</p>";
        contactsDetails += "<p>List of Contacts for User with ID#: " + userId + "</p>";
        JSONArray listOfContacts = getContacts(userId);
        if (listOfContacts.length() == 0) {
            contactsDetails += "<br>No contacts...<br>";
        } else {
            contactsDetails += "<table style='table-layout: fixed; width: 300px'>";
            StringBuilder contactsDetailsBuilder = new StringBuilder(contactsDetails);
            for (int i = 0; i < listOfContacts.length(); i++) {
                JSONObject contactInfo = listOfContacts.getJSONObject(i);
                contactsDetailsBuilder.append("<tr><td>ContactID#:</td><td>").append(contactInfo.getInt("contactId")).append("</td></tr>");
                contactsDetailsBuilder.append("<tr><td>Name:</td><td>").append(contactInfo.getString("firstName")).append(" ").append(contactInfo.getString("lastName")).append("</td></tr>");
                contactsDetailsBuilder.append("<tr><td>Phone Number:</td><td>").append(contactInfo.getString("phoneNumber")).append("</td></tr>");
                contactsDetailsBuilder.append("<tr><td>Email:</td><td>").append(contactInfo.getString("email")).append("</td></tr>");
                contactsDetailsBuilder.append("<tr><td>Address:</td><td>").append(contactInfo.getString("address")).append("</td></tr>");
                contactsDetailsBuilder.append("<tr><td></td><td></td></tr>");
                contactsDetailsBuilder.append("<tr style='height: 10px'><td colspan='2'></td></tr>");
            }
            contactsDetails = contactsDetailsBuilder.toString();
            contactsDetails += "</table>";
        }

        contactsDetails += "<br><p><small><em>This message is sent by automatic system. If it was sent to you by mistake, delete it immediately and report to raafatwahb@gmail.com</em></small></p></html>";
        return contactsDetails;
    }
}
