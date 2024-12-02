import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;

    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees = new HashSet<>();

}