package model;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    public User(String john, String mail) {
    }

    public void getClass(String johnDoe) {

    }

    // Getters and setters
}