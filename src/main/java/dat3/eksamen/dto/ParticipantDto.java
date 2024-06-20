package dat3.eksamen.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.eksamen.entity.EnumTypes.Gender;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParticipantDto implements Serializable {

    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
    private String club;
    private List<String> disciplines;

}
