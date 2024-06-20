package dat3.eksamen.service;

import dat3.eksamen.dto.ResultsDto;
import dat3.eksamen.entity.Discipline;
import dat3.eksamen.entity.Participants;
import dat3.eksamen.entity.Results;
import dat3.eksamen.repository.DisciplineRepository;
import dat3.eksamen.repository.ParticipantsRepository;
import dat3.eksamen.repository.ResultsRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
public class ResultsService {

    private final ResultsRepository resultsRepository;
    private final ParticipantsRepository participantsRepository;
    private final DisciplineRepository disciplineRepository;

    public ResultsService(ResultsRepository resultsRepository, ParticipantsRepository participantsRepository, DisciplineRepository disciplineRepository) {
        this.resultsRepository = resultsRepository;
        this.participantsRepository = participantsRepository;
        this.disciplineRepository = disciplineRepository;
    }

    public List<Results> getResults() {
        try {
            return resultsRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not find results");
        }
    }

    public Results getResultById(int id) {
        try {
            return resultsRepository.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not find the given result");
        }
    }

    @Transactional
    public Results addResult(ResultsDto resultDTO) {
        Results result = new Results();
        result.setResultType(resultDTO.getResultType());
        result.setDate(resultDTO.getDate());
        result.setResult(resultDTO.getResult());

        Participants participant = participantsRepository.findByFirstNameAndLastName(
                resultDTO.getParticipantFirstName(),
                resultDTO.getParticipantLastName()
        );
        if (participant == null) {
            throw new RuntimeException("Participant not found");
        }
        result.setParticipant(participant);

        Discipline discipline = disciplineRepository.findByDisciplineName(resultDTO.getDisciplineName());
        if (discipline == null) {
            throw new RuntimeException("Discipline not found");
        }
        result.setDiscipline(discipline);

        return resultsRepository.save(result);
    }



    public Results updateResult(Integer id, Results updatedResult) {
        return resultsRepository.findById(id).map(result -> {
            result.setResultType(updatedResult.getResultType());
            result.setDate(updatedResult.getDate());
            result.setResult(updatedResult.getResult());
            return resultsRepository.save(result);
        }).orElseGet(() -> {
            updatedResult.setId(id);
            return resultsRepository.save(updatedResult);
        });
    }

    public void deleteResult(Integer id) {
        try {
            resultsRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not delete the given result");
        }
    }


}
