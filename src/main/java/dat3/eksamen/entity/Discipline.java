package dat3.eksamen.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "disciplines")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "discipline_name")
    private String disciplineName;
}
