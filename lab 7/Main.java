import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = new Employee();
        employee.setName("Jessica");
        employee.setIsEmployed(true);

        Address address = new Address();
        address.setStreet("234 Elm St");
        address.setCity("Boston");
        address.setState("MA");
        address.setEmployee(employee);
        employee.setAddress(address);

        Phone phone1 = new Phone();
        phone1.setPhoneNumber("123-456-7890");
        phone1.setEmployee(employee);

        Phone phone2 = new Phone();
        phone2.setPhoneNumber("987-654-3210");
        phone2.setEmployee(employee);

        employee.getPhones().add(phone1);
        employee.getPhones().add(phone2);

        Project project1 = new Project();
        project1.setProjectName("Project A");

        Project project2 = new Project();
        project2.setProjectName("Project B");

        employee.getProjects().add(project1);
        employee.getProjects().add(project2);
        project1.getEmployees().add(employee);
        project2.getEmployees().add(employee);

        session.save(employee);

        Employee retrievedEmployee = session.get(Employee.class, employee.getId());
        System.out.println("Retrieved Employee: " + retrievedEmployee.getName());
        System.out.println("Address: " + retrievedEmployee.getAddress().getStreet());
        retrievedEmployee.getPhones().forEach(phone -> 
            System.out.println("Phone: " + phone.getPhoneNumber())
        );
        retrievedEmployee.getProjects().forEach(project -> 
            System.out.println("Project: " + project.getProjectName())
        );

        retrievedEmployee.setName("Jessica Updated");
        session.update(retrievedEmployee);

        Phone phoneToDelete = retrievedEmployee.getPhones().get(0);
        session.delete(phoneToDelete);

        session.delete(retrievedEmployee);

        transaction.commit();
        session.close();
    }
}