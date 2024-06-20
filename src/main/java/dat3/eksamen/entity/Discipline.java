package dat3.eksamen.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "disciplines")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "discipline_name")
    private String disciplineName;

    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL)
    private Set<Results> results = new HashSet<>();
}
