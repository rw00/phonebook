package org.rw.phonebook.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.rw.phonebook.entity.User;


public class UserManager {
    public UserManager() {
    }


    public static void createUser(User user) {
        EntityManager entityManager = UnitManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    public static User findUser(int userId) {
        EntityManager entityManager = UnitManager.createEntityManager();
        return entityManager.find(User.class, userId);
    }

    public static void updateUser(int userId, String firstName, String lastName, String phoneNumber, String email, String password, String address) {
        EntityManager entityManager = UnitManager.createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        entityManager.getTransaction().commit();
    }

    public static void deleteUser(int userId) {
        EntityManager entityManager = UnitManager.createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

    public static void deleteUserInfo(User u) {
        EntityManager em = UnitManager.createEntityManager();
        EntityTransaction transact = em.getTransaction();
        transact.begin();
        Query q = em.createQuery("DELETE FROM Contact c WHERE c.user = :user");
        q.setParameter("user", u);
        q.executeUpdate();
        transact.commit();
        em.close();

        em = UnitManager.createEntityManager();
        transact = em.getTransaction();
        transact.begin();
        q = em.createQuery("DELETE FROM Email_Schedule sch WHERE sch.user = :user");
        q.setParameter("user", u);
        q.executeUpdate();
        transact.commit();
    }

    public static User findUserByPhoneNumberAndPassword(String phoneNumber, String password) {
        Query q = UnitManager.createEntityManager().createQuery("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber AND u.password = :password");
        q.setParameter("phoneNumber", phoneNumber);
        q.setParameter("password", password);
        try {
            return (User) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean userExists(String phoneNumber) {
        Query q = UnitManager.createEntityManager().createQuery("SELECT u FROM User u WHERE u.phoneNumber = :phoneNumber");
        q.setParameter("phoneNumber", phoneNumber);
        return !q.getResultList().isEmpty();
    }
}
