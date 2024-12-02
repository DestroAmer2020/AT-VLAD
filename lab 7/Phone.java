import jakarta.persistence.*;

@Entity
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

}