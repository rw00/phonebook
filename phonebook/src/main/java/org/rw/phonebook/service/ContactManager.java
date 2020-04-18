package org.rw.phonebook.service;

import javax.persistence.EntityManager;

import org.rw.phonebook.entity.Contact;


public class ContactManager {
    public ContactManager() {
    }


    public static void createContact(Contact contact) {
        EntityManager entityManager = UnitManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(contact);
        entityManager.getTransaction().commit();
    }

    public static Contact findContact(int contactId) {
        return UnitManager.createEntityManager().find(Contact.class, contactId);
    }

    public static void updateContact(int contactId, String firstName, String lastName, String phoneNumber, String email, String address) {
        EntityManager entityManager = UnitManager.createEntityManager();
        entityManager.getTransaction().begin();
        Contact contact = entityManager.find(Contact.class, contactId);
        contact.setEmail(email);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPhoneNumber(phoneNumber);
        contact.setAddress(address);
        entityManager.getTransaction().commit();
    }

    public static void deleteContact(int contactId) {
        EntityManager entityManager = UnitManager.createEntityManager();
        entityManager.getTransaction().begin();
        Contact contact = entityManager.find(Contact.class, contactId);
        entityManager.remove(contact);
        entityManager.getTransaction().commit();
    }
}
