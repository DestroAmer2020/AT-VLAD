import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TestDataGenerator {
    public static void generateTestUsers() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Створення тестових даних
            session.save(new User("testuser1", "test1@example.com", "password123"));
            session.save(new User("testuser2", "test2@example.com", "password456"));
            session.save(new User("testuser3", "test3@example.com", "password789"));

            transaction.commit();
            System.out.println("Test data generated successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<User> getAllUsers() {
        Session session = HibernateUtil.getSession();
        List<User> users = session.createQuery("from User", User.class).list();
        session.close();
        return users;
    }

    public static void main(String[] args) {
        generateTestUsers();
        List<User> users = getAllUsers();
        users.forEach(user -> System.out.println(user.getUsername()));
    }
}
