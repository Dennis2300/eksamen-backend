package dat3.eksamen.entity;

import jakarta.persistence.*;
import lombok.Data;


// Laver automatisk getters og setters
@Data
// Definerer at det er en entity
@Entity(name = "disciplines")
public class Discipline {

    // Definerer at det er en primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincrement
    private Integer id;

    // Definerer at det er en kolonne i databasen
    @Column(name = "discipline_name")
    private String disciplineName;

}
