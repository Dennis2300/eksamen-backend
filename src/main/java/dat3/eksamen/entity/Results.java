package dat3.eksamen.entity;

import dat3.eksamen.entity.EnumTypes.ResultType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "results")
public class Results {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "result_type")
    @Enumerated(EnumType.STRING)
    private ResultType resultType;

    private Date date;

    private Integer result;

    @ManyToOne // Many results to one participant
    @JoinColumn(name = "participant_id")
    private Participants participant;

    @ManyToOne // Many results to one discipline
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;
}
