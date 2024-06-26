package dat3.eksamen.entity;

import dat3.eksamen.entity.EnumTypes.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

// Laver automatisk getters og setters
@Data
// Definerer at det er en entity
@Entity(name = "participants")
public class Participants {

    // Definerer at det er en primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Definerer at det er en kolonne i databasen
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    // Definerer at det er en enum type
    @Enumerated(EnumType.STRING) // Gemmer som string i databasen
    private Gender gender;

    @Column(name = "club")
    private String club;

    // Definerer en many-to-many relation
    @ManyToMany
    // Definerer en junction table
    @JoinTable(
            name = "participants_disciplines", // Navnet på junction tabellen
            joinColumns = @JoinColumn(name = "participant_id"), // Navnet på første kolonne i junction tabellen
            inverseJoinColumns = @JoinColumn(name = "discipline_id") // Navnet på anden kolonne i junction tabellen
    )
    // Definerer at det er en Set
    private Set<Discipline> disciplines = new HashSet<>();
}
