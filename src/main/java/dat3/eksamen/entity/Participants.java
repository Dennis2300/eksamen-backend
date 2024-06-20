package dat3.eksamen.entity;

import dat3.eksamen.entity.EnumTypes.Gender;
import jakarta.persistence.*;


@Entity(name = "participants")
public class Participants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Integer age;

    private Gender gender;

    private String club;
}
