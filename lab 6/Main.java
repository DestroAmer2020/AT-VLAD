import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = new Employee();
        employee.setName("Jessica");
        employee.setIsEmployed(false);

        Address address = new Address();
        address.setStreet("234 Elm St");
        address.setCity("Boston");
        address.setState("MA");
        address.setEmployee(employee);

        employee.setAddress(address);

        session.save(employee);

        Employee retrievedEmployee = session.get(Employee.class, employee.getId());
        System.out.println("Retrieved Employee: " + retrievedEmployee.getName());

        retrievedEmployee.setIsEmployed(true);
        session.update(retrievedEmployee);

        session.delete(retrievedEmployee);

        transaction.commit();
        session.close();
    }
}