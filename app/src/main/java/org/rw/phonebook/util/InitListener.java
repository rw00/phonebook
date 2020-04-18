package org.rw.phonebook.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.rw.phonebook.entity.User;
import org.rw.phonebook.service.UserService;


@WebListener
public class InitListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        try {
            User admin0 = new User("", "", "admin", "raafatwahb@gmail.com", "admin", "");
            admin0.setAdmin(true);
            UserService.createUser(admin0);
        } catch (Exception ignored) {
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }
}
