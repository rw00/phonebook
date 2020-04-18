package org.rw.phonebook.functions;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.rw.phonebook.entity.Contact;
import org.rw.phonebook.entity.User;
import org.rw.phonebook.service.ContactManager;
import org.rw.phonebook.service.UserManager;


@WebListener
public class InitListener implements ServletContextListener {
    public InitListener() {
    }


    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        try {
            User admin0 = new User("", "", "admin", "raafat.waheb@outlook.com", "admin", "");
            admin0.setAdmin(true);
            UserManager.createUser(admin0);

            User admin = new User("", "", "rw", "raafat.waheb@outlook.com", "rw", "");
            admin.setAdmin(true);
            UserManager.createUser(admin);

            UserManager.createUser(new User("Raafat", "Waheb", "12341234", "raafatwahb@gmail.com", "71121744", "Leb, Beirut"));
            UserManager.createUser(new User("Nabil", "Waheb", "12345678", "nabil_w@live.com", "76842956", "Hasbaya"));

            ContactManager.createContact(new Contact("Raafat", "Waheb", "71121744", "", "Beirut, Zalka", UserManager.findUser(1)));
            ContactManager.createContact(new Contact("Eyad", "Badawi", "76467036", "", "Hasbaya", UserManager.findUser(3)));

            ContactManager.createContact(new Contact("William", "Sharrouf", "0014078007424", "william@gmail.com", "USA, Florida", UserManager.findUser(3)));
            ContactManager.createContact(new Contact("Sweet", "Home", "07551080", "", "Hasbaya", UserManager.findUser(3)));
        } catch (Exception e) {

        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {

    }
}
