package dat3.eksamen.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.eksamen.entity.EnumTypes.ResultType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultsDto implements Serializable {

    private ResultType resultType;
    private Date date;
    private Integer result;
    private String participantFirstName;
    private String participantLastName;
    private String disciplineName;
}