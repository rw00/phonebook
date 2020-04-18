package org.rw.phonebook.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.json.JSONArray;
import org.json.JSONObject;
import org.rw.phonebook.entity.Contact;
import org.rw.phonebook.service.EntityManagerProvider;
import org.rw.phonebook.service.UserService;


@SuppressWarnings("unchecked")
public class ContactRepository {
    private ContactRepository() {
    }

    public static List<Contact> getContactsList(long userId) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        try {
            Query q = entityManager.createQuery("SELECT c FROM Contact c WHERE c.user = :user");
            q.setParameter("user", UserService.findUser(userId));
            return q.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public static JSONArray getContacts(long userId) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        try {
            Query q = entityManager.createQuery("SELECT c FROM Contact c WHERE c.user = :user");
            q.setParameter("user", UserService.findUser(userId));
            List<Contact> result = q.getResultList();
            if (result.isEmpty()) {
                return null;
            }
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
        } finally {
            entityManager.close();
        }
    }
}
