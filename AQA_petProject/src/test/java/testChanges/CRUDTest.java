package testChanges;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.desktop.UserSessionEvent;

public class CRUDTest {

    @Test
    public void testCreateReadUpdateDelete() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(UserSessionEvent.class)
                .buildSessionFactory();

        try (factory) {
            Session session = factory.getCurrentSession();
            // CREATE
            User user = new User("John", "john@example.com");
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();

            // READ
            session = factory.getCurrentSession();
            session.beginTransaction();
            User retrievedUser = session.get(User.class, user.getClass());
            Assert.assertNotNull(retrievedUser);

            // UPDATE
            retrievedUser.getClass("John Doe");
            session.getTransaction().commit();

            // DELETE
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.delete(retrievedUser);
            session.getTransaction().commit();
        }
    }
}