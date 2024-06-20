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

    private final ResultsService resultsService;

    public ResultsController(ResultsService resultsService) {
        this.resultsService = resultsService;
    }

    @GetMapping
    public List<Results> getResults() {
        return resultsService.getResults();
    }

}
