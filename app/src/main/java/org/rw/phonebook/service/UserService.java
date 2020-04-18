package org.rw.phonebook.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.rw.phonebook.entity.User;


public class UserService {
    private UserService() {
    }

    public static void createUser(User user) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static User findUser(long userId) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        try {
            return entityManager.find(User.class, userId);
        } finally {
            entityManager.close();
        }
    }

    public static void updateUser(long userId, String firstName, String lastName, String phoneNumber, String email, String password, String address) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void deleteUser(long userId) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void deleteUserInfo(User u) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query q = entityManager.createQuery("DELETE FROM Contact c WHERE c.user = :user");
        q.setParameter("user", u);
        q.executeUpdate();
        transaction.commit();
        entityManager.close();

        entityManager = EntityManagerProvider.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
        q = entityManager.createQuery("DELETE FROM Email_Schedule sch WHERE sch.user = :user");
        q.setParameter("user", u);
        q.executeUpdate();
        transaction.commit();
        entityManager.close();
    }

    public static User findUserByPhoneNumberAndPassword(String phoneNumber, String password) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        try {
            Query q = entityManager.createQuery("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber AND u.password = :password");
            q.setParameter("phoneNumber", phoneNumber);
            q.setParameter("password", password);
            try {
                return (User) q.getSingleResult();
            } catch (Exception e) {
                return null;
            }
        } finally {
            entityManager.close();
        }
    }

    public static boolean userExists(String phoneNumber) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        try {
            Query q = entityManager.createQuery("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber");
            q.setParameter("phoneNumber", phoneNumber);
            return !q.getResultList().isEmpty();
        } finally {
            entityManager.close();
        }
    }
}
