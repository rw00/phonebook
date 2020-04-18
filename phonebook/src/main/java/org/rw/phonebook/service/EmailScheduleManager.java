package org.rw.phonebook.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.rw.phonebook.entity.Email_Schedule;
import org.rw.phonebook.entity.User;


public class EmailScheduleManager {
    public EmailScheduleManager() {
    }


    public static void createSchedule(Email_Schedule schedule) {
        EntityManager entityManager = UnitManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(schedule);
        entityManager.getTransaction().commit();
    }

    public static Email_Schedule findSchedule(int scheduleId) {
        EntityManager entityManager = UnitManager.createEntityManager();
        return entityManager.find(Email_Schedule.class, scheduleId);
    }

    public static Email_Schedule findSchedule(User admin, User user) {
        EntityManager entityManager = UnitManager.createEntityManager();
        Query q = entityManager.createQuery("SELECT sch FROM Email_Schedule sch WHERE sch.admin = :admin AND sch.user = :user");
        q.setParameter("admin", admin);
        q.setParameter("user", user);

        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (Email_Schedule) q.getSingleResult();
        }
    }

    public static void deleteSchedule(User admin, User user) {
        EntityManager entityManager = UnitManager.createEntityManager();
        entityManager.getTransaction().begin();
        Email_Schedule schedule = entityManager.getReference(Email_Schedule.class, findSchedule(admin, user).getScheduleId());
        entityManager.remove(schedule);
        entityManager.getTransaction().commit();
    }
}
