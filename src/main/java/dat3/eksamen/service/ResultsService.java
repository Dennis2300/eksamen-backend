package dat3.eksamen.service;

import dat3.eksamen.entity.Results;
import dat3.eksamen.repository.ResultsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
public class ResultsService {

    private final ResultsRepository resultsRepository;

    public ResultsService(ResultsRepository resultsRepository) {
        this.resultsRepository = resultsRepository;
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

    public Results addResult(Results result) {
        try {
            return resultsRepository.save(result);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not add the given result");
        }
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
