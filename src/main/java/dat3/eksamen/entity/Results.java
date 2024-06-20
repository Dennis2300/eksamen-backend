package dat3.eksamen.entity;

import dat3.eksamen.entity.EnumTypes.ResultType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity(name = "results")
public class Results {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private ResultType resultType;

    private Date date;

    private Integer result;
}
