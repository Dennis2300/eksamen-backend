package dat3.eksamen.controller;

import dat3.eksamen.entity.Results;
import dat3.eksamen.service.ResultsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Results getResultById( @PathVariable Integer id) {
        return resultsService.getResultById(id);
    }

    @PostMapping("/add")
    public Results addResult(@RequestBody Results result) {
        return resultsService.addResult(result);
    }

    @PutMapping("/update/{id}")
    public Results updateResult(@PathVariable Integer id, @RequestBody Results updatedResult) {
        return resultsService.updateResult(id, updatedResult);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteResult(@PathVariable Integer id) {
        resultsService.deleteResult(id);
        return ResponseEntity.ok("Result deleted successfully");
    }

}
