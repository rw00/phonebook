package org.rw.phonebook.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.json.JSONArray;
import org.json.JSONObject;
import org.rw.phonebook.entity.User;
import org.rw.phonebook.service.EntityManagerProvider;


@java.lang.SuppressWarnings("unchecked")
public class UserRepository {
    private UserRepository() {
    }

    public static JSONArray getUsers() {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        try {
            Query q = entityManager.createQuery("SELECT u FROM User u WHERE u.admin = :admin");
            q.setParameter("admin", false);
            List<User> result = q.getResultList();
            if (result.isEmpty()) {
                return null;
            }
            JSONArray list = new JSONArray();
            for (User u : result) {
                long userId = u.getUserId();
                JSONObject details = new JSONObject();
                details.put("userId", userId);
                details.put("phoneNumber", u.getPhoneNumber());
                details.put("firstName", u.getFirstName());
                details.put("lastName", u.getLastName());
                details.put("email", u.getEmail());
                details.put("address", u.getPassword());

                list.put(details);
            }
            return list;
        } finally {
            entityManager.close();
        }
    }

    public static JSONArray getAdmins(long adminId) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        try {
            Query q = entityManager.createQuery("SELECT u FROM User u WHERE u.userId <> :adminId AND u.admin = :admin");
            q.setParameter("admin", true);
            q.setParameter("adminId", adminId);
            List<User> result = q.getResultList();
            if (result.isEmpty()) {
                return null;
            }
            JSONArray list = new JSONArray();
            for (User u : result) {
                long userId = u.getUserId();
                JSONObject details = new JSONObject();
                details.put("userId", userId);
                details.put("phoneNumber", u.getPhoneNumber());
                details.put("firstName", u.getFirstName());
                details.put("lastName", u.getLastName());
                details.put("email", u.getEmail());
                details.put("address", u.getPassword());

                list.put(details);
            }
            return list;
        } finally {
            entityManager.close();
        }
    }

    public static JSONObject getUserInfo(long userId) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        try {
            Query q = entityManager.createQuery("SELECT u FROM User u WHERE u.userId = :userId");
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
        } finally {
            entityManager.close();
        }
    }
}
