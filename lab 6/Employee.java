import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean isEmployed;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Address address;

}