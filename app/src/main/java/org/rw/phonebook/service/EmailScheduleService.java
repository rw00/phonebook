package org.rw.phonebook.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.rw.phonebook.entity.EmailSchedule;
import org.rw.phonebook.entity.User;


public class EmailScheduleService {
    private EmailScheduleService() {
    }

    public static void createSchedule(EmailSchedule schedule) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(schedule);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static EmailSchedule findSchedule(long scheduleId) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        try {
            return entityManager.find(EmailSchedule.class, scheduleId);
        } finally {
            entityManager.close();
        }
    }

    public static void deleteSchedule(User admin, User user) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        entityManager.getTransaction().begin();
        EmailSchedule schedule = entityManager.getReference(EmailSchedule.class, findSchedule(admin, user).getScheduleId());
        entityManager.remove(schedule);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static EmailSchedule findSchedule(User admin, User user) {
        EntityManager entityManager = EntityManagerProvider.createEntityManager();
        try {
            Query q = entityManager.createQuery("SELECT sch FROM Email_Schedule sch WHERE sch.admin = :admin AND sch.user = :user");
            q.setParameter("admin", admin);
            q.setParameter("user", user);
            if (q.getResultList().isEmpty()) {
                return null;
            } else {
                return (EmailSchedule) q.getSingleResult();
            }
        } finally {
            entityManager.close();
        }
    }
}
