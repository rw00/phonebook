package org.rw.phonebook.repository;

import java.util.List;

import javax.persistence.Query;

import org.json.JSONArray;
import org.json.JSONObject;

import org.rw.phonebook.entity.Contact;
import org.rw.phonebook.service.UnitManager;
import org.rw.phonebook.service.UserManager;


@SuppressWarnings("unchecked")
public class ContactRepository {
    public ContactRepository() {
    }


    public static List<Contact> getContactsList(int userId) {
        Query q = UnitManager.createEntityManager().createQuery("SELECT c FROM Contact c WHERE c.user = :user");
        q.setParameter("user", UserManager.findUser(userId));
        return (List<Contact>) q.getResultList();
    }

    public static JSONArray getContacts(int userId) {
        Query q = UnitManager.createEntityManager().createQuery("SELECT c FROM Contact c WHERE c.user = :user");
        q.setParameter("user", org.rw.phonebook.service.UserManager.findUser(userId));
        List<Contact> result = q.getResultList();
        if (result.isEmpty()) {
            return null;
        }
        JSONArray list = new JSONArray();
        for (Contact c : result) {
            int contactId = c.getContactId();
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
}
