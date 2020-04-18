package org.rw.phonebook.service;

import javax.persistence.EntityManager;
import org.rw.phonebook.entity.Contact;


public class ContactService {
    private ContactService() {
    }

    public static void createContact(Contact contact) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(contact);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static Contact findContact(long contactId) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        try {
            return entityManager.find(Contact.class, contactId);
        } finally {
            entityManager.close();
        }
    }

    public static void updateContact(long contactId, String firstName, String lastName, String phoneNumber, String email, String address) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        entityManager.getTransaction().begin();
        Contact contact = entityManager.find(Contact.class, contactId);
        contact.setEmail(email);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPhoneNumber(phoneNumber);
        contact.setAddress(address);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void deleteContact(long contactId) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        entityManager.getTransaction().begin();
        Contact contact = entityManager.find(Contact.class, contactId);
        entityManager.remove(contact);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
