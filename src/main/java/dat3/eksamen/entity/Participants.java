package dat3.eksamen.entity;

import dat3.eksamen.entity.EnumTypes.Gender;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "participants")
public class Participants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "club")
    private String club;
}
