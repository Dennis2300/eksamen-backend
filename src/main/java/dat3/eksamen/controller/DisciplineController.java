package dat3.eksamen.controller;

import dat3.eksamen.entity.Discipline;
import dat3.eksamen.service.DisciplineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplineController {

    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping
    public List<Discipline> getDisciplines() {
        return disciplineService.getDisciplines();
    }
}
