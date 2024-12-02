import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String state;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}