package dat3.eksamen.controller;

import dat3.eksamen.entity.Results;
import dat3.eksamen.repository.ResultsRepository;
import dat3.eksamen.service.ResultsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultsController {

    private final ResultsRepository resultsRepository;
    private final ResultsService resultsService;

    public ResultsController(ResultsRepository resultsRepository, ResultsService resultsService) {
        this.resultsRepository = resultsRepository;
        this.resultsService = resultsService;
    }

    @GetMapping
    public List<Results> getResults() {
        return resultsService.getResults();
    }

}
