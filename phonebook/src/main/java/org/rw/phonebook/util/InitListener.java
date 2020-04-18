package org.rw.phonebook.util;

import org.rw.phonebook.entity.User;
import org.rw.phonebook.service.UserManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        try {
            User admin0 = new User("", "", "admin", "rw@outlook.com", "admin", "");
            admin0.setAdmin(true);
            UserManager.createUser(admin0);
        } catch (Exception ignored) {
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }
}
